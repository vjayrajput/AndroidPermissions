package vjayrajput.androidpermission;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tbruyelle.rxpermissions.RxPermissions;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.Manifest;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getName();
    RxPermissions rxPermissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);
        setContentView(R.layout.activity_main);
        Button btnSingalPremission = findViewById(R.id.btn_singal_permission);
        Button btnMultiplePremission = findViewById(R.id.btn_multi_permission);
        btnSingalPremission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Must be done during an initialization phase like onCreate
                rxPermissions
                        .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION)
                        .subscribe(permission -> {
                            if (permission.granted) {
                                // `permission.name` is granted !
                                Log.e(TAG, "granted==>" + permission.name);
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                // Denied permission without ask never again
                                Log.e(TAG, "Denied without ask never again==>" + permission.name);
                            } else {
                                // Denied permission with ask never again
                                // Need to go to the settings
                                Log.e(TAG, "Denied ask never again==>" + permission.name);
                            }
                        });
            }
        });

    }

}
