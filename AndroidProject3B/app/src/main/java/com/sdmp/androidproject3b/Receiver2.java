package com.sdmp.androidproject3b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receiver2 extends BroadcastReceiver {

    private  int pos;

    String smartphone[]= {"Iphone 11 Pro","Iphone 11 Pro Max", "Samsung Galaxy S10", "Samsung Note10", "One Plus 7 Pro", "Motorolla Moto Z4"};

    @Override
    public void onReceive(Context context, Intent intent) {
        pos=intent.getIntExtra("EXTRA_POSITION",0);
        Toast.makeText(context,(String)smartphone[pos],
                Toast.LENGTH_LONG).show() ;


    }
}
