package cn.twimi.tinynote.listener;

import android.view.View;

import cn.twimi.tinynote.model.NoteModel;

public interface OnNoteClickListener {
    void onClick(View view, NoteModel model);
}