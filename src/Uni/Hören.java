package Uni;

import javax.persistence.*;

/**
 * Created by peach on 12.05.16
 */
@Entity
@IdClass(HörenPK.class)
public class Hören {
    private int matrNr;
    private int vorlNr;
    private Studenten studentenByMatrNr;
    private Vorlesungen vorlesungenByVorlNr;

    @Id
    @Column(name = "MatrNr", nullable = false)
    public int getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(int matrNr) {
        this.matrNr = matrNr;
    }

    @Id
    @Column(name = "VorlNr", nullable = false)
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

        Hören hören = (Hören) o;

        if (matrNr != hören.matrNr) return false;
        if (vorlNr != hören.vorlNr) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = matrNr;
        result = 31 * result + vorlNr;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "MatrNr", referencedColumnName = "MatrNr", nullable = false, insertable=false, updatable=false)
    public Studenten getStudentenByMatrNr() {
        return studentenByMatrNr;
    }

    public void setStudentenByMatrNr(Studenten studentenByMatrNr) {
        this.studentenByMatrNr = studentenByMatrNr;
    }

    @ManyToOne
    @JoinColumn(name = "VorlNr", referencedColumnName = "VorlNr", nullable = false, insertable=false, updatable=false)
    public Vorlesungen getVorlesungenByVorlNr() {
        return vorlesungenByVorlNr;
    }

    public void setVorlesungenByVorlNr(Vorlesungen vorlesungenByVorlNr) {
        this.vorlesungenByVorlNr = vorlesungenByVorlNr;
    }
}
