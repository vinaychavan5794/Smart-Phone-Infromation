package com.sdmp.androidproject3b;

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
    private Receiver2 mReceiver2 ;

    private IntentFilter mFilter2 ;
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
            mReceiver2 = new Receiver2() ;
            mFilter2 = new IntentFilter(TOAST_INTENT_FILTER) ;
            mFilter2.setPriority(20);
            registerReceiver(mReceiver2, mFilter2) ;
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.sdmp.androidproject3c");
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
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.sdmp.androidproject3c");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                }
            }
            else {
                Toast.makeText(this, "No permission was granted", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        }
    }
    protected void onDestroy() {
        super.onDestroy() ;
        unregisterReceiver(mReceiver2) ;
    }

}

