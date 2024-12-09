import java.awt.BorderLayout;

import java.io.File;

import javax.swing.*;

public class RecursiveLister extends JFrame {
    private JButton fileChooserBtn;
    private JButton quitBtn;
    private JTextArea output;
    private JLabel currDirLabel;
    public RecursiveLister() {

        super("Recursive Lister");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JPanel btnPanel = new JPanel();

        fileChooserBtn = new JButton("Choose Directory");
        btnPanel.add(fileChooserBtn);

        fileChooserBtn.addActionListener(e -> {
            output.setText("");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int status = fileChooser.showOpenDialog(this);
            if (status == JFileChooser.APPROVE_OPTION) {
                File f = fileChooser.getSelectedFile();
                currDirLabel.setText("Current directory: " + f);
                listFiles(f);
            }
        });

        quitBtn = new JButton("Quit");
        btnPanel.add(quitBtn);

        quitBtn.addActionListener(e -> {
            int confirmed = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to quit?", "Quit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });


        output = new JTextArea(10, 40);
        currDirLabel = new JLabel("Current directory:");

        add(btnPanel, BorderLayout.NORTH);
        add(new JScrollPane(output));
        add(currDirLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new RecursiveLister();
    }

    private void listFiles(File file) {
        if (file.isFile()) {
            output.append(file + "\n");
        } else {
            File files[] = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    output.append(f + "\n");
                    if (f.isDirectory()) {
                        listFiles(f);
                    }
                }
            }
        }
    }
}