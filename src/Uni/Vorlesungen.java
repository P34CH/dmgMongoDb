package Uni;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by peach on 12.05.16
 */
@Entity
@NamedQueries({@NamedQuery(name = "Vorlesungen.findAll", query = "SELECT p FROM Vorlesungen p")})
public class Vorlesungen {
    private int vorlNr;
    private String titel;
    private Integer sws;
    private Integer gelesenVon;
    private Professoren professorenByGelesenVon;
    private Collection<Hören> hörensByVorlNr;
    private Collection<Prüfen> prüfensByVorlNr;
    private Collection<Voraussetzen> voraussetzensByVorlNr;
    private Collection<Voraussetzen> voraussetzensByVorlNr_0;

    @Id
    @Column(name = "VorlNr", nullable = false)
    public int getVorlNr() {
        return vorlNr;
    }

    public void setVorlNr(int vorlNr) {
        this.vorlNr = vorlNr;
    }

    @Basic
    @Column(name = "Titel", nullable = true, length = 30)
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    @Basic
    @Column(name = "SWS", nullable = true)
    public Integer getSws() {
        return sws;
    }

    public void setSws(Integer sws) {
        this.sws = sws;
    }

    @Basic
    @Column(name = "gelesenVon", nullable = true)
    public Integer getGelesenVon() {
        return gelesenVon;
    }

    public void setGelesenVon(Integer gelesenVon) {
        this.gelesenVon = gelesenVon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vorlesungen that = (Vorlesungen) o;

        if (vorlNr != that.vorlNr) return false;
        if (titel != null ? !titel.equals(that.titel) : that.titel != null) return false;
        if (sws != null ? !sws.equals(that.sws) : that.sws != null) return false;
        if (gelesenVon != null ? !gelesenVon.equals(that.gelesenVon) : that.gelesenVon != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vorlNr;
        result = 31 * result + (titel != null ? titel.hashCode() : 0);
        result = 31 * result + (sws != null ? sws.hashCode() : 0);
        result = 31 * result + (gelesenVon != null ? gelesenVon.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "gelesenVon", referencedColumnName = "PersNr", nullable = false, insertable=false, updatable=false)
    public Professoren getProfessorenByGelesenVon() {
        return professorenByGelesenVon;
    }

    public void setProfessorenByGelesenVon(Professoren professorenByGelesenVon) {
        this.professorenByGelesenVon = professorenByGelesenVon;
    }

    @OneToMany(mappedBy = "vorlesungenByVorlNr")
    public Collection<Hören> getHörensByVorlNr() {
        return hörensByVorlNr;
    }

    public void setHörensByVorlNr(Collection<Hören> hörensByVorlNr) {
        this.hörensByVorlNr = hörensByVorlNr;
    }

    @OneToMany(mappedBy = "vorlesungenByVorlNr")
    public Collection<Prüfen> getPrüfensByVorlNr() {
        return prüfensByVorlNr;
    }

    public void setPrüfensByVorlNr(Collection<Prüfen> prüfensByVorlNr) {
        this.prüfensByVorlNr = prüfensByVorlNr;
    }

    @OneToMany(mappedBy = "vorlesungenByVorgänger")
    public Collection<Voraussetzen> getVoraussetzensByVorlNr() {
        return voraussetzensByVorlNr;
    }

    public void setVoraussetzensByVorlNr(Collection<Voraussetzen> voraussetzensByVorlNr) {
        this.voraussetzensByVorlNr = voraussetzensByVorlNr;
    }

    @OneToMany(mappedBy = "vorlesungenByNachfolger")
    public Collection<Voraussetzen> getVoraussetzensByVorlNr_0() {
        return voraussetzensByVorlNr_0;
    }

    public void setVoraussetzensByVorlNr_0(Collection<Voraussetzen> voraussetzensByVorlNr_0) {
        this.voraussetzensByVorlNr_0 = voraussetzensByVorlNr_0;
    }
}
