package operations;


public class Parameter {
    double number = 0f;
    double[] var = new double[1000];
    boolean terisi = false;
    char symbol = (char) 181; // 181

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
        for (int i = first; i < last; i++){
            if (var[i] != 0){
                int val = (int) var[i];
                if (val == var[i]){
                    if (val == 1){
                        temp += symbol+Integer.toString(i+1) + " ";
                    } else if (val == -1){
                        temp += "-"+symbol+Integer.toString(i+1)+" ";
                    } else {
                        if (val < 0 || temp == ""){
                            temp += Integer.toString(val)+symbol+Integer.toString(i+1)+" ";
                        } else {
                            temp += "+ "+Integer.toString(val)+symbol+Integer.toString(i+1)+" ";
                        }
                    }
                } else {
                    String str = Double.toString(number);
                    if (number > 0 && temp != ""){
                        temp += "+ ";
                    }
                    if (str.length() < 9){
                        temp += str;
                    } else {
                        for (int j = 0; j < 9; j++){
                            temp += str.charAt(j);
                        }
                    }
                    temp += symbol+Integer.toString(i+1)+" ";
                }
            }
        }
        if (number != 0){
            int val = (int) number;
            if (val == number){
                temp = val +" " + temp;
            } else {
                //System.out.println("Nilai :"+Math.round(number));
                String str = Double.toString(number);
                if (str.length() < 9){
                    temp = str + " " + temp;
                } else {
                    String slice = "";
                    for (int i = 0; i < 9; i++){
                        slice += str.charAt(i);
                    }
                    temp = slice + " "+temp;
                }
            }
        } else {
            if (temp == ""){
                temp += "0";
            }
        }
        return temp;
        //return symbol+first+" ";
    }

    public boolean isTerisi(){
        return terisi;
    }
}
    