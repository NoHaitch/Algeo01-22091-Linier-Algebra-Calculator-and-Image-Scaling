package interfacec;

import operations.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInputKetikMatrix extends JFrame{
    private Matrix M;
    private JPanel mainPanel;
    private JTextField rowInput;
    private JTextField colInput;
    private JTextArea matrixInput;
    private JButton calculateButton;
    private JButton kembaliButton;
    private JPanel buttonPanel;
    private JLabel mainLabel;
    private JLabel sizeLabel;
    private JLabel matrixLabel;
    private JPanel sizePanel;
    public void setMatrix(){

    }
    public MenuInputKetikMatrix(String lastMenu){
        setContentPane(mainPanel);
        setTitle("Main Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(840, 500);
        setVisible(true);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        kembaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuInputMatrix menu = new MenuInputMatrix(lastMenu);
            }
        });
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean sizeValid = false;
                int rowEff = 0;
                int colEff = 0;
                try {
                    rowEff = Integer.parseInt(colInput.getText());
                    colEff = Integer.parseInt(rowInput.getText());
                    sizeValid = true;
                }
                catch (NumberFormatException nfe){
                    ErrorPopOut popOut = new ErrorPopOut("Masukkan salah format","<html>Ukuran Matriks harus dalam bentuk bilangan bulat<br>(pastikan tidak ada spasi di akhir angka)</html>");
                }
                if(sizeValid){
                    Matrix M = new Matrix(rowEff,colEff);
                    String inputText = matrixInput.getText();
                    String[] lines = inputText.split("\n");
                    if(countElmtInput(lines) == (M.getRowEff() * M.getColEff()) && M.getColEff() == countColInput(lines) && M.getRowEff() == countRowInput(lines)){
                        int i = 0;
                        boolean error = false;
                        for(String line : lines){
                            String[] splitedInput = line.split(" ");
                            int j = 0;
                            for (String s : splitedInput) {
                                if(s.contains("\n")){
                                    s = s.replace("\n","");
                                }
                                try {
                                    double elmt = Double.parseDouble(s);
                                    M.setElmt(elmt,i,j);
                                }
                                catch (NumberFormatException nfe){
                                    ErrorPopOut popOut = new ErrorPopOut("Masukkan salah format","<html>Isi Matriks harus dalam bentuk bilangan atau desimal<br>Pastikan setiap elemen dipisahkan spasi dan setiap baris dipisahkan enter</html>");
                                    error = true;
                                }
                                if(error){
                                    break;
                                }
                                j++;
                            }
                            if(error){
                                break;
                            }
                            i++;
                        }
                        System.out.println(countColInput(lines) + " col " + M.getColEff());
                        System.out.println(countRowInput(lines) + " row " + M.getRowEff());
                        M.displayMatrix();
                    }
                    else {
                        ErrorPopOut popOut = new ErrorPopOut("Ukuran Matriks dan masukan isi berbeda jumlahnya","Masukan Matriks harus sesuai dengan ukurannya");
                    }
                }
            }
        });
    }

    public Matrix getMatrix(){
        /* Prekondisi Input ketik berhasil dan Matriks bernilai */
        /* Mengembalikan Matriks hasil input ketik*/
        return M;
    }

    public int countElmtInput(String[] lines){
        int count = 0;
        for(String line: lines){
            for(String elmt: line.split(" ")){
                count ++;
            }
        }
        return count;
    }
    public int countRowInput(String[] lines){
        int count = 0;
        for(String line: lines){
            for(String elmt: line.split(" ")){
                count ++;
            }
            break;
        }
        return count;
    }
    public int countColInput(String[] lines){
        int count = 0;
        for(String line: lines){
            count ++;
        }
        return count;
    }

}
