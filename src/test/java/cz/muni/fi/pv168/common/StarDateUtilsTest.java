package cz.muni.fi.pv168.common;

import org.junit.Test;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author Michal Polovka
 */
public class StarDateUtilsTest {

    @Test
    public void dateToStarDate() {
        LocalDate isoDate = LocalDate.of(2019, 3, 15);
        assertTrue(StarDateUtils.dateToStarDate(isoDate) == 11222.15);
    }
}