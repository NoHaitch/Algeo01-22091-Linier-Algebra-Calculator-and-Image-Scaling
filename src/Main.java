import operations.*;

public class Main {
    public static void main(String[] args) {
        // menu
        Matrix M = new Matrix(4,3);
        for(int i=0;i<4;i++){
            for(int j=0; j<3; j++){
                M.setElmt(i+j,i,j);
            }
        }
        M.displayMatrix();
        OBE MOBE = new OBE(M);
        MOBE.sortMatriksOBE();
        System.out.println("============");
        MOBE.displayIndeksUtama();
        MOBE.displayMatrix();
    }
}