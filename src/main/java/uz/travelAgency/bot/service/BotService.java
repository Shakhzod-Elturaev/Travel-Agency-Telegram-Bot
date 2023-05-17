package uz.travelAgency.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import uz.travelAgency.country.entity.CountryEntity;
import uz.travelAgency.country.service.CountryService;
import uz.travelAgency.country.service.CountryServiceImpl;
import uz.travelAgency.user.entity.UserStep;


import java.io.File;
import java.util.ArrayList;

import static uz.travelAgency.utils.Utils.*;

public class BotService {

    private final CountryService countryService = new CountryServiceImpl();


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
        sendPhoto.setCaption("Choose vehicle type to travel");
        sendPhoto.setReplyMarkup(botServiceButtons.travelTypeButtons());
        return sendPhoto;
    }

    public SendPhoto countries(Long chatId, ArrayList<CountryEntity> countries){
        String path = "";
        switch (countries.get(0).getContinent()){
            case EUROPE -> path = "/home/shakhzod/Pictures/europe.jpg";
            case ASIA -> path = "/home/shakhzod/Pictures/asia.jpg";
            case AMERICA -> path = "/home/shakhzod/Pictures/america.jpg";
            case AFRICA -> path = "/home/shakhzod/Pictures/africa.jpg";
        }
        SendPhoto sendPhoto = new SendPhoto(
                chatId.toString(),
                new InputFile(new File(path))
        );
        sendPhoto.setReplyMarkup(botServiceButtons.countryButtons(countries));
        return sendPhoto;
    }

    public DeleteMessage deleteMessage(Long chatId, Integer messageId){
        return new DeleteMessage(chatId.toString(), messageId);
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

    public ArrayList<CountryEntity> findOutCountryType(String text) {
        UserStep type = null;
        switch (text){
            case "\uD83C\uDF0F Europe" -> {
                type = UserStep.EUROPE;
            }
            case "\uD83D\uDD4C Asia" -> {
                type = UserStep.ASIA;
            }
            case "\uD83D\uDDFF Africa" -> {
                type = UserStep.AFRICA;
            }
            case "\uD83D\uDDFD America" -> {
                type = UserStep.AMERICA;
            }
        }
        return (type == null) ? new ArrayList<>() : countryService.getAllByType(type);
    }
}
