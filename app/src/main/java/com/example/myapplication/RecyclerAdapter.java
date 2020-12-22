package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Response;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RepoViewHolder>{
    private List<Response> responses=new ArrayList<>();
    private Context context;
    private ClickListener listener;

    public RecyclerAdapter(Context context,ClickListener listener){
        this.context=context;
        this.listener=listener;
    }


    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    @NonNull
    @Override
    public RecyclerAdapter.RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.repo_item,parent,false);

        return new RepoViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.tv.setText(responses.get(position).getName());

    }



    @Override
    public int getItemCount() {
        return responses.size();
    }
    public class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv;
        Button favBut;
        ClickListener clickListener;
        public RepoViewHolder(@NonNull View itemView, ClickListener clickListener) {
            super(itemView);
            tv = itemView.findViewById(R.id.repoTextView);
            favBut=itemView.findViewById(R.id.favButton);
            this.clickListener=clickListener;
            favBut.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("OnClick","In adapter onClick");
            clickListener.onClickFavBut(getAdapterPosition());
        }
    }
    public interface ClickListener{
        void onClickFavBut(int position);
    }
}
