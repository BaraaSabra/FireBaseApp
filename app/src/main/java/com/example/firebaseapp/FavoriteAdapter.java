package com.example.firebaseapp;

import android.content.Context;
import android.util.Log;
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
import java.util.List;

import kotlin.ranges.CharRange;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    Context context;
    boolean isEmpty;
    boolean isfavarite=false;
    Favorite favorites;
    ArrayList<CreateBookClass> NameBook;

    public FavoriteAdapter(  ArrayList<CreateBookClass> NameBook, Context context,Favorite favorite) {
       this.NameBook=NameBook;
        this.context = context;
        this.favorites=favorite;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavertitemBinding binding=FavertitemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new FavoriteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        int pos = position;
        CreateBookClass library=  NameBook.get(position);
        holder.BookName.setText(library.getBookName().toString());
        Log.d("Nameofbbok",holder.BookName.getText().toString());
//        holder.imageView.setImageDrawable(favorite.getImage());
//        setImageBitmap(favorite.getImage());

        holder.ibFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isfavarite){
                    holder.ibFavourite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
//                    favorites.unfavarite(holder.BookName.getText().toString());
                    Log.d("bbbooks",  favorites.unfavarite(holder.BookName.getText().toString()));

                }else{
                    holder.ibFavourite.setImageResource(R.drawable.ic_baseline_favorite_2222);
//                    favorites.Favarite(holder.BookName.getText().toString());
                }
                isfavarite=!isfavarite;

            }
        });




    }

    @Override
    public int getItemCount() {
        return NameBook.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder{
        TextView BookName,bookYear;
        ImageView ibFavourite;

        public FavoriteViewHolder(FavertitemBinding binding) {
            super(binding.getRoot());
            BookName=binding.nameBook;
            bookYear=binding.bookYear;
ibFavourite=binding.ibFavourite;
        }
    }
}
