package com.sdmp.androidproject3a;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class DisplayWebPage extends Activity {

    private  int position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        position=intent.getIntExtra("POSITION",0);
        Intent newIntent=new Intent(Intent.ACTION_VIEW);
        if (position ==  0) {
            newIntent.setData(Uri.parse("https://www.apple.com/iphone-11-pro/"));
            startActivity(newIntent);
        }
        else if (position ==  1) {
            newIntent.setData(Uri.parse("https://www.apple.com/iphone-11-pro/"));
            startActivity(newIntent);
        }
        else if (position ==  2) {
            newIntent.setData(Uri.parse("https://www.samsung.com/us/mobile/galaxy-s10/"));
            startActivity(newIntent);
        }
        else if (position ==  3) {
            newIntent.setData(Uri.parse("https://www.samsung.com/us/mobile/galaxy-note10/"));
            startActivity(newIntent);
        }
        else if (position ==  4) {
            newIntent.setData(Uri.parse("https://www.oneplus.com"));
            startActivity(newIntent);
        }
        else if (position ==  5) {
            newIntent.setData(Uri.parse("https://www.motorola.com/us/products/moto-z-gen-4-unlocked"));
            startActivity(newIntent);
        }
        finish();
    }
}
