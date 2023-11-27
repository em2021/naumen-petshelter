package ru.project.naumenpetshelter.component;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.objects.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.project.naumenpetshelter.constants.Constants;
import ru.project.naumenpetshelter.constants.UserState;
import ru.project.naumenpetshelter.model.Animal;
import ru.project.naumenpetshelter.service.AnimalService;
import ru.project.naumenpetshelter.utils.AnimalMessageBuilder;
import ru.project.naumenpetshelter.utils.KeyboardFactory;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static org.telegram.abilitybots.api.util.AbilityUtils.getChatId;
import static ru.project.naumenpetshelter.constants.Constants.*;
import static ru.project.naumenpetshelter.constants.UserState.*;

@Component
public class PetshelterBot extends AbilityBot {

    private final AnimalService service;
    private final AnimalMessageBuilder messageBuilder;
    private final Map<Long, UserState> chatStates;


    public PetshelterBot(Environment env, AnimalService service, AnimalMessageBuilder messageBuilder) {
        super(env.getProperty("petshelter.bot.token"),
                env.getProperty("petshelter.bot.name"));
        this.service = service;
        this.messageBuilder = messageBuilder;
        chatStates = db.getMap(CHAT_STATES);
    }

    public Ability startBot() {
        return Ability
                .builder()
                .name("start")
                .info(Constants.START_DESCRIPTION)
                .locality(Locality.USER)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> replyToStart(ctx.chatId()))
                .build();
    }

    public Reply replyToCallback() {
        BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) ->
                replyToCallbackQuery(upd.getCallbackQuery().getMessage().getChatId(),
                        upd.getCallbackQuery());
        return Reply.of(action, Flag.CALLBACK_QUERY, upd -> userIsActive(getChatId(upd)));
    }

    public Reply replyToAnimalId() {
        BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) ->
                replyToId(upd.getMessage().getChatId(),
                        Integer.parseInt(upd.getMessage().getText()));
        return Reply.of(action, Flag.MESSAGE, upd -> !upd.getMessage().getText().equals("/start"), upd -> userIsActive(getChatId(upd)));
    }

    public void replyToStart(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(START_TEXT);
        message.setReplyMarkup(KeyboardFactory.getStartMenuKeyboard());
        try {
            sender.execute(message);
            chatStates.put(chatId, AWAITING_CHOICE);
        } catch (TelegramApiException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void replyToCallbackQuery(long chatId, CallbackQuery callbackQuery) {
        switch (callbackQuery.getData()) {
            case VIEW_ANIMALS_BUTTON -> replyToViewAnimals(chatId);
            case STOP_BUTTON -> replyToStop(chatId);
            case ALL_ANIMALS_BUTTON -> replyToAllAnimals(chatId);
            case ANIMALS_BY_TYPE_BUTTON -> replyToAnimalsByType(chatId);
            case ANIMAL_BY_ID_BUTTON -> replyToAnimalById(chatId);
            case RETURN_TO_VIEW_SELECTION_BUTTON -> replyToViewAnimals(chatId);
            case CATS_BUTTON -> replyToAnimalTypeChoice(chatId, CATS_BUTTON);
            case DOGS_BUTTON -> replyToAnimalTypeChoice(chatId, DOGS_BUTTON);
            default -> unexpectedMessage(chatId);
        }
    }

    public void replyToId(long chatId, Integer id) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        Animal animal = service.getAnimalById(id);
        String messageText = ANIMAL_NOT_FOUND_MESSAGE;
        if (animal != null) {
            messageText = messageBuilder.buildAnimalMessage(animal);
        }
        message.setChatId(chatId);
        message.setText(messageText);
        message.setReplyMarkup(KeyboardFactory.getViewNavigationMenuKeyboard());
        try {
            sender.execute(message);
            chatStates.put(chatId, ANIMAL_VIEW_SELECTION);
        } catch (TelegramApiException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void replyToViewAnimals(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(VIEW_ANIMALS_TEXT);
        message.setReplyMarkup(KeyboardFactory.getViewAnimalsMenuKeyboard());
        try {
            sender.execute(message);
            chatStates.put(chatId, ANIMAL_VIEW_SELECTION);
        } catch (TelegramApiException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void replyToStop(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(STOP_TEXT);
        chatStates.remove(chatId);
        message.setReplyMarkup(new ReplyKeyboardRemove(true));
        try {
            sender.execute(message);
        } catch (TelegramApiException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void replyToAllAnimals(long chatId) {
        List<Animal> animals = service.listAllAnimals();
        String messageText = messageBuilder.buildAnimalsListMessage(animals);
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        chatStates.put(chatId, VIEWING_ALL_ANIMALS);
        message.setReplyMarkup(KeyboardFactory.getViewNavigationMenuKeyboard());
        try {
            sender.execute(message);
        } catch (TelegramApiException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void replyToAnimalsByType(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(ANIMALS_BY_TYPE_TEXT);
        chatStates.put(chatId, AWAITING_ANIMAL_TYPE_CHOICE);
        message.setReplyMarkup(KeyboardFactory.getAnimalsByTypeKeyboard());
        try {
            sender.execute(message);
        } catch (TelegramApiException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void replyToAnimalTypeChoice(long chatId, String type) {
        List<Animal> animals = service.listAnimalsByType(type);
        String messageText = messageBuilder.buildAnimalsListMessage(animals);
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        chatStates.put(chatId, VIEWING_ALL_ANIMALS);
        message.setReplyMarkup(KeyboardFactory.getViewNavigationMenuKeyboard());
        try {
            sender.execute(message);
        } catch (TelegramApiException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void replyToAnimalById(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(ANIMAL_BY_ID_TEXT);
        chatStates.put(chatId, AWAITING_ANIMAL_ID);
        try {
            sender.execute(message);
        } catch (TelegramApiException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void unexpectedMessage(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Sorry, unexpected message(((");
        try {
            sender.execute(sendMessage);
        } catch (TelegramApiException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean userIsActive(Long chatId) {
        return chatStates.containsKey(chatId);
    }

    @Override
    public long creatorId() {
        return 1L;
    }
}