package com.example.fragmentfirebase;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmentfirebase.databinding.FragmentRecyclerBinding;

import java.util.ArrayList;
import java.util.List;


public class RecyclerFragment extends Fragment {

    FragmentRecyclerBinding binding;

    MutableLiveData<List<ModelUser>> mutableLiveData = new MutableLiveData<>();
    List<ModelUser> modelUserList = new ArrayList<ModelUser>();
    AdapterRecycler adapterRecycler = new AdapterRecycler(getContext(), modelUserList);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container, false);

        View view = binding.getRoot();
        binding.setMyAdapter(adapterRecycler);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        modelUserList.add(new ModelUser("asda", "sdfsdf"));
        modelUserList.add(new ModelUser("asdfsdf", "sdfsdf"));
        modelUserList.add(new ModelUser("sdfsdf", "sdfsdf"));

    }

}