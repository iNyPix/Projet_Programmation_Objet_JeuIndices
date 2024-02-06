/**
 *
 * @author thomasnicolle
 */
public class Indice {
    private static String natures[] = {"+","-","*",">","<"}; // Indices disponible, ATTENTION au calcul associé dans le switch !
    private String nature;

    public String getNature() {
        return nature;
    }

    public String getIndices() {
        String s = "Les natures d'indices : ";
        for (int i = 0; i < natures.length; i++) {
            s += natures[i] + " ";
        }
        return s;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public static String alea(int difficulte) { // choisis aléatoirement un indice
        int diffIndice = 0;
        switch (difficulte) {
            case 1: diffIndice = 2; break;
            case 2: diffIndice = natures.length/2 + 1 ; break;
            case 3: diffIndice = natures.length; break;
            default: break;
        }
        int alea = (int) (0 + (Math.random() * (diffIndice - 0))); 
        return natures[alea];
    }

    public static Double getResultat(Case c1, Case c2) { // Retourne la valeur Indice
        double res = 0;
        switch (c1.getIndice()) { 
        case "+": res = c1.getValeur() + c2.getValeur(); break;
        case "-": res = c1.getValeur() - c2.getValeur(); break;
        case "*": res = c1.getValeur() * c2.getValeur(); break;
        case ">": res = Math.max(c1.getValeur(), c2.getValeur()); break;
        case "<": res = Math.min(c1.getValeur(), c2.getValeur()); break;
        //case "/": if (c2.getValeur() != 0) { res = c1.getValeur() / c2.getValeur(); } else { System.out.println(new Error("Division par 0")); }; break;
        // Pour ajouter un nouveau calcul
        // case "operateur" : res = c1.getValeur() operateur c2.getValeur(); break; 
        default: break;
        }
        return res;
    }

    public String toString() {
        return nature;
    }

    public Indice (String n){ // constructeur de test
        setNature(n);
    }

    public Indice (int difficulte){ // constructeur
        this.nature = alea(difficulte);
    }
}
