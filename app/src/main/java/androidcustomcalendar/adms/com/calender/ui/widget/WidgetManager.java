package androidcustomcalendar.adms.com.calender.ui.widget;

import android.support.annotation.Nullable;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;

import androidcustomcalendar.adms.com.calender.ui.widget.transparent.MonthWidget;
import androidcustomcalendar.adms.com.calender.ui.widget.white.WhiteWidget;



public class WidgetManager {
    public static void updateAllWidget(Context context) {
        AppWidgetManager appWidgetManager =
                (AppWidgetManager) context.getSystemService(Context.APPWIDGET_SERVICE);
        updateMonthWidget(context, appWidgetManager);
        updateWhiteWidget(context, appWidgetManager);//
    }

    public static void updateWhiteWidget(Context context,@Nullable AppWidgetManager appWidgetManager) {
        if(appWidgetManager != null){
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, WhiteWidget.class));
            if (appWidgetIds != null) {
                for (int appWidgetId : appWidgetIds) {
                    WhiteWidget.updateAppWidget(context, appWidgetManager, appWidgetId);
                }
            }
        }
    }

    public static void updateMonthWidget(Context context,@Nullable AppWidgetManager appWidgetManager) {
        if(appWidgetManager != null){
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, MonthWidget.class));
            if (appWidgetIds != null) {
                for (int appWidgetId : appWidgetIds) {
                    MonthWidget.updateAppWidget(context, appWidgetManager, appWidgetId);
                }
            }
        }
    }
}
