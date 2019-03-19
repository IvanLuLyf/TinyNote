package cn.twimi.tinynote.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import cn.twimi.tinynote.model.NoteModel;

@Dao
public interface NoteDao {
    @Query("select * from note order by timestamp desc")
    LiveData<List<NoteModel>> getAll();

    @Query("select * from note where id=:id")
    NoteModel getNoteById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteModel... model);

    @Delete
    void delete(NoteModel model);

    @Update
    void update(NoteModel... model);
}
