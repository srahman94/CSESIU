package com.example.sujon.csesiu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sujon.csesiu.R;
import com.example.sujon.csesiu.adapter.NoticeAdapter;

/**
 * Created by SuJoN on 4/17/2018.
 */

public class Notice_Frag extends Fragment {

    ListView noticeList;
    String[] noticeTitle ={
            "Safari",
            "Camera",
            "Global",
            "FireFox",
            "UC Browser",
            "Android Folder",
            "VLC Player",
            "Cold War"
    };

    Integer[] noticeImg={
            R.drawable.me,
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_gallery,
            R.drawable.me,
            R.drawable.ic_menu_manage,
            R.drawable.ic_menu_send,
            R.drawable.ic_menu_share,
            R.drawable.ic_menu_slideshow,
    };
    View v;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.notice_frag, container, false);

        //NoticeAdapter adapter = new NoticeAdapter(getActivity(), noticeTitle, noticeImg);
        noticeList = v.findViewById(R.id.noticeList);
        //noticeList.setAdapter(adapter);

        noticeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= noticeTitle[+position];
                Toast.makeText(context, Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }
}

