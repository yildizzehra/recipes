package com.example.lezzetansiklopedisi;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDishDao {

    @Insert
    void insertUserDish(UserDish userDish);

    @Query("SELECT * FROM user_dishes WHERE userId = :userId")
    List<UserDish> getUserDishes(String userId);
}
