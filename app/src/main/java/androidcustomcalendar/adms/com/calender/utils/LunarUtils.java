package androidcustomcalendar.adms.com.calender.utils;

import java.util.Calendar;
import java.util.Date;

import androidcustomcalendar.adms.com.calender.entity.LunarDay;

import static androidcustomcalendar.adms.com.calender.global.Global.YEAR_END;
import static androidcustomcalendar.adms.com.calender.global.Global.YEAR_END;
import static androidcustomcalendar.adms.com.calender.global.Global.YEAR_END;
import static androidcustomcalendar.adms.com.calender.global.Global.YEAR_END;
import static androidcustomcalendar.adms.com.calender.global.Global.YEAR_END;
import static androidcustomcalendar.adms.com.calender.global.Global.YEAR_END;
import static androidcustomcalendar.adms.com.calender.global.Global.YEAR_END;
import static androidcustomcalendar.adms.com.calender.global.Global.YEAR_START;
//import static androidcustomcalendar.adms.com.calender.ui.adapter.month.MonthFragmentAdapter.YEAR_END;


public class LunarUtils {

    static final long[] LUNAR_INFO = {0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,//1900-1909
            0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977,//1910-1919
            0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,//1920-1929
            0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950,//1930-1939
            0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557,//1940-1949
            0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,//1950-1959
            0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0,//1960-1969
            0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b6a0, 0x195a6,//1970-1979
            0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570,//1980-1989
            0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,//1990-1999
            0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5,//2000-2009
            0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930,//2010-2019
            0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,//2020-2029
            0x05aa0, 0x076a3, 0x096d0, 0x04afb, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,//2030-2039
            0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0,//2040-2049
            0x14b63, 0x09370, 0x049f8, 0x04970, 0x064b0, 0x168a6, 0x0ea50, 0x06b20, 0x1a6c4, 0x0aae0,//2050-2059
            0x0a2e0, 0x0d2e3, 0x0c960, 0x0d557, 0x0d4a0, 0x0da50, 0x05d55, 0x056a0, 0x0a6d0, 0x055d4,//2060-2069
            0x052d0, 0x0a9b8, 0x0a950, 0x0b4a0, 0x0b6a6, 0x0ad50, 0x055a0, 0x0aba4, 0x0a5b0, 0x052b0,//2070-2079
            0x0b273, 0x06930, 0x07337, 0x06aa0, 0x0ad50, 0x14b55, 0x04b60, 0x0a570, 0x054e4, 0x0d160,//2080-2089
            0x0e968, 0x0d520, 0x0daa0, 0x16aa6, 0x056d0, 0x04ae0, 0x0a9d4, 0x0a2d0, 0x0d150, 0x0f252,//2090-2099
            0x0d520};//2100

    public static final String[] LUNAR_MONTH = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
    public static final String[] CHINESE_TEN = {" ", " ", " ", " "};
    public static final String[] LUNAR_DAY = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static final Long BASE_DATE = new Date(0, 0, 31).getTime();


    public static final LunarDay getLunar(Calendar calendar) {
        int days = 0;
        int offset = getOffset(calendar);
        int lunarYear;
        for (lunarYear = YEAR_START; lunarYear < YEAR_END + 1 && offset > 0; lunarYear++) {
            days = daysOfYear(lunarYear);
            offset -= days;
        }
        if (offset <= 0) {
            offset += days;
            lunarYear--;
        }
        int leapMonth = monthOfLeap(lunarYear);
        boolean leap = false; //
        int lunarMonth;
        for (lunarMonth = 1; lunarMonth <= 12 && offset > 0; lunarMonth++) {
            if (leapMonth > 0 && lunarMonth == (leapMonth + 1) && !leap) {
                lunarMonth--;
                leap = true;
                days = daysOfLeapMonth(lunarYear);
            } else {
                days = daysOfMonth(lunarYear, lunarMonth);
            }
            offset -= days;
            if (leap && lunarMonth == (leapMonth + 1)) {
                leap = false;
            }
        }

        if (offset <= 0) {
            offset += days;
            lunarMonth--;
        }
        int day = offset;

        LunarDay lunarDay = new LunarDay();
        lunarDay.setYear(lunarYear);
        lunarDay.setDayOfMonth(getLunarDay(day));
        lunarDay.setMonth(getLunarMonth(leap,lunarMonth));
        
        return lunarDay;
    }

    private static int getOffset(Calendar calendar) {
        return (int) ((calendar.getTime().getTime() - BASE_DATE) / 86400000L) + 1;
    }

    private static final String getLunarMonth(boolean leap, int month) {
        return (leap ? "闰" : "") + LUNAR_MONTH[month - 1];
    }

    public static final String getLunarDay(int day) {
        if (day > 30) {
            return "";
        }
        int n = (day % 10 == 0) ? 9 : day % 10 - 1;
        if (day == 10) {
            return "Sepuluh pertama";
        } else {
            return CHINESE_TEN[day / 10] + LUNAR_DAY[n];
        }
    }

    private static final int daysOfYear(int year) {
        int sum = 348;
        for (int i = 0x8000; i > 0x8; i >>= 1) {
            if ((LUNAR_INFO[year - YEAR_START] & i) != 0) {
                sum += 1;
            }
        }
        return (sum + daysOfLeapMonth(year));
    }

    /**
     * Hitung jumlah hari dalam bulan dalam setahun
     *
     * @param year Tahun bulan
     * @return Hari kembali
     */
    private static final int daysOfLeapMonth(int year) {
        if (monthOfLeap(year) != 0) {
            if ((LUNAR_INFO[year - YEAR_START] & 0x10000) != 0) {
                return 30;
            } else {
                return 29;
            }
        } else {
            return 0;
        }
    }

    private static final int daysOfMonth(int year, int month) {
        if ((LUNAR_INFO[year - 1900] & (0x10000 >> month)) == 0) {
            return 29;
        } else {
            return 30;
        }
    }

    /**
     * Temukan bulan dalam setahun
     *
     * @param year Tahun bulan
     * @return Mengembalikan 0 berarti tidak ada bulan dalam setahun, jika tidak maka akan kembali ke bulan dalam sebulan.
     */
    private static final int monthOfLeap(int year) {
        return (int) (LUNAR_INFO[year - YEAR_START] & 0xf);
    }

}
