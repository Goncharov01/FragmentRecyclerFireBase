package com.example.fragmentfirebase;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentfirebase.databinding.TaskItemListBinding;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    TaskItemListBinding binding;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(Object obj) {
        binding.setVariable(BR.modelUser, obj);
        binding.executePendingBindings();
    }

}
