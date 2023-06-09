package uz.travelAgency.user.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import uz.travelAgency.user.entity.UserEntity;
import uz.travelAgency.user.entity.UserStep;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface UserRepository {

    void saveUser(UserEntity user);

    UserEntity getById(Long id);

    void updateUser(Long id, UserStep step);
    void updateUser(UserEntity user);

    ArrayList<UserEntity> getAll();





    ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
            .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

    String path = "/media/shakhzod/2fed4cf4-6e79-4664-86d9-ee1b72f9388f/Intellij Ultimate Works/" +
            "Telegram Bots/TravelAgencyTelegramBot/src/main/resources/database/users.json";

    static void writeToFile(ArrayList<UserEntity> users){
        try {
            objectMapper.writeValue(new File(path), users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static ArrayList<UserEntity> readFromFile(){
        try {
            return objectMapper.readValue(new File(path), new TypeReference<ArrayList<UserEntity>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
