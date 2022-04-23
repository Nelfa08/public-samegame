package fr.iutfbleau.samegame;

/**
 * La classe <code>MenuFin</code> dessine le menu de fin du jeu.
 */
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;

public class MenuFin extends JPanel {

    /**
     * variables des boutons du menu de fin de jeu.
     */
    public JButton rejouer = new JButton("rejouer");
    public JButton fermer = new JButton("fermer");

    /**
     * variables images.
     */
    private BufferedImage arr;
    private BufferedImage imgDracau;
    private BufferedImage imgReptin;
    private BufferedImage imgSalam;
    private BufferedImage pokeavfin;

    /**
     * variables textes.
     */
    JTextArea scorefinal = new JTextArea();
    JTextArea design = new JTextArea();

    /**
     * variable score du joueur.
     */
    public int score=0;

    /**
     * construteur de la classe MenuFin initialisant les variables.
     */
    public MenuFin(){

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL arrURL = classLoader.getResource("arr.jpg");
            URL imgDracauURL = classLoader.getResource("imgDracau.png");
            URL imgReptinURL = classLoader.getResource("imgReptin.png");
            URL imgSalamURL = classLoader.getResource("imgSalam.png");
            URL pokeavfinURL = classLoader.getResource("pokeavfin.png");

            assert arrURL != null;
            this.arr = ImageIO.read(arrURL);
            assert imgDracauURL != null;
            this.imgDracau = ImageIO.read(imgDracauURL);
            assert imgReptinURL != null;
            this.imgReptin = ImageIO.read(imgReptinURL);
            assert imgSalamURL != null;
            this.imgSalam = ImageIO.read(imgSalamURL);
            assert pokeavfinURL != null;
            this.pokeavfin = ImageIO.read(pokeavfinURL);
        } catch (IOException e){
            throw new IllegalThreadStateException();
        }

        this.scorefinal.setFont(new Font("Comic Sans",Font.BOLD,40));
        this.scorefinal.setEditable(false);
        this.scorefinal.setForeground(new Color(124,44,67));
        this.scorefinal.setBackground(new Color(40,36,47));

        this.design.setFont(new Font("Comic Sans",Font.ITALIC,16));
        this.design.setEditable(false);
        this.design.setForeground(Color.WHITE);
        this.design.setBackground(new Color(40,36,47));

        add(scorefinal);
        add(design);

        this.add(rejouer);
        this.add(fermer);

        System.out.println(score);

    }

    /**
     * Redefinition de la méthode paintComponent pour dessiner le menu de fin.
     */
    @Override
    protected void paintComponent(Graphics pinceau) {
        Graphics pinceau1 = pinceau.create();
        if (this.isOpaque()) {
            pinceau1.setColor(this.getBackground());
            pinceau1.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        pinceau1.drawImage(this.arr,0,0,this.getWidth(),this.getHeight(),this);
        pinceau1.drawImage(this.imgSalam,700,100,500,500,this);
        pinceau1.drawImage(this.pokeavfin,30,30,300,55,this);

        this.fermer.setBounds(290,380,100,50);
        this.rejouer.setBounds(150,380,100,50);
        this.rejouer.setBackground(new Color(124, 44, 67));
        this.fermer.setBackground(new Color(44, 47, 110));
        this.rejouer.setForeground(Color.BLACK);
        this.fermer.setForeground(Color.BLACK);
    }
}

