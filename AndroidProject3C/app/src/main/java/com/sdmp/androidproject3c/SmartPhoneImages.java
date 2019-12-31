package com.sdmp.androidproject3c;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class SmartPhoneImages extends Fragment {

    private static final String TAG = "SmartPhoneImages";
    ImageView image=null;
    private int currentIndex = -1;

    int getCurrentIndex(){
        return currentIndex;
    }

    void showImageAtIndex(int position) {
        if (position < 0)
            return;
        currentIndex = position;
        image.setImageResource(MainActivity.phonePictures[currentIndex]);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.phone_images,
                container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        image = (ImageView) getActivity().findViewById(R.id.imagePhone);
    }

}
