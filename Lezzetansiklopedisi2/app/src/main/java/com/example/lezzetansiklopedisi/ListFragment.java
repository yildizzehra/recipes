package com.example.lezzetansiklopedisi;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExecutorService executorService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private String getUserId() {
        // Kullanıcının ID'sini almak için gerekli işlemi burada yapın
        return "sampleUserId"; // Örnek ID, gerçek uygulamada dinamik olarak alınmalı
    }
}
