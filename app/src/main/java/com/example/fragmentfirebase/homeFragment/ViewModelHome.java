package com.example.fragmentfirebase.homeFragment;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fragmentfirebase.AuthAppRepository;
import com.example.fragmentfirebase.ModelUser;
import com.example.fragmentfirebase.messageFragment.ModelMessage;

import java.util.List;

public class ViewModelHome extends ViewModel {

    private Context context;
    private AuthAppRepository authAppRepository;
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<List<ModelMessage>> listOfMessages = new MutableLiveData<List<ModelMessage>>();

    public ViewModelHome(Context context) {
        this.context = context;
        this.authAppRepository = new AuthAppRepository(context);
    }

    public void register(View v) {
        authAppRepository.regist(email.getValue(), password.getValue());
    }

    public void login(View v) {
        authAppRepository.login(email.getValue(), password.getValue());
    }


}
