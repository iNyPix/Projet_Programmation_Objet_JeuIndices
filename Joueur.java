/**
 *
 * @author thomasnicolle
 */
public class Joueur {
    private String pseudo;
    private int score;

    public String getPseudo() {
        return pseudo;
    }

    public int getScore() {
        return score;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void modifScore(int point, char op) {
        if (op == '+') {
            setScore(score + point);
        }
        if (op == '-') {
            setScore(score - point);
        }
    }

    public Joueur(String pseudo) {
        setPseudo(pseudo);
        setScore(0);
    }

    public String toString() {
        String info = "============"+"\n";
        info += "Joueur " + this.getPseudo()+". Score :"+this.getScore() + ". Joue \n";
        info += "============";
        return info;
    }
}
