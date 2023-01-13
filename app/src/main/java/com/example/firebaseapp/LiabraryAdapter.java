package com.example.firebaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseapp.databinding.LibraryitemBinding;

import java.text.BreakIterator;
import java.util.ArrayList;

public class LiabraryAdapter extends RecyclerView.Adapter<LiabraryAdapter.LibraryViewHolder> {
    ArrayList<CreateBookClass> arrayList;
    Context context;
    Details details;
    boolean isEmpty;
    Favorite favorite;
    boolean isfavarite;

    public LiabraryAdapter(ArrayList<CreateBookClass> arrayList, Context context, Details details) {
        this.arrayList = arrayList;
        this.context = context;
        this.details=details;

    }

    @NonNull
    @Override
    public LibraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LibraryitemBinding binding=LibraryitemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new LibraryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryViewHolder holder, int position) {
        int pos=position;
       CreateBookClass library=  arrayList.get(position);
       holder.textView.setText(library.getBookName());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               details.OnClike(pos);
           }
       });

        if(isfavarite){
            holder.Favet.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            favorite.unfavarite(holder.textView.getText().toString());

        }else{
            holder.Favet.setImageResource(R.drawable.ic_baseline_favorite_2222);
            favorite.Favarite(holder.textView.getText().toString());
        }
        isfavarite=!isfavarite;

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class LibraryViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView Favet,unfav;


        public LibraryViewHolder(LibraryitemBinding binding) {
            super(binding.getRoot());
            textView=binding.nameBook;
            unfav=binding.ibunFavourite;



        }
    }
}
