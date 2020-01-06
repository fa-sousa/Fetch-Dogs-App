package com.example.fetchdogsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fetchdogsapp.R;
import com.example.fetchdogsapp.holder.DogsHolder;
import com.example.fetchdogsapp.models.Dogs;
import com.squareup.picasso.Picasso;
import java.util.Collections;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogsHolder> {

    List<Dogs> list = Collections.emptyList();
    Context context;

    public DogAdapter(List<Dogs> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public DogsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_dogs_row_layout, parent, false);

        return new DogsHolder(view);
    }

    @Override
    public void onBindViewHolder(DogsHolder holder, int position) {

        holder.txtName.setText(list.get(position).getName());
        //Picasso.get().load(list.get(position).getProfilePic()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.imgDog);

    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Dogs data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Dogs data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }
}

