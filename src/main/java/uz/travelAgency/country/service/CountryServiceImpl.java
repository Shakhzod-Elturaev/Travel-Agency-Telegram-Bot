package uz.travelAgency.country.service;

import uz.travelAgency.country.entity.CountryEntity;
import uz.travelAgency.country.repository.CountryRepository;
import uz.travelAgency.country.repository.CountryRepositoryImpl;
import uz.travelAgency.user.entity.UserStep;

import java.util.ArrayList;

public class CountryServiceImpl implements CountryService{

    private final CountryRepository countryRepository = new CountryRepositoryImpl();

    @Override
    public ArrayList<CountryEntity> getAllByType(UserStep type) {
        return countryRepository.getAllByContinent(type);
    }

    @Override
    public ArrayList<CountryEntity> getAll() {
        return countryRepository.getAll();
    }
}
