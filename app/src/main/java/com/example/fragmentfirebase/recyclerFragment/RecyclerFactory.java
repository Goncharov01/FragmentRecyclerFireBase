package com.example.fragmentfirebase.recyclerFragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class RecyclerFactory implements ViewModelProvider.Factory {

    Context context;

    public RecyclerFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ViewModelRecycler.class)) {
            return (T) new ViewModelRecycler(context);
        } else {
            throw new IllegalArgumentException("Unknown class");
        }
    }
}
