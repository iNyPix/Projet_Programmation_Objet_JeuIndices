/**
 *
 * @author thomasnicolle
 */
public class Plateau {

    private int X, Y;
    private Case LesCases[][];

    // Getters
    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Case[][] getPlateau() {
        return LesCases;
    }

    public Case getCase(int y, int x) {
        if (x >= 0 || x <= this.X || y >= 0 || y <= this.Y) {
            return LesCases[y][x];
        } else {
            return null;
        }

    }

    // Setters
    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    // Constructeur
    public Plateau(int X, int Y, int valMax, boolean indiceVisible, int difficulte, boolean demo) {
        if ((X * Y) % 2 == 0) { // Nombre de cases paires
            setX(X);
            setY(Y);
            LesCases = new Case[getX()][getY()]; // instanciation du tableau de type case
            // init cases avec valeur max et choix de la visibilité des indices
            for (int i = 0; i < getY(); i++) {
                for (int j = 0; j < getX(); j++) {
                    LesCases[j][i] = new Case(valMax, indiceVisible, difficulte, demo); // Instant sciassions de chaque
                                                                                        // cases du tableau
                }
            }
        } else {
            System.out.println(new Error("Le nombre de case doit être paire"));
        }
    }

    // Toute les cases sont découverte ?
    public boolean allFind(boolean demo) {
        boolean b = false;
        if (demo == false) { // Si pas de mode demo, but 
            int cpt = 0;
            for (int i = 0; i < getY(); i++) {
                for (int j = 0; j < getX(); j++) {
                    if (LesCases[j][i].getDecouverte()) {
                        cpt++;
                    }
                }
            }
            if (cpt == getY() * getX()) {
                b = true;
            }
        } else { // Si mode demo active, modification du but de la partie
            System.out.println("\nContinuer la démo ? o/n :");
            if (Lire.c() == 'n'){
                b = true;
            }
        }
        return b;
    }

    public String toString() { // Affiche le plateau
        String s = "";
        int i = 0, j = 0;
        for (i = 0; i < getY(); i++) {
            for (j = 0; j < getX(); j++) {
                s += LesCases[j][i].toString() + " ";
            }
            s += "\n"; // retour à la ligne
        }
        return s;
    }
}
