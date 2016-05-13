package Uni;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by peach on 12.05.16
 */
public class VoraussetzenPK implements Serializable {
    private int vorgänger;
    private int nachfolger;

    @Column(name = "Vorgänger", nullable = false)
    @Id
    public int getVorgänger() {
        return vorgänger;
    }

    public void setVorgänger(int vorgänger) {
        this.vorgänger = vorgänger;
    }

    @Column(name = "Nachfolger", nullable = false)
    @Id
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

        VoraussetzenPK that = (VoraussetzenPK) o;

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
}
