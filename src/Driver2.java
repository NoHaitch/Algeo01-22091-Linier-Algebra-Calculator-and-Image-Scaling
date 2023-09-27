import models.BicubicSpline;

public class Driver2 {
    public static void main(String[] args) {
        BicubicSpline bic = new BicubicSpline();
        bic.inputInitFromText("src/bicInput.txt");
        bic.initF.displayMatrix();
        bic.solveBicubic();
        double[][] xy = new double[][]{{0,0},{0.5,0.5},{0.25,0.75},{0.1,0.9}};
        double f = 0;
        //for (double[] temp : xy){
        //    f = bic.getFValueOf(temp[0], temp[1]);
        //}
        f = bic.getRequestAnswer();
        bic.saveProccessesToText("test/firstBicubicTest.txt");
        System.out.println(bic.function);
    }
}