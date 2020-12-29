package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.roomDB.DbRepository;
import com.example.myapplication.roomDB.RepoDbTable;
import com.example.myapplication.viewmodel.DbViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavsActivity extends AppCompatActivity implements FavRecyclerAdapter.ClickListener{
    public RecyclerView recyclerView;
    public FavRecyclerAdapter favRecyclerAdapter;
    DbViewModel dbViewModel;

    List<RepoDbTable> nullTableList =new ArrayList<>();
    List<RepoDbTable> actualTableList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs);
        recyclerView=findViewById(R.id.FavrecyclerView);
        dbViewModel=new DbViewModel(getApplication());
        dbViewModel=new ViewModelProvider(this).get(DbViewModel.class);
        dbViewModel.getFavourites().observe(this,repoDbTables->{
            if(repoDbTables==null){
                Toast.makeText(FavsActivity.this,"There is no repo in this name",Toast.LENGTH_SHORT).show();
                favRecyclerAdapter.setTables(nullTableList);
            }else{
                actualTableList=repoDbTables;
                favRecyclerAdapter.setTables(actualTableList);
            }
            favRecyclerAdapter.notifyDataSetChanged();
        });

        initRecyclerView();


    }



    @Override
    public void onClickUnFavBut(int position) {
        dbViewModel.deleteOneFav(actualTableList.get(position).repoName);

    }
    private void initRecyclerView(){
        if(favRecyclerAdapter==null){
            favRecyclerAdapter=new FavRecyclerAdapter(FavsActivity.this,this::onClickUnFavBut);
            favRecyclerAdapter.setTables(nullTableList);
            RecyclerView.LayoutManager layoutManager=
                    new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(favRecyclerAdapter);

        }
        favRecyclerAdapter.notifyDataSetChanged();
    }

    public void passToMainActivity(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}