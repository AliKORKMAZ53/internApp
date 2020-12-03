package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.model.Response;
import com.example.myapplication.viewmodel.RepoViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private RepoViewModel repoViewModel;

   // ArrayList<String> responseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=findViewById(R.id.recyclerView);
        repoViewModel=new ViewModelProvider(this).get(RepoViewModel.class);
        repoViewModel.init();


        repoViewModel.getAllRepoNames().observe(this, new Observer<List<Response>>() {
                    @Override
                    public void onChanged(List<Response> responses) {
                   //    recyclerAdapter.notifyDataSetChanged();
                        Log.d("ARRIVAL:", String.valueOf(repoViewModel.getAllRepoNames().getValue().get(0).getName()));
                    }
                }
                );

        Button button=findViewById(R.id.searchButton);
        button.setOnClickListener(v -> {
            initRecyclerView();

        });



    }

    private void initRecyclerView() {
        if(recyclerAdapter==null){
         /*   int a=repoViewModel.getAllRepoNames().getValue().size();
            for(int i=0;i<=a;i++){
                responseList.add(repoViewModel.getAllRepoNames().getValue().get(i).getName());

            }*/
            recyclerAdapter=new RecyclerAdapter(MainActivity.this,repoViewModel.getAllRepoNames().getValue());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerAdapter);
        }else{
            recyclerAdapter.notifyDataSetChanged();
        }
    }
}