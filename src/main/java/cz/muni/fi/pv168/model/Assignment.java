package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.StarDateUtils;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Katarina Bulkova
 * @author Michal Polovka
 */
public class Assignment {
    private long id;
    private long shipId;
    private long crewmanId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Assignment(long id, long shipId, long crewmanId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.shipId = shipId;
        this.crewmanId = crewmanId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShipId() {
        return shipId;
    }

    public void setShipId(long shipId) {
        this.shipId = shipId;
    }

    public long getCrewmanId() {
        return crewmanId;
    }

    public void setCrewmanId(long crewmanId) {
        this.crewmanId = crewmanId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(shipId, that.shipId) &&
                Objects.equals(crewmanId, that.crewmanId) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(shipId, crewmanId, startDate, endDate);
    }
}
