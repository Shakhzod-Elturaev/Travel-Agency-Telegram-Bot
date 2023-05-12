package uz.travelAgency.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.travelAgency.user.entity.UserStep;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import static uz.travelAgency.utils.Utils.*;

public class BotService {

    private final BotServiceButtons botServiceButtons = new BotServiceButtons();


    public SendMessage register(Long chatId){
        SendMessage sendMessage = new SendMessage(
                chatId.toString(),
                "Please share your phone number!");
        sendMessage.setReplyMarkup(botServiceButtons.shareContact());
        return sendMessage;
    }

    public SendMessage menu(Long chatId){
        SendMessage sendMessage = new SendMessage(
                chatId.toString(),
                "Menu");
        sendMessage.setReplyMarkup(botServiceButtons.menuButtons());
        return sendMessage;
    }

    public SendMessage travelType(Long chatId){
        SendMessage sendMessage = new SendMessage(
                chatId.toString(),
                "Choose the travel type!");
        sendMessage.setReplyMarkup(botServiceButtons.travelTypeButtons());
        return sendMessage;
    }




    public UserStep identifyUserStep(UserStep step, Long chatId, String text){
        switch (text){
            case "\uD83C\uDF0F Europe" -> {
                userService.updateUser(chatId, UserStep.EUROPE);
                step = UserStep.EUROPE;
            }
            case "\uD83D\uDD4C Asia" -> {
                userService.updateUser(chatId, UserStep.ASIA);
                step = UserStep.ASIA;
            }
            case "\uD83D\uDDFF Africa" -> {
                userService.updateUser(chatId, UserStep.AFRICA);
                step = UserStep.AFRICA;
            }
            case "\uD83D\uDDFD America" -> {
                userService.updateUser(chatId, UserStep.AMERICA);
                step = UserStep.AMERICA;
            }
        }
        return step;
    }

}
