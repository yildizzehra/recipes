package com.example.lezzetansiklopedisi;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Comment.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract CommentDao commentDao();

    // Singleton instance of the database
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration() // Rebuilds the database if the schema changes
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
