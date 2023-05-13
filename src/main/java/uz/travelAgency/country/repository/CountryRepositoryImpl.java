package uz.travelAgency.country.repository;


import uz.travelAgency.country.entity.CountryEntity;
import uz.travelAgency.exceptions.DuplicateDataException;
import uz.travelAgency.user.entity.UserStep;

import java.util.ArrayList;
import java.util.Objects;

import static uz.travelAgency.country.repository.CountryRepository.*;
public class CountryRepositoryImpl implements CountryRepository{
    @Override
    public void saveCountry(CountryEntity country) {
        ArrayList<CountryEntity> countries = getAll();
        countries.add(country);
        writeToFile(countries);
    }

    @Override
    public CountryEntity getByName(String name) {
        for (CountryEntity c : getAll()) {
            if(Objects.equals(c.getName(), name))
                return c;
        }
        return null;
    }

    @Override
    public ArrayList<CountryEntity> getAllByContinent(UserStep continent) {
        ArrayList<CountryEntity> countries = new ArrayList<>();
        for (CountryEntity c : getAll()) {
            if(Objects.equals(c.getContinent(), continent))
                countries.add(c);
        }
        return countries;
    }

    @Override
    public ArrayList<CountryEntity> getAll() {
        return readFromFile();
    }
}
