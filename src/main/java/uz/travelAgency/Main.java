package uz.travelAgency;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.travelAgency.bot.registeredTelBot.TravelAgencyBot;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TravelAgencyBot());
            System.out.println("Bot started!!!");
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}