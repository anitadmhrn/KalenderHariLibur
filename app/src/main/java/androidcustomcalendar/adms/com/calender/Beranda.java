package androidcustomcalendar.adms.com.calender;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import java.util.Calendar;

public class Beranda extends AppCompatActivity {

    ImageButton HariLibur;
    ImageButton ImgKalender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityberanda);

        HariLibur = (ImageButton) findViewById(R.id.libur);
        HariLibur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Beranda.this, GbrLibur.class);
                startActivity(intentLoadNewActivity);

            }
        });
        ImgKalender = (ImageButton) findViewById(R.id.kalender);
        ImgKalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivityDua = new Intent(Beranda.this, MainActivity.class);
                startActivity(intentLoadActivityDua);
            }
        });

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Apakah Yakin Ingin Keluar Nih..?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
