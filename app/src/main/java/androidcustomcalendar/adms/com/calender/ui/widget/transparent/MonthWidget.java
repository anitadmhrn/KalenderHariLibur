package androidcustomcalendar.adms.com.calender.ui.widget.transparent;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.Locale;

import androidcustomcalendar.adms.com.calender.MainActivity;
import androidcustomcalendar.adms.com.calender.R;
import androidcustomcalendar.adms.com.calender.entity.LunarDay;
import androidcustomcalendar.adms.com.calender.global.Setting;
import androidcustomcalendar.adms.com.calender.ui.widget.transparent.service.MonthService;
import androidcustomcalendar.adms.com.calender.utils.LunarUtils;

/**
 * Implementation of App Widget functionality.
 */
public class MonthWidget extends AppWidgetProvider {

    private static final String TAG = "MonthWidget";

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId) {
        Setting.loadSetting(context);
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        RemoteViews views = setupRemoteViews(context, calendar);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.gv_month);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    private static RemoteViews setupRemoteViews(Context context, Calendar calendar) {
        // Tema harus diubah untuk mencapai tujuan, tetapi Android tampaknya tidak menyediakan fitur seperti setTheme for View w (゚ Д ゚) w
        int layout = Setting.TransparentWidget.trans_widget_theme_color == 0 ? R.layout.month_widget : R.layout.month_widget_light;
        RemoteViews views = new RemoteViews(context.getPackageName(), layout);
        LunarDay lunarDay = LunarUtils.getLunar(calendar);

        Intent intent = new Intent(context, MonthService.class);

        int month = calendar.get(Calendar.MONTH) + 1;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);

        views.setOnClickPendingIntent(R.id.iv_launch, pendingIntent);
        int themeColor = Setting.TransparentWidget.trans_widget_theme_color;
        views.setInt(R.id.widget, "setBackgroundColor", Color.argb(Setting.TransparentWidget.trans_widget_alpha, themeColor, themeColor, themeColor));

        views.setRemoteAdapter(R.id.gv_month, intent);
        views.setTextViewText(R.id.tv_lunar, lunarDay.getLunarDate());
        views.setTextViewTextSize(R.id.tv_lunar, TypedValue.COMPLEX_UNIT_SP, Setting.TransparentWidget.trans_widget_lunar_month_text_size);
        views.setTextViewTextSize(R.id.tv_year, TypedValue.COMPLEX_UNIT_SP, Setting.TransparentWidget.trans_widget_year_text_size);
        views.setTextViewTextSize(R.id.tv_date, TypedValue.COMPLEX_UNIT_SP, Setting.TransparentWidget.trans_widget_month_text_size);
        views.setTextViewText(R.id.tv_year, String.valueOf(calendar.get(Calendar.YEAR)));
        views.setTextViewText(R.id.tv_date, String.format(Locale.ENGLISH, "Bulan %02d", month));
        return views;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onDisabled(Context context) {
    }

}

