/**
 *
 * @author thomasnicolle
 */
public class Case {
    private int Valeur;
    private Indice i;
    private boolean decouverte, indiceVisible;

    public int getValeur() {
        return this.Valeur;
    }

    public String getIndice() {
        return i.toString();
    }

    public Indice getI() {
        return this.i;
    }

    public boolean getDecouverte() {
        return this.decouverte;
    }

    public void setValeur(int Valeur) {
        this.Valeur = Valeur;
    }

    public void setDecouverte(boolean decouverte) {
        this.decouverte = decouverte;
    }

    public void setIndiceVisible(boolean indiceVisible) {
        this.indiceVisible = indiceVisible;
    }

    public Case(int valMax, boolean indiceVisible, int difficulte, boolean demo) { // constructeur
        int val = 0 + (int)(Math.random() * ((valMax - 0) + 1));
        setValeur(val); // attributation de la valeur aléatoirement Générée
        this.i = new Indice(difficulte);
        setIndiceVisible(indiceVisible);
        if (demo) {
            setDecouverte(true);
        } else {
            setDecouverte(false);
        }
        
    }

    public String toString() {
        String s = "";
        if (decouverte) { // Affichage de la valeur case si decouverte
            s += getValeur();
        } else {
            s += "X";
        }
        if (indiceVisible) { // et l'indice si l'option est choisi
            s += getIndice();
        } else {
            s += "X";
        }
        return s;
    }

}
