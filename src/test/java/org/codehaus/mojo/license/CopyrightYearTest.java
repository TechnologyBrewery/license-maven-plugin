package org.codehaus.mojo.license;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.codehaus.mojo.license.model.Copyright;
import org.junit.Test;

public class CopyrightYearTest {

    @Test
    public void testCopyrightWhenRangeIsPresent() {
        UpdateFileHeaderMojo updateFileHeaderMojo = new UpdateFileHeaderMojo();

        Integer inceptionYear = 1977;
        updateFileHeaderMojo.inceptionYear = inceptionYear;
        Copyright copyright = updateFileHeaderMojo.getCopyright("Test Organization");
        assertNotNull(copyright);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Integer lastYear = cal.get(Calendar.YEAR);

        String years = copyright.getYears();
        assertEquals("Expected to exactly match year range!", inceptionYear.toString() + " - " + lastYear.toString(),
                years);
    }

    @Test
    public void testSingleYearCopyrightWhenRangeIsPresentButIgnored() {
        UpdateFileHeaderMojo updateFileHeaderMojo = new UpdateFileHeaderMojo();
        updateFileHeaderMojo.ignoreLastDate = true;

        Integer inceptionYear = 1977;
        updateFileHeaderMojo.inceptionYear = inceptionYear;
        Copyright copyright = updateFileHeaderMojo.getCopyright("Test Organization");
        assertNotNull(copyright);

        String years = copyright.getYears();
        assertEquals("Expected to exactly match the inception year!", inceptionYear.toString(), years);
    }

    @Test
    public void testCopyrightWhenInceptionYearEqualsCurrentYear() {
        UpdateFileHeaderMojo updateFileHeaderMojo = new UpdateFileHeaderMojo();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Integer currentYear = cal.get(Calendar.YEAR);

        updateFileHeaderMojo.inceptionYear = currentYear;
        Copyright copyright = updateFileHeaderMojo.getCopyright("Test Organization");
        assertNotNull(copyright);

        String years = copyright.getYears();
        assertEquals("Expected to exactly current year!", currentYear.toString(), years);
    }

}
