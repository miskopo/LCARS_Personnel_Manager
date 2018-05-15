package cz.muni.fi.pv168.common;

import java.time.LocalDate;

/**
 * @author Michal Polovka
 */
public class StarDateUtils {
    private double starDate;

    public StarDateUtils(double starDate) {
        this.starDate = starDate;
    }

    public double getStarDate() {
        return starDate;
    }

    public void setStarDate(double starDate) {
        this.starDate = starDate;
    }

    /**
     * Helper method to convert standard ISO date to stardate as seen in Star Trek: The Next Generation series.
     * Please note, that ISO to stardate conversion is only one-way, as stardate does not contain sufficient information
     * to be reversed (for example 2019-01-01 and 2018-01-01 yield the same result). This does not, however, diminish
     * purpose of this converter and application per se, as it's set in ST: TNG time (24+th century)
     * @param isoDate LocalDate object - ISO date to be converted to stardate
     * @return double representing stardate
     */
    public static double dateToStarDate(LocalDate isoDate){
        int century = isoDate.getYear() / 100 + 1;
        int season = isoDate.getMonthValue() / 4 + 1;
        int dayOfYear = isoDate.getDayOfYear();
        int dayOfMonth = isoDate.getDayOfMonth();
        String starDateString = String.format("%d%d%03d.%02d",
                century - 20,
                season,
                (dayOfYear * 3) % 999,
                dayOfMonth);
        return Double.parseDouble(starDateString);

    }

    public static double getCurrentStarDate(){
        return dateToStarDate(LocalDate.now());
    }
}
