package cz.muni.fi.pv168.common;

import java.time.LocalDate;

/**
 * @author Michal Polovka
 */
public class StarDateUtils {

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
}
