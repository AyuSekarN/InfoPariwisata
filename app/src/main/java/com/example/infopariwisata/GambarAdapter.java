package com.example.infopariwisata;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GambarAdapter extends RecyclerView.Adapter<GambarAdapter.GambarViewHolder> {

    private int[] imageResIds;

    public GambarAdapter(int[] imageResIds) {
        this.imageResIds = imageResIds;
    }

    @NonNull
    @Override
    public GambarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new GambarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GambarViewHolder holder, int position) {
        // Set gambar untuk posisi yang sesuai
        int imageResId = imageResIds[position];
        holder.imageView.setImageResource(imageResId);
    }

    @Override
    public int getItemCount() {
        return imageResIds.length;
    }

    public static class GambarViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public GambarViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewPager);
        }
    }
}
