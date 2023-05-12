package uz.travelAgency.bot.registeredTelBot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.travelAgency.user.entity.UserEntity;
import uz.travelAgency.user.entity.UserStep;

import java.util.concurrent.Executors;

import static uz.travelAgency.utils.Utils.*;

public class TravelAgencyBot extends TelegramLongPollingBot {

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Executors.newCachedThreadPool().execute(() -> {
            if(update.hasMessage()) {
                Message message = update.getMessage();
                start(message);
            }
        });
    }



    private void start(Message message){
        Long chatId = message.getChatId();
        String text = message.getText();

        UserEntity user = userService.getById(chatId);
        UserStep step = UserStep.START;

        if(user != null){

            step = user.getStep();
            step = figureOutUserStep(step, text, chatId);

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

    private UserStep figureOutUserStep(UserStep step, String text, Long chatId) {
        switch (step){
            case REGISTERED, MENU -> {
                step = botService.identifyUserStep(step, chatId, text);
            }
            case EUROPE -> {

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
