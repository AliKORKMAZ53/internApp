package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Response;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RepoViewHolder>{
    private List<Response> responses=new ArrayList<>();
    private Context context;

    public RecyclerAdapter(Context context){
        this.context=context;
    }


    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    @NonNull
    @Override
    public RecyclerAdapter.RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.repo_item,parent,false);

        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.tv.setText(responses.get(position).getName());
    }



    @Override
    public int getItemCount() {
        return responses.size();
    }
    public class RepoViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.repoTextView);
        }
    }
}
