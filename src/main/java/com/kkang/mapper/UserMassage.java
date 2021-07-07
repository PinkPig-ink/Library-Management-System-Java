package com.kkang.mapper;

import com.kkang.pojo.User;
import com.kkang.pojo.UserAllMassage;

import java.util.List;

public interface UserMassage {
    List<UserAllMassage> selectAllUserMassage();
    List<UserAllMassage> selectAllLibraryMsg();
    List<UserAllMassage> selectAllUser();
    List<User> selectUnReturn();
    UserAllMassage selectUser(String id);
    UserAllMassage selectLibrary(String id);
    void insertDataToLibrary(UserAllMassage userAllMassage);
    void insertDataToUser(UserAllMassage userAllMassage);
    void deleteLibrary(String id);
    void deleteUser(String id);

    void updateUserTFlag(String id);
    void updateUserFFlag(String id);
}
