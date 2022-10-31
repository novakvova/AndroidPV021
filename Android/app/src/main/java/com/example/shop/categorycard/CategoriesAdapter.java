package com.example.shop.categorycard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shop.application.HomeApplication;
import com.example.shop.constants.Urls;
import com.example.shop.dto.categories.CategoryItemDTO;
import com.example.shop.R;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryCardViewHolder> {
    private List<CategoryItemDTO> categories;
    public CategoriesAdapter(List<CategoryItemDTO> categories) { this.categories=categories; }

    @NonNull
    @Override
    public CategoryCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_view, parent,false);
        return new CategoryCardViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryCardViewHolder holder, int position) {
        if(categories!=null&&position<categories.size())
        {
            CategoryItemDTO category = categories.get(position);
            holder.categoryname.setText(category.getName());
            String url = Urls.BASE + category.getImage();
            Glide.with(HomeApplication.getAppContext())
                    .load(url)
                    .apply(new RequestOptions().override(600))
                    .into(holder.categoryimage);
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
