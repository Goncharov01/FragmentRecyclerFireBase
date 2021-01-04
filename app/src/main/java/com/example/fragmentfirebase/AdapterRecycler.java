package com.example.fragmentfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentfirebase.databinding.TaskItemListBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<ItemViewHolder> {

    Context context;
    List<ModelUser> modelUserList = new ArrayList<>();

    public AdapterRecycler(Context context, List<ModelUser> modelUserList) {
        this.context = context;
        this.modelUserList = modelUserList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TaskItemListBinding binding = TaskItemListBinding.inflate(layoutInflater, parent, false);

        return new ItemViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ModelUser modelUser = modelUserList.get(position);
        holder.bind(modelUser);
    }

    @Override
    public int getItemCount() {
        return modelUserList.size();
    }

    public void onChange(List<ModelUser> modelUserList) {
        modelUserList.clear();
        modelUserList.addAll(modelUserList);
        notifyDataSetChanged();
    }

}