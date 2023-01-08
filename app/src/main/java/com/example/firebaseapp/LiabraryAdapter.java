package com.example.firebaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseapp.databinding.LibraryitemBinding;

import java.util.ArrayList;

public class LiabraryAdapter extends RecyclerView.Adapter<LiabraryAdapter.LibraryViewHolder> {
    ArrayList<CreateLibraryClass> arrayList;
    Context context;

    public LiabraryAdapter(ArrayList<CreateLibraryClass> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public LibraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LibraryitemBinding binding=LibraryitemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new LibraryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryViewHolder holder, int position) {
       CreateLibraryClass library=  arrayList.get(position);
       holder.textView.setText(library.getCategoryName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class LibraryViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public LibraryViewHolder(LibraryitemBinding binding) {
            super(binding.getRoot());
            textView=binding.categoryName;
        }
    }
}
