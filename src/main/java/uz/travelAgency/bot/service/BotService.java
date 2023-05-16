package uz.travelAgency.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import uz.travelAgency.user.entity.UserStep;


import java.io.File;

import static uz.travelAgency.utils.Utils.*;

public class BotService {


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

    public SendMessage removeMenu(Long chatId){
        SendMessage sendMessage = new SendMessage(
                chatId.toString(),
                "\uD83C\uDF0E"
        );
        sendMessage.setReplyMarkup(botServiceButtons.deleteMenu());
        return sendMessage;
    }

    public SendPhoto travelType(Long chatId){
        SendPhoto sendPhoto = new SendPhoto(
                chatId.toString(),
                new InputFile(new File("/home/shakhzod/Pictures/1000_F_300101300_FevTg0mqZG5S1g94Dk2pzzGW6vnVgUDl.jpg")));
        sendPhoto.setCaption("Choose vehicle to travel");
        sendPhoto.setReplyMarkup(botServiceButtons.travelTypeButtons());
        return sendPhoto;
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
