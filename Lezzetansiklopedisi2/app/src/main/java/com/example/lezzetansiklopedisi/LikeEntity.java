package com.example.lezzetansiklopedisi;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LikeEntity")
public class LikeEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String recipeName;
    private int likeCount;

    // Getter ve Setter metodları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
