package Uni;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by peach on 12.05.16
 */
@Entity
@NamedQueries({@NamedQuery(name = "Professoren.findAll", query = "SELECT p FROM Professoren p")})
public class Professoren {
    private int persNr;
    private String name;
    private String rang;
    private Integer raum;
    private Collection<Assistenten> assistentensByPersNr;
    private Collection<Vorlesungen> vorlesungensByPersNr;
    private Collection<Prüfen> prüfensByPersNr;

    @Id
    @Column(name = "PersNr", nullable = false)
    public int getPersNr() {
        return persNr;
    }

    public void setPersNr(int persNr) {
        this.persNr = persNr;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Rang", nullable = true, length = 2)
    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    @Basic
    @Column(name = "Raum", nullable = true)
    public Integer getRaum() {
        return raum;
    }

    public void setRaum(Integer raum) {
        this.raum = raum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Professoren that = (Professoren) o;

        if (persNr != that.persNr) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (rang != null ? !rang.equals(that.rang) : that.rang != null) return false;
        if (raum != null ? !raum.equals(that.raum) : that.raum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = persNr;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (rang != null ? rang.hashCode() : 0);
        result = 31 * result + (raum != null ? raum.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "professorenByBoss")
    public Collection<Assistenten> getAssistentensByPersNr() {
        return assistentensByPersNr;
    }

    public void setAssistentensByPersNr(Collection<Assistenten> assistentensByPersNr) {
        this.assistentensByPersNr = assistentensByPersNr;
    }

    @OneToMany(mappedBy = "professorenByGelesenVon")
    public Collection<Vorlesungen> getVorlesungensByPersNr() {
        return vorlesungensByPersNr;
    }

    public void setVorlesungensByPersNr(Collection<Vorlesungen> vorlesungensByPersNr) {
        this.vorlesungensByPersNr = vorlesungensByPersNr;
    }

    @OneToMany(mappedBy = "professorenByPersNr")
    public Collection<Prüfen> getPrüfensByPersNr() {
        return prüfensByPersNr;
    }

    public void setPrüfensByPersNr(Collection<Prüfen> prüfensByPersNr) {
        this.prüfensByPersNr = prüfensByPersNr;
    }
}
