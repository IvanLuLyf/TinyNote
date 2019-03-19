package cn.twimi.tinynote.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import cn.twimi.tinynote.R;
import cn.twimi.tinynote.adapter.NoteListAdapter;
import cn.twimi.tinynote.dao.NoteDao;
import cn.twimi.tinynote.listener.OnNoteClickListener;
import cn.twimi.tinynote.model.NoteModel;
import cn.twimi.tinynote.util.DatabaseUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoteDao noteDao = DatabaseUtil.getNoteDao(this);
        noteDao.getAll().observe(this, listObserver);
        adapter = new NoteListAdapter();
        adapter.setNoteClick(noteClick);
        RecyclerView recyclerView = findViewById(R.id.listNote);
        recyclerView.setAdapter(adapter);

        FloatingActionButton button = findViewById(R.id.fabAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NoteEditActivity.class);
                startActivity(intent);
            }
        });
    }

    private OnNoteClickListener noteClick = new OnNoteClickListener() {
        @Override
        public void onClick(View view, NoteModel model) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, NoteViewActivity.class);
            intent.putExtra("note_id", model.getId());
            startActivity(intent);
        }
    };

    private Observer<List<NoteModel>> listObserver = new Observer<List<NoteModel>>() {
        @Override
        public void onChanged(List<NoteModel> models) {
            adapter.setModels(models);
            adapter.notifyDataSetChanged();
        }
    };
}
