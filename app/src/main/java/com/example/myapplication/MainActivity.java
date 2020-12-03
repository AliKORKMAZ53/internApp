package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.model.Response;
import com.example.myapplication.viewmodel.RepoViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private RepoViewModel repoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       repoViewModel=new ViewModelProvider(this).get(RepoViewModel.class);
       repoViewModel.init();
       initRecyclerView();
       repoViewModel.getAllRepoNames().observe(this, responses ->
                recyclerAdapter.notifyDataSetChanged());


    }

    private void initRecyclerView() {
        if(recyclerAdapter==null){
            recyclerAdapter=new RecyclerAdapter(MainActivity.this,repoViewModel.getAllRepoNames().getValue());
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerAdapter);
        }
    }
}