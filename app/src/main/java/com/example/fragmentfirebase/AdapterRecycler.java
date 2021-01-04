package com.example.fragmentfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.RecyclerViewHolder> {

    Context context;
    List<ModelUser> modelUserList = new ArrayList<>();

    public AdapterRecycler(Context context, List<ModelUser> modelUserList) {
        this.context = context;
        this.modelUserList = modelUserList;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView author;
        private TextView body;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.textAuthor);
            body = itemView.findViewById(R.id.textBody);
        }

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_item_list, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        if (modelUserList != null) {
            ModelUser modelUserList = this.modelUserList.get(position);

            holder.author.setText(modelUserList.getAuthor());
            holder.body.setText(modelUserList.getBody());

        }
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