package fr.iutfbleau.samegame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


/**
 * La classe <code>Fenetre</code> Initialise la fenêtre du menu de fin et contient les listener des boutons du menu de fin
 */

public class FenetreFin extends JFrame implements ActionListener {
    private MenuFin menufin = new MenuFin();

    private BufferedImage icone;

    /**
     * Constructeur de la classe FenetreFin qui affiche le menu de fin
     * @param scorejoueur score du joueur permettant de l'afficher à l'écran
     */
    public FenetreFin(int scorejoueur) {
        super();
        int score1;
        score1 = scorejoueur;
        menufin.scorefinal.setText("Le score est  de : " + score1);
        menufin.design.setText("Design by Corentin R. & Nicolas A.");
        menufin.design.setBounds(420,640,270,30);
        menufin.setLayout(null);
        menufin.score=score1;
        if(score1<1000){
            menufin.scorefinal.setBounds(80,220,520,50);
        }else{
            menufin.scorefinal.setBounds(80,220,550,50);
        }
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL pokeURL = classLoader.getResource("poke.png");
            assert pokeURL != null;
            this.icone = ImageIO.read(pokeURL);

        } catch (IOException e){
            throw new IllegalThreadStateException();
        }
        setIconImage(icone);
        this.setTitle("SameGame");
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        menufin.rejouer.addActionListener(this);
        menufin.fermer.addActionListener(this);

        this.getContentPane().add(this.menufin);
        System.out.println(score1);


    }


    /**
     * Listener des boutons de menu de fin
     */
    public void actionPerformed(ActionEvent event) {
        if(event.getSource().equals(menufin.rejouer)) {
            System.out.println("rejouer");
            dispose();
            new Fenetre();
        }

        if(event.getSource().equals(menufin.fermer)) {
            dispose();
            System.exit(0);
        }
    }
}