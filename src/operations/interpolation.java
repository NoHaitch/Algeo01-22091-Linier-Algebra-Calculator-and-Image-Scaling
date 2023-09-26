package operations;
public class interpolation {
    Matrix m = new Matrix();
    public static Matrix convertPtoM (Matrix m){
        int i, j, n, k;
        double x,y;
        n = m.getRowEff();
        Matrix newm = new Matrix(n,n + 1);
        for (i = 0; i < n;i++){
            x = m.getElmt(i, 0);
            y = m.getElmt(i, 1); 
            k = 1;
            for (j = n; j >= 0; j--){
                if (j == n){
                    newm.setElmt(y, i, j);
                } else{
                    newm.setElmt(k,i,j);
                    k*= x;
                }
            }
        }    
        return newm;
    }
}
