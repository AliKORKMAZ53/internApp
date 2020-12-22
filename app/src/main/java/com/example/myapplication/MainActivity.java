package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.model.Response;
import com.example.myapplication.viewmodel.RepoViewModel;
import com.example.myapplication.viewmodel.dbViewModel;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ClickListener {

    public RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private RepoViewModel repoViewModel;
    dbViewModel dbViewModel;
    EditText editText;
    Button button;
    List<Response> nullResponseList =new ArrayList<>();
    List<Response> actualResponseList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.searchButton);
        editText=findViewById(R.id.editTextSearch);
        recyclerView=findViewById(R.id.recyclerView);
        dbViewModel=new dbViewModel(getApplication());

        repoViewModel=new ViewModelProvider(this).get(RepoViewModel.class);
        repoViewModel.getAllRepoNames().observe(this, responses -> {

                    if(responses==null){
                        Toast.makeText(MainActivity.this,"There is no repo in this name",Toast.LENGTH_SHORT).show();
                        recyclerAdapter.setResponses(nullResponseList);
                    }else{

                        actualResponseList=responses;
                        recyclerAdapter.setResponses(actualResponseList);

                    }
            recyclerAdapter.notifyDataSetChanged();

                }
        );

        button.setOnClickListener(v -> {
            repoViewModel.initandupdate(editText.getText().toString());

        });

        initRecyclerView();

    }

    private void initRecyclerView() {
       if(recyclerAdapter==null){
            recyclerAdapter=new RecyclerAdapter(MainActivity.this, this::onClickFavBut);
            recyclerAdapter.setResponses(nullResponseList);//for nullablity
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerAdapter);
       }else{
           recyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClickFavBut(int position) {
        Log.d("MainActivity callback",actualResponseList.get(position).getOwner().getLogin());
        dbViewModel.addFavourite(actualResponseList.get(position).getOwner().getLogin(),actualResponseList.get(position).getName());
        Log.d("MainActivity callback",actualResponseList.get(position).getName());
    }
}