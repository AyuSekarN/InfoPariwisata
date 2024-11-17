package com.example.infopariwisata;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class KulinerActivity extends AppCompatActivity {

    private TextView textViewLocationDetail, kuliner1Name, kuliner1Desc, kuliner2Name, kuliner2Desc;
    private CardView cardImageKuliner1, cardImageKuliner2, cardImageKuliner3, cardImageKuliner4;
    private ImageView imageKuliner1, imageKuliner2, imageKuliner3, imageKuliner4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuliner);

        // Inisialisasi Views
        textViewLocationDetail = findViewById(R.id.textViewLocationKuliner);
        kuliner1Name = findViewById(R.id.kuliner1Name);
        kuliner1Desc = findViewById(R.id.kuliner1Desc);
        kuliner2Name = findViewById(R.id.kuliner2Name);
        kuliner2Desc = findViewById(R.id.kuliner2Desc);

        cardImageKuliner1 = findViewById(R.id.cardKuliner1);
        cardImageKuliner2 = findViewById(R.id.cardKuliner2);
        cardImageKuliner3 = findViewById(R.id.cardKuliner3);
        cardImageKuliner4 = findViewById(R.id.cardKuliner4);

        imageKuliner1 = findViewById(R.id.imageKuliner1);
        imageKuliner2 = findViewById(R.id.imageKuliner2);
        imageKuliner3 = findViewById(R.id.imageKuliner3);
        imageKuliner4 = findViewById(R.id.imageKuliner4);

        // Ambil data lokasi dari Intent
        String lokasiWisata = getIntent().getStringExtra("lokasi_wisata");

        // Set lokasi di TextView
        textViewLocationDetail.setText(lokasiWisata);

        // Tampilkan kuliner berdasarkan lokasi
        showKulinerBasedOnLocation(lokasiWisata);

        // Tambahkan animasi
        setAnimations();

        Button kembaliButton = findViewById(R.id.kembali_button);
        kembaliButton.setOnClickListener(v -> {
            Intent intent = new Intent(KulinerActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    // Fungsi untuk menambahkan animasi
    private void setAnimations() {
        Animation cardPopIn = AnimationUtils.loadAnimation(this, R.anim.card_pop_in);
        Animation fadeInText = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Animasi untuk CardView
        cardImageKuliner1.startAnimation(cardPopIn);
        cardImageKuliner2.startAnimation(cardPopIn);
        cardImageKuliner3.startAnimation(cardPopIn);
        cardImageKuliner4.startAnimation(cardPopIn);

        // Animasi untuk TextView
        textViewLocationDetail.startAnimation(fadeInText);
        kuliner1Name.startAnimation(fadeInText);
        kuliner1Desc.startAnimation(fadeInText);
        kuliner2Name.startAnimation(fadeInText);
        kuliner2Desc.startAnimation(fadeInText);
    }


        private void showKulinerBasedOnLocation(String lokasiWisata) {
        if (lokasiWisata == null) return;

        switch (lokasiWisata) {
            case "Bali":
                // Kuliner Bali
                kuliner1Name.setText("Babi Guling");
                kuliner1Desc.setText("Babi Guling adalah hidangan tradisional Bali yang terbuat dari daging babi yang dibumbui " +
                        "dengan rempah-rempah khas Bali seperti kunyit dan ketumbar, lalu dipanggang hingga kulitnya renyah dan " +
                        "dagingnya empuk. Hidangan ini biasanya disajikan dengan nasi, lawar, sambal, dan sayuran, dan sering " +
                        "dinikmati saat perayaan atau upacara adat.");
                kuliner2Name.setText("Ayam Betutu");
                kuliner2Desc.setText("Ayam Betutu adalah ayam yang dimasak dengan bumbu Bali, seperti bawang, kunyit, dan jahe, " +
                        "kemudian dibungkus daun pisang dan dimasak perlahan hingga empuk. Hidangan ini disajikan dengan " +
                        "nasi dan sambal, serta sering hadir dalam acara adat atau perayaan di Bali.");
                imageKuliner1.setImageResource(R.drawable.babi_guling);
                imageKuliner2.setImageResource(R.drawable.bebek_betutu);
                imageKuliner3.setImageResource(R.drawable.babi_guling2);
                imageKuliner4.setImageResource(R.drawable.bebek_betutu2);
                break;

            case "Yogyakarta":
                kuliner1Name.setText("Gudeg");
                kuliner1Desc.setText("Gudeg adalah hidangan khas Yogyakarta yang terbuat dari nangka muda yang dimasak perlahan " +
                        "dengan santan dan campuran rempah seperti bawang merah, bawang putih, daun salam, dan lengkuas. " +
                        "Hidangan ini memiliki rasa manis yang khas dan biasanya disajikan dengan nasi, ayam opor, telur pindang, " +
                        "tahu, tempe, serta sambal goreng krecek, menjadikannya menu istimewa di kota ini.");
                kuliner2Name.setText("Bakpia");
                kuliner2Desc.setText("Bakpia adalah kue khas Yogyakarta yang terbuat dari adonan tepung berisi kacang hijau manis " +
                        "atau varian lain seperti cokelat, keju, dan durian. Kue ini dipanggang hingga kulitnya renyah dan lembut di dalam. " +
                        "Bakpia sering dijadikan oleh-oleh favorit wisatawan yang berkunjung ke Yogyakarta.");
                imageKuliner1.setImageResource(R.drawable.gudeg);
                imageKuliner2.setImageResource(R.drawable.bakpia);
                imageKuliner3.setImageResource(R.drawable.gudeg2);
                imageKuliner4.setImageResource(R.drawable.bakpia2);
                break;

            case "Jawa Tengah":
                kuliner1Name.setText("Sate Kelinci");
                kuliner1Desc.setText("Sate Kelinci adalah sate yang terbuat dari daging kelinci yang dipanggang dengan bumbu kacang " +
                        "khas Jawa Tengah. Daging kelinci yang empuk dan gurih ini disajikan dengan lontong atau nasi putih dan sambal " +
                        "pedas. Hidangan ini sangat populer di daerah Jawa Tengah dan sering disajikan pada acara spesial atau hari biasa.");
                kuliner2Name.setText("Lontong Balap");
                kuliner2Desc.setText("Lontong Balap adalah hidangan khas Surabaya yang terdiri dari lontong dengan kuah bening, tahu, lentho " +
                        "(bakwan jagung), taoge, sambal, dan kecap manis. Rasanya gurih dan sedikit pedas. Lontong Balap menjadi hidangan yang sangat " +
                        "digemari oleh warga Surabaya dan wisatawan sebagai makanan utama saat makan siang.");
                imageKuliner1.setImageResource(R.drawable.sate_kelinci);
                imageKuliner2.setImageResource(R.drawable.lontong_balap);
                imageKuliner3.setImageResource(R.drawable.sate_kelinci2);
                imageKuliner4.setImageResource(R.drawable.lontong_balap2);
                break;

            case "Jawa Timur":
                kuliner1Name.setText("Rujak Cingur");
                kuliner1Desc.setText("Rujak Cingur adalah hidangan khas Surabaya yang terdiri dari sayuran segar, tahu, tempe, dan cingur " +
                        "(bagian mulut sapi) dicampur dengan bumbu petis hitam. Rasa pedas, manis, dan gurihnya memberikan sensasi unik, " +
                        "menjadikannya makanan favorit di Surabaya dan sering dinikmati pada acara keluarga dan perayaan.");
                kuliner2Name.setText("Rawon");
                kuliner2Desc.setText("Rawon adalah sup daging sapi khas Jawa Timur yang kaya rempah, terutama kluwek, yang memberikan warna " +
                        "hitam pada kuahnya. Rasanya gurih, pedas, dan sedikit manis. Rawon biasanya disajikan dengan nasi putih, tauge, sambal, " +
                        "dan pelengkap lainnya. Hidangan ini sangat digemari di Jawa Timur, terutama saat makan siang.");
                imageKuliner1.setImageResource(R.drawable.rujak_cingur);
                imageKuliner2.setImageResource(R.drawable.rawon);
                imageKuliner3.setImageResource(R.drawable.rujak_cingur2);
                imageKuliner4.setImageResource(R.drawable.rawon2);
                break;

            case "Nusa Tenggara Timur":
                kuliner1Name.setText("Ayam Taliwang");
                kuliner1Desc.setText("Ayam Taliwang adalah ayam bakar khas Lombok yang dibumbui dengan bumbu pedas dan manis dari rempah khas " +
                        "daerah tersebut. Ayam ini dipanggang hingga empuk dan disajikan dengan nasi putih, sambal terasi, dan lalapan. Hidangan " +
                        "ini sangat populer di Lombok dan sekitarnya, sering disantap dalam acara keluarga dan perayaan.");
                kuliner2Name.setText("Se'i Sapi");
                kuliner2Desc.setText("Se'i Sapi adalah daging sapi asap khas Nusa Tenggara Timur yang memiliki rasa gurih dan sedikit smoky. Daging " +
                        "sapi ini disajikan dengan nasi putih, sambal, dan sayuran. Hidangan ini sangat digemari oleh masyarakat lokal dan wisatawan " +
                        "yang berkunjung, menjadi sajian khas yang populer di daerah tersebut.");
                imageKuliner1.setImageResource(R.drawable.ayam_taliwang);
                imageKuliner2.setImageResource(R.drawable.sei_sapi);
                imageKuliner3.setImageResource(R.drawable.ayam_taliwang2);
                imageKuliner4.setImageResource(R.drawable.sei_sapi2);
                break;

            case "Sumatera Utara":
                kuliner1Name.setText("Babi Panggang Karo");
                kuliner1Desc.setText("Babi Panggang Karo adalah hidangan khas Sumatera Utara yang terbuat dari daging babi yang dipanggang dengan " +
                        "bumbu khas Karo. Hidangan ini memiliki rasa gurih, sedikit pedas, dan disajikan dengan nasi putih, sambal, serta sayuran. Babi " +
                        "Panggang Karo menjadi salah satu hidangan favorit di kalangan masyarakat Batak.");
                kuliner2Name.setText("Mie Gomak");
                kuliner2Desc.setText("Mie Gomak adalah mie khas Batak yang memiliki tekstur lebih kenyal. Mie ini disajikan dengan kuah kental yang kaya " +
                        "rempah, biasanya dilengkapi dengan daging sapi atau ayam. Mie Gomak sangat populer di Sumatera Utara, terutama di Medan, dan sering " +
                        "disantap sebagai hidangan makan siang atau malam yang memuaskan.");
                imageKuliner1.setImageResource(R.drawable.babi_panggang_karo);
                imageKuliner2.setImageResource(R.drawable.mie_gomak);
                imageKuliner3.setImageResource(R.drawable.babi_panggang_karo2);
                imageKuliner4.setImageResource(R.drawable.mie_gomak2);
                break;

            case "Papua Barat":
                kuliner1Name.setText("Papeda");
                kuliner1Desc.setText("Papeda adalah makanan khas Papua yang terbuat dari sagu. Makanan ini memiliki tekstur kental seperti bubur dan biasanya " +
                        "disajikan dengan kuah ikan yang gurih. Papeda sering dianggap sebagai makanan pokok masyarakat Papua dan merupakan sajian tradisional " +
                        "yang menyatukan rasa khas daerah Papua dengan cita rasa laut yang segar.");
                kuliner2Name.setText("Ikan Bakar Manokwari");
                kuliner2Desc.setText("Ikan Bakar Manokwari adalah ikan yang dibakar dengan bumbu khas Papua. Ikan yang dipilih biasanya adalah ikan laut yang " +
                        "segar, dibumbui dengan rempah lokal dan dibakar hingga matang sempurna. Rasanya pedas dan gurih, sangat cocok disantap dengan sambal " +
                        "pedas dan nasi putih, menjadikannya hidangan yang populer di Papua Barat.");
                imageKuliner1.setImageResource(R.drawable.papeda);
                imageKuliner2.setImageResource(R.drawable.ikan_bakar_manokwari);
                imageKuliner3.setImageResource(R.drawable.papeda2);
                imageKuliner4.setImageResource(R.drawable.ikan_bakar_manokwari2);
                break;

            case "Kalimantan Timur":
                kuliner1Name.setText("Sate Bandeng");
                kuliner1Desc.setText("Sate Bandeng adalah sate khas Kalimantan Timur yang terbuat dari ikan bandeng. Ikan ini dibumbui dengan rempah khas, " +
                        "lalu dibakar hingga matang. Rasanya gurih dan sedikit pedas. Sate Bandeng biasanya disajikan dengan nasi putih dan sambal, dan menjadi " +
                        "hidangan yang sangat populer di daerah ini, terutama pada acara-acara adat atau perayaan.");
                kuliner2Name.setText("Ayam Penyet Samarinda");
                kuliner2Desc.setText("Ayam Penyet Samarinda adalah ayam goreng yang disajikan dengan sambal terasi khas Samarinda. Ayam yang digoreng garing ini " +
                        "memiliki rasa yang renyah dan empuk. Disajikan dengan nasi putih, sambal pedas, dan lalapan, hidangan ini sangat menggugah selera dan " +
                        "menjadi favorit di Kalimantan Timur, terutama untuk makan siang.");
                imageKuliner1.setImageResource(R.drawable.sate_bandeng);
                imageKuliner2.setImageResource(R.drawable.ayam_penyet_samarinda);
                imageKuliner3.setImageResource(R.drawable.sate_bandeng2);
                imageKuliner4.setImageResource(R.drawable.ayam_penyet_samarinda2);
                break;

            case "Nusa Tenggara Barat":
                kuliner1Name.setText("Ayam Penyet Lombok");
                kuliner1Desc.setText("Ayam Penyet Lombok adalah ayam yang digoreng hingga garing, lalu dipenyet dan disajikan dengan sambal khas Lombok yang pedas " +
                        "dan gurih. Rasanya yang pedas segar dan renyah sangat menggugah selera. Hidangan ini biasa disajikan dengan nasi putih dan lalapan, menjadi " +
                        "favorit masyarakat Lombok, baik di warung makan maupun acara keluarga.");
                kuliner2Name.setText("Nasi Campur Sasak");
                kuliner2Desc.setText("Nasi Campur Sasak adalah hidangan khas Lombok yang terdiri dari nasi putih yang disajikan dengan berbagai lauk-pauk, seperti " +
                        "ayam, telur, sambal, dan sayuran. Setiap komponen dalam nasi campur ini memberikan rasa yang gurih, pedas, dan segar, menjadikannya salah " +
                        "satu hidangan utama yang sangat populer di Lombok, terutama saat makan siang.");
                imageKuliner1.setImageResource(R.drawable.ayam_penyet_lombok);
                imageKuliner2.setImageResource(R.drawable.nasi_campur_sasak);
                imageKuliner3.setImageResource(R.drawable.ayam_penyet_lombok2);
                imageKuliner4.setImageResource(R.drawable.nasi_campur_sasak2);
                break;

            case "Kepulauan Bangka Belitung":
                kuliner1Name.setText("Mie Bangka");
                kuliner1Desc.setText("Mie Bangka adalah mie kuning khas dari Bangka Belitung yang disajikan dengan kuah kaldu yang gurih, terbuat dari ayam atau " +
                        "seafood. Hidangan ini memiliki rasa yang kaya dengan bumbu rempah, dan biasanya dilengkapi dengan telur, ayam suwir, serta taburan bawang " +
                        "goreng. Mie Bangka adalah makanan yang banyak dijumpai di berbagai warung makan di Bangka Belitung.");
                kuliner2Name.setText("Lempah Kuning");
                kuliner2Desc.setText("Lempah Kuning adalah hidangan khas Bangka Belitung yang terbuat dari ikan laut segar, seperti ikan tenggiri atau ikan kerapu, " +
                        "yang dimasak dengan kuah kuning berbumbu rempah khas, termasuk kunyit, cabai, dan asam. Rasanya gurih, pedas, dan segar, menjadikannya " +
                        "salah satu hidangan wajib yang populer di daerah ini.");
                imageKuliner1.setImageResource(R.drawable.mie_bangka);
                imageKuliner2.setImageResource(R.drawable.lempah_kuning);
                imageKuliner3.setImageResource(R.drawable.mie_bangka2);
                imageKuliner4.setImageResource(R.drawable.lempah_kuning2);
                break;

            case "Sulawesi Tenggara":
                kuliner1Name.setText("Coto Makassar");
                kuliner1Desc.setText("Coto Makassar adalah hidangan sup daging sapi khas Sulawesi Selatan yang terkenal dengan kuah kental berbumbu rempah yang kaya. " +
                        "Daging sapi dimasak lama hingga empuk, lalu disajikan dengan ketupat atau nasi putih. Coto Makassar memiliki cita rasa gurih, pedas, dan " +
                        "sedikit manis, menjadi salah satu hidangan favorit di Makassar.");
                kuliner2Name.setText("Konro");
                kuliner2Desc.setText("Konro adalah hidangan khas Sulawesi Tenggara yang terbuat dari iga sapi yang dimasak dengan bumbu rempah khas. " +
                        "Iga sapi yang dimasak lama hingga empuk disajikan dengan kuah kental yang memiliki cita rasa gurih dan sedikit manis. Hidangan ini biasanya " +
                        "disajikan dengan nasi putih dan sambal, memberikan pengalaman makan yang kaya rasa dan nikmat.");
                imageKuliner1.setImageResource(R.drawable.coto_makassar);
                imageKuliner2.setImageResource(R.drawable.konro);
                imageKuliner3.setImageResource(R.drawable.coto_makassar2);
                imageKuliner4.setImageResource(R.drawable.konro2);
                break;


            default:
                kuliner1Name.setText("Kuliner khas belum tersedia untuk lokasi ini.");
                kuliner1Desc.setText("");
                kuliner2Name.setText("");
                kuliner2Desc.setText("");
                imageKuliner1.setImageResource(R.drawable.placeholder);
                imageKuliner2.setImageResource(R.drawable.placeholder);
                break;
        }
    }
}




/*public class KulinerActivity extends AppCompatActivity {

    private TextView textViewLocationDetail, kuliner1Name, kuliner1Desc, kuliner2Name, kuliner2Desc;
    private ImageView imageKuliner1, imageKuliner2, imageKuliner3, imageKuliner4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuliner);

        // Inisialisasi Views
        textViewLocationDetail = findViewById(R.id.textViewLocationKuliner);
        kuliner1Name = findViewById(R.id.kuliner1Name);
        kuliner1Desc = findViewById(R.id.kuliner1Desc);
        kuliner2Name = findViewById(R.id.kuliner2Name);
        kuliner2Desc = findViewById(R.id.kuliner2Desc);
        imageKuliner1 = findViewById(R.id.imageKuliner1);
        imageKuliner2 = findViewById(R.id.imageKuliner2);
        imageKuliner3 = findViewById(R.id.imageKuliner3);
        imageKuliner4 = findViewById(R.id.imageKuliner4);

        // Ambil data lokasi dari Intent
        String lokasiWisata = getIntent().getStringExtra("lokasi_wisata");

        // Set lokasi di TextView
        textViewLocationDetail.setText(lokasiWisata);

        // Tampilkan kuliner berdasarkan lokasi
        showKulinerBasedOnLocation(lokasiWisata);
    }*/

