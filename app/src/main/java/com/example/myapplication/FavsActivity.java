package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.roomDB.RepoDbTable;
import com.example.myapplication.viewmodel.DbViewModel;
import com.example.myapplication.viewmodel.ViewmodelFactory;

import java.util.ArrayList;
import java.util.List;

public class FavsActivity extends AppCompatActivity implements FavRecyclerAdapter.ClickListener{
    public RecyclerView recyclerView;
    public FavRecyclerAdapter favRecyclerAdapter;
    DbViewModel dbViewModel;
    ViewmodelFactory viewmodelFactory;

    List<RepoDbTable> nullTableList =new ArrayList<>();
    List<RepoDbTable> actualTableList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs);
        recyclerView=findViewById(R.id.FavrecyclerView);
        viewmodelFactory=new ViewmodelFactory(getApplication());
        dbViewModel=viewmodelFactory.create(1);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(FavsActivity.this);
        builder.setTitle("Warning");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbViewModel.deleteOneFav(actualTableList.get(position).repoName);
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.show();

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