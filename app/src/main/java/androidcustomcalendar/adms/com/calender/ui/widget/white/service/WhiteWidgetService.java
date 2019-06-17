package androidcustomcalendar.adms.com.calender.ui.widget.white.service;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidcustomcalendar.adms.com.calender.R;
import androidcustomcalendar.adms.com.calender.entity.Day;
import androidcustomcalendar.adms.com.calender.global.Setting;
import androidcustomcalendar.adms.com.calender.ui.widget.base.BaseRemoteViewFactory;

import static androidcustomcalendar.adms.com.calender.global.Global.VIEW_DAY;
import static androidcustomcalendar.adms.com.calender.global.Global.VIEW_TODAY;
import static androidcustomcalendar.adms.com.calender.global.Global.VIEW_WEEK;

public class WhiteWidgetService extends RemoteViewsService {
    public WhiteWidgetService() {
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewFactory(this);
    }

    private class RemoteViewFactory extends BaseRemoteViewFactory {
        private RemoteViewFactory(Context context) {
            super(context);
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = null;
            switch (getItemViewType(position)) {
                case VIEW_WEEK:
                    int index = (position + Setting.date_offset) % WEEK_ARRAY.length;
                    remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.item_white_widget_week);
                    remoteViews.setTextViewText(R.id.tv, WEEK_ARRAY[index]);
                    break;
                case VIEW_TODAY:
                    remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.item_white_widget_today);
                    break;
                case VIEW_DAY:
                    remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.item_white_widget_day);
                    remoteViews.setTextViewText(R.id.tv_greg, "");
                    remoteViews.setTextViewText(R.id.tv_lunar, "");
                    break;
            }

            if (position >= mDateStartPos && position < mEndPosition && position - mDateStartPos < mDays.size()) {
                Day day = mDays.get(position - mDateStartPos);
                remoteViews.setTextViewText(R.id.tv_greg, String.valueOf(day.getDayOfMonth()));
                if (day.hasBirthday()) {
                    remoteViews.setTextViewText(R.id.tv_lunar, "Ulang tahun");
                    remoteViews.setInt(R.id.iv_birth, "setVisibility", View.VISIBLE);
                } else {
                    remoteViews.setTextViewText(R.id.tv_lunar, day.getLunar().getSimpleLunar());
                    remoteViews.setInt(R.id.iv_birth, "setVisibility", View.INVISIBLE);
                }

                if (day.hasEvent()) {
                    remoteViews.setInt(R.id.fl_event, "setVisibility", View.VISIBLE);
                } else {
                    remoteViews.setInt(R.id.fl_event, "setVisibility", View.INVISIBLE);
                }
            }
            return remoteViews;

        }

        @Override
        public RemoteViews getLoadingView() {
            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.item_white_widget_day);
            return remoteViews;
        }
    }
}
