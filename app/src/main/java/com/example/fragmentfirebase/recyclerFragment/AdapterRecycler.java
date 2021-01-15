package com.example.fragmentfirebase.recyclerFragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentfirebase.model.ModelMessage;
import com.example.fragmentfirebase.databinding.TaskItemListBinding;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecycler extends FirestoreRecyclerAdapter<ModelMessage, ItemViewHolder> {

    public AdapterRecycler(@NonNull FirestoreRecyclerOptions<ModelMessage> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull ModelMessage model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TaskItemListBinding binding = TaskItemListBinding.inflate(layoutInflater, parent, false);

        return new ItemViewHolder(binding.getRoot());
    }

//    @Override
//    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
//        ModelMessage modelMessage = listMessage.get(position);
//        holder.bind(modelMessage);
//    }

//    @Override
//    public int getItemCount() {
//        return listMessage.size();
//    }

//    public void onChange(List<ModelMessage> listMessage) {
//        listMessage.clear();
//        listMessage.addAll(listMessage);
//        notifyDataSetChanged();
//    }

}