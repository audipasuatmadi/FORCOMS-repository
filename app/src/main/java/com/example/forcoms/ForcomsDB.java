package com.example.forcoms;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.forcoms.topicentity.TopicDao;
import com.example.forcoms.topicentity.TopicData;
import com.example.forcoms.userentity.UserDao;
import com.example.forcoms.userentity.UserData;

@Database(entities = {UserData.class, TopicData.class}, version = 2)
public abstract class ForcomsDB extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract TopicDao topicDao();

    private static ForcomsDB INSTANCE;

    public static ForcomsDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ForcomsDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ForcomsDB.class, "forcoms_db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
