package androidcustomcalendar.adms.com.calender.utils;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidcustomcalendar.adms.com.calender.database.dao.EventDao;
import androidcustomcalendar.adms.com.calender.entity.Birthday;
import androidcustomcalendar.adms.com.calender.entity.Day;
import androidcustomcalendar.adms.com.calender.entity.Event;
import androidcustomcalendar.adms.com.calender.entity.LunarDay;
import androidcustomcalendar.adms.com.calender.global.GlobalData;



public class MonthUtils {
    private static final String DATE_SP = "-";

    /**
     * * Tentukan apakah ada Malam Tahun Baru
           *
     *
     * @param calendar
     * @return
     */
    public static final String checkNextDayIsChuxi(Calendar calendar) {
        Calendar next = Calendar.getInstance();
        next.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        next.add(Calendar.DATE, 1);
        LunarDay lunarDay = LunarUtils.getLunar(next);
        String lunar = lunarDay.getLunarDate();
        String lunarHoliday = HolidayUtils.getHolidayOfLunar(lunar);
        if ("Tahun baru".equals(lunarHoliday)) {
            return null;
        }
        return "Tahun baru";
    }

    public static final LunarDay getLunar(Calendar calendar) {
        LunarDay lunarDay = LunarUtils.getLunar(calendar);
        setHoliday(calendar, lunarDay);
        setSolar(calendar, lunarDay);
        setEra(lunarDay);
        setZodiac(lunarDay);
        setLunarHoliday(calendar, lunarDay);
        return lunarDay;
    }

    private static void setSolar(Calendar calendar, LunarDay lunarDay) {
        String solar = SolarUtils.getSolar(calendar);
        lunarDay.setSolar(solar);
    }

    private static void setHoliday(Calendar calendar, LunarDay lunarDay) {
        String holidayOfMonth = getHolidayOfMonth(calendar);
        lunarDay.setHoliday(holidayOfMonth);
    }

    private static void setLunarHoliday(Calendar calendar, LunarDay lunarDay) {
        String lunarHoliday = getLunarHoliday(calendar, lunarDay);
        if (lunarHoliday != null) {
            lunarDay.setHoliday(lunarHoliday);
        }
    }

    private static void setZodiac(LunarDay lunarDay) {
        String zodiac = EraUtils.getYearForTwelveZodiac(lunarDay.getYear());
        lunarDay.setZodiac(zodiac);
    }

    private static void setEra(LunarDay lunarDay) {
        String branches = EraUtils.getYearForEarthlyBranches(lunarDay.getYear());
        String stems = EraUtils.getYearForHeavenlyStems(lunarDay.getYear());
        lunarDay.setEra(stems + branches);
    }

    private static String getLunarHoliday(Calendar calendar, LunarDay lunarDay) {
        String lunar = lunarDay.getLunarDate();
        String lunarHoliday = HolidayUtils.getHolidayOfLunar(lunar);
        if ("Tahun baru".equals(lunarHoliday)) {
            lunarHoliday = checkNextDayIsChuxi(calendar);
        }
        return lunarHoliday;
    }


    private static String getHolidayOfMonth(Calendar calendar) {
        String holidayOfMonth = HolidayUtils.getHolidayOfMonth(calendar);
        if (holidayOfMonth != null && holidayOfMonth.length() > 4) {
            holidayOfMonth = holidayOfMonth.substring(0, 4);
        }
        return holidayOfMonth;
    }

    @NonNull
    public static Day generateDay(Calendar calendar, EventDao eventDao) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        boolean isToday = isToday(dayOfMonth, year, month);

        LunarDay lunarDay = MonthUtils.getLunar(calendar);
        Day day = new Day(year, month, lunarDay, isToday, dayOfMonth, dayOfWeek);
        setBirthday(month, dayOfMonth, lunarDay, day);
        setHoliday(year, month, dayOfMonth, day);
        setEvent(eventDao, day);
        return day;
    }

    private static void setHoliday(int year, int month, int dayOfMonth, Day day) {
        String str = new StringBuilder()
                .append(year)
                .append(DATE_SP)
                .append(month)
                .append(DATE_SP)
                .append(dayOfMonth)
                .toString();
        day.setHoliday(GlobalData.HOLIDAY.contains(str));
        day.setWorkday(GlobalData.WORKDAY.contains(str));
    }

    private static void setBirthday(int month, int dayOfMonth, LunarDay lunarDay, Day day) {
        List<Birthday> birthday0 = GlobalData.BIRTHDAY.get(lunarDay.getLunarDate());
        List<Birthday> birthday1 = GlobalData.BIRTHDAY.get(month + "Bulan" + dayOfMonth + "Hari");
        day.addBirthday(birthday0);
        day.addBirthday(birthday1);
    }

    private static void setEvent(EventDao eventDao, Day day) {
        List<Event> events = eventDao.query("Tahun" + day.getYear()  + "Bulan" + day.getMonth() + "Hari" + day.getDayOfMonth() );
        day.setEvents(events);
    }

    private static boolean isToday(int dayOfMonth, int year, int month) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        return calendar.get(Calendar.DAY_OF_MONTH) == dayOfMonth
                && calendar.get(Calendar.YEAR) == year
                && calendar.get(Calendar.MONTH) + 1 == month;
    }
}
