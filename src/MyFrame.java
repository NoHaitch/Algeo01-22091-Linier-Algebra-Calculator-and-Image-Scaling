import javax.swing.*;

public class MyFrame extends JFrame{
    private JButton interpolasiPolinomButton;
    private JButton splButton;
    private JButton balikanButton;
    private JButton determinanButton;
    private JButton interpolasiBicubicButton;
    private JButton regresiLinierButton;
    private JButton keluarButton;
    private JPanel mainPanel;
    public MyFrame() {
        setContentPane(mainPanel);
        setTitle("Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        setVisible(true);
    }
    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
    }
}
