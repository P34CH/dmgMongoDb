package Uni;

import javax.persistence.*;

/**
 * Created by peach on 12.05.16
 */
@Entity
@NamedQueries({@NamedQuery(name = "Assistenten.findAll", query = "SELECT p FROM Assistenten p")})
public class Assistenten {
    private int persNr;
    private String name;
    private String fachgebiet;
    private Integer boss;
    private Professoren professorenByBoss;

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
    @Column(name = "Fachgebiet", nullable = true, length = 30)
    public String getFachgebiet() {
        return fachgebiet;
    }

    public void setFachgebiet(String fachgebiet) {
        this.fachgebiet = fachgebiet;
    }

    @Basic
    @Column(name = "Boss", nullable = true)
    public Integer getBoss() {
        return boss;
    }

    public void setBoss(Integer boss) {
        this.boss = boss;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assistenten that = (Assistenten) o;

        if (persNr != that.persNr) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (fachgebiet != null ? !fachgebiet.equals(that.fachgebiet) : that.fachgebiet != null) return false;
        if (boss != null ? !boss.equals(that.boss) : that.boss != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = persNr;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fachgebiet != null ? fachgebiet.hashCode() : 0);
        result = 31 * result + (boss != null ? boss.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Boss", referencedColumnName = "PersNr", nullable = false, insertable=false, updatable=false)
    public Professoren getProfessorenByBoss() {
        return professorenByBoss;
    }

    public void setProfessorenByBoss(Professoren professorenByBoss) {
        this.professorenByBoss = professorenByBoss;
    }
}
