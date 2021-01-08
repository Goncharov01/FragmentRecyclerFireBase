package com.example.fragmentfirebase.homeFragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class HomeFactory implements ViewModelProvider.Factory {

    Context context;

    public HomeFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ViewModelHome.class)) {
            return (T) new ViewModelHome(context);
        } else {
            throw new IllegalArgumentException("Unknown class");
        }
    }
}
