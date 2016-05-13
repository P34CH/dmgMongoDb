package Uni;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by peach on 12.05.16
 */
@Entity
@IdClass(PrüfenPK.class)
public class Prüfen {
    private int matrNr;
    private int vorlNr;
    private Integer persNr;
    private BigDecimal note;
    private Studenten studentenByMatrNr;
    private Vorlesungen vorlesungenByVorlNr;
    private Professoren professorenByPersNr;

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

    @Basic
    @Column(name = "PersNr", nullable = true)
    public Integer getPersNr() {
        return persNr;
    }

    public void setPersNr(Integer persNr) {
        this.persNr = persNr;
    }

    @Basic
    @Column(name = "Note", nullable = true, precision = 1)
    public BigDecimal getNote() {
        return note;
    }

    public void setNote(BigDecimal note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prüfen prüfen = (Prüfen) o;

        if (matrNr != prüfen.matrNr) return false;
        if (vorlNr != prüfen.vorlNr) return false;
        if (persNr != null ? !persNr.equals(prüfen.persNr) : prüfen.persNr != null) return false;
        if (note != null ? !note.equals(prüfen.note) : prüfen.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = matrNr;
        result = 31 * result + vorlNr;
        result = 31 * result + (persNr != null ? persNr.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "PersNr", referencedColumnName = "PersNr", nullable = false, insertable=false, updatable=false)
    public Professoren getProfessorenByPersNr() {
        return professorenByPersNr;
    }

    public void setProfessorenByPersNr(Professoren professorenByPersNr) {
        this.professorenByPersNr = professorenByPersNr;
    }
}
