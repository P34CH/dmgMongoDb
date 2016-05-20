package Uni;

import javax.persistence.*;

/**
 * Created by peach on 12.05.16
 */
@Entity
@IdClass(VoraussetzenPK.class)
@NamedQueries({@NamedQuery(name = "Voraussetzen.findAll", query = "SELECT p FROM Voraussetzen p")})
public class Voraussetzen {
    private int vorgänger;
    private int nachfolger;
    private Vorlesungen vorlesungenByVorgänger;
    private Vorlesungen vorlesungenByNachfolger;

    @Id
    @Column(name = "Vorgänger", nullable = false)
    public int getVorgänger() {
        return vorgänger;
    }

    public void setVorgänger(int vorgänger) {
        this.vorgänger = vorgänger;
    }

    @Id
    @Column(name = "Nachfolger", nullable = false)
    public int getNachfolger() {
        return nachfolger;
    }

    public void setNachfolger(int nachfolger) {
        this.nachfolger = nachfolger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Voraussetzen that = (Voraussetzen) o;

        if (vorgänger != that.vorgänger) return false;
        if (nachfolger != that.nachfolger) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vorgänger;
        result = 31 * result + nachfolger;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Vorgänger", referencedColumnName = "VorlNr", nullable = false, insertable=false, updatable=false)
    public Vorlesungen getVorlesungenByVorgänger() {
        return vorlesungenByVorgänger;
    }

    public void setVorlesungenByVorgänger(Vorlesungen vorlesungenByVorgänger) {
        this.vorlesungenByVorgänger = vorlesungenByVorgänger;
    }

    @ManyToOne
    @JoinColumn(name = "Nachfolger", referencedColumnName = "VorlNr", nullable = false, insertable=false, updatable=false)
    public Vorlesungen getVorlesungenByNachfolger() {
        return vorlesungenByNachfolger;
    }

    public void setVorlesungenByNachfolger(Vorlesungen vorlesungenByNachfolger) {
        this.vorlesungenByNachfolger = vorlesungenByNachfolger;
    }
}
