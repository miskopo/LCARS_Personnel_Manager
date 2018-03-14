package cz.muni.fi.pv168.model;

import java.util.Objects;

/**
 * @author Katarina Bulkova
 */
public class Ship {
    private Long id;
    private String name;
    private String designation;
    private String type;
    private double warpCapabilities;

    public Ship() {
    }

    public Ship(Long id, String name, String designation, String type, double warpCapabilities) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.type = type;
        this.warpCapabilities = warpCapabilities;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWarpCapabilities() {
        return warpCapabilities;
    }

    public void setWarpCapabilities(double warpCapabilities) {
        this.warpCapabilities = warpCapabilities;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", type='" + type + '\'' +
                ", warpCapabilities=" + warpCapabilities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Objects.equals(id, ship.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
