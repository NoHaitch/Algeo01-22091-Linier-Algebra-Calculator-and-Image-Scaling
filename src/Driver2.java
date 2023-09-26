import models.SPL;;

public class Driver2 {
    public static void main(String[] args) {
        SPL x = new SPL();
        x.inputSPLText();
        SPL temp = new SPL(x);
        temp.spl.setSolusiUnik(true);
        x.spl.gaussAndSolutions();
        temp.spl.setMElmt(9999, 0, 0);
        String str = x.spl.getStep();
        System.out.println(str);
        System.out.println(x.spl.getSolusiUnik());
        temp.spl.printAugmented();
        System.out.println();
        x.spl.printAugmented();
    }
}
