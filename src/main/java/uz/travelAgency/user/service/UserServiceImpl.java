package uz.travelAgency.user.service;

import uz.travelAgency.user.entity.UserEntity;
import uz.travelAgency.user.entity.UserStep;


import java.util.ArrayList;

import static uz.travelAgency.utils.Utils.*;

public class UserServiceImpl implements UserService{
    @Override
    public void createUser(UserEntity user) {
        userRepository.saveUser(user);
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void updateUser(Long charId, UserStep step) {
        userRepository.updateUser(charId, step);
    }

    @Override
    public void updateUser(UserEntity user) {

    }

    @Override
    public ArrayList<UserEntity> getAllUsers() {
        return userRepository.getAll();
    }
}
