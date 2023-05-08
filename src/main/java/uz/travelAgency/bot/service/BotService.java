package uz.travelAgency.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.travelAgency.user.entity.UserStep;

import java.util.List;

import static uz.travelAgency.utils.Utils.*;

public class BotService {



    public SendMessage register(Long chatId){
        SendMessage sendMessage = new SendMessage(
                chatId.toString(),
                "Please share your phone number!");
        sendMessage.setReplyMarkup(shareContact());
        return sendMessage;
    }



    public ReplyKeyboardMarkup shareContact(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton("Share phone number");
        button.setRequestContact(true);
        row.add(button);

        replyKeyboardMarkup.setKeyboard(List.of(row));
        return replyKeyboardMarkup;
    }




    public UserStep identifyUserStep(UserStep step, Long chatId, String text){
        switch (text){
            case "EUROPE" -> {
                userService.updateUser(chatId, UserStep.EUROPE);
                step = UserStep.EUROPE;
            }
            case "ASIA" -> {
                userService.updateUser(chatId, UserStep.ASIA);
                step = UserStep.ASIA;
            }
            case "AFRICA" -> {
                userService.updateUser(chatId, UserStep.AFRICA);
                step = UserStep.AFRICA;
            }
            case "AMERICA" -> {
                userService.updateUser(chatId, UserStep.AMERICA);
                step = UserStep.AMERICA;
            }
        }
        return step;
    }

}
