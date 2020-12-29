package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.roomDB.RepoDbTable;

import java.util.ArrayList;
import java.util.List;

public class FavRecyclerAdapter extends RecyclerView.Adapter<FavRecyclerAdapter.RepoViewHolder> {
    private List<RepoDbTable> tables=new ArrayList<>();
    private Context context;
    private ClickListener clickListener;

    public FavRecyclerAdapter(Context context, ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }
    public void setTables(List<RepoDbTable> tables){
        this.tables=tables;
    }

    @NonNull
    @Override
    public FavRecyclerAdapter.RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fav_item,parent,false);
        return new RepoViewHolder(view,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FavRecyclerAdapter.RepoViewHolder holder, int position) {
        holder.tvOwner.setText(tables.get(position).ownerName);
        holder.tvRepo.setText(tables.get(position).repoName);
        if(tables.get(position).url!=null){
            Glide.with(context)
                    .load(tables.get(position).url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.img);

        }else{
            Glide.clear(holder.img);
            holder.img.setImageDrawable(null);
        }

    }


    @Override
    public int getItemCount() {
        return tables.size();
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvOwner;
        TextView tvRepo;
        Button unFav;
        ImageView img;
        ClickListener clickListener;
        public RepoViewHolder(@NonNull View itemView, ClickListener clickListener) {
            super(itemView);
            tvOwner=itemView.findViewById(R.id.OwnertextView);
            tvRepo=itemView.findViewById(R.id.RepoFavtextView);
            unFav=itemView.findViewById(R.id.unFavButton);
            img=itemView.findViewById(R.id.imageView);
            this.clickListener=clickListener;
            unFav.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        clickListener.onClickUnFavBut(getAdapterPosition());

        }
    }
    public interface ClickListener{
        void onClickUnFavBut(int position);
    }

}
