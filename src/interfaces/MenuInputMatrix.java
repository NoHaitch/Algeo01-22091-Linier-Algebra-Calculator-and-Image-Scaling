package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MenuInputMatrix extends JFrame{
    private JPanel mainPanel;
    private JButton ketikButton;
    private JButton fileButton;
    private JButton kembaliButton;
    private JLabel mainLabel;

    public MenuInputMatrix(String lastMenu){
        setContentPane(mainPanel);
        setTitle("Main Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400, 240);
        setVisible(true);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        kembaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                if(Objects.equals(lastMenu, "SPL")){
                    MenuSPL menu = new MenuSPL();
                }
            }
        });
        ketikButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuInputKetikMatrix menuInputKetik = new MenuInputKetikMatrix(lastMenu);
            }
        });
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuInputFile menuInputFile = new MenuInputFile(lastMenu);
            }
        });
    }
}
