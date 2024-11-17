package com.example.infopariwisata;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.WisataViewHolder> implements Filterable {

    private List<Wisata> wisataList;
    private List<Wisata> wisataListFull;
    private Context context;
    private static final int SWITCH_DELAY = 5000; // Mengganti waktu delay jadi lebih singkat (misalnya 5 detik)

    public WisataAdapter(List<Wisata> wisataList, Context context) {
        this.wisataList = wisataList;
        this.context = context;
        this.wisataListFull = new ArrayList<>(wisataList);
    }

    @NonNull
    @Override
    public WisataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wisata, parent, false);
        return new WisataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WisataViewHolder holder, int position) {
        Wisata wisata = wisataList.get(position);
        holder.bind(wisata);

        // Tambahkan listener untuk item yang diklik
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("wisata_name", wisata.getName());
            intent.putExtra("wisata_location", wisata.getLocation());
            intent.putExtra("wisata_description", wisata.getDescription());  // Mengirimkan deskripsi
            intent.putExtra("wisata_images", wisata.getImageResIds());
            context.startActivity(intent);
        });

        // Set listener untuk tombol like
        holder.btnLike.setOnClickListener(v -> {
            boolean isLiked = !wisata.isLiked();
            wisata.setLiked(isLiked); // Update status like

            if (isLiked) {
                holder.btnLike.setImageResource(R.drawable.ic_liked);  // Hati terisi
            } else {
                holder.btnLike.setImageResource(R.drawable.ic_like);  // Hati kosong
            }
        });
    }

    @Override
    public int getItemCount() {
        return wisataList.size();
    }

    @Override
    public Filter getFilter() {
        return wisataFilter;
    }

    private final Filter wisataFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Wisata> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(wisataListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Wisata item : wisataListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern) ||
                            item.getLocation().toLowerCase().contains(filterPattern)) {  // Cari berdasarkan lokasi juga
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            wisataList.clear();
            wisataList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class WisataViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewLocation;
        ImageView imageView;
        ImageButton btnLike;  // Tambahkan tombol like
        private int currentImageIndex = 0;
        private final Handler imageHandler = new Handler();

        public WisataViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            imageView = itemView.findViewById(R.id.imageView);
            btnLike = itemView.findViewById(R.id.btnLike);  // Inisialisasi tombol like
        }

        public void bind(Wisata wisata) {
            textViewName.setText(wisata.getName());
            textViewLocation.setText(wisata.getLocation());

            // Set ikon like sesuai status like
            if (wisata.isLiked()) {
                btnLike.setImageResource(R.drawable.ic_liked);  // Hati terisi
            } else {
                btnLike.setImageResource(R.drawable.ic_like);  // Hati kosong
            }

            if (wisata.getImageResIds() != null && wisata.getImageResIds().length > 0) {
                updateImage(wisata);

                // Hapus callback sebelumnya untuk menghindari tumpang tindih.
                imageHandler.removeCallbacksAndMessages(null);

                // Atur rotasi gambar menggunakan Handler untuk item ini.
                imageHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentImageIndex = (currentImageIndex + 1) % wisata.getImageResIds().length;
                        updateImage(wisata);
                        imageHandler.postDelayed(this, SWITCH_DELAY);
                    }
                }, SWITCH_DELAY);
            }
        }

        private void updateImage(Wisata wisata) {
            int nextImageResId = wisata.getImageResIds()[currentImageIndex];

            ObjectAnimator fadeOut = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f);
            fadeOut.setDuration(1000);
            fadeOut.addListener(new android.animation.AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    imageView.setImageResource(nextImageResId);

                    ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
                    fadeIn.setDuration(1000);
                    fadeIn.start();
                }
            });
            fadeOut.start();
        }
    }
}


/*
package com.example.infopariwisata;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.WisataViewHolder> implements Filterable {

    private List<Wisata> wisataList;
    private List<Wisata> wisataListFull;
    private Context context;
    private static final int SWITCH_DELAY = 5000; // Mengganti waktu delay jadi lebih singkat (misalnya 5 detik)

    public WisataAdapter(List<Wisata> wisataList, Context context) {
        this.wisataList = wisataList;
        this.context = context;
        this.wisataListFull = new ArrayList<>(wisataList);
    }

    @NonNull
    @Override
    public WisataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wisata, parent, false);
        return new WisataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WisataViewHolder holder, int position) {
        Wisata wisata = wisataList.get(position);
        holder.bind(wisata);

        // Tambahkan listener untuk item yang diklik
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("wisata_name", wisata.getName());
            intent.putExtra("wisata_location", wisata.getLocation());
            intent.putExtra("wisata_description", wisata.getDescription());  // Mengirimkan deskripsi
            intent.putExtra("wisata_images", wisata.getImageResIds());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return wisataList.size();
    }

    @Override
    public Filter getFilter() {
        return wisataFilter;
    }

    private final Filter wisataFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Wisata> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(wisataListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Wisata item : wisataListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern) ||
                            item.getLocation().toLowerCase().contains(filterPattern)) {  // Cari berdasarkan lokasi juga
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            wisataList.clear();
            wisataList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class WisataViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewLocation;
        ImageView imageView;
        private int currentImageIndex = 0;
        private final Handler imageHandler = new Handler();

        public WisataViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(Wisata wisata) {
            textViewName.setText(wisata.getName());
            textViewLocation.setText(wisata.getLocation());

            if (wisata.getImageResIds() != null && wisata.getImageResIds().length > 0) {
                updateImage(wisata);

                // Hapus callback sebelumnya untuk menghindari tumpang tindih.
                imageHandler.removeCallbacksAndMessages(null);

                // Atur rotasi gambar menggunakan Handler untuk item ini.
                imageHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentImageIndex = (currentImageIndex + 1) % wisata.getImageResIds().length;
                        updateImage(wisata);
                        imageHandler.postDelayed(this, SWITCH_DELAY);
                    }
                }, SWITCH_DELAY);
            }
        }

        private void updateImage(Wisata wisata) {
            int nextImageResId = wisata.getImageResIds()[currentImageIndex];

            ObjectAnimator fadeOut = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f);
            fadeOut.setDuration(1000);
            fadeOut.addListener(new android.animation.AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    imageView.setImageResource(nextImageResId);

                    ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
                    fadeIn.setDuration(1000);
                    fadeIn.start();
                }
            });
            fadeOut.start();
        }
    }
}
*/
