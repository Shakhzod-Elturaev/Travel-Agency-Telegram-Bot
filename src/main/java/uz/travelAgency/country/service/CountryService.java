package uz.travelAgency.country.service;

import uz.travelAgency.country.entity.CountryEntity;
import uz.travelAgency.user.entity.UserStep;

import java.util.ArrayList;

public interface CountryService {

    ArrayList<CountryEntity> getAllByType(UserStep type);

    ArrayList<CountryEntity> getAll();
}
