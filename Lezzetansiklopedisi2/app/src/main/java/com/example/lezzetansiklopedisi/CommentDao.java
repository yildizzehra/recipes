package com.example.lezzetansiklopedisi;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface CommentDao {

    @Insert
    void insert(Comment comment);

    @Update
    void update(Comment comment);

    @Delete
    void delete(Comment comment);

    @Query("SELECT * FROM comments")
    List<Comment> getAllComments();

    @Query("SELECT * FROM comments WHERE id = :commentId")
    Comment getCommentById(int commentId);

    @Query("DELETE FROM comments")
    void deleteAllComments();
}
