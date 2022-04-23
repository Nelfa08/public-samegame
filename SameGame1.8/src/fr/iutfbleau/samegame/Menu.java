package fr.iutfbleau.samegame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Menu extends JLabel {
    public JButton jouer = new JButton("Jouer");
    public JButton importer = new JButton("Importer");

    private final BufferedImage arrierepl;
    private final BufferedImage dresseure;
    private final BufferedImage text;
    private final BufferedImage hyper;
    private final BufferedImage superb;
    private final BufferedImage pokeav;

    public Menu() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL arriereplURL = classLoader.getResource("arr.jpg");
            URL dresseureURL = classLoader.getResource("dresseur.png");
            URL textURL = classLoader.getResource("text.png");
            URL hyperURL = classLoader.getResource("hyperball.png");
            URL superbURL = classLoader.getResource("superball.png");
            URL pokeavURL = classLoader.getResource("pokeav.png");
            assert arriereplURL != null;
            this.arrierepl = ImageIO.read(arriereplURL);
            assert dresseureURL != null;
            this.dresseure = ImageIO.read(dresseureURL);
            assert textURL != null;
            this.text = ImageIO.read(textURL);
            assert hyperURL != null;
            this.hyper = ImageIO.read(hyperURL);
            assert superbURL != null;
            this.superb = ImageIO.read(superbURL);
            assert pokeavURL != null;
            this.pokeav = ImageIO.read(pokeavURL);

            this.add(jouer);
            this.add(importer);
        } catch (IOException e){
            throw new IllegalThreadStateException();
        }
    }

    @Override
    protected void paintComponent(Graphics pinceau) {
        Graphics pinceau1 = pinceau.create();
        if (this.isOpaque()) {
            pinceau1.setColor(this.getBackground());
            pinceau1.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        //background
        pinceau1.drawImage(this.arrierepl,0,0,this.getWidth(),this.getHeight(),this);

        //bouton "jouer"
        double l_jouer = (this.getWidth()/12);
        int largeur_jouer = (int)l_jouer;
        double h_jouer = (this.getHeight()/1.55);
        int hauteur_jouer = (int)h_jouer;
        this.jouer.setBounds(largeur_jouer-10,hauteur_jouer+80,100,50);
        this.jouer.setBackground(new Color(255,200,0));
        this.jouer.setForeground(Color.BLACK);
        this.jouer.setBorder(BorderFactory.createLineBorder(new Color(50,64,90), 1));

        //bouton "importer"
        double l_importer = (this.getWidth()/1.2);
        int largeur_importer = (int)l_importer;
        double h_importer = (this.getHeight()/1.55);
        int hauteur_importer = (int)h_importer;
        this.importer.setBounds(largeur_importer-10,hauteur_importer+80,100,50);
        this.importer.setBackground(new Color(255,200,0));
        this.importer.setForeground(Color.BLACK);
        this.importer.setBorder(BorderFactory.createLineBorder(new Color(50,64,90), 1));

        //Sasha
        double l_sasha = (this.getWidth()/7.5);
        int largeur_sasha = (int)l_sasha;
        double h_sasha = (this.getHeight()/2.69);
        int hauteur_sasha = (int) h_sasha;
        pinceau1.drawImage(this.dresseure,largeur_sasha,hauteur_sasha,280,400,this);

        //Pokeball 1
        double l_pokeball1 = (this.getWidth()/12);
        int largeur_pokeball1 = (int)l_pokeball1;
        double h_pokeball1 = (this.getHeight()/1.55);
        int hauteur_pokeball1 = (int) h_pokeball1;
        pinceau1.drawImage(this.hyper,largeur_pokeball1,hauteur_pokeball1,80,80,this);

        //Pokeball 2
        double l_pokeball2 = (this.getWidth()/1.2);
        int largeur_pokeball2 = (int)l_pokeball2;
        double h_pokeball2 = (this.getHeight()/1.55);
        int hauteur_pokeball2 = (int) h_pokeball2;
        pinceau1.drawImage(this.superb,largeur_pokeball2,hauteur_pokeball2,80,80,this);

        //titre
        pinceau1.drawImage(this.pokeav,100,100,1000,200,this);

        //regles
        double l_regles = (this.getWidth()/4.8);
        int largeur_regles = (int)l_regles;
        double h_regles = (this.getHeight()/1.75);
        int hauteur_regles = (int)h_regles;
        pinceau1.drawImage(this.text,largeur_regles,hauteur_regles,700,200,this);

    }
}
