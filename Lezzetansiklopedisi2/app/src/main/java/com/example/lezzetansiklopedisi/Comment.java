package com.example.lezzetansiklopedisi;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "comments")
public class Comment {

    @PrimaryKey(autoGenerate = true)
    private long id; // Auto-generated primary key

    private String username;
    private String text;
    private String image; // Base64 encoded image

    public Comment(String username, String text, String image) {
        this.username = username;
        this.text = text;
        this.image = image;
    }

    // Getter ve Setter metodları

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
