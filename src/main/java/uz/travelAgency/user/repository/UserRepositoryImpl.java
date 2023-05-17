package uz.travelAgency.user.repository;

import uz.travelAgency.user.entity.UserEntity;
import uz.travelAgency.user.entity.UserStep;

import java.util.ArrayList;
import java.util.Objects;

import static uz.travelAgency.user.repository.UserRepository.*;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public void saveUser(UserEntity user) {
        ArrayList<UserEntity> all = getAll();
        all.add(user);
        writeToFile(all);
    }

    @Override
    public UserEntity getById(Long id) {
        for (UserEntity u : getAll()) {
            if(Objects.equals(u.getId(), id))
                return u;
        }
        return null;
    }

    @Override
    public void updateUser(Long id, UserStep step) {
        int i = 0;
        ArrayList<UserEntity> all = getAll();
        for (UserEntity u : all) {
            if(Objects.equals(u.getId(), id)){
                u.setStep(step);
                all.set(i, u);
                break;
            }
            i++;
        }
        writeToFile(all);
    }

    @Override
    public void updateUser(UserEntity user) {
        int i = 0;
        ArrayList<UserEntity> all = getAll();
        for (UserEntity u : all) {
            if(Objects.equals(u.getId(), user.getId())){
                all.set(i, user);
                break;
            }
            i++;
        }
        writeToFile(all);
    }

    @Override
    public ArrayList<UserEntity> getAll() {
        return readFromFile();
    }
}
