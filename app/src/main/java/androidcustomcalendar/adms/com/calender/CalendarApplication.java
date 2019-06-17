package androidcustomcalendar.adms.com.calender;

import android.app.Application;

import androidcustomcalendar.adms.com.calender.crash.CrashHandler;
import androidcustomcalendar.adms.com.calender.global.Setting;



public class CalendarApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
        Setting.loadSetting(this);
    }
}
