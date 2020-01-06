package com.example.fetchdogsapp.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fetchdogsapp.R;

    public class DogsHolder extends RecyclerView.ViewHolder {

        public CardView cardDog;
        public TextView txtName;
        public ImageView imgDog;

        public DogsHolder(View itemView) {
            super(itemView);
            cardDog = (CardView) itemView.findViewById(R.id.cardDog);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            imgDog = (ImageView) itemView.findViewById(R.id.imgDog);
        }
    }
