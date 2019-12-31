package com.sdmp.androidproject3c;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class SmartPhoneList extends ListFragment {

    private static final String TAG="SmartPhoneList";
    private ItemSelectListener itemSelectListener = null;
    static final String OLD_INDEX = "oldIndex" ;
    Integer oldIndex = null ;

    public interface ItemSelectListener{
    public void onItemSelection(int position);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        getListView().setItemChecked(pos, true);
        itemSelectListener.onItemSelection(pos);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            itemSelectListener = (ItemSelectListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.phone_list, MainActivity.smartPhoneList));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedState) {
        View retView =  super.onCreateView(inflater, container, savedState) ;
        
        if (savedState != null) {
            int oldindex = savedState.getInt(OLD_INDEX) ;
            oldIndex = oldindex ;
        }
        else {
            oldIndex = null ;
        }
        return retView ;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (oldIndex != null) {
            int oldindex = oldIndex ;
            getListView().setSelection(oldindex) ;
            itemSelectListener.onItemSelection(oldindex);
        }
    }

}
