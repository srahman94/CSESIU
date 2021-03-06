package com.example.sujon.csesiu.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.sujon.csesiu.R;
import com.example.sujon.csesiu.controller.Controller;
import com.example.sujon.csesiu.model.DatatModel;

import java.util.List;

/**
 * Created by SuJoN on 4/12/2018.
 */

public class NoticeAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<DatatModel> noticeItems;
    ImageLoader imageLoader = Controller.getInstance().getImageLoader();

    public NoticeAdapter(Activity activity, List<DatatModel> noticeItems) {
        this.activity = activity;
        this.noticeItems = noticeItems;
    }

    @Override
    public int getCount() {
        return noticeItems.size();
    }

    @Override
    public Object getItem(int location) {
        return noticeItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.notice_list, null);
        if (imageLoader == null)
            imageLoader = Controller.getInstance().getImageLoader();
            NetworkImageView noticeImg = (NetworkImageView) convertView
                    .findViewById(R.id.notice_img);
            TextView noticeTitle = (TextView) convertView.findViewById(R.id.notice_title);
            TextView noticeDesc = (TextView) convertView.findViewById(R.id.notice_desc);
            TextView noticeDate = (TextView) convertView.findViewById(R.id.notice_date);

            // getting movie data for the row
            DatatModel em = noticeItems.get(position);

            noticeImg.setImageUrl(em.getEventImgUrl(), imageLoader);
            noticeTitle.setText(em.getEventTitle());
            noticeDesc.setText(em.getEventDesc());
            noticeDate.setText(String.valueOf(em.getDate()));

        return convertView;
    }
}
