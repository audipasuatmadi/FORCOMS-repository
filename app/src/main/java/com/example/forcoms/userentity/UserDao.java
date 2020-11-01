package com.example.forcoms.userentity;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert()
    long addUserData(UserData userData);

    @Query("SELECT * FROM users WHERE id=:id LIMIT 1")
    UserData getUserFromId(long id);

    @Update
    void updateUserData(UserData userData);

    @Query("SELECT * FROM users WHERE username=:username AND password=:password LIMIT 1")
    @Nullable
    UserData getUserWithCredentials(String username, String password);

}
