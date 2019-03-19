package cn.twimi.tinynote.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import cn.twimi.tinynote.R;
import cn.twimi.tinynote.databinding.LayoutNoteBinding;
import cn.twimi.tinynote.listener.OnNoteClickListener;
import cn.twimi.tinynote.model.NoteModel;


public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteHolder> {

    private List<NoteModel> models;
    private OnNoteClickListener noteClick;

    public void setModels(List<NoteModel> models) {
        this.models = models;
    }

    public void setNoteClick(OnNoteClickListener noteClick) {
        this.noteClick = noteClick;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LayoutNoteBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_note, parent, false);
        return new NoteHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        holder.binding.setNote(models.get(position));
        holder.binding.setNoteClick(noteClick);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return models == null ? 0 : models.size();
    }

    static class NoteHolder extends RecyclerView.ViewHolder {

        LayoutNoteBinding binding;

        NoteHolder(@NonNull View itemView, LayoutNoteBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }
}
