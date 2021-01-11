package com.example.fragmentfirebase.recyclerFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmentfirebase.R;
import com.example.fragmentfirebase.databinding.FragmentRecyclerBinding;
import com.example.fragmentfirebase.model.ModelMessage;

import java.util.ArrayList;
import java.util.List;


public class RecyclerFragment extends Fragment {

    FragmentRecyclerBinding binding;

    RecyclerFactory recyclerFactory;
    ViewModelRecycler viewModelRecycler;

    List<ModelMessage> listMessage = new ArrayList<>();

    AdapterRecycler adapterRecycler = new AdapterRecycler(listMessage);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container, false);

        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerFactory = new RecyclerFactory(getContext().getApplicationContext());

        viewModelRecycler = ViewModelProviders.of(this,recyclerFactory).get(ViewModelRecycler.class);

        binding.setMyAdapter(adapterRecycler);
        binding.setMyViewModel(viewModelRecycler);

        viewModelRecycler.getListLiveData().observe(getViewLifecycleOwner(), new Observer<List<ModelMessage>>() {
            @Override
            public void onChanged(List<ModelMessage> modelMessages) {

                adapterRecycler.onChange(modelMessages);

                System.out.println(modelMessages + "%%%%%%%%%%%%%");
            }
        });

        binding.executePendingBindings();

    }

}