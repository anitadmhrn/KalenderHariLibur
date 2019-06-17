package androidcustomcalendar.adms.com.calender.utils;

import androidcustomcalendar.adms.com.calender.R;
import androidcustomcalendar.adms.com.calender.global.Global;


public class EraUtils {
    public static final String[] HEAVENLY_STEMS = {"A", "B", "C", "D", "E", "", "", "", "", ""};
    public static final String[] EARTHLY_BRANCHES = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
    public static final String[] TWELVE_ZODIAC = {"Tikus", " Sapi", "harimau", "kelinci", "naga", "ular", "kuda", "domba", "monyet", "ayam", "anjing", "babi"};
    public static final int[] TWELVE_IMG = {
            R.drawable.shu,R.drawable.niu,R.drawable.hu,R.drawable.tu,
            R.drawable.lon,R.drawable.she,R.drawable.ma,R.drawable.yang,
            R.drawable.hou,R.drawable.ji,R.drawable.gou,R.drawable.zhu,
    };



    public static String getYearForHeavenlyStems(int year) {
        int position = (year - Global.YEAR_START) % 10;
        return HEAVENLY_STEMS[(position + 6) % 10];
    }


    public static String getYearForEarthlyBranches(int year) {
        int position = (year - Global.YEAR_START) % 12;
        return EARTHLY_BRANCHES[position];
    }


    public static String getYearForTwelveZodiac(int year) {
        int position = (year - Global.YEAR_START) % 12;
        return TWELVE_ZODIAC[position];
    }

    public static int getYearForTwelveZodiacImage(int year) {
        int position = (year - Global.YEAR_START) % 12;
        return TWELVE_IMG[position];
    }

}
