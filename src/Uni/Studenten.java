package Uni;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by peach on 12.05.16
 */
@Entity
public class Studenten {
    private int matrNr;
    private String name;
    private Integer semester;
    private Collection<Hören> hörensByMatrNr;
    private Collection<Prüfen> prüfensByMatrNr;

    @Id
    @Column(name = "MatrNr", nullable = false)
    public int getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(int matrNr) {
        this.matrNr = matrNr;
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
    @Column(name = "Semester", nullable = true)
    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Studenten studenten = (Studenten) o;

        if (matrNr != studenten.matrNr) return false;
        if (name != null ? !name.equals(studenten.name) : studenten.name != null) return false;
        if (semester != null ? !semester.equals(studenten.semester) : studenten.semester != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = matrNr;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "studentenByMatrNr")
    public Collection<Hören> getHörensByMatrNr() {
        return hörensByMatrNr;
    }

    public void setHörensByMatrNr(Collection<Hören> hörensByMatrNr) {
        this.hörensByMatrNr = hörensByMatrNr;
    }

    @OneToMany(mappedBy = "studentenByMatrNr")
    public Collection<Prüfen> getPrüfensByMatrNr() {
        return prüfensByMatrNr;
    }

    public void setPrüfensByMatrNr(Collection<Prüfen> prüfensByMatrNr) {
        this.prüfensByMatrNr = prüfensByMatrNr;
    }
}
