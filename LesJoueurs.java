import java.util.ArrayList;
/**
 *
 * @author thomasnicolle
 */
public class LesJoueurs {
    private ArrayList<Joueur> lstJ;

    public ArrayList<Joueur> getLstJ() {
        return lstJ;
    }
    
    public int getNbrJoueur(){
        return lstJ.size();
    }

    public Joueur getJoueur(int i) {
        return lstJ.get(i);
    }

    public void setLstJ(ArrayList<Joueur> lstJ) {
        this.lstJ = lstJ;
    }
    
    public void ajoutJoueur(Joueur j){
        this.lstJ.add(j);
    }
    
    public void retireJoueur(Joueur j){
        this.lstJ.remove(j);
    }
    
    public LesJoueurs(){
        lstJ = new ArrayList<Joueur>();
    }

    public String toString() { // Affiche info du joueur i
        String info = "============"+"\n";
        for (int i = 0; i < this.getNbrJoueur(); i++) {
            info += "Joueur " + getJoueur(i).getPseudo()+". Score: "+getJoueur(i).getScore() + "\n";  
        }
        info += "============";
        return info;
    }
}
