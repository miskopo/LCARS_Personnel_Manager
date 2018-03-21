package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.Rank;

import java.util.Objects;

/**
 * @author Katarina Bulkova
 */
public class Crewman {
    private Long id;
    private String name;
    private Rank rank;

    public Crewman() {
    }

    public Crewman(Long id, String name, Rank rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Crewman{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crewman crewman = (Crewman) o;
        return Objects.equals(id, crewman.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
