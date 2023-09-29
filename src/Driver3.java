public class Driver3 {
    public static void main(String[] args){
        double x = 1;
        double y = 1;
        for (int j = 0; j < 4; j++){
            for (int i = 0; i < 4; i++){
                System.out.print(Math.pow(x,i)*Math.pow(y,j)+"\n");
            }
        }
    }
}
