package androidcustomcalendar.adms.com.calender.ui.floatwindow;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidcustomcalendar.adms.com.calender.R;
import androidcustomcalendar.adms.com.calender.utils.PermissionUtils;

public class FloatWindowService extends Service {

    private FloatWindowManager mFloatWindow;

    public FloatWindowService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mFloatWindow = FloatWindowManager.getInstance();
        try {
            mFloatWindow.show(this);
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
            Toast.makeText(this, "Silakan aktifkan izin float window di pengaturan.", Toast.LENGTH_SHORT).show();
            PermissionUtils.toSettings(this);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFloatWindow.hide(this);
        mFloatWindow.free();
        mFloatWindow = null;
    }
}
