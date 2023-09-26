package operations;

import java.lang.Math;

/* Class OBE */
/*  Membuat objek OBE, yang digunakan untuk melakukan OBE */
/*  dengan metode Gauss atau Gauss-Jordan */

public class OBE {
    private Matrix Augmented;
    private int[] indexMain = new int[1000];
    private double[] solusi = new double[1000];
    private String[] parameter = new String[1000];
    private boolean solusiUnik = false;
    private boolean noSolusi = false;
    private String stepByStep = "";

    /* ---------- KONSTRUKTOR ---------- */
    public OBE(){
        /* Kasus Kosong */
        this(0,0);
    }

    public OBE(int row, int col){
        /* Kasus Matrix */
        this.Augmented = new Matrix(row, col);
    }

    public OBE(OBE newOBE){
        /* Kasus OBE baru */
        this.Augmented = newOBE.Augmented;
        this.indexMain = newOBE.indexMain;
        this.solusi = newOBE.solusi;
        this.parameter = newOBE.parameter;
        this.solusiUnik = newOBE.solusiUnik;
        this.noSolusi = newOBE.noSolusi;
        this.stepByStep = newOBE.stepByStep;
    }

    /* ----------- KELOMPOK Interaksi dengan IO ----------- */
    @Override
    public String toString(){
        /* Melakukan Override fungsi untuk mempermudah penunjukan hasil */
        return "OBE{solusi unik: "+getSolusiUnik()+", tidak ada solusi: "+getNoSolusi()+"}";
    }

    public String formatDouble(int i, int j, int length){
        /* Mengubah format double menjadi lebih pendek */
        String temp = Augmented.createString(i, j, length);
        int panjang = temp.length();
        if (panjang < length){
            for (int x = 0; x < length-panjang; x++){
                temp = " "+temp;
            }
        }
        temp += " ";
        return temp;
    }

    public void printAugmented(){
        /* Menuliskan Matrix Augemented */
        Augmented.displayMatrix();
    }

    public void printIMain(){
        /* Menuliskan IndexMain */
        System.out.print("[");
        for (int i = 0; i < getMatrixRow(); i++){
            System.out.print(getIndexMain(i));
            if (i != getMatrixRow()-1){
                System.out.print(" ");
            }
        }
        System.out.println("]");
    }

    /* ---------- SELEKTOR ---------- */
    public int getMatrixRow(){
        /* Mengembalikan nilai baris efektif matrix */
        return Augmented.getRowEff();
    }

    public void setMatrixRow(int i){
        /* Mengubah nilai baris efektif matrix */
        Augmented.setRowEff(i);
    }

    public int getMatrixCol(){
        /* Mengembalikan nilai kolom efektif matrix */
        return Augmented.getColEff();
    }

    public void setMatrixCol(int j){
        /* Mengubah nilai kolom efektif matrix */
        Augmented.setColEff(j);
    }

    public double getMElmt(int i, int j){
        /* Mengembalikan nilai dari Elemen pada baris i+1 dan kolom j+1 */
        return Augmented.getElmt(i, j);
    }

    public void setMElmt(double val, int i, int j){
        /* Mengubah nilai dari Elemen pada baris i+1 dan kolom j+1 */
        Augmented.setElmt(val, i, j);
    }

    public int getIndexMain(int i){
        /* Mengembalikan nilai dari Elemen Indeks Main pada indeks i */
        return indexMain[i];
    }

    public void setIndexMain(int val, int i){
        /* Mengubah nilai dari Elemen Indeks Main pada indeks i */
        indexMain[i] = val;
    }

    public double getSolutions(int i){
        return solusi[i];
    }

    public void setSolutions(double val, int i){
        solusi[i] = val;
    }

    public String getParameter(int i){
        return parameter[i];
    }

    public void setParameter(String s, int i){
        parameter[i] = s;
    }

    public void setSolusiUnik(boolean cond){
        solusiUnik = cond;
    }

    public void setNoSolusi(boolean cond){
        noSolusi = cond;
    }

    public String getStep(){
        return new String(stepByStep);
    }

    public boolean getNoSolusi(){
        return noSolusi;
    }

    public boolean getSolusiUnik(){
        return solusiUnik;
    }

    /* ---------- KELOMPOK TEST ---------- */
    public boolean isContinue(){
        /* Mengetest apakah operasi OBE perlu dilanjutkan atau tidak */
        for (int i = 0; i < getMatrixRow()-1; i++){
            int pass = getIndexMain(i);
            if (pass == -1){
                setNoSolusi(true);
                //System.out.println("pass: "+pass);
                return false;
            }
            for (int j = i+1; j < getMatrixRow(); j++){
                if (pass != getMatrixCol() && getIndexMain(j) == pass){
                    //System.out.println("pass: "+pass+" ke-"+j+": "+getIndexMain(j));
                    return true;
                }
            }
        }
        //System.out.println("lewat");
        return false;
    }

    public boolean isSolusiUnik(){
        /* Melakukan test solusi unik */
        for (int i = 0; i < getMatrixRow(); i++){
            if (getIndexMain(i) != i){
                return false;
            }
        }
        return true;
    }

    /* ---------- KELOMPOK PENAMBAHAN Langkah ke dalam Output ----------- */
    public void addAugmentedToStep(int length){
        addNewLineToStep();
        for (int i = 0; i < getMatrixRow(); i++){
            for (int j = 0; j < getMatrixCol(); j++){
                String temp = formatDouble(i, j, length);
                stepByStep += temp;
            }
            stepByStep += "\n";
        }
        addNewLineToStep();
    }

    public void addSubstractToStep(int row1, int row2, double left, double right){
        String temp = ">>> ";
        String leftt = Double.toString(left);
        String rightt = Double.toString(right);
        String fleft = leftt;
        String fright = rightt;
        if (leftt.length() > 9){
            fleft = "";
            for (int i = 0; i < 9; i++){
                fleft += leftt.charAt(i);
            }
        }
        if (fright.length() > 9){
            fright = "";
            for (int i = 0; i < 9; i++){
                fright += rightt.charAt(i);
            }
        }
        if (left == right){
            temp += "R"+Integer.toString(row1 + 1)+ " - "+"R"+Integer.toString(row2 + 1);
        } else if (right%left == 0){
            int mul =(int) (right/left);
            temp += "R"+Integer.toString(row1 + 1)+" - ("+Integer.toString(mul)+")R"+Integer.toString(row2 + 1);
        } else if (left%right == 0){
            int mul =(int) (left/right);
            temp += Integer.toString(mul)+"R"+Integer.toString(row1 + 1)+" - R"+Integer.toString(row2 + 1);
        } else {
            int lval = (int)left;
            int rval = (int)right;
            if (lval == left && rval == right){
                if (lval == 1){
                    temp += "R"+Integer.toString(row1 + 1)+" - ("+rval+")R"+Integer.toString(row2 + 1);
                } else if(rval == 1){
                    temp += lval+"R"+Integer.toString(row1 + 1)+" - R"+Integer.toString(row2 + 1);
                } else {
                    temp += lval+"R"+Integer.toString(row1 + 1)+" - ("+rval+")R"+Integer.toString(row2 + 1);
                }
            } else if (lval == left && rval != right){
                if (lval == 1){
                    temp += "R"+Integer.toString(row1 + 1)+" - ("+fright+")R"+Integer.toString(row2 + 1);
                } else {
                    temp += lval+"R"+Integer.toString(row1 + 1)+" - ("+fright+")R"+Integer.toString(row2 + 1);
                }
            } else if (lval != left && rval == right){
                if (rval == 1){
                    temp += "("+fleft+")R"+Integer.toString(row1 + 1)+" - R"+Integer.toString(row2 + 1);
                }else{
                    temp += "("+fleft+")R"+Integer.toString(row1 + 1)+" - ()"+rval+")R"+Integer.toString(row2 + 1);
                }
            } else {
                temp += "("+fleft+")R"+Integer.toString(row1 + 1)+" - ("+fright+")R"+Integer.toString(row2 + 1);
            }

        }
        stepByStep += temp + "\n";
    }

    public void addNewLineToStep(){
        stepByStep += "\n";
    }

    public void addSwaptoStep(int row1, int row2){
        stepByStep += ">>> R"+ Integer.toString(row1+1) +" <--> R"+Integer.toString(row2+1)+"\n";
    }

    public void addMkOnetoStep(int row, Double val){
        int temp = (int) (val/1);
        String saved = ">>> ";
        if (temp == val){
            saved += "R"+Integer.toString(row+1)+"/"+Integer.toString(temp);
        } else {
            String vall = Double.toString(val);
            String fval = new String(vall);
            if (fval.length() > 9){
                fval = "";
                for (int i = 0; i < 9; i++){
                    fval += vall.charAt(i);
                }
            }
            saved += "R"+Integer.toString(row+1)+"/("+fval+")";
        }
        stepByStep += saved + "\n";
    }

    public void addNoSolutionsToStep(){
        addNewLineToStep();
        stepByStep += "Tidak ada Solusi\n";
    }

    public void addGaussJordanRejected(){
        addNewLineToStep();
        stepByStep += "\nTidak dapat melanjutkan proses Gauss-Jordan karena solusi tidak unik.\n\n";
    }

    public void addTitleStep(){
        stepByStep += "<><><><> Langkah Penyelesaian <><><><>\n\n>>> Matriks Augmented Awal:\n";
    }

    public void addGaussJordanAccepted(){
        stepByStep += ">>> Lanjutan Proses Gauss-Jordan\n\n";
    }

    /* ---------- KELOMPOK Pencarian ---------- */
    public int findIdxMain(int row){
        /* Mencari indeks kolom utama yaitu indeks pertama dengan nilai ELMT bukan = 0 */
        for (int i = 0; i < getMatrixCol(); i++){
            if (getMElmt(row, i) != 0){
                if (i < getMatrixCol()-1){
                    setIndexMain(i, row);
                    return i;
                }
                setIndexMain(-1, row);
                setNoSolusi(true);
                return -1;
            }
        }
        setSolusiUnik(false);
        setIndexMain(getMatrixCol(), row);
        return getMatrixCol();
    }

    /* ---------- KELOMPOK Fungsi Utama ---------- */
    public void roundAllElement(){
        /* Melakukan Pembulatan nilai*/
        for (int i = 0; i < getMatrixRow(); i++){
            for (int j = 0; j < getMatrixCol(); j++){
                double temp = getMElmt(i, j);
                temp *=10000000;
                temp = (double)Math.round(temp);
                temp /=10000000;
                setMElmt(temp, i, j);
            }
        }
    }

    public void refreshIdxMain(int i){
        /* Melakukan pembaruan nilai Index Main */
        for (int j = i; j < getMatrixRow(); j++){
            findIdxMain(j);
        }
    }

    public void substractRow(int row1, int row2){
        /* Mengurangi semua Element pada baris row1 dengan ELement pada baris row2 */
        int idx = findIdxMain(row2);
        double mainVal = getMElmt(row2, idx);
        double tempVal = getMElmt(row1, idx);
        addSubstractToStep(row1, row2, mainVal, tempVal);
        for (int i = idx; i < getMatrixCol(); i++){
            if (mainVal == tempVal){
                if(i == idx){
                    System.out.printf("   R%d - R%d\n\n", row1+1, row2+1);
                }
                setMElmt(getMElmt(row1, i)-getMElmt(row2, i), row1, i);
            } else if (mainVal%tempVal == 0){
                double mul = mainVal/tempVal;
                if(i == idx){
                    System.out.printf("   %dR%d - R%d\n\n",(int)mul,row1+1,row2+1);
                }
                setMElmt(getMElmt(row1, i)*mul-getMElmt(row2, i), row1, i);
            } else if (tempVal%mainVal == 0){
                double mul = tempVal/mainVal;
                if(i == idx){
                    System.out.printf("   R%d - (%d)R%d\n\n", row1+1,(int)mul, row2+1);
                }
                setMElmt(getMElmt(row1, i)-getMElmt(row2, i)*mul, row1, i);
            } else {
                if(i == idx){
                    System.out.printf("   %.3fR%d - (%.3f)R%d\n\n", mainVal,row1+1,tempVal, row2+1);
                }
                setMElmt(getMElmt(row1, i)*mainVal-getMElmt(row2, i)*tempVal, row1, i);
            }
        }
        printAugmented();
        System.out.println();
    }

    public void substractJordan(int row1,int row2){
        /* Melakukan Pengurangan Jordan atau mengurangi setiap baris sehingga menjadi */
        /* matrix eselon baris tereduksi */
        int idx = findIdxMain(row2);
        double rightVal = getMElmt(row2, idx);
        double leftVal = getMElmt(row1, idx);
        addSubstractToStep(row1, row2, rightVal, leftVal);
        if (leftVal == rightVal){
            System.out.printf("   R%d - R%d\n\n",row1+1,row2+1);
            for (int i = idx; i < getMatrixCol(); i++){
                setMElmt(getMElmt(row1, i)-getMElmt(row2, i), row1, i);
            }
        } else if(leftVal%rightVal == 0){
            double mul = leftVal/rightVal;
            System.out.printf("   R%d - (%d)R%d\n\n",row1+1,(int)mul,row2+1);
            for (int i = idx; i < getMatrixCol(); i++){
                setMElmt(getMElmt(row1, i)-getMElmt(row2, i)*mul, row1, i);
            }
        } else {
            System.out.printf("   %.3fR%d - (%.3f)R%d\n\n",rightVal,row1+1,leftVal,row2+1);
            for (int i = idx; i <getMatrixCol(); i++){
                setMElmt(getMElmt(row1, i)*rightVal-getMElmt(row2, i)*leftVal, row1, i);
            }
        }
        printAugmented();
        System.out.println();
    }

    public void swapRow(int row1, int row2){
        /* Menukar isi dari baris row 1 dengan baris row 2*/
        double temp;
        for (int i = 0; i < getMatrixCol(); i++){
            temp = getMElmt(row1, i);
            setMElmt(getMElmt(row2, i), row1, i);
            setMElmt(temp, row2, i);
        }
        int tmpIdx = getIndexMain(row1);
        setIndexMain(getIndexMain(row2), row1);
        setIndexMain(tmpIdx, row2);
        System.out.printf("   R%d <--> R%d\n\n", row1+1, row2+1);
        addSwaptoStep(row1, row2);
    }

    public void sortIdxMain(int start, boolean wasSwapped){
        /* I.S. Index Main dan Matrix Augmented bernilai */
        /* F.S. Index Main dan Matrix Augmented tersortir */
        for (int i = start; i < getMatrixRow()-1; i++){
            int temp = getIndexMain(i);
            int idx = i;
            for (int j = i+1; j < getMatrixRow(); j++){
                if (temp > getIndexMain(j)){
                    temp = getIndexMain(j);
                    idx = j;
                }
            }
            if (idx != i){
                wasSwapped = true;
                swapRow(idx, i);
                printAugmented();
                System.out.println();
            }
        }
        //addNewLineToStep();
        if (wasSwapped){
            addAugmentedToStep(9);
        }
        //addNewLineToStep();
    }

    public void mkOneMain(){
        /* Membuat satu baris sebagai baris Utama */
        for (int i = 0; i < getMatrixRow(); i++){
            int iMain = findIdxMain(i);
            if (iMain >= 0 && iMain < getMatrixCol()-1){
                double valMain = getMElmt(i, iMain);
                if (valMain != 1){
                    addMkOnetoStep(i, valMain);
                    System.out.printf("   R%d/%.3f\n\n", i+1, valMain);
                    for (int j = iMain; j < getMatrixCol(); j++){
                        setMElmt(getMElmt(i, j)/valMain, i, j);
                    }
                }
            }
        }
    }

    public void obeGauss(){
        /* I.S. Agumented bernilai */
        /* F.S. Agumented adalah hasil dari operasi OBE Metode Eliminasi Gauss */
        addTitleStep();
        addAugmentedToStep(9);
        boolean swapped = false;
        refreshIdxMain(0);
        sortIdxMain(0, swapped);
        if (!swapped){
            printAugmented();
        }
        swapped = false;
        System.out.println();
        int pass = 1;
        boolean lanjut = isContinue();
        while (lanjut && !getNoSolusi() && pass < getMatrixRow()){
            for (int i = pass; i < getMatrixRow(); i++){
                if (getIndexMain(i) == getIndexMain(pass-1)){
                    substractRow(i, pass-1);
                }
            }
            addAugmentedToStep(9);
            refreshIdxMain(pass);
            sortIdxMain(pass, swapped);
            pass++;
            System.out.println();
            lanjut = isContinue();
        }
        mkOneMain();
        roundAllElement();
        addAugmentedToStep(9);
        if (getNoSolusi()){
            addNewLineToStep();
            addNoSolutionsToStep();
        }
        printAugmented();
        if (isSolusiUnik()){
            setSolusiUnik(true);
        }
    }

    public void obeGaussJordan(){
        /* I.S. Agumented bernilai */
        /* F.S. Agumented adalah hasil dari operasi OBE Metode Eliminasi Gauss-Jordan */
        obeGauss();
        if(getSolusiUnik() && !getNoSolusi()){
            addGaussJordanAccepted();
            System.out.println("\n\nLanjutan: Metode Gauss-Jordan:\n");
            for (int i = getMatrixRow()-2; i >= 0; i--){
                for (int j = getMatrixRow()-1; j > i; j--){
                    substractJordan(i, j);
                }
            }
            addAugmentedToStep(9);
            roundAllElement();
            addAugmentedToStep(9);
        } else {
            addGaussJordanRejected();
            System.out.println("\nTidak dapat dilakukan metode Gauss-Jordan.\nKarena solusi tidak unik.\n");
        }
    }
}