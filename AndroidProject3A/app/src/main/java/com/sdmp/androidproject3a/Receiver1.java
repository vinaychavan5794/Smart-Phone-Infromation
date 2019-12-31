package com.sdmp.androidproject3a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Receiver1 extends BroadcastReceiver {
    private  int pos;

    @Override
    public void onReceive(Context context, Intent intent) {

        pos=intent.getIntExtra("EXTRA_POSITION",0);
        Intent intent1=new Intent(context,DisplayWebPage.class);
        intent1.putExtra("POSITION",pos);
        context.startActivity(intent1);

    }
}
