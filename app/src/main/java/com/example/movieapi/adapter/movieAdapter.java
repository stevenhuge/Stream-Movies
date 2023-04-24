package com.example.movieapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapi.R;
import com.example.movieapi.data.movieData;

import java.util.ArrayList;

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.ViewHolder> {
    private Context context;
    private ArrayList<movieData> values;
    private LayoutInflater layoutInflater;
    public movieAdapter(Context context, ArrayList<movieData> values){
        this.context=context;
        this.values=values;
        this.layoutInflater=layoutInflater.from(context);
    }
    @NonNull
    @Override
    public movieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }
    //requiredContext()
    @Override
    public void onBindViewHolder(@NonNull movieAdapter.ViewHolder holder, int position) {
        movieData data=values.get(position);
        holder.title.setText(data.movieTitle);
        holder.year.setText(data.movieYear);
        holder.tipe.setText(data.movieTipe);
        Glide.with(context.getApplicationContext()).load(data.movieGambar).into(holder.gambar);
    }
    @Override
    public int getItemCount() {
        return values.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView year,tipe;
        ImageView gambar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            year=itemView.findViewById(R.id.year);
            gambar=itemView.findViewById(R.id.gambar);
            tipe=itemView.findViewById(R.id.tipe);
        }
    }
}
