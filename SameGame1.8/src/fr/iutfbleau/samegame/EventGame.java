package fr.iutfbleau.samegame;

import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.ImageIcon;

/**
 * La classe <code> EventGame </code> regroupe toutes les actions liées au clic et déplacements de la souris.
 * Contient également toute les actions effectuées sur la grille
 *
 */

public class EventGame extends JFrame implements MouseListener {

    /**
     * Variable générant la grille d'image visuelle.
     */
    private final JLabel [][] grille;

    /**
     * Variable générant les tableaux de lettre et d'état de case.
     */

    private boolean tab_etat[][] = new boolean [10][15];
    private char tab_letter[][] = new char [10][15];

    /**
     * Variables utilisées pour l'évolution de la grille de lettre.
     */

    public int condition = 0;
    private int score = 0;
    private int fin = 0;
    private int casex, casey;

    /**
     * Variables graphiques dessinant la partie droite de l'écran (score + pokemon).
     */
    private JTextArea areascore;
    private JLabel nompok = new JLabel();
    private JPanel paneldroite = new JPanel();
    private JLabel scorepok = new JLabel();
    private JLabel fond = new JLabel();

    /**
     * Fenetre graphique dans laquelle le jeu principale se trouve.
     */
    private JFrame jeux;

    /**
     * Constructeur permettant d'initialiser les variables utilisées.
     */

    public EventGame(char [][] tab_letter, JLabel [][] grid, JTextArea textArea, JPanel paneldroite, JFrame jeux) {
        this.tab_letter=tab_letter;
        this.grille=grid;
        this.areascore=textArea;
        this.paneldroite=paneldroite;
        this.jeux=jeux;
        afficheGrille();
    }


    /**
     * Méthode qui detecte l'entrée de la souris dans une case.
     */
    @Override
    public void mouseEntered (MouseEvent e) {
        bgdefault();
        setStatTab();
        JLabel b = (JLabel)e.getSource();
        Point p = b.getLocation();
        casex = p.x/(744/14);
        casey = p.y/(626/9);
        groupeIcon(casey,casex);

        bgon(casey, casex);


    }

    /**
     * Méthode qui detecte la sortie de la souris dans une case.
     */
    @Override
    public void mouseExited (MouseEvent e){
        setStatTab();
        JLabel b = (JLabel)e.getSource();
        Point p = b.getLocation();
        casex = p.x/(744/14);
        casey = p.y/(626/9);
        groupeIcon(casey,casex);

        bgoff(casey, casex);
        bgdefault();

    }
    /**
     * Méthode qui detecte un clic de la souris dans une case.
     */
    public void mouseClicked (MouseEvent e) {
        JLabel b = (JLabel)e.getSource();
        Point p = b.getLocation();
        casex = p.x/(744/14);
        casey = p.y/(626/9);
        System.out.println(casey + " " + casex);

        setStatTab();
        condition=0;
        groupeIcon(casey,casex);
        condition=0;
        test();
        descenteBoule();
        decaleGauche();
        afficheGrille();
        refreshScore();
        refresh();

        if(score<=500) {
            Salam();
        } else if (500<score && score<=1000) {
            fond.removeAll();
            Reptin();
        } else if (1000<score) {
            fond.removeAll();
            Dracau();
        }

        setStatTab();
        bgdefault();
        if(endGame()) {
            System.err.println("Fin du jeu");
            jeux.dispose();
            FenetreFin fenetrefin = new FenetreFin(score);
        }

    }
    /**
     * Méthode qui detecte un bouton appuyé de la souris
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }
    /**
     * Méthode qui detecte un bouton relaché de la souris
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Méthode qui remet la background de tout le tableau à la couleur initiale.
     */
    public void bgdefault () {
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 15 ; j++) {
                grille[i][j].setBackground(new Color(40,36,47));
            }
        }
    }

    /**
     * Méthode qui remet le background des cases à la couleur initiale
     * @param y ordonnée de la case.
     * @param x abscisse de la case.
     */
    public void bgon (int y, int x) {
        groupeIcon(y,x);
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 15 ; j++) {
                if(tab_etat[i][j]==true) {
                    grille[i][j].setBackground(new Color(124,44,67));
                }
            }
        }
    }
    /**
     * Méthode qui remet le background des cases à la couleur initiale.
     * @param y ordonnée de la case.
     * @param x abscisse de la case.
     */
    public void bgoff (int y, int x) {
        groupeIcon(y,x);
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 15 ; j++) {
                if(tab_etat[i][j]==true) {
                    grille[i][j].setBackground(new Color(40,36,47));
                }
            }
        }
    }

    /**
     * Méthode redessine la grille visuelle permettant de voir la descente et le décalage des icones.
     */
    public void refresh () {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        for (int k = 0 ; k < 10 ; k++) {
            for(int i = 0; i < 10 ; i++) {
                for (int j = 0 ; j < 15 ; j++) {

                    if(tab_letter[i][j]=='R') {
                        //System.out.print("refresh");
                        URL iconeSalamURL = classLoader.getResource("iconeSalam.png");
                        assert iconeSalamURL != null;
                        ImageIcon iconeSalam = new ImageIcon(iconeSalamURL);
                        grille[i][j].setIcon(iconeSalam);
                    } else if (tab_letter[i][j]=='B') {
                        //System.out.print("refresh");
                        URL iconeZapperURL = classLoader.getResource("iconeZapper.png");
                        assert iconeZapperURL != null;
                        ImageIcon iconeZapper = new ImageIcon(iconeZapperURL);
                        grille[i][j].setIcon(iconeZapper);
                    } else if (tab_letter[i][j]=='V') {
                        URL iconeBulbiURL = classLoader.getResource("iconeBulbi.png");
                        assert iconeBulbiURL != null;
                        ImageIcon iconeBulbi = new ImageIcon(iconeBulbiURL);
                        grille[i][j].setIcon(iconeBulbi);
                    } else if (tab_letter[i][j]==' ') {
                        //System.out.print("refresh");
                        grille[i][j].setIcon(null);
                    }
                }
            }
        }
    }


    /**
     * Méthode qui redessine le score.
     */
    public void refreshScore() {

        int scorejoueur = 0;
        scorejoueur=score();
        this.areascore.setText("SCORE : "+ scorejoueur);
        this.areascore.setFont(new Font("Comic Sans",Font.BOLD,40));
        //this.areascore.setEditable(false);
        this.areascore.setBounds(810, 7,423,55);
        this.areascore.setForeground(new Color(124,44,67));
        this.areascore.setBackground(new Color(40,36,47));
        System.out.println("Score : " + scorejoueur);



    }

    /**
     * Méthode qui regroupe les cases en fonction de la lettre du tableau de lettre
     * @param y ordonnée de la case.
     * @param x abscisse de la case.
     */

    public void groupeIcon(int y, int x) {
        char cara;
        boolean vide = true;
        cara=tab_letter[y][x];

        //System.out.println("tab_letter " + tab_letter[y][x]);

        tab_etat[y][x]=true;

        if(cara==' ') {
            tab_etat[y][x]=false;
            vide = false;
        }

        if (vide==true) {
            if(y>0) {
                if(cara==tab_letter[y-1][x] && tab_etat[y-1][x] == false) {
                    condition+=2;
                    groupeIcon(y-1,x);
                }
            }
            if (x>0) {
                if(cara==tab_letter[y][x-1] && tab_etat[y][x-1] == false) {
                    condition+=2;
                    groupeIcon(y,x-1);

                }
            }
            if (y<9) {
                if(cara==tab_letter[y+1][x] && tab_etat[y+1][x] == false) {
                    condition+=2;
                    groupeIcon(y+1,x);
                }
            }
            if (x<14) {
                if(cara==tab_letter[y][x+1] && tab_etat[y][x+1] == false) {
                    condition+=2;
                    groupeIcon(y,x+1);
                }
            }
            if (condition == 0) {
                tab_etat[y][x]=false;
                fin++;
            }
        }
    }

    /**
     * Methode qui compte le nombre de true dans le tableau d'état.
     * @return nombre de true dans le tableau d'état.
     */
    public int compteEtat () {
        int cpt_etat = 0;
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 15 ; j++) {
                if (tab_etat[i][j]==true) {
                    cpt_etat++;
                }
            }
        }

        return cpt_etat;
    }

    /**
     * Methode qui compte le score en fonction du nombre de true retourné par compteEtat.
     * @return score du joueur en fonction de la
     */
    public int score () {
        int cpt_etat = 0;
        cpt_etat = compteEtat();
        if (cpt_etat<=2) {
            score=score;
        } else {
            score += (cpt_etat-2)*(cpt_etat-2);
        }
        return score;
    }

    /**
     * Methode qui lis le tableau d'état et vide la case du tableau de lettre correspondante.
     * lorsque la valeur du tableau état vaut true.
     */
    public void test () {
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 15 ; j++) {
                if(tab_etat[i][j]==true) {
                    tab_letter[i][j]=' ';
                }
            }
        }
    }
    /**
     * Methode qui initialise le tableau d'état à false.
     */
    public void setStatTab () {
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 15 ; j++) {
                tab_etat[i][j] = false;
            }
        }
    }

    /**
     * Methode qui detecte la fin du jeu en fonction du nombre de true dans le tableau d'état.
     * @return true si il y a aucun true dans le tableau d'état. Sinon false.
     */
    public boolean endGame () {
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 15 ; j++) {
                if(tab_letter[i][j] != ' ') {
                    groupeIcon(i,j);
                    if(compteEtat() > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * Methode aidant au developpement permettant d'afficher le tableau état sur la console.
     */
    public void afficheEtat() {
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 15 ; j++) {
                System.out.print(" " + tab_etat[i][j]);
            }
            System.out.println("");
        }
    }
    /**
     * Methode qui affiche le tableau de lettre sur la console.
     */
    public void afficheGrille(){
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 15 ; j++) {
                System.out.print(tab_letter[i][j]);
            }
            System.out.println("");
        }
    }
    /**
     * Methode qui vide une case du tableau de lettre.
     * @param y ordonnée de la case.
     * @param x abscisse de la case.
     */
    public void upgradeTab(int y, int x) {
        tab_letter[y][x] = ' ';
    }
    /**
     * Methode qui fait descendre les lettres ayant du vide en dessous d'elles.
     */
    public void descenteBoule() {
        for (int k = 0 ; k < 10 ; k++) {
            for (int i = 0 ; i < 9 ; i++) {
                for (int j = 0 ; j < 15 ; j++) {
                    if ((tab_letter[i][j] != ' ') && (tab_letter[i+1][j] == ' ')) {
                        tab_letter[i+1][j] = tab_letter[i][j];
                        tab_letter[i][j] = ' ';
                    }
                }
            }
        }
    }
    /**
     * Methode qui decale les colonnes à gauche quand une colonne se vide.
     */
    public void decaleGauche() {
        for (int k = 0 ; k < 10 ; k++) {
            for (int j = 0 ; j < 14 ; j++) {
                if (etatCln(j)) {
                    for (int i = 0 ; i < 10 ; i++) {
                        tab_letter[i][j] = tab_letter[i][j+1];
                        tab_letter[i][j+1] = ' ';
                    }
                }
            }
        }
    }
    /**
     * Methode qui renvoie l'état d'une colonne (true si elle est vide ou false si elle ne l'est pas)
     * @param j numero de la colonne à vérifier.
     * @return true si la colonne est vide. Sinon false.
     */
    public boolean etatCln(int j) {
        for (int i = 0 ; i < 10 ; i++) {
            if (tab_letter[i][j] != ' ') {
                return false;
            }
        }
        return true;
    }
    /**
     * Methode permettant de dessiner le pokemon en fonction du score.
     */
    public void Reptin(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL dracaufURL = classLoader.getResource("nameReptin.png");
        assert dracaufURL != null;
        ImageIcon dracauf = new ImageIcon(dracaufURL);
        this.nompok.setIcon(dracauf);
        this.nompok.setBounds(76,10,270,46);
        this.paneldroite.add(nompok);

        URL imgReptinURL = classLoader.getResource("imgReptin.png");
        assert imgReptinURL != null;
        ImageIcon imgReptin = new ImageIcon(imgReptinURL);
        this.scorepok.setIcon(imgReptin);
        this.paneldroite.setLayout(null);
        this.scorepok.setBounds(0,20,500,700);
        this.paneldroite.add(scorepok);

        URL arrURL = classLoader.getResource("arr.jpg");
        assert arrURL != null;
        ImageIcon arr = new ImageIcon(arrURL);
        this.fond.setIcon(arr);
        this.fond.setBounds(0,-190,500,900);
        this.paneldroite.add(fond);
    }
    /**
     * Methode permettant de dessiner le pokemon en fonction du score.
     */
    public void Salam(){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL nameSalamURL = classLoader.getResource("nameSalam.png");
        assert nameSalamURL != null;
        ImageIcon nameSalam = new ImageIcon(nameSalamURL);
        this.nompok.setIcon(nameSalam);
        this.nompok.setBounds(13,10,396,63);
        this.paneldroite.add(nompok);

        URL imgSalamURL = classLoader.getResource("imgSalam.png");
        assert imgSalamURL != null;
        ImageIcon imgSalam = new ImageIcon(imgSalamURL);
        this.scorepok.setIcon(imgSalam);
        this.paneldroite.setLayout(null);
        this.scorepok.setBounds(0,100,475,475);
        this.paneldroite.add(scorepok);

        URL arrURL = classLoader.getResource("arr.jpg");
        assert arrURL != null;
        ImageIcon arr = new ImageIcon(arrURL);
        this.fond.setIcon(arr);
        this.fond.setBounds(0,-190,500,900);
        this.paneldroite.add(fond);
    }
    /**
     * Methode permettant de dessiner le pokemon en fonction du score.
     */
    public void Dracau(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL nameDracauURL = classLoader.getResource("nameDracau.png");
        assert nameDracauURL != null;
        ImageIcon nameDracau = new ImageIcon(nameDracauURL);
        this.nompok.setIcon(nameDracau);
        this.nompok.setBounds(26,10,370,55);
        this.paneldroite.add(nompok);

        URL imgDracauURL = classLoader.getResource("imgDracau.png");
        assert imgDracauURL != null;
        ImageIcon imgDracau = new ImageIcon(imgDracauURL);
        this.scorepok.setIcon(imgDracau);
        this.paneldroite.setLayout(null);
        this.scorepok.setBounds(0,50,475,475);
        this.paneldroite.add(scorepok);

        URL arrURL = classLoader.getResource("arr.jpg");
        assert arrURL != null;
        ImageIcon arr = new ImageIcon(arrURL);
        this.fond.setIcon(arr);
        this.fond.setBounds(0,-190,500,900);
        this.paneldroite.add(fond);
    }
}