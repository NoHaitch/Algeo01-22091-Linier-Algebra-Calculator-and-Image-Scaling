# Contains Main Program

public void OperasiOBE() {
/* I.S. : Matrix M bernilai */
/* F.S. : Matrix M dilakukan operasi OBE hingga ditemukan tidak
bersolusi atau hingga matrix menjadi matrix eselon baris */
boolean bersolusi = true;
int i = 0;

        while (i < M.getRowEff() && bersolusi) {
            /* Mensiapkan Matriks untuk operasi */
            getIndeksUtama();
            sortMatriksOBE();

            /* cek solusi*/
            if (!cekBersolusi()) {
                bersolusi = false;
                System.out.println("MATRIKS TIDAK MEMILIKI SOLUSI");
            } else {
                /* Operasi agar Kolom Eselon */
                if (M.getElmt(i, getIndeksUtamaElmt(i)) != 1) {
                    /* menjadikan nilai utama baris menjadi 1 */
                    /* contoh : baris 3 2 6 1 menjadi 1 2/3 2 1/3 */
                    double pembagi = M.getElmt(i, getIndeksUtamaElmt(i));
                    for (int j = 0; j < M.getColEff(); j++) {
                        M.setElmt(i, j, M.getElmt(i, j) / pembagi);
                    }
                }
                /* Kurangi baris lain sehingga menjadi matrix eselon baris */
                for (int j = i + 1; j < M.getRowEff(); j++) {
                    for (int k = getIndeksUtamaElmt(i); k < M.getColEff(); k++) {
                        /* j : index baris, k : index kolom */
                        continue;
                    }
                }
            }
        }
        i++;
    }