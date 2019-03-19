package cn.twimi.tinynote.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import cn.twimi.tinynote.R;
import cn.twimi.tinynote.databinding.ActivityNoteEditBinding;
import cn.twimi.tinynote.model.NoteModel;
import cn.twimi.tinynote.util.DatabaseUtil;

public class NoteEditActivity extends AppCompatActivity {

    private NoteModel noteModel;
    private boolean isEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNoteEditBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_note_edit);

        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        long note_id = intent.getLongExtra("note_id", -1);
        if (note_id != -1) {
            isEdit = true;
            noteModel = DatabaseUtil.getNoteDao(this).getNoteById(note_id);
            setTitle(R.string.txt_modify_note);
        } else {
            isEdit = false;
            noteModel = new NoteModel("", "");
            setTitle(R.string.txt_new_note);
        }
        binding.setNote(noteModel);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_edit_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnuSave) {
            noteModel.setTimestamp(System.currentTimeMillis());
            if (isEdit) {
                DatabaseUtil.getNoteDao(this).update(noteModel);
            } else {
                DatabaseUtil.getNoteDao(this).insert(noteModel);
            }
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
