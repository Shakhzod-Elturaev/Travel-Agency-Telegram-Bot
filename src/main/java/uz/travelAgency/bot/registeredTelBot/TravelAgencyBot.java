package uz.travelAgency.bot.registeredTelBot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import uz.travelAgency.country.entity.CountryEntity;
import uz.travelAgency.user.entity.UserEntity;
import uz.travelAgency.user.entity.UserStep;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import static uz.travelAgency.utils.Utils.*;

public class TravelAgencyBot extends TelegramLongPollingBot {

    ArrayList<CountryEntity> countries = new ArrayList<>();


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Executors.newCachedThreadPool().execute(() -> {
            if(update.hasMessage()) {
                Message message = update.getMessage();
                start(message);


            } else if(update.hasCallbackQuery()){
                CallbackQuery callbackQuery = update.getCallbackQuery();
                startWithCallBackQuery(callbackQuery);
            }
        });
    }

    @SneakyThrows
    private void startWithCallBackQuery(CallbackQuery callbackQuery) {
        String data = callbackQuery.getData();
        Message message = callbackQuery.getMessage();
        Long chatId = message.getChatId();

        switch (data){
            case "EUROPE_1" -> {
                execute(botService.deleteMessage(chatId, message.getMessageId()));
                execute(botService.travelType(chatId));
            }
            case "BACK" -> {
                execute(botService.menu(chatId));
            }
        }
    }


    private void start(Message message){
        Long chatId = message.getChatId();

        UserEntity user = userService.getById(chatId);
        UserStep step = UserStep.START;

        if(user != null){

            step = user.getStep();
            step = figureOutUserStep(step, message, chatId);

        } else if(message.hasContact()){

            Contact c = message.getContact();
            UserEntity userEntity = UserEntity.builder()
                    .phoneNumber(c.getPhoneNumber())
                    .step(UserStep.REGISTERED)
                    .id(chatId)
                    .build();
            userService.createUser(userEntity);
            step = UserStep.REGISTERED;

        }

        executeUserSteps(step, chatId);

    }



    @SneakyThrows
    private UserStep figureOutUserStep(UserStep step, Message message, Long chatId) {
        switch (step){
            case REGISTERED, MENU -> {
                step = botService.identifyUserStep(step, chatId, message.getText());
                countries = botService.findOutCountryType(message.getText());
            }
        }
        return step;
    }

    @SneakyThrows
    private void executeUserSteps(UserStep step, Long chatId) {
        switch (step){
            case START -> {
                execute(botService.register(chatId));
            }
            case REGISTERED, MENU -> {
                execute(botService.menu(chatId));
            }
            case EUROPE, ASIA, AFRICA, AMERICA -> {
                execute(botService.removeMenu(chatId));
                if(countries.isEmpty()){
                    execute(new SendMessage(chatId.toString(), "\uD83E\uDD37\u200D♂\uFE0F"));
                    execute(botService.menu(chatId));
                } else {
                    execute(botService.countries(chatId, countries));
                }
                userService.updateUser(chatId, UserStep.MENU);
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
