package ex1;
import java.util.Scanner;
public class Tableaux {
	 int[][] tab;

	  Tableaux() {
	    tab = new int[9][9];
	  }

	  Scanner clavier = new Scanner(System.in);

	  public void remplirtab() {
	    // for(int i=0;i<9;i++) {
	    // for (int j=0;j<9;j++) {
	    // System.out.print("donner la case tab["+(i+1)+"]["+(j+1)+"]");]
	    // tab[i][j]=clavier.nextInt();
	    int tableau[][] = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
	        { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
	        { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
	        { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
	        { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
	        { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
	        { 0, 6, 0, 0, 0, 0, 0, 0, 0 },
	        { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
	        { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };
	    tab = tableau;
	  }

	  public void affichetab() {
	    System.out.println("|-----------------------------------|");
	    for (int i = 0; i < 9; i++) {
	      System.out.print("|  ");
	      for (int j = 0; j < 9; j++) {
	        if (tab[i][j] == 0)
	          System.out.print("   ");
	        else
	          System.out.print(tab[i][j] + "  ");
	        if (j == 2 || j == 5 || j == 8)
	          System.out.print("|  ");

	      }
	      System.out.println();
	      if (i == 2 || i == 5)
	        System.out.println("|-----------------------------------|");
	    }
	    System.out.println("|-----------------------------------|");
	  }

	  public boolean estDansLaColonne(int valeur, int colonne) {
	    for (int ligne = 0; ligne < 9; ligne++) {
	      if (tab[ligne][colonne] == valeur)
	        return true;
	    }
	    return false;
	  }

	  public boolean estDansLaLigne(int valeur, int ligne) {
	    for (int colonne = 0; colonne < 9; colonne++) {
	      if (tab[ligne][colonne] == valeur)
	        return true;
	    }
	    return false;
	  }

	  public boolean estDansLeCarre(int valeur, int ligne, int colonne) {
	    int pointGauche = 3 * (colonne / 3);
	    int pointHaut = 3 * (ligne / 3);
	    for (int c = pointGauche; c < pointGauche + 3; c++) {
	      for (int l = pointHaut; l < pointHaut + 3; l++) {
	        if (tab[l][c] == valeur)
	          return true;
	      }
	    }
	    return false;
	  }

	  public boolean estValeurPossible(int valeur, int ligne, int colonne) {
	    return !estDansLaColonne(valeur, colonne)
	        && !estDansLaLigne(valeur, ligne)
	        && !estDansLeCarre(valeur, ligne, colonne);
	  }

	  public boolean trouveSolution(int ligne, int colonne) {
	    // calcul de la position suivante
	    int ligneSuivante;
	    int colonneSuivante;
	    if (colonne == 8) {
	      ligneSuivante = ligne + 1;
	      colonneSuivante = 0;
	    } else {
	      ligneSuivante = ligne;
	      colonneSuivante = colonne + 1;
	    }

	    // Est-ce que j'ai parcouru toutes les cases du tableau ?
	    if (ligne == 9) {
	      affichetab();
	      return true;
	    }

	    if (tab[ligne][colonne] != 0) { // case déjà remplie ==> case suivante
	      return trouveSolution(ligneSuivante, colonneSuivante);
	    } else { // case non remplie ==> je dois essayer les différentes valeurs
	      for (int valeur = 1; valeur < 10; valeur++) {
	        // si la je ne peux pas poser la valeur, je passe à la suivante
	        if (!estValeurPossible(valeur, ligne, colonne))
	          continue;
	        tab[ligne][colonne] = valeur; // je pose une valeur (hypothèse)
	        boolean correct = trouveSolution(ligneSuivante, colonneSuivante);
	        if (correct)
	          return true; // c'est bon, la valeur était correcte
	      }
	      tab[ligne][colonne] = 0; // c'était une case "vide", on la remet
	      return false; // Je n'ai pas trouvé de valeur correcte
	    }
	  }

	}
