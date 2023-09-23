import operations.*;

public class Main {
    public static void main(String[] args) {
        // menu
        Matrix M = new Matrix(4,4);
        for(int i = 0; i<4;i++){
            for(int j=0; j<4; j++){
                M.setElmt(2,i,j);
            }
        }
        M.setElmt(5,3,3);
        M.setElmt(0,3,0);
        M.setElmt(0,3,1);
        M.setElmt(0,3,2);
        M.setElmt(0,3,3);
        M.setElmt(0,0,0);
        M.setElmt(0,0,1);
        M.setElmt(0,1,0);
        M.displayMatrix();
        OBE MOBE = new OBE(M);
        MOBE.displayIndeksUtama();
        MOBE.displayMatrix();
        MOBE.sortMatriksOBE();
        MOBE.displayIndeksUtama();
        MOBE.displayMatrix();
        System.out.println(MOBE.cekBersolusi());
    }
}