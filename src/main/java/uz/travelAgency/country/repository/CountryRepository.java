package uz.travelAgency.country.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import uz.travelAgency.country.entity.CountryEntity;
import uz.travelAgency.user.entity.UserStep;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface CountryRepository {

    void saveCountry(CountryEntity country);

    CountryEntity getByName(String name);
    ArrayList<CountryEntity> getAllByContinent(UserStep continent);

    ArrayList<CountryEntity> getAll();









    ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
            .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    String path = "/media/shakhzod/2fed4cf4-6e79-4664-86d9-ee1b72f9388f/Intellij Ultimate Works/" +
            "Telegram Bots/TravelAgencyTelegramBot/src/main/resources/database/countries.json";

    static void writeToFile(ArrayList<CountryEntity> countries){
        try {
            objectMapper.writeValue(new File(path), countries);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static ArrayList<CountryEntity> readFromFile(){
        try {
            return objectMapper.readValue(new File(path), new TypeReference<ArrayList<CountryEntity>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
