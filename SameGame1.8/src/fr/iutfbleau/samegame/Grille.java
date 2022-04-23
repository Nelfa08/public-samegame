package fr.iutfbleau.samegame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;


/**
 * La classe <code> Grille </code> permet de dessiner la grille sur la fenetre et génère une grille
 */

public class Grille extends JFrame{

    private BufferedImage icone;

    private final int ligne;
    private final int colonne;
    private final JLabel[][] grid;

    private final JFrame jeux = new  JFrame();

    private final JPanel panelgauche = new JPanel();
    private final JPanel paneldroite = new JPanel();

    private ImageIcon image1;

    private final JLabel scorepok = new JLabel();
    private final JLabel nompok = new JLabel();
    private final JLabel fond = new JLabel();

    JTextArea textArea = new JTextArea();

    private final RemplirCase casej;
    private final char[][] tab_letter;

    /**
     * Constructeur de la classe Grille appelé par la bouton "jouer".
     */
    public Grille(int nbligne, int nbcolonne){
        // Musique
        //Thread playWave=new Son("res/pokemo.wav");
        //playWave.start();

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL pokeURL = classLoader.getResource("poke.png");
            assert pokeURL != null;
            this.icone = ImageIO.read(pokeURL);
        } catch (IOException e) {
            System.out.println("Error");
        }

        this.ligne = nbligne;
        this.colonne = nbcolonne;
        this.grid = new JLabel[nbligne][nbcolonne];

        // Fenetre de jeux
        this.jeux.setIconImage(icone);
        this.jeux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jeux.setSize(1250,750);
        this.jeux.setLayout(null);
        this.jeux.setLocationRelativeTo(null);
        this.jeux.setResizable(true);
        this.jeux.add(panelgauche);
        this.jeux.setTitle("PokemonAventure");
        this.jeux.add(paneldroite);
        this.jeux.getContentPane().setBackground(new Color(40,36,47));
        this.jeux.add(textArea);

        //Partie gauche de la fenetre (grille)
        this.panelgauche.setBounds(5,5,800,700);
        this.panelgauche.setLayout(new GridLayout(nbligne, nbcolonne));
        this.panelgauche.setBackground(new Color(40,36,47));
        this.panelgauche.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(124,44,67)));

        //partie droite de la fenetre (score + pokemon)
        this.paneldroite.setBounds(805,65,423,639);
        this.paneldroite.setBackground(new Color(40,36,47));
        dessinScoreInit();

        casej=new RemplirCase();
        tab_letter = casej.randomGrille();
        fillGrille();

    }

    /**
     * Constructeur de la classe Grille appelé par la bouton "importer".
     */
    public Grille(int nbligne, int nbcolonne, String s){

        //Thread playWave=new Son("res/pokemo.wav");
        //playWave.start();

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL pokeURL = classLoader.getResource("poke.png");
            assert pokeURL != null;
            this.icone = ImageIO.read(pokeURL);
        } catch (IOException e) {
            System.out.println("Error");
        }
        this.ligne = nbligne;
        this.colonne = nbcolonne;
        this.grid = new JLabel[nbligne][nbcolonne];

        // Fenetre de jeux
        this.jeux.setIconImage(icone);
        this.jeux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jeux.setSize(1250,750);
        this.jeux.setLayout(null);
        this.jeux.setLocationRelativeTo(null);
        this.jeux.setResizable(true);
        this.jeux.add(panelgauche);
        this.jeux.setTitle("PokemonAventure");
        this.jeux.add(paneldroite);
        this.jeux.getContentPane().setBackground(new Color(40,36,47));
        this.jeux.add(textArea);

        //Partie gauche de la fenetre (grille)
        this.panelgauche.setBounds(5,5,800,700);
        this.panelgauche.setLayout(new GridLayout(nbligne, nbcolonne));
        this.panelgauche.setBackground(new Color(40,36,47));
        this.panelgauche.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(124,44,67)));

        //partie droite de la fenetre (score + pokemon)
        this.paneldroite.setBounds(805,65,423,639);
        this.paneldroite.setBackground(new Color(40,36,47));
        dessinScoreInit();
        casej=new RemplirCase();
        tab_letter = casej.fileGrille(s);
        fillGrille();
    }


    /**
     * Methode qui affiche les images en fonction du tableau de lettres.
     */
    public void fillGrille(){
        char lettre;
        EventGame eventgame = new EventGame(tab_letter, grid, textArea, paneldroite, jeux);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        for(int i = 0; i < 10 ; i++) {
            for (int j = 0 ; j < 15 ; j++) {
                lettre=tab_letter[i][j];

                if(lettre=='R') {
                    grid[i][j] = new JLabel();
                    grid[i][j].setOpaque(true);
                    grid[i][j].setBackground(new Color(40,36,47));
                    URL iconeSalamURL = classLoader.getResource("iconeSalam.png");
                    assert iconeSalamURL != null;
                    ImageIcon iconeSalam = new ImageIcon(iconeSalamURL);
                    grid[i][j].setIcon(iconeSalam);
                    this.panelgauche.add(grid[i][j]);
                } else if (lettre=='B') {
                    grid[i][j] = new JLabel();
                    grid[i][j].setOpaque(true);
                    grid[i][j].setBackground(new Color(40,36,47));
                    URL iconeZapperURL = classLoader.getResource("iconeZapper.png");
                    assert iconeZapperURL != null;
                    ImageIcon iconeZapper = new ImageIcon(iconeZapperURL);
                    grid[i][j].setIcon(iconeZapper);
                    this.panelgauche.add(grid[i][j]);
                } else if (lettre=='V') {
                    grid[i][j] = new JLabel();
                    grid[i][j].setOpaque(true);
                    grid[i][j].setBackground(new Color(40,36,47));
                    URL iconeBulbiURL = classLoader.getResource("iconeBulbi.png");
                    assert iconeBulbiURL != null;
                    ImageIcon iconeBulbi = new ImageIcon(iconeBulbiURL);
                    grid[i][j].setIcon(iconeBulbi);
                    this.panelgauche.add(grid[i][j]);
                } else if (lettre!= ' ') {
                    grid[i][j] = new JLabel();
                    grid[i][j].setOpaque(true);
                    grid[i][j].setBackground(new Color(40,36,47));
                    URL iconeBulbiURL = classLoader.getResource("iconeBulbi.png");
                    assert iconeBulbiURL != null;
                    ImageIcon iconeBulbi = new ImageIcon(iconeBulbiURL);
                    grid[i][j].setIcon(iconeBulbi);
                    this.panelgauche.add(grid[i][j]);
                } else if (lettre==' ') {
                    grid[i][j] = new JLabel();
                    grid[i][j].setOpaque(true);
                    grid[i][j].setBackground(new Color(40,36,47));
                    grid[i][j].setIcon(new ImageIcon());
                    this.panelgauche.add(grid[i][j]);
                }
                grid[i][j].addMouseListener(eventgame);
            }
        }

        this.jeux.setResizable(false);
        this.jeux.setVisible(true);
    }

    /**
     * Methode qui dessine le score.
     */
    public void dessinScoreInit () {

        int scorejoueur = 0;
        this.textArea.setText("SCORE : "+ scorejoueur);
        this.textArea.setFont(new Font("Comic Sans",Font.BOLD,40));
        this.textArea.setEditable(false);
        this.textArea.setBounds(810, 7,423,55);
        this.textArea.setForeground(new Color(124,44,67));
        this.textArea.setBackground(new Color(40,36,47));
    }
}
