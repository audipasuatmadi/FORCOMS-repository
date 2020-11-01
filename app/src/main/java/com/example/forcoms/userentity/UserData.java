package com.example.forcoms.userentity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity (tableName = "users")
public class UserData {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "username")
    @NonNull
    private String username;

    @ColumnInfo(name = "password")
    @NonNull
    private String password;


    public UserData(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public void setUsername(@NonNull String username) { this.username = username; }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
