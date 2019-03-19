package cn.twimi.tinynote.util;

import android.content.Context;

import androidx.room.Room;
import cn.twimi.tinynote.dao.NoteDao;

public class DatabaseUtil {
    private static AppRoomDatabase database;

    private static AppRoomDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, AppRoomDatabase.class, "database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }

    public static NoteDao getNoteDao(Context context) {
        return getDatabase(context).noteDao();
    }
}
