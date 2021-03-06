package com.radzhabov.learnroom.DataModel;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO{
    @Query("Select * from Users")
    List<User> getAllUsers();

    @Insert
    void insertUser(User user);

}
