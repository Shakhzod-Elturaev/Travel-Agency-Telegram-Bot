package uz.travelAgency.user.service;

import uz.travelAgency.user.entity.UserEntity;
import uz.travelAgency.user.entity.UserStep;

import java.util.ArrayList;

public interface UserService {
    void createUser(UserEntity user);

    UserEntity getById(Long id);

    void deleteById(Long id);

    void updateUser(Long chatId, UserStep step);
    void updateUser(UserEntity user);

    ArrayList<UserEntity> getAllUsers();
}
