/**
 *
 * @author thomasnicolle
 */
public class JeuIndices {
    private static int longu; // longueur du plateau
    private static int larg; // largeur du plateua
    private static int valMax; // valeur maximal possible d'une case
    private static LesJoueurs lstJ; // collection de joueurs
    private static Plateau p; // plateau du jeu
    private static int difficulte; // niveau de difficulté
    final private static boolean demo = false; // mode demo

    public static String infoJeu() { // Information sur le jeu
        String info = lstJ.toString() + "\n"; // affiche les joueurs
        info += "Difficulté de la partie : ";
        if (difficulte == 1) {
            info += "Facile \n";
        } else if (difficulte == 2) {
            info += "Normal \n";
        } else {
            info += "Difficile \n";
        }
        info += "Valeur maximale des cases : " + valMax + "\n";
        if (demo) {
            info += "# Mode demo activé \n";
        }
        return info;
    }

    public static void setDifficulte(int i) { // Définition de la difficulté du jeu
        switch (i) {
            case 1:
                difficulte = 1; // Pour une difficulté facile
                longu = 4;
                larg = 3;
                valMax = 25;
                break;
            case 2:
                difficulte = 2; // Pour une difficulté moyenne
                longu = 8;
                larg = 6;
                valMax = 50;
                break;
            case 3:
                difficulte = 3; // Pour une difficulté avancée
                longu = 12;
                larg = 9;
                valMax = 100;
                break;
            default:
                difficulte = 1;
                longu = 4;
                larg = 3;
                valMax = 9;
                break;
        }
    }

    public static boolean verifCase(int y, int x) { // Verification validité de la case
        boolean ok = true;
        if (p.getCase(y, x) == null) { // existance de la case
            ok = false;
            System.out.println("Valeur hors limites ; recommencez ! ");
        }
        if (!demo) { // Si le mode demo est desactive
            if (p.getCase(y, x).getDecouverte()) { // case déjà découverte ou non
                ok = false;
                System.out.println("Case déjà dévoilée. Choisissez-en une autre !");
            }
        }
        return ok;
    }

    public static Joueur getGagnant() { // Renvoie le joueur avec le score le plus haut
        Joueur j = lstJ.getJoueur(0);
        for (int i = 0; i < lstJ.getNbrJoueur(); i++) {
            if (lstJ.getJoueur(i).getScore() > j.getScore()) {
                j = lstJ.getJoueur(i);
            }
        }
        return j;
    }
    public static void main(String[] args) {
        char rep;
        int x, y, hyp1, hyp2;
        // Demande info joueur(s)
        lstJ = new LesJoueurs();
        do {
            System.out.println("Qui sont le/les joueur(s) ?");
            System.out.print("Nom du joueur : ");
            lstJ.ajoutJoueur(new Joueur(Lire.S()));
            System.out.println("Joueur supplémentaire o/n : ");
            rep = Lire.c();
        } while (rep != 'n' || lstJ.getNbrJoueur() < 1);

        // Choisir la difficulté de la partie
        int d;
        do {
            System.out.println("Veuillez choisir la difficulté:");
            System.out.println("1.Facile | 2.Normal | 3.Difficile");
            d = Lire.i();
            setDifficulte(d);
        } while ( d < 0 || d > 3);

        // Récap info de la partie
        System.out.println(infoJeu());
        p = new Plateau(longu, larg, valMax, true, difficulte, demo);
        System.out.println(p.toString()); // Affichage du plateau

        // Lancement de la partie
        while (p.allFind(demo) == false) { // Tant que toutes les cases n'ont pas été découverte
            for (int i = 0; i < lstJ.getNbrJoueur(); i++) { // Pour chaque joueur

                // Affichage de ses informations
                System.out.println(lstJ.getJoueur(i).toString());

                // Choix des cases et vérification de leur validités
                System.out.println("Choix de la première case "); // 1er case
                do {
                    System.out.println("Numéro de ligne entre 1 et " + larg + " :");
                    y = Lire.i() - 1;
                    System.out.println("Numéro de colonne entre 1 et " + longu + " :");
                    x = Lire.i() - 1;
                } while (verifCase(y, x) == false); // Tant que la case proposée ne respecte pas la condition
                Case C1 = p.getCase(y, x);
                System.out.println("Choix de la deuxième case "); // 2ème case
                do {
                    System.out.println("Numéro de ligne entre 1 et " + larg + " :");
                    x = Lire.i() - 1;
                    System.out.println("Numéro de colonne entre 1 et " + longu + " :");
                    y = Lire.i() - 1;
                } while (verifCase(y, x) == false);
                Case C2 = p.getCase(y, x);

                // Indices donnés
                System.out.println("Indice première case : " + Indice.getResultat(C1, C2));
                System.out.println("Indice deuxième case : " + Indice.getResultat(C2, C1));

                // Debut des hypothèses
                System.out.println("Veux-tu faire des hypothèses sur les valeurs des cases choisies o/n : ");
                rep = Lire.c();
                if (rep == 'o') {
                    System.out.println("Valeur de la première case : ");
                    hyp1 = Lire.i();
                    System.out.println("Valeur de la seconde case : ");
                    hyp2 = Lire.i();
                    if (hyp1 == C1.getValeur() && hyp2 == C2.getValeur()) { // Si la valeur correspond
                        System.out.println("Bravo ! Le contenu des cases a été trouvé ! ");
                        lstJ.getJoueur(i).setScore(lstJ.getJoueur(i).getScore() + 1);
                        C1.setDecouverte(true); // Affichage des valeurs trouvées
                        C2.setDecouverte(true);
                    } else {
                        System.out.println("Au moins une des hypothèses est fausse !");
                    }
                } else {
                    System.out.println("Pas de choix ; trop de possibilités.");
                }
                // Fin des questions
            }
        }
        // Fin de la partie et annonce du gagnant
        System.out.println(
                getGagnant().getPseudo() + " à gagné la partie, avec un score de " + getGagnant().getScore() + " !");
    }
}
