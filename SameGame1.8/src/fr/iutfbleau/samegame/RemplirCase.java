package fr.iutfbleau.samegame;

import java.util.*;
import java.io.*;

/**
 * La classe <code> RemplirCase </code> génère une grille en fonction du constructeur appelée.
 */

public class RemplirCase {

    private final Random random = new Random();
    private final char[][] tab_letter = new char [10][15];

    /**
     * Methode permettant de générer une grille aléatoirement.
     * @return le tableau généré.
     */
    public char [][] randomGrille () {
        int nb_rand;
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 15 ; j++) {
                nb_rand = random.nextInt(3);
                if (nb_rand==0) {

                    tab_letter[i][j] = 'R';
                } else if (nb_rand==1) {
                    tab_letter[i][j] = 'V';
                } else if (nb_rand==2) {
                    tab_letter[i][j] = 'B';
                }
            }
        }
        return tab_letter;
    }
    /**
     * Methode permettant de générer une grille en lisant un fichier.
     * @return le tableau généré.
     */
    public char [][] fileGrille (String s) {
        try {
            FileInputStream fichier = new FileInputStream(s);
            InputStreamReader flux = new InputStreamReader(fichier);
            try {
                for (int i = 0 ; i < 10 ; i++) {
                    for (int j = 0 ; j < 15 ; j++) {
                        char cmp = (char)flux.read();
                        tab_letter[i][j] = cmp;
                        if (tab_letter[i][j]=='\n') {
                            j--;
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Erreur de lecture du fichier");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erreur d'ouverture de fichier");
        }
        return tab_letter;
    }

}
