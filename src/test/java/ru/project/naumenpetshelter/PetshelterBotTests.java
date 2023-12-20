package ru.project.naumenpetshelter;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.db.MapDBContext;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.project.naumenpetshelter.component.PetshelterBot;
import ru.project.naumenpetshelter.service.AnimalService;
import ru.project.naumenpetshelter.utils.AnimalMessageBuilder;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;

public class PetshelterBotTests {

    public static final long USER_ID = 123;
    public static final long CHAT_ID = 123L;
    private static int testCounter;
    @Mock
    private Environment envMock;
    @Mock
    private AnimalService serviceMock;
    @Mock
    private AnimalMessageBuilder messageBuilderMock;
    @Mock
    private MessageSender sender;
    @Mock
    private DBContext db;
    @InjectMocks
    private PetshelterBot bot;
    private AutoCloseable closeable;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("PetshelterBotTests tests started");
    }

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        Mockito.when(envMock.getProperty("petshelter.bot.token")).thenReturn(Mockito.anyString());
        Mockito.when(envMock.getProperty("petshelter.bot.name")).thenReturn(Mockito.anyString());
        //Test counter added to db filename to avoid file locked db exception in parametrized tests
        db = MapDBContext.offlineInstance("test" + testCounter);
        testCounter++;
        bot = new PetshelterBot(envMock, serviceMock, messageBuilderMock, db);
        bot.setMessageSender(sender);
        bot.onRegister();
        System.out.println("AnimalServiceTests test started");
    }

    @AfterEach
    public void tearDown() throws Exception {
        db.clear();
        closeable.close();
        System.out.println("AnimalServiceTests test completed");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("AnimalServiceTests tests completed");
    }

    @Test
    public void testStartBot_whenCalled_callOnceSenderExecute() throws TelegramApiException {
        //given:
        Update upd = new Update();
        User user = new User(USER_ID, "testFName", false);
        MessageContext context = MessageContext.newContext(upd, user, CHAT_ID, bot);
        //when:
        bot.startBot().action().accept(context);
        //then:
        Mockito.verify(sender, times(1)).execute(any(SendMessage.class));
    }

    @ParameterizedTest
    @MethodSource("sourceForReplyToCallbackQuery")
    public void testReplyToCallbackQuery_whenCallbackQueryPassed_CorrespondingMethodsCalled(CallbackQuery cq)
            throws TelegramApiException {
        //given:
        Mockito.when(messageBuilderMock.buildAnimalsListMessage(any())).thenReturn("test");
        //when:
        bot.replyToCallbackQuery(CHAT_ID, cq);
        //then:
        Mockito.verify(sender, times(1)).execute(any(SendMessage.class));
    }

    @Test
    public void testReplyToStop_whenCalled_callOnceSenderExecute() throws TelegramApiException {
        //given:
        //when:
        bot.replyToStop(CHAT_ID);
        //then:
        Mockito.verify(sender, times(1)).execute(any(SendMessage.class));
    }

    @Test
    public void testReplyToViewAnimals_whenCalled_callOnceSenderExecute() throws TelegramApiException {
        //given:
        //when:
        bot.replyToViewAnimals(CHAT_ID);
        //then:
        Mockito.verify(sender, times(1)).execute(any(SendMessage.class));
    }

    @Test
    public void testReplyToAllAnimals_whenCalled_callOnceSenderExecute() throws TelegramApiException {
        //given:
        Mockito.when(messageBuilderMock.buildAnimalsListMessage(anyList())).thenReturn("testMessage");
        //when:
        bot.replyToAllAnimals(CHAT_ID);
        //then:
        Mockito.verify(sender, times(1)).execute(any(SendMessage.class));
    }

    @Test
    public void testReplyToAnimalsByType_whenCalled_callOnceSenderExecute() throws TelegramApiException {
        //given:
        Mockito.when(messageBuilderMock.buildAnimalsListMessage(anyList())).thenReturn("testMessage");
        //when:
        bot.replyToAnimalById(CHAT_ID);
        //then:
        Mockito.verify(sender, times(1)).execute(any(SendMessage.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {"cats", "dogs"})
    public void testReplyToAnimalTypeChoice_whenCalled_callOnceSenderExecute(String type) throws TelegramApiException {
        //given:
        Mockito.when(messageBuilderMock.buildAnimalsListMessage(anyList())).thenReturn("testMessage");
        //when:
        bot.replyToAnimalTypeChoice(CHAT_ID, type);
        //then:
        Mockito.verify(sender, times(1)).execute(any(SendMessage.class));
    }

    @Test
    public void testReplyToAnimalById_whenCalled_callOnceSenderExecute() throws TelegramApiException {
        //given:
        CallbackQuery cq = new CallbackQuery();
        cq.setData("animal_by_id");
        Mockito.when(messageBuilderMock.buildAnimalsListMessage(anyList())).thenReturn("testMessage");
        //when:
        bot.replyToAnimalById(CHAT_ID);
        //then:
        Mockito.verify(sender, times(1)).execute(any(SendMessage.class));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testReplyToId_whenCalled_callOnceSenderExecute(Integer id)
            throws TelegramApiException {
        //given:
        //when:
        bot.replyToId(CHAT_ID, id);
        //then:
        Mockito.verify(sender, times(1)).execute(any(SendMessage.class));
    }

    @Test
    public void testUnexpectedMessage_whenCalled_callOnceSenderExecute() throws TelegramApiException {
        //given:
        //when:
        bot.unexpectedMessage(CHAT_ID);
        //then:
        Mockito.verify(sender, times(1)).execute(any(SendMessage.class));
    }

    @ParameterizedTest
    @MethodSource("sourceForUserIsActive")
    public void testUserIsActive_whenUserStartsBot_returnsTrue(long userIdActive, long userIdChecked, boolean expected) {
        //given:
        Update upd = new Update();
        User user = new User(userIdActive, "testFName", false);
        MessageContext context = MessageContext.newContext(upd, user, userIdActive, bot);
        //when:
        bot.startBot().action().accept(context);
        boolean actual = bot.userIsActive(userIdChecked);
        //then:
        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> sourceForUserIsActive() {
        //given:
        return Stream.of(Arguments.of(CHAT_ID, CHAT_ID, true),
                Arguments.of(CHAT_ID, 0, false));
    }

    public static Stream<Arguments> sourceForReplyToCallbackQuery() {
        //given:
        CallbackQuery cq_stop = new CallbackQuery();
        cq_stop.setData("stop");
        CallbackQuery cq_view = new CallbackQuery();
        cq_view.setData("view_animals");
        CallbackQuery cq_all = new CallbackQuery();
        cq_all.setData("all_animals");
        CallbackQuery cq_type = new CallbackQuery();
        cq_type.setData("animals_by_type");
        CallbackQuery cq_cats = new CallbackQuery();
        cq_cats.setData("cats");
        CallbackQuery cq_dogs = new CallbackQuery();
        cq_dogs.setData("dogs");
        CallbackQuery cq_id = new CallbackQuery();
        cq_id.setData("animal_by_id");
        CallbackQuery cq_unexpected = new CallbackQuery();
        cq_unexpected.setData("&#dJ");
        return Stream.of(Arguments.of(cq_stop),
                Arguments.of(cq_view),
                Arguments.of(cq_all),
                Arguments.of(cq_type),
                Arguments.of(cq_cats),
                Arguments.of(cq_dogs),
                Arguments.of(cq_id),
                Arguments.of(cq_unexpected));
    }
}