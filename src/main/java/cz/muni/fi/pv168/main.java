package cz.muni.fi.pv168;

import cz.muni.fi.pv168.common.StarDateUtils;

import java.util.Date;
import java.time.LocalDate;

/**
 * @author Michal Polovka
 */
public class main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2486, 1, 1);
        System.out.println(date.toString()  );
        System.out.println(StarDateUtils.dateToStarDate(date));
    }
}
