package com.example.fragmentfirebase.recyclerFragment;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fragmentfirebase.AuthAppRepository;
import com.example.fragmentfirebase.model.ModelMessage;

import java.util.ArrayList;
import java.util.List;

public class ViewModelRecycler extends ViewModel {

    Context context;
    public MutableLiveData<List<ModelMessage>> listMessages = new MutableLiveData<>();
    private AuthAppRepository authAppRepository;

    public ViewModelRecycler(Context context) {
        this.context = context;
        this.authAppRepository = new AuthAppRepository(context);
        this.listMessages = authAppRepository.getMessagesLiveData();
        System.out.println(listMessages.getValue() + "LISTCONSTUCTOR");
    }

}
