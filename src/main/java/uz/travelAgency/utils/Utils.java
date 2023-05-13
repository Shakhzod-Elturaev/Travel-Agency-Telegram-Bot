package uz.travelAgency.utils;

import uz.travelAgency.bot.service.BotService;
import uz.travelAgency.bot.service.BotServiceButtons;
import uz.travelAgency.country.repository.CountryRepository;
import uz.travelAgency.country.repository.CountryRepositoryImpl;
import uz.travelAgency.user.repository.UserRepository;
import uz.travelAgency.user.repository.UserRepositoryImpl;
import uz.travelAgency.user.service.UserService;
import uz.travelAgency.user.service.UserServiceImpl;

public interface Utils {
    UserService userService = new UserServiceImpl();
    UserRepository userRepository = new UserRepositoryImpl();



    CountryRepository countryRepository = new CountryRepositoryImpl();


    BotService botService = new BotService();
    BotServiceButtons botServiceButtons = new BotServiceButtons();
}
