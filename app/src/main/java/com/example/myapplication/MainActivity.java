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
import android.widget.EditText;

import com.example.myapplication.model.Response;
import com.example.myapplication.viewmodel.RepoViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private RepoViewModel repoViewModel;
    EditText editText;
    Button button;
    List<Response> responseList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.searchButton);
        editText=findViewById(R.id.editTextSearch);
        recyclerView=findViewById(R.id.recyclerView);

        initRecyclerView();

        repoViewModel=new ViewModelProvider(this).get(RepoViewModel.class);


        button.setOnClickListener(v -> {
            repoViewModel.initanduptade(editText.getText().toString());
            repoViewModel.getAllRepoNames().observe(this, responses -> {
                        Log.d("ARRIVAL",responses.get(0).getName());//test
                        responseList=responses;
                        recyclerAdapter.setResponses(responseList);
                        recyclerAdapter.notifyDataSetChanged();

                    }
            );
        });


    }

    private void initRecyclerView() {
       if(recyclerAdapter==null){
            recyclerAdapter=new RecyclerAdapter(MainActivity.this);
            recyclerAdapter.setResponses(responseList);//for nullablity
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerAdapter);
       }else{
           recyclerAdapter.notifyDataSetChanged();
        }
    }
}