package operations;

public class OBE {
    /* global variable */
    private Matrix M;
    private int[] indeksUtama = new int[1000];

    /*-------------- Konstruktor --------------*/
    public OBE(Matrix M) {
        this.M = M;
        setIndeksUtama();
    }

    /*-------------- Selektor --------------*/
    public int[] getIndeksUtama(){
        return indeksUtama;
    }

    public int getIndeksUtamaElmt(int idx){
        return indeksUtama[idx];
    }

    public void setIndeksUtamaElmt(int idx, int val){
        indeksUtama[idx] = val;
    }

    public void setIndeksUtama(){
        /* I.S. : indeksUtama terdefinisi */
        /* F.S. : indeksUtama terisi indeks utama setiap baris */
        /* Indeks utama adalah array berisi indeks kolom terdepan yang bukan bernilai 0
        *  contoh : matriks 3 x 4
        *           | 4 2 0 1 |
        *           | 1 0 3 3 | maka indeks utamanya = [0,0,2,1]
        *           | 0 0 2 3 |
        *           | 0 3 4 2 |
        * */
        for(int i = 0; i < M.getRowEff(); i++){
            int j = 0;
            while(M.getElmt(i,j) == 0 && j < M.getColEff()){
                j++;
            }
            setIndeksUtamaElmt(i,j);
        }
    }

    /*-------------- Pengecekan --------------*/
    public boolean cekBersolusi(){
        /* Mengembalikan apakah matriks memiliki solusi atau tidak */
        int i = 0;
        boolean noSolution = false;
        while(i < M.getRowEff() && !noSolution){

            if(getIndeksUtamaElmt(i) == M.getRowEff()-1){
                /* karena indeks utama ada pada kolom b pada ax=b, */
                /* maka tidak ada solusi sebab 0 = k, dengan k bilangan Real */
                noSolution = true;
            }
            i++;
        }
        return !noSolution;
    }
    /*-------------- Display --------------*/
    public void displayIndeksUtama(){
        for(int i = 0; i<M.getRowEff();i++){
            System.out.print(getIndeksUtamaElmt(i) + " ");
        }
        System.out.print("\n");
    }
    public void displayMatrix(){
        M.displayMatrix();
    }

    /*-------------- Sorting --------------*/
    public void sortMatriksOBE(){
        /* I.S. : Matriks M dan IndeksUtama bernilai */
        /* F.S. : Matriks M terurut barisnya berdasarkan nilai IndeksUtama
        *         Indeks Utama terurut */

        /* sorting array indeksUtama */
        for(int j = 0; j < M.getRowEff(); j++){
            for(int k = j+1; k < M.getRowEff(); k++){
                /* swap nilai IndeksUtama dan swap baris Matriks */
                if(getIndeksUtamaElmt(j) > getIndeksUtamaElmt(k)){
                    /* swap nilai indeksUtama */
                    int tempIdx = getIndeksUtamaElmt(j);
                    setIndeksUtamaElmt(j,getIndeksUtamaElmt(k));
                    setIndeksUtamaElmt(k,tempIdx);

                    /* swap baris matriks */
                    double[] tempRow = M.getRow(j);
                    M.setRow(M.getRow(k),j);
                    M.setRow(tempRow,k);
                }
            }
        }
    }

    /*-------------- OPERASI UTAMA OBE --------------*/
    public void OperasiOBE() {
        /* I.S. : Matrix M bernilai */
        /* F.S. : Matrix M dilakukan operasi OBE hingga ditemukan tidak
                  bersolusi atau hingga matrix menjadi matrix eselon baris */
        boolean bersolusi = true;
        int i = 0;

        while(i < M.getRowEff() && bersolusi) {
            /* Mensiapkan Matriks untuk operasi */
            getIndeksUtama();
            sortMatriksOBE();

            /* cek solusi*/
            if (!cekBersolusi()) {
                bersolusi = false;
                System.out.println("MATRIKS TIDAK MEMILIKI SOLUSI");
            }
            else {
                /* Operasi agar Kolom Eselon */
                if(M.getElmt(i,getIndeksUtamaElmt(i)) != 1) {
                    /* menjadikan nilai utama baris menjadi 1 */
                    /* contoh : baris 3 2 6 1 menjadi 1 2/3 2 1/3 */
                    double pembagi = M.getElmt(i, getIndeksUtamaElmt(i));
                    for (int j = 0; j < M.getColEff(); j++) {
                        M.setElmt(M.getElmt(i, j) / pembagi, i, j);
                    }
                }
                /* Kurangi baris lain sehingga menjadi matrix eselon baris */
                for (int j = i+1; j < M.getRowEff(); j++){
                    for(int k = getIndeksUtamaElmt(i); k < M.getColEff(); k++){
                        /* j : index baris, k : index kolom */


                    }
                }
            }
        }
        i++;
    }
}
