package com.example.infopariwisata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    // Durasi splash screen (misalnya 3 detik)
    private static final int SPLASH_DURATION = 3000; // 3000 ms = 3 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);  // Mengatur layout untuk SplashActivity

        // Dapatkan elemen ImageView untuk logo atau elemen lain yang ingin dianimasikan
        ImageView logo = findViewById(R.id.logo);  // Pastikan ID ini ada di layout

        // Muat animasi dari XML
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_zoom_in);

        // Mulai animasi pada logo
        logo.startAnimation(animation);

        // Handler untuk menunda transisi ke halaman utama
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                // Menghentikan SplashActivity agar tidak kembali ke halaman ini
            }
        }, SPLASH_DURATION);  // Tunda selama 3 detik sebelum beralih ke MainActivity
    }
}
