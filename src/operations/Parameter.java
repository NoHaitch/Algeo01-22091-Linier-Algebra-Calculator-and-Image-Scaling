package operations;


public class Parameter {
    double number = 0f;
    double[] var = new double[1000];
    boolean terisi = false;
    char symbol = (char)966; // 181

    public Parameter(){
    }
    
    public void setParamtr(double number, int idx, double koef, boolean terisi){
        //System.out.println(number);
        //System.out.println(idx);
        //System.out.println(koef);
        this.number += number;
        var[idx] += koef;
        this.terisi = terisi;
    }

    public String turnIntoString(int first, int last){
        String temp = "";
        boolean allZero = true;
        if (number != 0){
            allZero = false;
            int val = (int) number;
            if (val == number){
                temp += val +" ";
            } else {
                //System.out.println("Nilai :"+Math.round(number));
                String str = Double.toString(number);
                for (int i = 0; i < 9; i++){
                    temp += str.charAt(i);
                }
                temp += " ";
            }
        }
        for (int i = first; i < last; i++){
            if (var[i] != 0){
                allZero = false;
                int val = (int) var[i];
                if (val == var[i]){
                    if (val == 1){
                        temp += "X"+(i+1) + " ";
                    } else if (val == -1){
                        temp += "-X"+(i+1)+" ";
                    } else {
                        temp += val+"X"+(i+1)+" ";
                    }
                } else {
                    String str = Double.toString(number);
                    for (int j = 0; j < 9; j++){
                        temp += str.charAt(j);
                    }
                    temp += "X"+(i+1)+" ";
                }
            }
        }
        if (allZero){
            return "0";
        }
        return temp;
    }

    public boolean isTerisi(){
        return terisi;
    }
}
    