package androidcustomcalendar.adms.com.calender.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class HolidayUtils {

    private static final Map<String, String> LUNAR_HOLIDAY = new HashMap<>();

    static {
        if (LUNAR_HOLIDAY.isEmpty()) {
//            LUNAR_HOLIDAY.put("Hari pertama bulan jan", "Festival Musim Semi");
//            LUNAR_HOLIDAY.put("Bulan Jan tanggal lima belas", "Festival Yuan Lan");
//            LUNAR_HOLIDAY.put("5 Mei", "Festival Perahu Naga");
//            LUNAR_HOLIDAY.put("7 Juli", "Festival Qi Xi");
//            LUNAR_HOLIDAY.put("15 Juli", "Festival Yuan Cina");
//            LUNAR_HOLIDAY.put("15 Agustus", "Festival Pertengahan Musim Gugur");
//            LUNAR_HOLIDAY.put("9 September", "Festival Chongyang");
//            LUNAR_HOLIDAY.put("1 Desember", "Laba Festival");
//            LUNAR_HOLIDAY.put("Tahun Baru Tradisional Imlek", "Malam Tahun Baru");
//            LUNAR_HOLIDAY.put("30 Desember", "Malam Tahun Baru");
        }
    }

    public static final String getHolidayOfMonth(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        switch (dayOfMonth) {
            case 1:
                if (month == Calendar.JANUARY) {
                    return "Tahun baru";
                }
                if (month == Calendar.APRIL) {
                    return "Hari April Mop";
                }
                if (month == Calendar.MAY) {
                    return "Hari kerja";
                }
                if (month == Calendar.JUNE) {
                    return "Hari anak-anak";
                }
                if (month == Calendar.JULY) {
                    return "Festival pembangunan pesta";
                }
                if (month == Calendar.AUGUST) {
                    return "Hari Tentara";
                }
                if (month == Calendar.OCTOBER) {
                    return "Hari Nasional";
                }
            default:
                switch (month) {
                    case Calendar.FEBRUARY:
                        if (dayOfMonth == 2) {
                            return "Hari lahan basah";
                        }
                        if (dayOfMonth == 14) {
                            return "Hari valentine";
                        }
                        break;
                    case Calendar.MARCH:
                        if (dayOfMonth == 8) {
                            return "Hari Perempuan";
                        }
                        if (dayOfMonth == 12) {
                            return "Arbor Day";
                        }
                        if (dayOfMonth == 15) {
                            return "Hari Hak Konsumen";
                        }
                        break;
                    case Calendar.APRIL:
                        if (dayOfMonth == 22) {
                            return "Hari Bumi";
                        }
                        break;

                    case Calendar.MAY:
                        switch (dayOfMonth) {
                            case 4:
                                return "Festival pemuda";
                            case 12:
                                return "Hari perawat";
                            case 15:
                                return "Hari museum";
                            default:
                                return calculateHolidayForWeek(calendar);
                        }
                    case Calendar.JUNE:
                        return calculateHolidayForWeek(calendar);
                    case Calendar.JULY:
                        break;
                    case Calendar.AUGUST:
                        break;
                    case Calendar.SEPTEMBER:
                        if (dayOfMonth == 3) {
                            return "Hari Kemenangan Anti-Jepang";
                        }
                        break;
                    case Calendar.OCTOBER:
                        break;
                    case Calendar.NOVEMBER:
                        break;
                    case Calendar.DECEMBER:
                        if (dayOfMonth == 1) {
                            return "Hari AIDS";
                        }
                        if (dayOfMonth == 25) {
                            return "Natal";
                        }
                        break;
                }
                return null;
        }
    }

    public static String getHolidayOfLunar(String lunar) {
        return LUNAR_HOLIDAY.get(lunar);
    }

    private static String calculateHolidayForWeek(Calendar calendar) {
        int dayForWeek = DayUtils.getDayForWeek(calendar);
        String holiday = null;
        if (dayForWeek == 0) {
            switch (calendar.get(Calendar.MONTH)) {
                case Calendar.MAY:
                    if (DayUtils.getWeekForMonth(calendar) == 3) {
                        holiday = "Hari ibu";
                    }
                    break;
                case Calendar.JUNE:
                    if (DayUtils.getWeekForMonth(calendar) == 4) {
                        holiday = "Hari ayah";
                    }
                    break;
            }
        }
        return holiday;
    }

}
