package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuMain extends JFrame{
    private JButton interpolasiPolinomButton;
    private JButton splButton;
    private JButton balikanButton;
    private JButton determinanButton;
    private JButton interpolasiBicubicButton;
    private JButton regresiLinierButton;
    private JButton keluarButton;
    private JPanel mainPanel;
    private JPanel IdentityPanel;
    private JLabel IdentityLabel;
    private JLabel MainLabel;

    public MenuMain() {
        setContentPane(mainPanel);
        setTitle("Main Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 520);
        setVisible(true);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        keluarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        splButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuSPL menu = new MenuSPL();
            }
        });
    }
    public static void main(String[] args) {
        MenuMain myFrame = new MenuMain();
    }
}
