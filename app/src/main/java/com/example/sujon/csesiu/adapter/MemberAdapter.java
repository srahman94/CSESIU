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
 * Created by SuJoN on 5/3/2018.
 */

public class MemberAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<DatatModel> stdList;
    ImageLoader imageLoader = Controller.getInstance().getImageLoader();

    public MemberAdapter(Activity activity, List<DatatModel> stdList) {
        this.activity = activity;
        this.stdList = stdList;
    }

    @Override
    public int getCount() {
        return stdList.size();
    }

    @Override
    public Object getItem(int location) {
        return stdList.get(location);
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
            convertView = inflater.inflate(R.layout.student_list, null);
        if (imageLoader == null)
            imageLoader = Controller.getInstance().getImageLoader();
        NetworkImageView stdImg = (NetworkImageView) convertView
                .findViewById(R.id.std_img);
        TextView stdTitle = (TextView) convertView.findViewById(R.id.std_title);
        TextView stdDesc = (TextView) convertView.findViewById(R.id.std_desc);

        // getting movie data for the row
        DatatModel em = stdList.get(position);

        stdImg.setImageUrl(em.getEventImgUrl(), imageLoader);
        stdTitle.setText(em.getEventTitle());
        stdDesc.setText(em.getEventDesc());

        return convertView;
    }
}
