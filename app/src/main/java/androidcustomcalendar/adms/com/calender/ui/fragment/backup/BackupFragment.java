package androidcustomcalendar.adms.com.calender.ui.fragment.backup;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import androidcustomcalendar.adms.com.calender.R;
import androidcustomcalendar.adms.com.calender.database.dao.BirthdayDao;
import androidcustomcalendar.adms.com.calender.database.dao.EventDao;
import androidcustomcalendar.adms.com.calender.entity.Backup;
import androidcustomcalendar.adms.com.calender.entity.Birthday;
import androidcustomcalendar.adms.com.calender.entity.Event;
import androidcustomcalendar.adms.com.calender.ui.fragment.base.BaseFragment;
import androidcustomcalendar.adms.com.calender.utils.BackupUtils;
import androidcustomcalendar.adms.com.calender.utils.FileUtils;
import androidcustomcalendar.adms.com.calender.utils.PermissionUtils;

public class BackupFragment extends BaseFragment {

    private static final int BACKUP_VERSION = 0;
    public static final String TAG = "BackupFragment";
    public static final int SELECT_FILE = 012;
    public static final int REQUEST_CODE_BACKUP = 0x1;
    public static final int REQUEST_CODE_LOAD = 0x2;
    private TextView mTvOutput;

    public BackupFragment() {
        super(R.layout.fragment_backup);
    }

    public static BackupFragment newInstance() {
        Bundle args = new Bundle();
        BackupFragment fragment = new BackupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setupUI() {
        findToolbar().setNavigationOnClickListener(v -> removeFragment(BackupFragment.this));
        findViewById(R.id.btn_backup).setOnClickListener(this::click);
        findViewById(R.id.btn_import).setOnClickListener(this::click);
        findViewById(R.id.btn_clear).setOnClickListener(this::click);
        mTvOutput = findViewById(R.id.tv_out_put);
        mTvOutput.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    public void appendOutput(String text) {
        mTvOutput.append("\n");
        mTvOutput.append(text);
    }

    private void click(View view) {
        switch (view.getId()) {
            case R.id.btn_backup:
                if (PermissionUtils.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_CODE_BACKUP)) {
                    backup();
                }
                break;
            case R.id.btn_import:
                if (PermissionUtils.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_CODE_LOAD)) {
                    load();
                }
                break;
            case R.id.btn_clear:
                clear();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionUtils.handleResults(permissions, grantResults)) {
            switch (requestCode) {
                case REQUEST_CODE_BACKUP:
                    backup();
                    break;
                case REQUEST_CODE_LOAD:
                    load();
                    break;
            }
        }else {
            PermissionUtils.manual(mHostActivity);
        }
    }

    private void load() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Cadangkan"), SELECT_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_FILE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if (uri != null) {
                try {
                    Backup backup = BackupUtils.load(mHostActivity.getContentResolver(), uri);
                    if (backup == null) {
                        appendOutput("Gagal mengimpor!!");
                        return;
                    }
                    BackupUtils.insertBackupData(backup, mHostActivity);
                    appendOutput("Berhasil diimpor!!");
                } catch (Exception e) {
                    appendOutput(e.getMessage());
                    appendOutput("Gagal mengimpor!!");
                }
            }
        }
    }


    private void clear() {
        File parent = Environment.getExternalStorageDirectory();
        if (parent == null) {
            parent = mHostActivity.getCacheDir();
        }
        File[] list = parent.listFiles((dir, name) -> name.endsWith(".cdt"));
        if (list != null) {
            for (File file : list) {
                file.delete();
                appendOutput("Dihapus" + file.getName());
            }
        }
        appendOutput("Dibersihkan");
    }

    private void backup() {
        EventDao eventDao = EventDao.getInstance(mHostActivity);
        BirthdayDao birthdayDao = BirthdayDao.getInstance(mHostActivity);
        List<Event> events = eventDao.queryAll();
        List<Birthday> birthdays = birthdayDao.queryAll();
        Backup backup = new Backup();
        backup.setVersion(BACKUP_VERSION);
        backup.setBirthdays(birthdays);
        backup.setEvents(events);

        String file = BackupUtils.save(backup, mHostActivity);
        if (file == null) {
            appendOutput("Pencadangan gagal");
        } else {
            appendOutput("Pencadangan berhasil, dan file dihasilkan:" + file);
            FileUtils.shareFile(mHostActivity, file);
        }
    }


}
