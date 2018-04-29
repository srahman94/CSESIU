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
import com.example.sujon.csesiu.controller.EventController;
import com.example.sujon.csesiu.model.EventModel;

import java.util.List;

/**
 * Created by SuJoN on 4/28/2018.
 */

public class BlogAdapter extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<EventModel> blogItems;
    ImageLoader imageLoader = EventController.getInstance().getImageLoader();

    public BlogAdapter(Activity activity, List<EventModel> blogItems) {
        this.activity = activity;
        this.blogItems = blogItems;
    }

    @Override
    public int getCount() {
        return blogItems.size();
    }

    @Override
    public Object getItem(int location) {
        return blogItems.get(location);
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
            convertView = inflater.inflate(R.layout.blogs_list, null);
        if (imageLoader == null)
            imageLoader = EventController.getInstance().getImageLoader();
        NetworkImageView blogImg = (NetworkImageView) convertView
                .findViewById(R.id.blogs_img);
        TextView blogTitle = (TextView) convertView.findViewById(R.id.blogs_title);
        TextView blogDesc = (TextView) convertView.findViewById(R.id.blogs_desc);
        TextView blogDate = (TextView) convertView.findViewById(R.id.blogs_date);

        // getting movie data for the row
        EventModel em = blogItems.get(position);

        blogImg.setImageUrl(em.getEventImgUrl(), imageLoader);
        blogTitle.setText(em.getEventTitle());
        blogDesc.setText(em.getEventDesc());
        blogDate.setText(String.valueOf(em.getDate()));

        return convertView;
    }
}
