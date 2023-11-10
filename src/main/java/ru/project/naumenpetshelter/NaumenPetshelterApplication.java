package ru.project.naumenpetshelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.project.naumenpetshelter.component.PetshelterBot;

@SpringBootApplication
public class NaumenPetshelterApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(NaumenPetshelterApplication.class, args);
		try {
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(ctx.getBean("petshelterBot", PetshelterBot.class));
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}