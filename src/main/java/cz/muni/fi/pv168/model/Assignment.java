package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.StarDateUtils;

import java.util.Objects;

/**
 * @author Katarina Bulkova
 */
public class    Assignment {
    private Ship ship;
    private Crewman crewman;
    private StarDateUtils startDate;
    private StarDateUtils endDate;

    public Assignment(Ship ship, Crewman crewman, StarDateUtils startDate, StarDateUtils endDate) {
        this.ship = ship;
        this.crewman = crewman;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Crewman getCrewman() {
        return crewman;
    }

    public void setCrewman(Crewman crewman) {
        this.crewman = crewman;
    }

    public StarDateUtils getStartDate() {
        return startDate;
    }

    public void setStartDate(StarDateUtils startDate) {
        this.startDate = startDate;
    }

    public StarDateUtils getEndDate() {
        return endDate;
    }

    public void setEndDate(StarDateUtils endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(ship, that.ship) &&
                Objects.equals(crewman, that.crewman) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ship, crewman, startDate, endDate);
    }
}
