package com.example.infopariwisata;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager2.widget.ViewPager2;

public class DetailActivity extends AppCompatActivity {

    private TextView textViewName, textViewLocation, textViewDescription;
    private ViewPager2 viewPagerImages;
    private GambarAdapter gambarAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Inisialisasi view
        textViewName = findViewById(R.id.textViewNameDetail);
        textViewLocation = findViewById(R.id.textViewLocationDetail);
        textViewDescription = findViewById(R.id.textViewDescriptionDetail);
        viewPagerImages = findViewById(R.id.viewPagerImages);

        // Ambil data dari Intent
        String wisataName = getIntent().getStringExtra("wisata_name");
        String wisataLocation = getIntent().getStringExtra("wisata_location");
        int[] wisataImages = getIntent().getIntArrayExtra("wisata_images");

        // Set nama dan lokasi
        textViewName.setText(wisataName);
        textViewLocation.setText(wisataLocation);

        // Dapatkan deskripsi berdasarkan nama wisata
        String wisataDescription = getPlaceDescription(wisataName);
        textViewDescription.setText(wisataDescription);

        // Set adapter untuk ViewPager2 jika ada gambar
        if (wisataImages != null && wisataImages.length > 0) {
            gambarAdapter = new GambarAdapter(wisataImages);
            viewPagerImages.setAdapter(gambarAdapter);
        }

// Menambahkan animasi fade-in untuk ViewPager2 dan elemen lainnya
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        CardView cardViewPager = findViewById(R.id.cardViewPager);
        TextView textViewName = findViewById(R.id.textViewNameDetail);
        TextView textViewLocation = findViewById(R.id.textViewLocationDetail);
        TextView textViewDescription = findViewById(R.id.textViewDescriptionDetail);

        cardViewPager.startAnimation(fadeInAnimation);
        textViewName.startAnimation(fadeInAnimation);
        textViewLocation.startAnimation(fadeInAnimation);
        textViewDescription.startAnimation(fadeInAnimation);

        // Menampilkan Toast dengan gaya kustom
        showCustomToast();

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Opsional: Menutup DetailActivity setelah berpindah ke MainActivity
        });

        // Menambahkan action untuk tombol Kuliner & Akomodasi
        Button kulinerAkomodasiButton = findViewById(R.id.buttonKulinerAkomodasi);
        kulinerAkomodasiButton.setOnClickListener(v -> {
            String lokasiWisata = textViewLocation.getText().toString();
            Intent intent = new Intent(DetailActivity.this, KulinerActivity.class);
            intent.putExtra("lokasi_wisata", lokasiWisata); // Kirim lokasi
            startActivity(intent);
        });
    }

    private void showCustomToast() {
        // Membuat custom Toast dengan layout kustom
        Toast toast = new Toast(getApplicationContext());
        TextView text = new TextView(getApplicationContext());
        text.setText("Temukan keajaiban di setiap sudut destinasi ini! ^^");

        // Mengatur warna teks menjadi DBDBDD (abu-abu terang)
        text.setTextColor(Color.parseColor("#DBDBDD")); // Set warna teks sesuai dengan #DBDBDD

        text.setPadding(30, 20, 30, 20);
        text.setTypeface(ResourcesCompat.getFont(this, R.font.montserrat_regular)); // Set font Montserrat Regular
        text.setTypeface(text.getTypeface(), android.graphics.Typeface.ITALIC); // Set italic

        // Membuat background Toast dengan opacity 80% dan warna hitam
        float[] radii = new float[8]; // Untuk sudut rounded
        for (int i = 0; i < 8; i++) {
            radii[i] = 50f; // Radius sudut
        }
        ShapeDrawable shape = new ShapeDrawable(new RoundRectShape(radii, null, null));
        shape.getPaint().setColor(Color.argb(200, 0, 0, 0)); // Opacity 80% dengan warna hitam (RGBA)

        // Set background Toast
        text.setBackground(shape);

        // Menambahkan animasi dan posisikan di tengah
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(text);
        toast.show();
    }

// Metode untuk mendapatkan deskripsi tempat wisata berdasarkan nama
    private String getPlaceDescription(String placeName) {
        switch (placeName) {
            case "Pantai Kuta":
                return "Pantai Kuta adalah salah satu pantai paling terkenal di Bali, bahkan di seluruh Indonesia, yang telah menjadi ikon pariwisata pulau dewata. Dikenal dengan pasir putihnya yang halus dan ombaknya yang cocok untuk berselancar, pantai ini menarik pengunjung dari berbagai penjuru dunia. Pada tahun 1970-an, Kuta mulai dikenal sebagai surga bagi para peselancar. Kini, meski semakin ramai, keindahannya tetap memikat banyak orang untuk datang, baik untuk bersantai maupun berpetualang.\n" +
                        "\n" +
                        "Pantai ini berlokasi hanya sekitar 15 menit dari Bandara Internasional Ngurah Rai, menjadikannya destinasi pertama bagi banyak wisatawan. Kuta adalah tempat yang sempurna untuk menikmati sunset yang menakjubkan. Setiap sore, langit berubah warna menjadi oranye dan merah yang memukau, menciptakan pemandangan yang tak terlupakan. Banyak pengunjung yang datang ke pantai ini hanya untuk melihat matahari terbenam, mengabadikan momen tersebut dalam foto, atau hanya sekadar menikmati suasana yang damai.\n" +
                        "\n" +
                        "Selain pantainya, Kuta menawarkan berbagai aktivitas lain, seperti berbelanja di pasar seni dan toko-toko lokal, menikmati kuliner Bali di berbagai restoran dan kafe, serta merasakan kehidupan malam yang ramai di berbagai klub dan bar. Pantai Kuta juga dikelilingi oleh banyak hotel, dari yang berkelas internasional hingga penginapan yang ramah di kantong. Di sekitar pantai, Anda juga bisa menemukan banyak kios penyewaan papan selancar dan pelatihan surfing, sehingga para pemula pun dapat mencoba pengalaman berselancar di ombak Bali.\n" +
                        "\n" +
                        "Dengan kombinasi antara pantai yang indah, fasilitas lengkap, dan suasana yang menyenangkan, Pantai Kuta menjadi salah satu destinasi wisata yang wajib dikunjungi saat berlibur di Bali. Meski terkenal ramai, pantai ini tetap memiliki pesona tersendiri yang membuat wisatawan selalu ingin kembali.\n" +
                        "\n" +
                        "Alamat: Jl. Pantai Kuta, Kuta, Kabupaten Badung, Bali, Indonesia.";

            case "Candi Borobudur":
                return "Candi Borobudur, terletak di Magelang, Jawa Tengah, adalah candi Buddha terbesar di dunia dan situs warisan dunia UNESCO. Dibangun pada abad ke-9, candi ini memiliki 9 platform bertingkat, 2.672 panel relief, dan 504 arca Buddha. Candi ini menggambarkan ajaran Buddha mengenai kehidupan, kematian, dan pencerahan. Setiap tingkatan melambangkan perjalanan spiritual menuju pencerahan.\n" +
                        "\n" +
                        "Candi Borobudur dikelilingi oleh pemandangan alam yang indah, dengan bukit hijau dan Gunung Merapi di kejauhan. Pengunjung dapat menikmati matahari terbit dari puncak candi, menciptakan pemandangan yang menakjubkan. Selain sebagai situs spiritual, Borobudur juga menarik wisatawan yang tertarik dengan keindahan arsitektur dan nilai historisnya.\n" +
                        "\n" +
                        "Pelestarian Borobudur terus dilakukan untuk menjaga struktur dan nilai historisnya. Candi ini tetap menjadi simbol penting dalam sejarah dan budaya Indonesia, serta tujuan wisata utama di dunia.\n" +
                        "\n" +
                        "Alamat: Jl. Badrawati, Borobudur, Magelang, Jawa Tengah, Indonesia.";

            case "Gunung Bromo":
                return "Gunung Bromo, terletak di Taman Nasional Bromo Tengger Semeru, Jawa Timur, adalah salah satu gunung berapi paling ikonik di Indonesia. Dikenal dengan pemandangan matahari terbitnya yang spektakuler, Bromo menawarkan panorama alam yang luar biasa dengan kaldera luas dan lautan pasir yang mengelilingi kawah aktif. Setiap pagi, wisatawan datang untuk menyaksikan matahari terbit yang menakjubkan.\n" +
                        "\n" +
                        "Gunung Bromo juga kaya akan budaya. Suku Tengger yang tinggal di sekitar gunung memiliki tradisi unik, termasuk Upacara Yadnya Kasada, di mana mereka melemparkan sesaji ke dalam kawah sebagai penghormatan kepada Dewa Gunung Bromo. Bromo tetap menjadi destinasi wisata utama bagi pendaki dan pecinta alam.\n" +
                        "\n" +
                        "Keindahan alam dan tradisi budaya membuat Gunung Bromo menjadi salah satu tujuan wisata terbaik di Indonesia.\n" +
                        "\n" +
                        "Alamat: Taman Nasional Bromo Tengger Semeru, Probolinggo, Jawa Timur, Indonesia.";

            case "Taman Nasional Komodo":
                return "Taman Nasional Komodo, di Nusa Tenggara Timur, adalah situs warisan dunia UNESCO yang terkenal dengan komodo, kadal raksasa yang hanya ada di Indonesia. Komodo adalah predator puncak di ekosistemnya, dan melihat mereka di habitat alami merupakan pengalaman yang luar biasa.\n" +
                        "\n" +
                        "Selain komodo, taman nasional ini juga memiliki keindahan alam dengan pantai berpasir merah, laut jernih, dan pulau-pulau eksotis. Pulau Padar adalah salah satu daya tarik utama dengan pemandangan spektakulernya. Aktivitas menyelam dan snorkeling memungkinkan wisatawan menikmati keindahan bawah lautnya yang kaya.\n" +
                        "\n" +
                        "Taman Nasional Komodo menawarkan pengalaman alam, keanekaragaman hayati, dan budaya lokal yang tak terlupakan.\n" +
                        "\n" +
                        "Alamat: Labuan Bajo, Komodo, Nusa Tenggara Timur, Indonesia.";

            case "Danau Toba":
                return "Danau Toba, di Sumatera Utara, adalah danau vulkanik terbesar di dunia. Terbentuk akibat letusan supervulkan sekitar 74.000 tahun yang lalu, danau ini memiliki Pulau Samosir di tengahnya, yang menjadi pusat kebudayaan Batak.\n" +
                        "\n" +
                        "Danau Toba menawarkan pemandangan alam yang luar biasa, dengan air jernih dan dikelilingi oleh pegunungan hijau. Wisatawan dapat berperahu, berenang, atau memancing, serta mengunjungi desa-desa adat Batak untuk mempelajari tradisi mereka. Tomok, dengan makam Raja Sidabutar, adalah salah satu situs sejarah penting di Samosir.\n" +
                        "\n" +
                        "Keindahan alam dan budaya membuat Danau Toba menjadi salah satu destinasi wisata utama di Indonesia.\n" +
                        "\n" +
                        "Alamat: Danau Toba, Sumatera Utara, Indonesia.";

            case "Raja Ampat":
                return "Raja Ampat, di Papua Barat, adalah surga bagi penyelam dan pecinta alam bawah laut. Kepulauan ini terdiri dari empat pulau utama dan lebih dari 1.500 pulau kecil, dengan pantai putih bersih dan laut jernih. Raja Ampat adalah rumah bagi keanekaragaman hayati laut terbesar di dunia, dengan lebih dari 600 spesies karang dan 1.300 spesies ikan.\n" +
                        "\n" +
                        "Raja Ampat juga menawarkan pemandangan alam daratan yang memukau, dengan hutan tropis dan bukit hijau. Salah satu spot terkenal adalah Wayag, dengan pemandangan pulau-pulau kecil yang menjulang dari laut. Selain itu, wisatawan dapat mengunjungi desa-desa tradisional untuk mempelajari budaya lokal yang kaya.\n" +
                        "\n" +
                        "Raja Ampat adalah destinasi wisata utama bagi pecinta alam dan petualangan yang ingin mengalami keindahan alam dan kelimpahan hayati yang luar biasa.\n" +
                        "\n" +
                        "Alamat: Raja Ampat, Papua Barat, Indonesia.";

            case "Pantai Parangtritis":
                return "Pantai Parangtritis, yang terletak di Yogyakarta, Indonesia, adalah salah satu destinasi wisata pantai yang paling terkenal di Pulau Jawa. Pantai ini dikenal dengan pemandangan alam yang menakjubkan, pasir hitam yang khas, dan ombak besar yang menjadikannya tempat favorit untuk berselancar. Selain keindahan alamnya, Pantai Parangtritis juga memiliki nilai spiritual yang mendalam bagi masyarakat setempat, yang menganggapnya sebagai tempat suci yang berhubungan dengan mitos dan legenda yang sudah turun-temurun.\n" +
                        "\n" +
                        "Nama Parangtritis berasal dari kata 'parang' yang berarti tebing dan 'tritis' yang berarti percikan air, menggambarkan kondisi geografi pantai yang dikelilingi oleh tebing-tebing besar dan ombak yang memecah di sepanjang pantai. Selain itu, Pantai Parangtritis terkenal dengan pesona matahari terbenamnya yang memukau, menjadikannya tempat yang ideal untuk menikmati suasana romantis maupun sekadar menikmati keindahan alam. Banyak wisatawan yang datang untuk berjalan-jalan di sepanjang garis pantai atau duduk menikmati pemandangan sambil merasakan angin laut yang sejuk.\n" +
                        "\n" +
                        "Selain menikmati keindahan alam, pengunjung juga dapat menjajal berbagai aktivitas di Pantai Parangtritis, seperti naik ATV atau berkuda di sepanjang pantai. Pantai ini juga menjadi lokasi populer untuk upacara adat atau ritual yang dilakukan oleh masyarakat setempat, yang menambah daya tarik budaya yang dimilikinya. Di sekitar pantai, terdapat banyak warung dan tempat makan yang menyajikan hidangan khas seperti seafood segar dan kuliner tradisional Jawa yang menggugah selera.\n" +
                        "\n" +
                        "Pantai Parangtritis tidak hanya menawarkan keindahan alam dan budaya, tetapi juga sebuah pengalaman yang berbeda bagi setiap pengunjung yang ingin merasakan suasana tenang dan damai di tepi laut. Meskipun terletak agak jauh dari pusat kota Yogyakarta, Pantai Parangtritis tetap menjadi salah satu tujuan wisata yang wajib dikunjungi bagi siapa saja yang ingin menikmati pesona alam pantai yang luar biasa dan meresapi keunikan budaya yang dimilikinya.\n" +
                        "\n" +
                        "Alamat: Jalan Parangtritis, Parangtritis, Kretek, Kabupaten Bantul, Yogyakarta 55183, Indonesia.";

            case "Kawah Ijen":
                return "Kawah Ijen, yang terletak di Banyuwangi, Jawa Timur, Indonesia, adalah sebuah kawah vulkanik yang terkenal dengan fenomena alam yang luar biasa, yaitu api biru atau blue fire. Kawah ini merupakan salah satu tempat yang paling menakjubkan di Indonesia, dengan pemandangan yang dramatis, terutama saat matahari terbit. Api biru yang muncul akibat pembakaran gas belerang yang terbakar di suhu tinggi hanya bisa ditemukan di beberapa tempat di dunia, dan Kawah Ijen menjadi salah satu lokasi terbaik untuk menyaksikan fenomena langka ini.\n" +
                        "\n" +
                        "Selain fenomena api biru, Kawah Ijen juga terkenal dengan danau kawahnya yang berwarna biru kehijauan, yang terbentuk akibat aktivitas vulkanik di masa lalu. Danau ini mengandung asam yang sangat tinggi, menjadikannya berbahaya untuk didekati terlalu dekat tanpa perlindungan. Kawah Ijen juga menjadi tempat yang penting untuk penambangan belerang, di mana para penambang bekerja keras setiap hari, membawa beban belerang dari dasar kawah ke puncak dengan berjalan kaki dalam kondisi yang sangat sulit dan berbahaya.\n" +
                        "\n" +
                        "Bagi para wisatawan, pendakian menuju puncak Kawah Ijen menjadi pengalaman yang menantang, tetapi sangat memuaskan. Pemandangan matahari terbit yang memukau dari puncak gunung ini menawarkan panorama yang sangat spektakuler. Banyak pengunjung datang untuk merasakan sensasi mendaki gunung yang penuh tantangan ini dan menyaksikan langsung keindahan alam yang tak tertandingi, terutama api biru yang menyala di malam hari.\n" +
                        "\n" +
                        "Kawah Ijen juga memiliki nilai ekologis yang penting, dengan keanekaragaman hayati yang melimpah di sekitarnya, termasuk tanaman dan fauna khas gunung. Kawasan ini sering dikunjungi oleh para pecinta alam dan fotografer yang ingin menangkap keindahan alam yang unik. Meskipun cukup menantang untuk dijangkau, Kawah Ijen tetap menjadi salah satu destinasi wisata alam yang wajib dikunjungi bagi mereka yang mencari petualangan dan keindahan alam yang spektakuler.\n" +
                        "\n" +
                        "Alamat: Desa Sempol, Kecamatan Ijen, Kabupaten Bondowoso, Jawa Timur 68281, Indonesia.";

            case "Pulau Derawan":
                return "Pulau Derawan, yang terletak di Kabupaten Berau, Kalimantan Timur, Indonesia, adalah salah satu destinasi wisata tropis yang menakjubkan di Indonesia. Pulau ini terkenal dengan keindahan alam bawah lautnya, termasuk terumbu karang yang masih terjaga dengan baik, serta keanekaragaman hayati laut yang luar biasa. Pulau Derawan menawarkan pengalaman snorkeling dan diving yang memukau, dengan kesempatan untuk berenang bersama penyu, ikan-ikan tropis, dan bahkan menyelam di sekitar bangkai kapal yang terendam di dasar laut.\n" +
                        "\n" +
                        "Pulau Derawan juga dikenal dengan pantainya yang putih dan air laut yang jernih, menjadikannya tempat yang sempurna untuk berlibur sambil menikmati keindahan alam. Salah satu atraksi utama di Pulau Derawan adalah Kolam Haji Mangku, sebuah danau air payau yang dihuni oleh ribuan penyu hijau. Pengunjung dapat menikmati kegiatan berenang bersama penyu di kolam ini, memberikan pengalaman yang sangat unik dan menyenangkan.\n" +
                        "\n" +
                        "Selain pantai dan bawah laut yang luar biasa, Pulau Derawan juga merupakan rumah bagi banyak spesies langka, termasuk ikan pari manta dan paus. Keanekaragaman hayati ini menjadikan Pulau Derawan sebagai destinasi favorit bagi para pecinta alam dan fotografer yang ingin mengabadikan keindahan alam tropis yang masih alami. Para wisatawan yang berkunjung ke sini dapat merasakan suasana yang tenang, jauh dari keramaian, dan sangat cocok untuk mereka yang mencari ketenangan dan petualangan di alam bebas.\n" +
                        "\n" +
                        "Sebagai bagian dari Taman Nasional Derawan, pulau ini juga memiliki upaya pelestarian yang kuat untuk menjaga ekosistem laut dan satwa liar di sekitarnya. Meskipun tergolong sebagai destinasi yang masih relatif tersembunyi, Pulau Derawan semakin populer di kalangan wisatawan domestik dan internasional yang mencari keindahan alam yang eksotis dan tidak terlalu komersial.\n" +
                        "\n" +
                        "Alamat: Pulau Derawan, Kecamatan Derawan, Kabupaten Berau, Kalimantan Timur 77381, Indonesia.";

            case "Labuan Bajo":
                return "Labuan Bajo, yang terletak di ujung barat Pulau Flores, Nusa Tenggara Timur, Indonesia, adalah destinasi wisata yang terkenal dengan keindahan alam bawah laut dan keunikan budaya lokalnya. Sebagai pintu gerbang menuju Taman Nasional Komodo, Labuan Bajo menjadi titik awal bagi para wisatawan yang ingin mengunjungi Pulau Komodo, yang dikenal dengan hewan purba, komodo, dan juga keindahan pantai serta taman lautnya yang luar biasa.\n" +
                        "\n" +
                        "Labuan Bajo menawarkan berbagai atraksi wisata yang mengesankan, mulai dari wisata laut dengan menyelam di kawasan terumbu karang yang indah, snorkeling di Pulau Kanawa, hingga menikmati keindahan alam yang masih alami. Salah satu daya tarik utama adalah Komodo Island, yang menjadi rumah bagi komodo, reptil terbesar di dunia. Selain itu, Labuan Bajo juga dikenal dengan pemandangan matahari terbenam yang spektakuler, yang dapat dinikmati dari berbagai titik di kota ini, termasuk dari Bukit Cinta yang terkenal.\n" +
                        "\n" +
                        "Selain wisata alam, Labuan Bajo juga menawarkan pengalaman budaya yang kaya. Kota ini adalah rumah bagi berbagai suku, termasuk suku Flores, yang memiliki tradisi unik dan kaya, seperti tarian tradisional, upacara adat, dan kerajinan tangan. Masyarakat lokal juga ramah dan selalu siap menyambut wisatawan yang datang untuk menikmati keindahan alam dan budaya mereka.\n" +
                        "\n" +
                        "Labuan Bajo tidak hanya dikenal sebagai tujuan wisata alam, tetapi juga sebagai tempat yang semakin berkembang dengan adanya infrastruktur dan fasilitas pariwisata yang lebih baik. Dengan pesona alam yang tiada duanya, serta akses yang semakin mudah, Labuan Bajo telah menjadi salah satu destinasi wisata terpopuler di Indonesia, menarik wisatawan domestik dan mancanegara yang mencari pengalaman petualangan dan ketenangan di tengah keindahan alam tropis.\n" +
                        "\n" +
                        "Alamat: Labuan Bajo, Kecamatan Komodo, Kabupaten Manggarai Barat, Nusa Tenggara Timur, Indonesia.";

            case "Candi Prambanan":
                return "Candi Prambanan, yang terletak di Sleman, Yogyakarta, Indonesia, adalah kompleks candi Hindu terbesar di Indonesia dan salah satu Situs Warisan Dunia UNESCO. Dibangun pada abad ke-9, candi ini didedikasikan untuk Trimurti, yaitu Brahma (Dewa Pencipta), Wisnu (Dewa Pemelihara), dan Siwa (Dewa Penghancur). Keindahan arsitekturnya mencerminkan keunggulan seni dan teknologi masyarakat Jawa kuno.\n" +
                        "\n" +
                        "Selain keindahan candi utama, kompleks Candi Prambanan juga memiliki banyak candi kecil di sekitarnya, seperti Candi Sewu, Candi Lumbung, dan Candi Bubrah, yang semuanya memiliki nilai sejarah dan keindahan tersendiri. Relief yang menghiasi dinding-dinding candi ini menceritakan kisah Ramayana, yang menjadi salah satu daya tarik utama bagi para pengunjung.\n" +
                        "\n" +
                        "Setiap malam, pengunjung dapat menikmati pertunjukan Sendratari Ramayana di area terbuka yang berlatarkan Candi Prambanan yang megah. Acara ini menggabungkan tari tradisional Jawa, musik gamelan, dan cerita epik yang menggambarkan kisah cinta Rama dan Sinta, menciptakan pengalaman budaya yang mendalam.\n" +
                        "\n" +
                        "Dengan keindahan arsitektur, nilai sejarah, dan pengalaman budaya yang ditawarkan, Candi Prambanan menjadi salah satu destinasi wisata wajib bagi wisatawan domestik maupun mancanegara yang ingin mengeksplorasi kekayaan budaya Indonesia.\n" +
                        "\n" +
                        "Alamat: Jl. Raya Solo – Yogyakarta, Prambanan, Sleman, Yogyakarta 55571, Indonesia.";

            case "Gunung Rinjani":
                return "Gunung Rinjani, yang berada di Pulau Lombok, Nusa Tenggara Barat, adalah gunung berapi tertinggi kedua di Indonesia dengan ketinggian 3.726 meter di atas permukaan laut. Gunung ini menawarkan pemandangan alam yang luar biasa, mulai dari hutan tropis, padang rumput, hingga danau kawah Segara Anak yang mempesona.\n" +
                        "\n" +
                        "Salah satu daya tarik utama Gunung Rinjani adalah perjalanan mendaki yang menantang dan pemandangan indah sepanjang rute pendakian. Pendaki akan melewati berbagai ekosistem dan dapat menikmati panorama yang meliputi Pulau Lombok hingga Bali dan Sumbawa. Kawah Segara Anak menjadi tempat peristirahatan favorit, dengan airnya yang jernih dan sumber mata air panas di sekitarnya.\n" +
                        "\n" +
                        "Gunung Rinjani juga memiliki nilai spiritual bagi masyarakat setempat. Kawasan ini dianggap suci oleh suku Sasak dan masyarakat Hindu Bali, yang sering melakukan upacara keagamaan di sekitar Danau Segara Anak. Pengalaman ini memberikan dimensi budaya yang unik bagi para pendaki.\n" +
                        "\n" +
                        "Dengan keindahan alam yang menakjubkan dan tantangan pendakiannya, Gunung Rinjani adalah destinasi impian bagi para pecinta alam dan petualangan, memberikan pengalaman yang tak terlupakan.\n" +
                        "\n" +
                        "Alamat: Gunung Rinjani, Lombok, Nusa Tenggara Barat, Indonesia.";

            case "Pulau Belitung":
                return "Pulau Belitung, yang terletak di lepas pantai timur Sumatra, Indonesia, terkenal dengan pantainya yang indah, pasir putih, dan formasi batu granit raksasa yang unik. Keindahan ini menjadi daya tarik utama bagi wisatawan yang mencari destinasi tropis yang masih alami.\n" +
                        "\n" +
                        "Pantai Tanjung Tinggi adalah salah satu ikon Pulau Belitung yang sering menjadi lokasi fotografi karena keindahannya. Selain itu, Pulau Lengkuas dengan mercusuarnya yang bersejarah menawarkan pemandangan spektakuler dari atas. Snorkeling dan diving juga menjadi aktivitas favorit, dengan keanekaragaman hayati laut yang mengagumkan.\n" +
                        "\n" +
                        "Pulau Belitung juga dikenal melalui novel dan film 'Laskar Pelangi,' yang menggambarkan kehidupan lokal serta keindahan alamnya. Para wisatawan dapat mengunjungi replika SD Muhammadiyah Gantong, yang menjadi salah satu lokasi syuting film tersebut, untuk memahami lebih dalam budaya dan sejarah daerah ini.\n" +
                        "\n" +
                        "Dengan perpaduan antara alam, budaya, dan sejarah, Pulau Belitung telah menjadi salah satu destinasi wisata paling menarik di Indonesia, cocok untuk keluarga maupun pecinta alam.\n" +
                        "\n" +
                        "Alamat: Pulau Belitung, Provinsi Kepulauan Bangka Belitung, Indonesia.";

            case "Danau Kelimutu":
                return "Danau Kelimutu, yang terletak di Pulau Flores, Nusa Tenggara Timur, terkenal dengan keunikannya sebagai danau kawah tiga warna. Warna-warna danau ini dapat berubah-ubah, seperti biru, hijau, hingga merah kehitaman, yang membuatnya menjadi fenomena alam yang menakjubkan.\n" +
                        "\n" +
                        "Danau ini berada di puncak Gunung Kelimutu dan menjadi tujuan favorit para pendaki yang ingin menikmati pemandangan matahari terbit yang memukau dari atas gunung. Keindahan panorama alam sekitarnya menambah daya tarik Danau Kelimutu sebagai salah satu tempat wisata terbaik di Flores.\n" +
                        "\n" +
                        "Danau Kelimutu juga memiliki nilai budaya dan spiritual bagi masyarakat setempat. Danau ini diyakini sebagai tempat peristirahatan jiwa-jiwa yang telah meninggal, dengan setiap warna danau dipercaya sebagai tempat bagi kelompok jiwa tertentu, seperti orang tua, muda, atau yang memiliki kepribadian buruk.\n" +
                        "\n" +
                        "Dengan kombinasi keindahan alam, keunikan geologi, dan nilai spiritualnya, Danau Kelimutu adalah destinasi yang wajib dikunjungi bagi siapa saja yang menjelajahi keindahan Pulau Flores.\n" +
                        "\n" +
                        "Alamat: Danau Kelimutu, Kecamatan Kelimutu, Kabupaten Ende, Flores, Nusa Tenggara Timur, Indonesia.";

            case "Wakatobi":
                return "Wakatobi, yang merupakan singkatan dari empat pulau utama yaitu Wangi-Wangi, Kaledupa, Tomia, dan Binongko, adalah destinasi wisata bahari yang terletak di Sulawesi Tenggara, Indonesia. Kawasan ini dikenal sebagai salah satu surga bawah laut terbaik di dunia, dengan keanekaragaman terumbu karang yang luar biasa dan keindahan alam bawah laut yang memukau.\n" +
                        "\n" +
                        "Wakatobi menawarkan pengalaman menyelam dan snorkeling kelas dunia. Wisatawan dapat menjelajahi ratusan titik selam yang tersebar di seluruh wilayah, seperti House Reef di Wangi-Wangi dan Mari Mabuk di Pulau Tomia. Kehidupan laut yang berwarna-warni, termasuk ikan-ikan tropis, penyu, dan bahkan lumba-lumba, menjadi daya tarik utama di perairan ini.\n" +
                        "\n" +
                        "Selain keindahan lautnya, Wakatobi juga menawarkan pengalaman budaya yang unik. Masyarakat lokal, terutama suku Bajo, memiliki tradisi hidup di rumah-rumah panggung di atas laut dan menawarkan keramahan yang hangat kepada para wisatawan. Kegiatan seperti festival budaya dan upacara adat dapat menjadi pengalaman yang menarik selama kunjungan.\n" +
                        "\n" +
                        "Dengan statusnya sebagai Taman Nasional Laut, Wakatobi juga memiliki komitmen yang kuat terhadap pelestarian ekosistem lautnya. Destinasi ini cocok bagi para pencinta alam dan penggemar aktivitas bawah air yang mencari keindahan alam tropis yang masih alami.\n" +
                        "\n" +
                        "Alamat: Wakatobi, Sulawesi Tenggara, Indonesia.";

            default:
                return "Deskripsi tidak tersedia.";
        }
    }
}

/*public class DetailActivity extends AppCompatActivity {

    private TextView textViewName, textViewLocation, textViewDescription;
    private ViewPager2 viewPagerImages;
    private GambarAdapter gambarAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Inisialisasi view
        textViewName = findViewById(R.id.textViewNameDetail);
        textViewLocation = findViewById(R.id.textViewLocationDetail);
        textViewDescription = findViewById(R.id.textViewDescriptionDetail);
        viewPagerImages = findViewById(R.id.viewPagerImages);

        // Ambil data dari Intent
        String wisataName = getIntent().getStringExtra("wisata_name");
        String wisataLocation = getIntent().getStringExtra("wisata_location");
        int[] wisataImages = getIntent().getIntArrayExtra("wisata_images");

        // Set nama dan lokasi
        textViewName.setText(wisataName);
        textViewLocation.setText(wisataLocation);

        // Dapatkan deskripsi berdasarkan nama wisata
        String wisataDescription = getPlaceDescription(wisataName);
        textViewDescription.setText(wisataDescription);

        // Set adapter untuk ViewPager2 jika ada gambar
        if (wisataImages != null && wisataImages.length > 0) {
            gambarAdapter = new GambarAdapter(wisataImages);
            viewPagerImages.setAdapter(gambarAdapter);
        }

        // Menambahkan animasi fade-in untuk semua elemen
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        viewPagerImages.startAnimation(fadeInAnimation);
        textViewName.startAnimation(fadeInAnimation);
        textViewLocation.startAnimation(fadeInAnimation);
        textViewDescription.startAnimation(fadeInAnimation);

        // Menampilkan Toast dengan gaya kustom
        showCustomToast();

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Opsional: Menutup DetailActivity setelah berpindah ke MainActivity
        });

        // Menambahkan action untuk tombol Kuliner & Akomodasi
        Button kulinerAkomodasiButton = findViewById(R.id.buttonKulinerAkomodasi);
        kulinerAkomodasiButton.setOnClickListener(v -> {
            // Membuka Activity KulinerAkomodasi dan mengirimkan data lokasi wisata
            Intent intent = new Intent(DetailActivity.this, KulinerAkomodasiActivity.class);
            intent.putExtra("lokasi_wisata", wisataLocation); // Mengirimkan lokasi wisata
            startActivity(intent);
        });
    }

    private void showCustomToast() {
        // Membuat custom Toast dengan layout kustom
        Toast toast = new Toast(getApplicationContext());
        TextView text = new TextView(getApplicationContext());
        text.setText("Temukan keajaiban di setiap sudut destinasi ini! ^^");

        // Mengatur warna teks menjadi DBDBDD (abu-abu terang)
        text.setTextColor(Color.parseColor("#DBDBDD")); // Set warna teks sesuai dengan #DBDBDD

        text.setPadding(30, 20, 30, 20);
        text.setTypeface(ResourcesCompat.getFont(this, R.font.montserrat_regular)); // Set font Montserrat Regular
        text.setTypeface(text.getTypeface(), android.graphics.Typeface.ITALIC); // Set italic

        // Membuat background Toast dengan opacity 80% dan warna hitam
        float[] radii = new float[8]; // Untuk sudut rounded
        for (int i = 0; i < 8; i++) {
            radii[i] = 50f; // Radius sudut
        }
        ShapeDrawable shape = new ShapeDrawable(new RoundRectShape(radii, null, null));
        shape.getPaint().setColor(Color.argb(200, 0, 0, 0)); // Opacity 80% dengan warna hitam (RGBA)

        // Set background Toast
        text.setBackground(shape);

        // Menambahkan animasi dan posisikan di tengah
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(text);
        toast.show();
    }*/