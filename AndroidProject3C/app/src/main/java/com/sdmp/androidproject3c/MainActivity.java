package com.sdmp.androidproject3c;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements  SmartPhoneList.ItemSelectListener {

    public static Integer[] phonePictures= {
            R.drawable.iphone11pro,
            R.drawable.iphone11promax,
            R.drawable.samsungs10,
            R.drawable.samsungnote10,
            R.drawable.oneplus7pro,
            R.drawable.motoz4};

    private static final String dangerousPermission =
            "edu.uic.cs478.s19.kaboom" ;
    private static final String TOAST_INTENT_FILTER =
            "edu.uic.cs478.s19.kaboom.showToast";

    public static String [] smartPhoneList;
    static String PREVIOUS_PHONE = "previous_phone" ;
    int Index=-1;
    int positionReference=-1;

    private final SmartPhoneImages smartPhoneImages = new SmartPhoneImages();
    private SmartPhoneList smartphone = null ;

    private FragmentManager fragmentManager;
    private FrameLayout phoneList,phoneImages;

    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        smartPhoneList=getResources().getStringArray(R.array.brand);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        phoneList = (FrameLayout) findViewById(R.id.smartphone_list);
        phoneImages = (FrameLayout) findViewById(R.id.smartphone_images);
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        smartphone=new SmartPhoneList();
        Index = -1;
        fragmentTransaction.replace(R.id.smartphone_list,smartphone);

        fragmentTransaction.commit();
        if (savedInstanceState != null) {
            Index = savedInstanceState.getInt(PREVIOUS_PHONE) ;
        }
        fragmentManager.addOnBackStackChangedListener(

                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setCustomLayout();
                    }
                });
    }
    public void onStart() {
        super.onStart() ;
        if (Index >= 0) {
            onItemSelection(Index);
            smartphone.setSelection(Index);
            smartphone.getListView().setItemChecked(Index,true);

        }
    }

    private void setCustomLayout() {
        int orientation = getResources().getConfiguration().orientation;
        if (!smartPhoneImages.isAdded()) {
            phoneList.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            phoneImages.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        } else {
            if (orientation == Configuration.ORIENTATION_LANDSCAPE){
                phoneList.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                phoneImages.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }
            else{
                phoneList.setLayoutParams(new LinearLayout.LayoutParams(
                        0, MATCH_PARENT));
                phoneImages.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT));
            }
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.launchA1A2:
                if(Index!=-1) {
                    sendA1A2Broadcast(positionReference);
                }
                else{
                    Toast.makeText(this, "No SmartPhone Was Selected", Toast.LENGTH_SHORT)
                            .show();
                }
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return false;
        }
    }

    private void sendA1A2Broadcast(int position) {
        Intent intent=new Intent(TOAST_INTENT_FILTER);
        intent.putExtra("EXTRA_POSITION",position);
        sendOrderedBroadcast(intent,dangerousPermission);
    }

    @Override
    public void onItemSelection(int position) {
        if (!smartPhoneImages.isAdded()) {

            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.add(R.id.smartphone_images,
                    smartPhoneImages);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();

        }

        smartPhoneImages.showImageAtIndex(position);
        Index=position;
        positionReference=position;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState) ;
        if (Index >= 0) {
            outState.putInt(PREVIOUS_PHONE, Index) ;
        }
        else {
            outState.putInt(PREVIOUS_PHONE, -1 ) ;
        }
    }

}
