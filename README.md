# 🐦 TP6DPBO2025C2 - Flappy Bird Java Swing
Tugas Praktikum 6 DPBO C2

## 🙏 Janji

Saya **Julian Dwi Satrio** mengerjakan evaluasi Tugas Praktikum dalam mata kuliah **Desain Pemrograman Berbasis Objek** untuk keberkahan-Nya, maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

---

## 🎨 Desain Program

Project ini merupakan implementasi permainan klasik **Flappy Bird** berbasis Java yang mengintegrasikan konsep **Object-Oriented Programming (OOP)** dengan antarmuka grafis menggunakan **Java Swing**.  
Game dibagi menjadi beberapa komponen utama yang dikelola dalam kelas-kelas terpisah agar modular dan mudah dikembangkan.

---

## 🧱 Struktur Kelas

### `1. App`
- **Fungsi:** Titik masuk utama (entry point) aplikasi.
- **Peran Utama:** 
  - Membuat `JFrame` utama.
  - Menampilkan menu awal (`StartMenu`) saat program dijalankan.
  - Mengatur transisi dari menu ke game (`FlappyBird`).

### `2. BackgroundPanel`
- **Fungsi:** Panel khusus untuk menggambar latar belakang.
- **Detail:**
  - Memuat gambar background dari file `assets/background.png`.
  - Digunakan oleh `StartMenu` untuk menampilkan background yang menarik.

### `3. FlappyBird`
- **Fungsi:** Logika inti dari permainan Flappy Bird.
- **Tanggung Jawab:**
  - Mengatur pergerakan burung (Player) dan rintangan (Pipe).
  - Mengatur loop permainan, skor, dan deteksi tabrakan.
  - Merespon input dari pengguna (keyboard event).

### `4. Pipe`
- **Fungsi:** Mewakili rintangan yang harus dihindari oleh burung.
- **Detail:**
  - Bergerak dari kanan ke kiri layar.
  - Terdiri dari bagian atas dan bawah.
  - Menangani logika tabrakan dengan objek `Player`.

### `5. Player`
- **Fungsi:** Karakter utama (burung) yang dikendalikan oleh pengguna.
- **Atribut dan Perilaku:**
  - Posisi (x, y), kecepatan, dan status aktif.
  - Melompat saat pengguna menekan tombol spasi.
  - Terpengaruh oleh gravitasi (turun jika tidak melompat).

### `6. StartMenu`
- **Fungsi:** Tampilan menu awal saat program dijalankan.
- **Komponen Utama:**
  - `BackgroundPanel` untuk latar belakang.
  - Tombol "Start Game" yang berada di tengah dan memiliki dekorasi font.
  - Transisi ke `FlappyBird` saat tombol ditekan.

---

## 🔁 Alur Program

1. **Program Dimulai**
   - `App.java` dijalankan.
   - Menu awal (`StartMenu`) ditampilkan dengan background dan tombol Start.

2. **User Klik "Start Game"**
   - Panel menu dihapus dari frame.
   - Game panel (`FlappyBird`) ditambahkan.

3. **Gameplay**
   - Burung muncul dan mulai terpengaruh gravitasi.
   - User bisa menekan tombol spasi untuk membuat burung melompat.
   - Pipa bergerak dari kanan ke kiri.
   - Jika burung menabrak pipa atau jatuh ke tanah → Game Over.

4. **Game Over**
   - Permainan berhenti, pengguna bisa menutup atau restart dari awal.

---

## 💡 Dokumentasi



https://github.com/user-attachments/assets/d9072cf8-b10a-4ffc-84a9-866268d958eb


---

> Dibuat sebagai Tugas Praktikum 6 - DPBO 2025  
