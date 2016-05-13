package Uni;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by peach on 12.05.16
 */
public class PrüfenPK implements Serializable {
    private int matrNr;
    private int vorlNr;

    @Column(name = "MatrNr", nullable = false)
    @Id
    public int getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(int matrNr) {
        this.matrNr = matrNr;
    }

    @Column(name = "VorlNr", nullable = false)
    @Id
    public int getVorlNr() {
        return vorlNr;
    }

    public void setVorlNr(int vorlNr) {
        this.vorlNr = vorlNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrüfenPK prüfenPK = (PrüfenPK) o;

        if (matrNr != prüfenPK.matrNr) return false;
        if (vorlNr != prüfenPK.vorlNr) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = matrNr;
        result = 31 * result + vorlNr;
        return result;
    }
}
