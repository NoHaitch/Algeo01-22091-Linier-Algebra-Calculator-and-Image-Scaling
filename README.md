<div align="center" id="readme-top">
<h1>Tugas Besar 1 IF2123 - Aljabar Linier dan Geometri ITB</h1>
<h3>TOPIK : Sistem Persamaan Linier, Determinan, dan Aplikasinya</h3>
</div>


Berisi Program mengenai Sistem Persamaan Linier(SPL), Determinan, Interpolasi Polinomial, Regresi Linier Berganda, dan Bicubic Spline Interpolation.
Untuk cara penyelasaian SPL, menggunakan Metode Eliminiasi Gauss, Gauss-Jordan, Matriks balikan, dan Kaidah Cramer.


<br>
<p align="center">
<img src="https://github.com/NoHaitch/Algeo01-22091/assets/72493275/6f2a7233-0f47-49a0-b50e-f9f2efd233f5" alt="Kelompok Panci Bolong" width="400"/>
</p>
<br>

## Kelompok 20 : Panci Bolong

|   NIM    |                  Nama                  |
| :------: | :------------------------------------: |
| 13522091 | Raden Francisco Trianto Bratadiningrat |
| 13522093 |       Matthew Vladimir Hutabarat       |
| 13522098 |      Suthasoma Mahardhika Munthe       |

## Quick Links
- [Getting Started](#installing--getting-started)
- [Developing](#developing)
- [Features](#features)
- [Contributing](#contributing)
- [Links](#links)
- [Licensing](#licensing)

## External Links

- [Link Spesifikasi](https://docs.google.com/document/d/1evaYvI5PfDij2UlA_mkqUCLK0mg4hbRzbcA461FFnwg)
- [Link Data Kelompok](https://docs.google.com/spreadsheets/d/1Lnc1Bf3rv3uHc4vgUdWsJcf4bDzlSIeslEvdyR90I3U/edit#gid=0)
- [Link Laporan](https://github.com/NoHaitch/Algeo01-22091/blob/main/doc/Algeo01-22091.pdf)


## Installing / Getting Started


Di bawah adalah instruksi instalasi dan  penggunaan program

1. Clone Repository ke dalam root folder
   ```bash
   git clone https://github.com/NoHaitch/Tubes_Algeo_1
   ```
2. Ubah Directory Terminal
   ```bash
   cd bin 
   ```
3. Jalankan Program
   ```bash
   java Main
   ```
<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Input Output Program

Terdapat 2 macam jenis input yang dapat dilakukan tergantung pada fitur
1. Input dengan Ketik
2. Input dengan File Text

Hal tersebut dapat dipilih pada menu dibawah
``` 
========== Pilih Metode Masukkan ==========
1. Masukkan Ketik
2. Masukkan dalam bentuk File
3. Kembali
 >>> Pilih Metode Masukkan : 
 ```

Program yang berhasil akan menunjukan hasil perhitungannya.  
Program dapat menyimpan hasil perhitungan pada File Text

Hal tersebut dapat dipilih pada menu dibawah

``` 
>>> Apakah ingin disimpan dalam file? [y/n]: y
>>> Masukkan nama file hasil: test.txt

Penyelesaian berhasil disimpan ke : ../test/spl/output/test.txt
```

Program hanya akan membaca file pada folder input yang dijelaskan di bawah ini.

### Lokasi File Input dan Output

#### Struktur Folder test
```
    .
    ├── ...
    ├── test                     # Test folder
    │   ├── bicubic              # Test Interpolasi Bicubic Spline
    │   ├── determinan           # Test Determinan
    │   ├── imgBSI               # Test Peningkatan Kualitas Gambar
    │   ├── interpolasi          # Test Interpolasi Polinomial
    │   ├── inverse              # Test Matriks Balikan 
    │   ├── regresi              # Test Regresi Linier Berganda
    │   └── spl                  # Test Sistem Persamaan Linier
    └── ...
```
Untuk setiap folder di atas mulai dari "bicubic" hingga "spl".  
Terdapat child folder yaitu input dan output.

#### Struktur Folder spl
```   
   ├── spl                       # Test 
   │   ├── input                 # Berisi file sebagai input untuk Fitur SPL
   │   │   ├── test1.txt         # Contoh file input 
   │   │   └── test2.txt         # Contoh file input 
   │   └── output                # Berisi file hasil output dari Fitur SPL
   │       ├── res1.txt          # Contoh file hasil 
   │       └── res2.txt          # Contoh file hasil 
```

"test1.txt" dan "test2.txt" adalah file yang dapat dibaca oleh program saat masuk menu input file dari spl.
Hasil dari program dapat di save dengan nama.txt yang disimpan pada output contohnya "res1.txt" dan "res2.txt".


## Developing

### Building

```Shell
# From root Directory
# cd ../ to return to last directory
cd src   
javac Main.java -d ../bin
```

Dengan kode ```cd src```, kita masuk ke directory "src"  
Dengan kode ```javac Main.java -d ../bin ```, Kita mengkompile program dan meletakan hasilnya pada folder bin
<p align="right">(<a href="#readme-top">back to top</a>)</p>  

## Features

### 1. Menyelesaikan Sistem Persamaa Linier (SPL)
_Menyelesaikan Sistem Persamaaan Linier Dalam Bentuk Matriks_
1. Metode Eliminasi Gauss
2. Metode Eliminasi Gauss-Jordan
3. Metode Matriks Balikan
4. Kaidah Cramer

### 2. Menghitung Determinan
1. Metode Ekspansi Kofaktor
2. Metode Reduksi Baris dengan OBE

### 3. Menghitung Matriks Balikan (Invers)
### 4. Menghitung Interpolasi Polinomial
### 5. Menghitung Interpolasi Bicubic Spline
### 6. Menghitung Regresi Linier Berganda
### 7. Peningkatan Kualitas Gambar
_Meningkatkan Kualitas Gambar_  
_Menggunakan Interpolasi Bicubic Spline sebagai basis_

### Other
- Menerima Input dari Ketikan
- Menerima Input dari File
- Menyimpan hasil perhitungan ke dalam File

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Contributing

Jika Anda ingin berkontribusi atau melanjutkan perkembangan program, silahkan fork repository ini dan gunakan branch fitur.  
Permintaan Pull sangat diperbolehkan dan diterima dengan hangat.

## Links

- [Project homepage](https://github.com/NoHaitch/Tubes_Algeo_1)
- [Repository](https://github.com/NoHaitch/Tubes_Algeo_1)
- [Spesifikasi Tugas](https://docs.google.com/document/d/1_-ZaP5vsYfCp17aHCh3ePt27dU7vYmoF2m0DnDNWUFg/edit?usp=sharing)
- [Laporan Tugas](https://github.com/NoHaitch/Algeo01-22091/blob/main/doc/Algeo01-22091.pdf)
- [Issue tracker](https://github.com/NoHaitch/Tubes_Algeo_1/issues)
  
_Jika terdapat kasus bug-bug yang sangat penting dan berbahaya, silahkan gunakan fitur issue pada github. Kami apresiasi semua bantuan dalam memperbaiki dan mengembangkan project ini!_

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## Licensing

The code in this project is licensed under MIT license.
Code dalam projek ini berada di bawah lisensi MIT.

<h4 align="center"><a href="#readme-top">back to top</a></h4>
<br><br>
<h1 align="center"> THANK YOU! </h1>
