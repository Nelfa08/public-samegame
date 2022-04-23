package fr.iutfbleau.samegame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Fenetre extends JFrame implements ActionListener {
    private Menu menu = new Menu();
    private String fichier_choix;

    public Fenetre() {
        super();
        this.setTitle("SameGame");
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        menu.jouer.addActionListener(this);
        menu.importer.addActionListener(this);
        this.getContentPane().add(this.menu);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource().equals(menu.jouer)) {
            System.out.println("jouer");
            Grille grille = new Grille(10,15);
            setVisible(false);
        }

        if(event.getSource().equals(menu.importer)) {
            System.out.println("import");
            JFileChooser choix = new JFileChooser();
            choix.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            if (choix.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
                File file = choix.getSelectedFile();
                String s = "" + file;
                fichier_choix = s;
                Grille grille = new Grille(10,15,fichier_choix);
                setVisible(false);
            }
        }
    }
}
