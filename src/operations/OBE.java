package operations;

import java.lang.Math;

public class OBE extends Matrix{
    private Matrix Augmented;
    private int[] indexMain = new int[1000];
    private double[] solusi = new double[1000];
    private String[] parameter = new String[1000];
    private boolean solusiUnik = false;
    private boolean noSolusi = false;
    private String stepByStep = "";

    public OBE(){ // Konstruktor
        this(0,0);
    }

    public OBE(int row, int col){
        this.Augmented = new Matrix(row, col);
    }

    public OBE(OBE newOBE){
        this.Augmented = newOBE.Augmented;
        this.indexMain = newOBE.indexMain;
        this.solusi = newOBE.solusi;
        this.parameter = newOBE.parameter;
        this.solusiUnik = newOBE.solusiUnik;
        this.noSolusi = newOBE.noSolusi;
        this.stepByStep = newOBE.stepByStep;
    }

    public int getMatrixRow(){
        return Augmented.getRowEff();
    }
    public void setMatrixRow(int i){
        Augmented.setRowEff(i);
    }

    public int getMatrixCol(){
        return Augmented.getColEff();
    }
    public void setMatrixCol(int j){
        Augmented.setColEff(j);
    }

    public double getMElmt(int i, int j){
        return Augmented.getElmt(i, j);
    }
    public void setMElmt(double val, int i, int j){
        Augmented.setElmt(val, i, j);
    }

    public int getIndexMain(int i){
        return indexMain[i];
    }
    public void setIndexMain(int val, int i){
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

    public boolean getSolusiUnik(){
        return solusiUnik;
    }
    public void setSolusiUnik(boolean cond){
        solusiUnik = cond;
    }

    public boolean getNoSolusi(){
        return noSolusi;
    }
    public void setNoSolusi(boolean cond){
        noSolusi = cond;
    }

    public String getStep(){
        return new String(stepByStep);
    }
    public void addStringToStep(String add){
        stepByStep += add;
    }

    public void printAugmented(){
        Augmented.displayMatrix();
    }

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

    public String formatDouble(int i, int j, int length){
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

    public int findIdxMain(int row){
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

    public void refreshIdxMain(int i){
        for (int j = i; j < getMatrixRow(); j++){
            findIdxMain(j);
        }
    }

    public void substractRow(int row1, int row2){
        int idx = findIdxMain(row2);
        double mainVal = getMElmt(row2, idx);
        double tempVal = getMElmt(row1, idx);
        addSubstractToStep(row1, row2, mainVal, tempVal);
        for (int i = idx; i < getMatrixCol(); i++){
            if (mainVal == tempVal){
                if(i == idx){
                    //System.out.printf("   R%d - R%d\n\n", row1+1, row2+1);
                }
                setMElmt(getMElmt(row1, i)-getMElmt(row2, i), row1, i);
            } else if (mainVal%tempVal == 0){
                double mul = mainVal/tempVal;
                if(i == idx){
                    //System.out.printf("   %dR%d - R%d\n\n",(int)mul,row1+1,row2+1);
                }
                setMElmt(getMElmt(row1, i)*mul-getMElmt(row2, i), row1, i);
            } else if (tempVal%mainVal == 0){
                double mul = tempVal/mainVal;
                if(i == idx){
                    //System.out.printf("   R%d - (%d)R%d\n\n", row1+1,(int)mul, row2+1);
                }
                setMElmt(getMElmt(row1, i)-getMElmt(row2, i)*mul, row1, i);
            } else {
                if(i == idx){
                    //System.out.printf("   %.3fR%d - (%.3f)R%d\n\n", mainVal,row1+1,tempVal, row2+1);
                }
                setMElmt(getMElmt(row1, i)*mainVal-getMElmt(row2, i)*tempVal, row1, i);
            }
        }
        //printAugmented();
        //System.out.println();
    }

    public void swapRow(int row1, int row2){
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
        for (int i = 0; i < getMatrixRow(); i++){
            int iMain = findIdxMain(i);
            if (iMain >= 0 && iMain < getMatrixCol()-1){
                double valMain = getMElmt(i, iMain);
                if (valMain != 1){
                    addMkOnetoStep(i, valMain);
                    //System.out.printf("   R%d/%.3f\n\n", i+1, valMain);
                    for (int j = iMain; j < getMatrixCol(); j++){
                        setMElmt(getMElmt(i, j)/valMain, i, j);
                    }
                }
            }
        }
    }

    public boolean isContinue(){
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

    public void printIMain(){
        System.out.print("[");
        for (int i = 0; i < getMatrixRow(); i++){
            System.out.print(getIndexMain(i));
            if (i != getMatrixRow()-1){
                System.out.print(" ");
            }
        }
        System.out.println("]");
    }

    public void roundAllElement(){
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

    public void obeGauss(){
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
        if (isSolusiUnik()){
            addAugmentedToStep(9);
            setSolusiUnik(true);
        } else {
            setParameterSolutions();
            roundAllElement();
            addAugmentedToStep(9);
            addParameterToStep();
        }
        //printAugmented();
    }
    
    public boolean isSolusiUnik(){
        for (int i = 0; i < getMatrixRow(); i++){
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
        int idx = findIdxMain(row2);
        double rightVal = getMElmt(row2, idx);
        double leftVal = getMElmt(row1, idx);
        if (isAdd){
            addSubstractToStep(row1, row2, rightVal, leftVal);
        }
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
        //printAugmented();
        //System.out.println();
    }

    public void setSolusi(){
        for (int i = 0; i < getMatrixRow(); i++){
            setSolutions(getMElmt(i, getMatrixCol()-1),i);
        }
    }

    public void addSolutionToStep(){
        String temp = "";
        for (int i = 0; i < getMatrixCol()-1; i++){
            temp += "---> X"+(i+1)+" = "+getSolutions(i)+"\n";
        }
        stepByStep += temp;
    }

    public void obeGaussJordan(){
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
            setSolusi();
            addAugmentedToStep(9);
        } else {
            addGaussJordanRejected();
            //System.out.println("\nTidak dapat dilakukan metode Gauss-Jordan.\nKarena solusi tidak unik.\n");
        }
    }

    public void gaussAndSolutions(){
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
        }
        if (isSolusiUnik()){
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
        Parameter[] result = new Parameter[1000];
        int existedVar = getMatrixCol()-1;
        for (int i = getMatrixRow()-1; i >= 0; i--){
            int iMainTemp = findIdxMain(i);
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

    @Override
    public String toString(){
        return "OBE{solusi unik: "+getSolusiUnik()+", tidak ada solusi: "+getNoSolusi()+"}";
    }

}