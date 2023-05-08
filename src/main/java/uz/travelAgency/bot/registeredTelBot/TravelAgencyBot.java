package uz.travelAgency.bot.registeredTelBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TravelAgencyBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            try {
                execute(new SendMessage(update.getMessage().getChatId().toString(), update.getMessage().getText()));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "G23_Travel_Agency_bot";
    }

    @Override
    public String getBotToken() {
        return "5707775474:AAHQ_EOWPhz-EARukckfuIKd37t4eojfQmo";
    }
}
