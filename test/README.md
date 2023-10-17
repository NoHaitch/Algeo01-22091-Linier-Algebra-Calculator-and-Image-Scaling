# Folder Test sebagai input dan output

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
