package com.example.fragmentfirebase.homeFragment;

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
import com.example.fragmentfirebase.databinding.FragmentHomeBinding;
import com.example.fragmentfirebase.model.ModelMessage;

import java.util.List;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    ViewModelHome viewModelHome;
    HomeFactory homeFactory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        View view = binding.getRoot();
        homeFactory = new HomeFactory(view.getContext().getApplicationContext());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModelHome = ViewModelProviders.of(this, homeFactory).get(ViewModelHome.class);

        viewModelHome.listOfMessages.observe(getViewLifecycleOwner(), new Observer<List<ModelMessage>>() {
            @Override
            public void onChanged(List<ModelMessage> modelMessages) {
                System.out.println("--------LIVE-DATA----------");
            }
        });

        binding.setViewModel(viewModelHome);

    }

}