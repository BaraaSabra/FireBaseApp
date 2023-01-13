package com.example.firebaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseapp.databinding.FavertitemBinding;
import com.example.firebaseapp.databinding.LibraryitemBinding;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    ArrayList<FavoriteClass>favoriteArrayList;
    Context context;
    boolean isEmpty;

    public FavoriteAdapter(ArrayList<FavoriteClass> favoriteArrayList, Context context) {
        this.favoriteArrayList = favoriteArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavertitemBinding binding=FavertitemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new FavoriteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FavoriteClass favorite=  favoriteArrayList.get(position);
        holder.BookName.setText(favorite.getBookName());
        holder.bookYear.setText(favorite.getbookYear());
//        holder.imageView.setImageDrawable(favorite.getImage());setImageBitmap(favorite.getImage());



    }

    @Override
    public int getItemCount() {
        return favoriteArrayList.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder{
        TextView BookName,bookYear;

        public FavoriteViewHolder(FavertitemBinding binding) {
            super(binding.getRoot());
            BookName=binding.nameBook;
            bookYear=binding.bookYear;

        }
    }
}
