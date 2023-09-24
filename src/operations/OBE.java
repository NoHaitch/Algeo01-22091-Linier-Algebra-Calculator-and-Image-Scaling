package operations;

import java.lang.Math;

public class OBE {
    private Matrix Augmented;
    private int[] indexMain = new int[1000];
    private double[] solusi = new double[1000];
    private String[] parameter = new String[1000];
    private boolean solusiUnik = false;
    private boolean noSolusi = false;

    public OBE(){ // Konstruktor
        this(0,0);
    }

    public OBE(int row, int col){
        this.Augmented = new Matrix(row, col);
    }

    public int getMatrixRow(){
        return Augmented.getRowEff();
    }

    public int getMatrixCol(){
        return Augmented.getColEff();
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

    public void printAugmented(){
        Augmented.displayMatrix();
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
        System.out.printf("   R%d <--> R%d\n\n", row1+1, row2+1);
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
                printAugmented();
                System.out.println();
            }
        }
    }

    public void mkOneMain(){
        for (int i = 0; i < getMatrixRow(); i++){
            int iMain = findIdxMain(i);
            if (iMain >= 0 && iMain < getMatrixCol()-1){
                double valMain = getMElmt(i, iMain);
                for (int j = iMain; j < getMatrixCol(); j++){
                    setMElmt(getMElmt(i, j)/valMain, i, j);
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
        boolean swapped = false;
        refreshIdxMain(0);
        sortIdxMain(0, swapped);
        if (!swapped){
            printAugmented();
        }    
        System.out.println();
        int pass = 1;
        boolean lanjut = isContinue();
        while (lanjut && !getNoSolusi() && pass < getMatrixRow()){
            for (int i = pass; i < getMatrixRow(); i++){
                if (getIndexMain(i) == getIndexMain(pass-1)){
                    substractRow(i, pass-1);
                }
            }
            refreshIdxMain(pass);
            sortIdxMain(pass, swapped);
            pass++;
            System.out.println();
            lanjut = isContinue();
        }
        System.out.println("End of Gauss Method: \n");
        mkOneMain();
        roundAllElement();
        printAugmented();
        if (isSolusiUnik()){
            setSolusiUnik(true);
        }
    }
    
    public boolean isSolusiUnik(){
        for (int i = 0; i < getMatrixRow(); i++){
            if (getIndexMain(i) != i){
                return false;
            }
        }
        return true;
    }

    public void substractJordan(int row1,int row2){
        int idx = findIdxMain(row2);
        double rightVal = getMElmt(row2, idx);
        double leftVal = getMElmt(row1, idx);
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

    public void obeGaussJordan(){
        obeGauss();
        if(getSolusiUnik()){
            System.out.println("\n\nLanjutan: Metode Gauss-Jordan:\n");
            for (int i = getMatrixRow()-2; i >= 0; i--){
                for (int j = getMatrixRow()-1; j > i; j--){
                    substractJordan(i, j);
                }
            }
            roundAllElement();
        } else {
            System.out.println("\nTidak dapat dilakukan metode Gauss-Jordan.\nKarena solusi tidak unik.\n");
        }
    }

    @Override
    public String toString(){
        return "OBE{solusi unik: "+getSolusiUnik()+", tidak ada solusi: "+getNoSolusi()+"}";
    }

}
