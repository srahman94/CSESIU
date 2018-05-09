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
 * Created by SuJoN on 4/20/2018.
 */

public class EventAdapter extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<DatatModel> eventItems;
    ImageLoader imageLoader = Controller.getInstance().getImageLoader();

    public EventAdapter(Activity activity, List<DatatModel> eventItems) {
        this.activity = activity;
        this.eventItems = eventItems;
    }

    @Override
    public int getCount() {
        return eventItems.size();
    }

    @Override
    public Object getItem(int location) {
        return eventItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.event_list, null);
        if (imageLoader == null)
            imageLoader = Controller.getInstance().getImageLoader();
            NetworkImageView eventImg = (NetworkImageView) convertView
                    .findViewById(R.id.event_img);
            TextView eventTitle = (TextView) convertView.findViewById(R.id.event_title);
            TextView eventDesc = (TextView) convertView.findViewById(R.id.event_desc);
            TextView eventDate = (TextView) convertView.findViewById(R.id.event_date);

            // getting movie data for the row
            DatatModel em = eventItems.get(position);

            eventImg.setImageUrl(em.getEventImgUrl(), imageLoader);
            eventTitle.setText(em.getEventTitle());
            eventDesc.setText(em.getEventDesc());
            eventDate.setText(String.valueOf(em.getDate()));

        return convertView;
    }
}
