package com.sdmp.androidproject3a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String dangerousPermission =
            "edu.uic.cs478.s19.kaboom" ;
    private static final String TOAST_INTENT_FILTER =
            "edu.uic.cs478.s19.kaboom.showToast";
    private Receiver1 mReceiver1 ;

    private IntentFilter mFilter1 ;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.welcome);
        button=(Button)findViewById(R.id.press);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndLaunch();
            }
        });

    }


    private void checkPermissionAndLaunch() {
        if (ContextCompat.checkSelfPermission(this, dangerousPermission)
                == PackageManager.PERMISSION_GRANTED) {
            mReceiver1 = new Receiver1() ;
            mFilter1 = new IntentFilter(TOAST_INTENT_FILTER) ;
            mFilter1.setPriority(10);
            registerReceiver(mReceiver1, mFilter1) ;
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.sdmp.androidproject3b");
            if (launchIntent != null) {
                startActivity(launchIntent);
            }
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{dangerousPermission}, 0) ;
        }

    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.sdmp.androidproject3b");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                }
            }
            else {
                Toast.makeText(this, "No permission was granted", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy() ;
        unregisterReceiver(mReceiver1) ;
    }
}
