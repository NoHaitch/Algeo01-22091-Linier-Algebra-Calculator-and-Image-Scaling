package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

public class MenuInputFile extends JFrame{
    private JPanel mainPanel;
    private JLabel mainLabel;
    private JButton findFileButton;
    private JButton calculasiButton;
    private JButton kembaliButton;
    private JTextField pathLabel;

    public MenuInputFile(String lastMenu){
        setContentPane(mainPanel);
        setTitle("Main Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600, 240);
        setVisible(true);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        findFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                // invoke the showsOpenDialog function to show the save dialog
                int r = j.showOpenDialog(null);

                // if the user selects a file
                if (r == JFileChooser.APPROVE_OPTION)

                {
                    // set the label to the path of the selected file
                    pathLabel.setText(j.getSelectedFile().getAbsolutePath());
                }
                // if the user cancelled the operation
                else
                    pathLabel.setText("the user cancelled the operation");
            }
        });
        kembaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                if(lastMenu == "SPL"){
                    MenuInputMatrix menu = new MenuInputMatrix(lastMenu);
                }
            }
        });
        calculasiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuResult menu = new MenuResult(pathLabel.getText(),"f","SPL");
            }
        });
    }
}
