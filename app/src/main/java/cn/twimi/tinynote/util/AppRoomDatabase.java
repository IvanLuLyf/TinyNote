package cn.twimi.tinynote.util;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import cn.twimi.tinynote.dao.NoteDao;
import cn.twimi.tinynote.model.NoteModel;

@Database(entities = {NoteModel.class}, version = 1, exportSchema = false)
abstract class AppRoomDatabase extends RoomDatabase {
    abstract NoteDao noteDao();
}
