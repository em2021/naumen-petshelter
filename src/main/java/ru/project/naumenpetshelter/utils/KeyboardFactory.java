package ru.project.naumenpetshelter.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class KeyboardFactory {
    public static ReplyKeyboard getStartMenuKeyboard() {
        List<InlineKeyboardButton> topRow = new ArrayList();
        InlineKeyboardButton viewBtn = new InlineKeyboardButton("View animals");
        viewBtn.setCallbackData("view_animals");
        topRow.add(viewBtn);
        List<InlineKeyboardButton> bottomRow = new ArrayList();
        InlineKeyboardButton stopBtn = new InlineKeyboardButton("Stop");
        stopBtn.setCallbackData("stop");
        bottomRow.add(stopBtn);
        return new InlineKeyboardMarkup(List.of(topRow, bottomRow));
    }

    public static ReplyKeyboard getViewAnimalsMenuKeyboard() {
        List<InlineKeyboardButton> topRow = new ArrayList();
        InlineKeyboardButton allAnimalsBtn = new InlineKeyboardButton("All animals");
        allAnimalsBtn.setCallbackData("all_animals");
        topRow.add(allAnimalsBtn);
        List<InlineKeyboardButton> middleRow = new ArrayList();
        InlineKeyboardButton animalsByTypeBtn = new InlineKeyboardButton("Animals by type");
        animalsByTypeBtn.setCallbackData("animals_by_type");
        middleRow.add(animalsByTypeBtn);
        List<InlineKeyboardButton> bottomRow = new ArrayList();
        InlineKeyboardButton animalById = new InlineKeyboardButton("Animal by id");
        animalById.setCallbackData("animal_by_id");
        bottomRow.add(animalById);
        return new InlineKeyboardMarkup(List.of(topRow, middleRow, bottomRow));
    }

    public static ReplyKeyboard getViewNavigationMenuKeyboard() {
        List<InlineKeyboardButton> topRow = new ArrayList();
        InlineKeyboardButton returnToViewSelectionBtn = new InlineKeyboardButton("Return to view selection");
        returnToViewSelectionBtn.setCallbackData("return_to_view_selection");
        topRow.add(returnToViewSelectionBtn);
        List<InlineKeyboardButton> middleRow = new ArrayList();
        InlineKeyboardButton animalById = new InlineKeyboardButton("Animal by id");
        animalById.setCallbackData("animal_by_id");
        middleRow.add(animalById);
        List<InlineKeyboardButton> bottomRow = new ArrayList();
        InlineKeyboardButton stopBtn = new InlineKeyboardButton("Stop");
        stopBtn.setCallbackData("stop");
        bottomRow.add(stopBtn);
        return new InlineKeyboardMarkup(List.of(topRow, middleRow, bottomRow));
    }

    public static ReplyKeyboard getAnimalsByTypeKeyboard() {
        List<InlineKeyboardButton> topRow = new ArrayList();
        InlineKeyboardButton returnToViewSelectionBtn = new InlineKeyboardButton("Return to animal types");
        returnToViewSelectionBtn.setCallbackData("animals_by_type");
        topRow.add(returnToViewSelectionBtn);
        List<InlineKeyboardButton> middleRow = new ArrayList();
        InlineKeyboardButton cats = new InlineKeyboardButton("Cats");
        cats.setCallbackData("cats");
        middleRow.add(cats);
        List<InlineKeyboardButton> bottomRow = new ArrayList();
        InlineKeyboardButton stopBtn = new InlineKeyboardButton("Dogs");
        stopBtn.setCallbackData("dogs");
        bottomRow.add(stopBtn);
        return new InlineKeyboardMarkup(List.of(topRow, middleRow, bottomRow));
    }
}