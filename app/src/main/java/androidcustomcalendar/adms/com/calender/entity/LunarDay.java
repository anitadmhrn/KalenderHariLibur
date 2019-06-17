package androidcustomcalendar.adms.com.calender.entity;

import java.io.Serializable;


public class LunarDay implements Serializable {

    private String dayOfMonth;
    private int year;
    private String month;

    private String holiday; // Berikan prioritas pada Festival Bulan
    private String solar; //
    private String era; // Tahun kalender bulan
    private String zodiac;

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getSolar() {
        return solar;
    }

    public void setSolar(String solar) {
        this.solar = solar;
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    /**
     * @return Kembalikan formulir {x bulan awal x}
     */
    public String getLunarDate() {
        return month + dayOfMonth;
    }

    public String getSimpleLunar() {

        if (holiday != null) {
            return holiday;
        } else if (solar != null) {
            return solar;
        } else if (dayOfMonth.equals("Hari pertama")) {
            return month;
        } else {
            return dayOfMonth;
        }
    }
}
