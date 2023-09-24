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

    public boolean cekSolusiTrivial(){
        /* Mengembalikan apakah matriks memiliki solusi trivial atau tidak */
        int i = M.getRowEff();;
        boolean trivialSolution = false;
        while(i >= 0 && !trivialSolution){
            int cekCount = 0 ;
            for(int j = 0; j < M.getColEff(); j++){
                if(M.getElmt(i,j) == 0){
                    cekCount += 1;
                }
            }
            System.out.println(cekCount);
            if(cekCount == M.getColEff()){
                trivialSolution = true;
            }
            i--;
        }
        return trivialSolution;
    }
    public boolean cekBersolusi(){
        /* Mengembalikan apakah matriks memiliki solusi atau tidak */
        int i = M.getRowEff();;
        boolean existSolution = true;
        while(i >= 0 && existSolution){
            if(getIndeksUtamaElmt(i) == M.getColEff()-1 && M.getElmt(i,getIndeksUtamaElmt(i)) != 0){
                /* karena indeks uitama ada pada kolom b pada ax=b, */
                /* maka tidak ada solusi sebab 0 = k, dengan k bilangan Real */
                existSolution = false;
            }
            i--;
        }
        return existSolution;
    }
    public boolean cekBersolusiTunggal(){
        /* pre-kondisi matriks tersortir */
        /* Mengembalikan apakah matriks memiliki solusi Tunggal atau tidak */
        int i = M.getRowEff();
        boolean tunggal = false;
        while(i >= 0 && !tunggal){
            if(getIndeksUtamaElmt(i) == M.getColEff()-2 && M.getElmt(i,getIndeksUtamaElmt(i)) != 0 ){
                /* karena indeks uitama ada pada kolom b pada ax=b, */
                /* maka tidak ada solusi sebab 0 = k, dengan k bilangan Real */
                tunggal = true;
            }
            i--;
        }
        return tunggal;
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

    public void displaySolusi(){
        if(cekSolusiTrivial()) {
            System.out.println("Matriks memiliki solusi trivial, tidak dipastikan memiliki solusi lain");
        } else if(cekBersolusiTunggal()){
            System.out.println("Matriks memiliki solusi tunggal");
        } else if(!cekBersolusi()){
            System.out.println("Matriks Tidak memiliki solusi");
        }
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
    public void operasiOBE() {
        /* I.S. : Matrix M bernilai */
        /* F.S. : Matrix M dilakukan operasi OBE hingga ditemukan tidak
                  bersolusi atau hingga matrix menjadi matrix eselon baris */
        boolean bersolusi = true;
        int i = 0;

        while(i < M.getRowEff() && bersolusi) {
            /* Mensiapkan Matriks untuk operasi */
            setIndeksUtama();
            sortMatriksOBE();
            /* cek solusi*/
            if (!cekBersolusi()) {
                bersolusi = false;
            } else {
                /* Operasi agar Kolom Eselon */
                if (M.getElmt(i, getIndeksUtamaElmt(i)) != 1) {
                    /* menjadikan nilai utama baris menjadi 1 */
                    /* contoh : baris 3 2 6 1 menjadi 1 2/3 2 1/3 */
                    double pembagi = M.getElmt(i, getIndeksUtamaElmt(i));
                    if(pembagi != 0) {
                        for (int j = 0; j < M.getColEff(); j++) {
                            M.setElmt(M.getElmt(i, j) / pembagi, i, j);
                        }
                    }
                }
                /* Kurangi baris lain sehingga menjadi matrix eselon baris */
                for (int j = i + 1; j < M.getRowEff(); j++) {
                    /* j : index baris, k : index kolom */
                    if (M.getElmt(j, getIndeksUtamaElmt(i)) != 0) {
                        double rasioPengurang = M.getElmt(j, getIndeksUtamaElmt(i));
                        for (int k = getIndeksUtamaElmt(i); k < M.getColEff(); k++) {
                            double newElmt = M.getElmt(j, k) - (M.getElmt(i, k) * rasioPengurang);
                            if(newElmt == -0){
                                newElmt *= -1;
                            }
                            M.setElmt(newElmt, j, k);
                        }
                    }
                }
            }
            i++;
        }
        if (cekBersolusi()) {
            i--;
            if (M.getElmt(i, getIndeksUtamaElmt(i)) != 1) {
                /* menjadikan nilai utama baris menjadi 1 */
                /* contoh : baris 3 2 6 1 menjadi 1 2/3 2 1/3 */
                double pembagi = M.getElmt(i, getIndeksUtamaElmt(i));
                if(pembagi != 0) {
                    for (int j = 0; j < M.getColEff(); j++) {
                        M.setElmt(M.getElmt(i, j) / pembagi, i, j);
                    }
                }
            }
        }
    }
}
