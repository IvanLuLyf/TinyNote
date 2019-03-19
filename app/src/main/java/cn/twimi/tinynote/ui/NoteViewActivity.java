package cn.twimi.tinynote.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import cn.twimi.tinynote.R;
import cn.twimi.tinynote.databinding.ActivityNoteViewBinding;
import cn.twimi.tinynote.listener.OnNoteClickListener;
import cn.twimi.tinynote.model.NoteModel;
import cn.twimi.tinynote.util.DatabaseUtil;

public class NoteViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNoteViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_note_view);

        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        long note_id = intent.getLongExtra("note_id", -1);
        if (note_id != -1) {
            NoteModel noteModel = DatabaseUtil.getNoteDao(this).getNoteById(note_id);
            setTitle(noteModel.getTitle());
            binding.txtContent.setMovementMethod(ScrollingMovementMethod.getInstance());
            binding.setNote(noteModel);
            binding.setEditClick(editClick);
            binding.setDeleteClick(deleteClick);
        } else {
            finish();
        }
    }

    private OnNoteClickListener editClick = new OnNoteClickListener() {
        @Override
        public void onClick(View view, NoteModel model) {
            Intent intent = new Intent();
            intent.setClass(NoteViewActivity.this, NoteEditActivity.class);
            intent.putExtra("note_id", model.getId());
            startActivity(intent);
            finish();
        }
    };

    private OnNoteClickListener deleteClick = new OnNoteClickListener() {
        @Override
        public void onClick(View view, NoteModel model) {
            DatabaseUtil.getNoteDao(NoteViewActivity.this).delete(model);
            finish();
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
