package com.example.infopariwisata;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WisataAdapter wisataAdapter;
    private List<Wisata> wisataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        SearchView searchView = findViewById(R.id.searchView);

        // Siapkan daftar tempat wisata dengan array gambar
        wisataList = new ArrayList<>();
        wisataList.add(new Wisata("Pantai Kuta", "Bali", new int[]{R.drawable.pantai_kuta1, R.drawable.pantai_kuta2, R.drawable.pantai_kuta3}));
        wisataList.add(new Wisata("Candi Borobudur", "Jawa Tengah", new int[]{R.drawable.candi_borobudur1, R.drawable.candi_borobudur2, R.drawable.candi_borobudur3}));
        wisataList.add(new Wisata("Gunung Bromo", "Jawa Timur", new int[]{R.drawable.gunung_bromo1, R.drawable.gunung_bromo2, R.drawable.gunung_bromo3}));
        wisataList.add(new Wisata("Taman Nasional Komodo", "Nusa Tenggara Timur", new int[]{R.drawable.taman_komodo1, R.drawable.taman_komodo2, R.drawable.taman_komodo3}));
        wisataList.add(new Wisata("Danau Toba", "Sumatera Utara", new int[]{R.drawable.danau_toba1, R.drawable.danau_toba2, R.drawable.danau_toba3}));
        wisataList.add(new Wisata("Raja Ampat", "Papua Barat", new int[]{R.drawable.raja_ampat1, R.drawable.raja_ampat2, R.drawable.raja_ampat3}));
        wisataList.add(new Wisata("Pantai Parangtritis", "Yogyakarta", new int[]{R.drawable.pantai_parangtritis1, R.drawable.pantai_parangtritis2, R.drawable.pantai_parangtritis3}));
        wisataList.add(new Wisata("Kawah Ijen", "Jawa Timur", new int[]{R.drawable.kawah_ijen1, R.drawable.kawah_ijen2, R.drawable.kawah_ijen3}));
        wisataList.add(new Wisata("Pulau Derawan", "Kalimantan Timur", new int[]{R.drawable.pulau_derawan1, R.drawable.pulau_derawan2, R.drawable.pulau_derawan3}));
        wisataList.add(new Wisata("Labuan Bajo", "Nusa Tenggara Timur", new int[]{R.drawable.labuan_bajo1, R.drawable.labuan_bajo2, R.drawable.labuan_bajo3}));
        wisataList.add(new Wisata("Candi Prambanan", "Yogyakarta", new int[]{R.drawable.candi_prambanan1, R.drawable.candi_prambanan2, R.drawable.candi_prambanan3}));
        wisataList.add(new Wisata("Gunung Rinjani", "Nusa Tenggara Barat", new int[]{R.drawable.gunung_rinjani1, R.drawable.gunung_rinjani2, R.drawable.gunung_rinjani3}));
        wisataList.add(new Wisata("Pulau Belitung", "Kepulauan Bangka Belitung", new int[]{R.drawable.pulau_belitung1, R.drawable.pulau_belitung2, R.drawable.pulau_belitung3}));
        wisataList.add(new Wisata("Danau Kelimutu", "Nusa Tenggara Timur", new int[]{R.drawable.danau_kelimutu1, R.drawable.danau_kelimutu2, R.drawable.danau_kelimutu3}));
        wisataList.add(new Wisata("Wakatobi", "Sulawesi Tenggara", new int[]{R.drawable.wakatobi1, R.drawable.wakatobi2, R.drawable.wakatobi3}));

        Toolbar toolbar = findViewById(R.id.toolbar);

        // Pasang adapter ke RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wisataAdapter = new WisataAdapter(wisataList, this);
        recyclerView.setAdapter(wisataAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                wisataAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                wisataAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}


