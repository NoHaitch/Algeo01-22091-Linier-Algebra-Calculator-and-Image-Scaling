package operations;

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
            setMElmt(getMElmt(row1, i)-(getMElmt(row2, i)*tempVal/mainVal), row1, i);
        }
        int nextIdx = findIdxMain(row1);
        if (nextIdx < getMatrixCol()-1){
            double temp = getMElmt(row1, nextIdx);
            for (int i = nextIdx; i < getMatrixCol(); i++){
                setMElmt(getMElmt(row1, i)/temp, row1, i);
            }
        }
        System.out.printf("   R%d - (%f/%f)R%d\n", row1,tempVal,mainVal , row2);
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
        System.out.printf("   R%d <--> R%d\n", row1, row2);
    }

    public void sortIdxMain(int start){
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
                swapRow(idx, i);
            }
        }
    }

    public void mkOneMain(int row){
        int idx = findIdxMain(row);
        if (idx != getMatrixCol()){
            double temp = getMElmt(row, idx);
            for (int i = idx; i < getMatrixCol(); i++){
                setMElmt(getMElmt(row, i)/temp, row, i);
            }
        }
    }

    public boolean isContinue(){
        for (int i = 0; i < getMatrixRow()-1; i++){
            int temp = getIndexMain(i);
            if (temp == -1){
                setNoSolusi(true);
                return false;
            }
            for (int j = i+1; j < getMatrixRow(); j++){
                if (temp == getIndexMain(j)){
                    return true;
                }
            }
        }
        return false;
    }

    public void obeGauss(){
        printAugmented();
        System.out.println();
        refreshIdxMain(0);
        sortIdxMain(getMatrixCol());
        mkOneMain(0);
        printAugmented();
        System.out.println();
        int pass = 1;
        while (isContinue() && !getNoSolusi() && pass < getMatrixRow()){
            for (int i = pass; i < getMatrixRow(); i++){
                if (getIndexMain(i) == getIndexMain(pass-1)){
                    substractRow(i, pass-1);
                }
            }
            sortIdxMain(pass);
            pass++;
            printAugmented();
            System.out.println();
        }
    }    
}
