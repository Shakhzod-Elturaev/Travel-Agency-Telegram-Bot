package uz.travelAgency.utils;

import uz.travelAgency.bot.service.BotService;
import uz.travelAgency.user.repository.UserRepository;
import uz.travelAgency.user.repository.UserRepositoryImpl;
import uz.travelAgency.user.service.UserService;
import uz.travelAgency.user.service.UserServiceImpl;

public interface Utils {
    UserService userService = new UserServiceImpl();
    UserRepository userRepository = new UserRepositoryImpl();

    BotService botService = new BotService();
}
