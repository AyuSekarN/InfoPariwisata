package com.example.infopariwisata;

public class Wisata {
    private String name;
    private String location;
    private String description;
    private int[] imageResIds;
    private boolean isLiked;  // Menambahkan properti untuk status Like

    // Constructor
    public Wisata(String name, String location, int[] imageResIds) {
        this.name = name;
        this.location = location;
        this.imageResIds = imageResIds;
        this.description = ""; // Atau bisa ditambahkan nilai default jika diperlukan
        this.isLiked = false;  // Default: tidak disukai
    }

    // Getter untuk nama tempat wisata
    public String getName() {
        return name;
    }

    // Getter untuk lokasi
    public String getLocation() {
        return location;
    }

    // Getter untuk array gambar
    public int[] getImageResIds() {
        return imageResIds;
    }

    // Getter untuk deskripsi
    public String getDescription() {
        return description;
    }

    // Getter untuk status Like
    public boolean isLiked() {
        return isLiked;
    }

    // Setter untuk status Like
    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}


/*
package com.example.infopariwisata;

public class Wisata {
    private String name;
    private String location;
    private String description;
    private int[] imageResIds;

    public Wisata(String name, String location, int[] imageResIds) {
        this.name = name;
        this.location = location;
        this.imageResIds = imageResIds;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int[] getImageResIds() {
        return imageResIds;
    }

    public String getDescription() {  // Getter untuk deskripsi
        return description;
    }
}
*/
