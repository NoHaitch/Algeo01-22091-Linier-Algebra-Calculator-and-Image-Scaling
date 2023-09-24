package interfacec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuSPL extends JFrame{
    private JPanel mainPanel;
    private JLabel mainLabel;
    private JButton GaussButton;
    private JButton GaussJordanButton;
    private JButton MBallikanButton;
    private JButton KramerButton;
    private JButton kembaliButton;

    public MenuSPL() {
        setContentPane(mainPanel);
        setTitle("Menu SPL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 420);
        setVisible(true);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        kembaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuMain menu = new MenuMain();
            }
        });
    }
}
