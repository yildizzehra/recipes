package com.example.lezzetansiklopedisi;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_dishes")
public class UserDish {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String userId;
    private String dishName;

    // Getter ve Setter metodları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
}
