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
        this(200,150);
    }

    public OBE(int row, int col){
        /* Kasus Matrix */
        this.Augmented = new Matrix(row, col);
    }

    public OBE(OBE newOBE){
        this.Augmented = new Matrix(newOBE.Augmented);
        this.indexMain = new int[1000];
        this.solusi = new double[1000];
        this.parameter = new String[1000];
        for (int i = 0; i < this.getMatrixCol(); i++){
            this.indexMain[i] = newOBE.indexMain[i];
            this.solusi[i] = newOBE.solusi[i];
            this.parameter[i] = newOBE.parameter[i];
        }
        this.solusiUnik = newOBE.solusiUnik;
        this.noSolusi = newOBE.noSolusi;
        this.stepByStep = new String(newOBE.stepByStep);
    }

    public Matrix getCopyAugmented(){
        return new Matrix(Augmented);
    }

    public void setAugmented(Matrix m){
        this.Augmented = new Matrix(m);
    }

    public double[] getAllCol(int j){
        return Augmented.getCol(j);
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
                temp = " " + temp;
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

    public void setSolusi(){
        for (int i = 0; i < getMatrixRow(); i++){
            setSolutions(getMElmt(i, getMatrixCol()-1),i);
        }
    }

    /* ----------- KELOMPOK Operasi Penerus dari Matriks Augmented ----------- */
    public boolean isSquare(){
        return Augmented.isSquare();
    }

    public boolean isIdxValid(int i, int j){
        return Augmented.isIdxValid(i, j);
    }

    public boolean isIdxEff(int i, int j){
        return Augmented.isIdxEff(i, j);
    }

    public double determinant(){
        return Augmented.determinant();
    }

    public void transposeMatrix(){
        Augmented.transposeMatrix();
    }

    public Matrix rTransposeMatrix(){
        return Augmented.rTransposeMatrix();
    }

    public void multiplyMatrixByConst(double k){
        Augmented.multiplyMatrixByConst(k);
    }

    public Matrix kofakMatrix(){
        return Augmented.kofaktorMatrix();
    }

    public Matrix inversMatrix(){
        return Augmented.inversMatrix();
    }

    public Matrix multiplyMatrix(Matrix m){
        return Augmented.multiplyMatrix(m);
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

    public void addStringToStep(String add){
        stepByStep += add;
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

    public void addSolutionToStep(){
        String temp = "";
        for (int i = 0; i < getMatrixCol()-1; i++){
            temp += "---> X"+(i+1)+" = "+getSolutions(i)+"\n";
        }
        stepByStep += temp;
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
                double temp = getMElmt(row1, i)-getMElmt(row2, i);
                if (temp == -0.0){
                    temp = 0.0;
                }
                setMElmt(temp, row1, i);
            } else if (mainVal%tempVal == 0){
                double mul = mainVal/tempVal;
                double temp = getMElmt(row1, i)*mul-getMElmt(row2, i);
                if (temp == -0.0){
                    temp = 0.0;
                }
                setMElmt(temp, row1, i);
            } else if (tempVal%mainVal == 0){
                double mul = tempVal/mainVal;
                double temp = getMElmt(row1, i)-getMElmt(row2, i)*mul;
                if (temp == -0.0){
                    temp = 0.0;
                }
                setMElmt(temp, row1, i);
            } else {
                double temp = getMElmt(row1, i)*mainVal-getMElmt(row2, i)*tempVal;
                if (temp == -0.0){
                    temp = 0.0;
                }
                setMElmt(temp, row1, i);
            }
        }
        //printAugmented();
        //System.out.println();
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
        //System.out.printf("   R%d <--> R%d\n\n", row1+1, row2+1);
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
                //printAugmented();
                //System.out.println();
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
                    //System.out.printf("   R%d/%.3f\n\n", i+1, valMain);
                    for (int j = iMain; j < getMatrixCol(); j++){
                        double temp = getMElmt(i, j)/valMain;
                        if (temp == -0.0){
                            temp = 0.0;
                        }
                        setMElmt(temp, i, j);
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
            //printAugmented();
        }
        swapped = false;
        //System.out.println();
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
            //System.out.println();
            lanjut = isContinue();
        }
        mkOneMain();
        if (getNoSolusi()){
            addNewLineToStep();
            addNoSolutionsToStep();
        }
        else if (isSolusiUnik()){
            addAugmentedToStep(9);
            setSolusiUnik(true);
        } //else {
        //    setParameterSolutions();
        //    roundAllElement();
        //    addAugmentedToStep(9);
        //    addParameterToStep();
        //}
        //printAugmented();
    }
    
    public boolean isSolusiUnik(){
        if (getMatrixRow() < getMatrixCol()-1){
            return false;
        }
        for (int i = 0; i < getMatrixCol()-1; i++){
            if (getIndexMain(i) != i){
                return false;
            }
        }
        return true;
    }

    public void addParameterToStep(){
        String temp = "";
        for (int i = 0; i < getMatrixCol()-1; i++){
            temp += "---> X"+(i+1)+" = "+getParameter(i)+"\n";
        }
        stepByStep += temp;
    }

    public void substractJordan(int row1,int row2, boolean isAdd){
        /* Melakukan Pengurangan Jordan atau mengurangi setiap baris sehingga menjadi */
        /* matrix eselon baris tereduksi */
        int idx = findIdxMain(row2);
        double rightVal = getMElmt(row2, idx);
        double leftVal = getMElmt(row1, idx);
        if (isAdd){
            addSubstractToStep(row1, row2, rightVal, leftVal);
        }
        if (leftVal == rightVal){
            //System.out.printf("   R%d - R%d\n\n",row1+1,row2+1);
            for (int i = idx; i < getMatrixCol(); i++){
                double temp = getMElmt(row1, i)-getMElmt(row2, i);
                if (temp == -0.0){
                    temp = 0.0;
                }
                setMElmt(temp, row1, i);
            }
        } else if(leftVal%rightVal == 0){
            double mul = leftVal/rightVal;
            //System.out.printf("   R%d - (%d)R%d\n\n",row1+1,(int)mul,row2+1);
            for (int i = idx; i < getMatrixCol(); i++){
                double temp = getMElmt(row1, i)-getMElmt(row2, i)*mul;
                    if (temp == -0.0){
                        temp = 0.0;
                    }
                setMElmt(temp, row1, i);
            }
        } else {
            //System.out.printf("   %.3fR%d - (%.3f)R%d\n\n",rightVal,row1+1,leftVal,row2+1);
            for (int i = idx; i <getMatrixCol(); i++){
                double temp = getMElmt(row1, i)*rightVal-getMElmt(row2, i)*leftVal;
                    if (temp == -0.0){
                        temp = 0.0;
                    }
                setMElmt(temp, row1, i);
            }
        }
        //printAugmented();
        //System.out.println();
    }

    public void obeGaussJordan(){
        /* I.S. Agumented bernilai */
        /* F.S. Agumented adalah hasil dari operasi OBE Metode Eliminasi Gauss-Jordan */
        obeGauss();
        if(getSolusiUnik() && !getNoSolusi()){
            addGaussJordanAccepted();
            //System.out.println("\n\nLanjutan: Metode Gauss-Jordan:\n");
            for (int i = getMatrixRow()-2; i >= 0; i--){
                for (int j = getMatrixRow()-1; j > i; j--){
                    substractJordan(i, j, true);
                }
            }
            addAugmentedToStep(9);
            roundAllElement();
            addAugmentedToStep(9);
            setSolusi();
            //addAugmentedToStep(9);
            addSolutionToStep();
        } else {
            addGaussJordanRejected();
            setParameterSolutions();
            roundAllElement();
            addAugmentedToStep(9);
            addParameterToStep();
            //System.out.println("\nTidak dapat dilakukan metode Gauss-Jordan.\nKarena solusi tidak unik.\n");
        }
    }

    public void gaussAndSolutions(){
        /* Melakukan Operasi OBE Metode Eliminasi Gauss serta mencari solusinya */
        addTitleStep();
        addAugmentedToStep(9);
        boolean swapped = false;
        refreshIdxMain(0);
        sortIdxMain(0, swapped);
        if (!swapped){
            //printAugmented();
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
            //System.out.println();
            lanjut = isContinue();
        }
        mkOneMain();
        if (getNoSolusi()){
            addNewLineToStep();
            addNoSolutionsToStep();
        } else if (isSolusiUnik()){
            addAugmentedToStep(9);
            setSolusiUnik(true);
            for (int i = getMatrixRow()-2; i >= 0; i--){
                for (int j = getMatrixRow()-1; j > i; j--){
                    substractJordan(i, j, false);
                }
            }
            roundAllElement();
            setSolusi();
            //addAugmentedToStep(9);
            addSolutionToStep();
        } else {
            setParameterSolutions();
            roundAllElement();
            addAugmentedToStep(9);
            addParameterToStep();
        }
        //printAugmented();
    }

    public void setParameterSolutions(){
        /* Menentukan Parameter Solusi */
        Parameter[] result = new Parameter[1000];
        int existedVar = getMatrixCol()-1;
        for (int i = getMatrixRow()-1; i >= 0; i--){
            int iMainTemp = findIdxMain(i);
            //System.out.println(iMainTemp);
            if (iMainTemp == getMatrixCol()-2){
                if (result[iMainTemp] == null){
                    result[iMainTemp] = new Parameter();
                }
                result[iMainTemp].setParamtr(getMElmt(i, iMainTemp+1), 0, 0, true);
                existedVar = iMainTemp;
                setParameter(result[iMainTemp].turnIntoString(iMainTemp, getMatrixCol()-1), iMainTemp);
            } else {
                if (result[iMainTemp] == null){
                    result[iMainTemp] = new Parameter();
                }
                for (int j = getMatrixCol()-2; j > iMainTemp; j--){
                    if (result[j] == null){
                        result[j] = new Parameter();
                    }
                    if (j < existedVar && !result[j].isTerisi()){
                        result[j].setParamtr(0, j, 1, true);
                        existedVar = j;
                        setParameter(result[j].turnIntoString(j, getMatrixCol()-1), j);
                    }
                }
                result[iMainTemp].setParamtr(getMElmt(i, getMatrixCol()-1), 0, 0, true);
                for (int j = getMatrixCol()-2; j > iMainTemp; j--){
                    if (getMElmt(i, j) != 0){
                        result[iMainTemp].setParamtr(getMElmt(i, j)*result[j].number*(-1), 0, 0, true);
                        //System.out.println("yang mw dicek di "+i+", "+j+": "+getMElmt(i, j)*result[j].number*(-1));
                        for (int k = j; k < getMatrixCol()-1; k++){
                            result[iMainTemp].setParamtr(0, k, getMElmt(i, j)*result[j].var[k]*(-1), true);
                            //System.out.println("yang mw dicek variabelnya di "+i+", "+k+": "+getMElmt(i, j)*result[j].var[k]*(-1));
                        }
                    }
                }
                existedVar = iMainTemp;
                setParameter(result[iMainTemp].turnIntoString(iMainTemp, getMatrixCol()-1), iMainTemp);
            }
        }

    }
}