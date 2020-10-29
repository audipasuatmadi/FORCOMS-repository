package com.example.forcoms.userentity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert()
    void addUserData(UserData userData);

    @Query("SELECT * FROM users")
    LiveData<List<UserData>> getAllUserData();

    @Query("SELECT * FROM users WHERE id=:id LIMIT 1")
    UserData getUserFromId(int id);
}
