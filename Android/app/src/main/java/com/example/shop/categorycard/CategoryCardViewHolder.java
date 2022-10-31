package com.example.shop.categorycard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shop.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CategoryCardViewHolder extends RecyclerView.ViewHolder {
    private View view;
    public ImageView categoryimage;
    public TextView categoryname;

    public CategoryCardViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        categoryimage=itemView.findViewById(R.id.categoryimage);
        categoryname=itemView.findViewById(R.id.categoryname);
    }
    public View getView() { return view; }
}
