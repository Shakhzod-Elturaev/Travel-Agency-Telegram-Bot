package uz.travelAgency.bot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class BotServiceButtons {

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


    public ReplyKeyboardMarkup menuButtons(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("\uD83C\uDF0F Europe");
        row.add("\uD83D\uDDFD America");

        rows.add(row);

        row = new KeyboardRow();
        row.add("\uD83D\uDD4C Asia");
        row.add("\uD83D\uDDFF Africa");

        rows.add(row);
        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;
    }


    public ReplyKeyboardMarkup travelTypeButtons(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("\uD83D\uDEEB By plane");
        row.add("\uD83D\uDE82 By train");
        rows.add(row);

        row = new KeyboardRow();
        row.add("\uD83D\uDE8C By bus");

        rows.add(row);

        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;
    }
}
