package com.example.sujon.csesiu.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.sujon.csesiu.R;
import com.example.sujon.csesiu.controller.EventController;
import com.example.sujon.csesiu.model.EventModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuJoN on 4/10/2018.
 */

public class HomePageListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<EventModel> newsfeedItems;
    ImageLoader imageLoader = EventController.getInstance().getImageLoader();

    public HomePageListAdapter(Activity activity, List<EventModel> newsfeedItems) {
        this.activity = activity;
        this.newsfeedItems = newsfeedItems;
    }

    @Override
    public int getCount() {
        return newsfeedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return newsfeedItems.get(location);
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
            convertView = inflater.inflate(R.layout.event_list, null);
        if (imageLoader == null)
            imageLoader = EventController.getInstance().getImageLoader();
        NetworkImageView eventImg = (NetworkImageView) convertView
                .findViewById(R.id.event_img);
        TextView eventTitle = (TextView) convertView.findViewById(R.id.event_title);
        TextView eventDesc = (TextView) convertView.findViewById(R.id.event_desc);
        TextView eventDate = (TextView) convertView.findViewById(R.id.event_date);

        // getting movie data for the row
        EventModel em = newsfeedItems.get(position);

        eventImg.setImageUrl(em.getEventImgUrl(), imageLoader);
        eventTitle.setText(em.getEventTitle());
        eventDesc.setText(em.getEventDesc());
        eventDate.setText(String.valueOf(em.getDate()));

        return convertView;
    }

}

/*ArrayAdapter<String> {

    private final Activity context;
    private final String[] noticeTitle;
    private final Integer[] noticeImg;

    public HomePageListAdapter( Activity context, String[] noticeTitle, Integer[] noticeImg) {
        super(context, R.layout.home_page_list, noticeTitle);
        this.context = context;
        this.noticeTitle = noticeTitle;
        this.noticeImg = noticeImg;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.home_page_list, null,true);

        TextView notcTitle = (TextView) rowView.findViewById(R.id.news_title);
        ImageView notcImg = (ImageView) rowView.findViewById(R.id.news_img);
        TextView notcDesc = (TextView) rowView.findViewById(R.id.news_desc);

        notcTitle.setText(noticeTitle[position]);
        notcImg.setImageResource(noticeImg[position]);
        //notcDesc.setText("Description "+noticeTitle[position]);
        return rowView;

    };

RecyclerView.Adapter<HomePageListAdapter.ViewHolder>{

    private ArrayList<String> noticeTitle = new ArrayList<>();
    private ArrayList<Integer> noticeImg = new ArrayList<>();
    private Context context;

    private String[] noticeTitle;
    private Integer[] noticeImg;

public class ViewHolder extends RecyclerView.ViewHolder{

    private TextView noticeTitle;
    private TextView noticeDesc;
    private ImageView noticeImg;
    public View layout;

    public ViewHolder(View v) {
        super(v);
        layout = v;
        noticeTitle = (TextView) v.findViewById(R.id.notice_title);
        noticeDesc = (TextView) v.findViewById(R.id.notice_desc);
        noticeImg = v.findViewById(R.id.notice_img);
    }
}

    public HomePageListAdapter(Context context, ArrayList<String> noticeTitle, ArrayList<Integer> noticeImg) {
        this.noticeTitle = noticeTitle;
        this.noticeImg = noticeImg;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.home_page_list, parent,false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.noticeTitle.setText(noticeTitle.get(position));
        holder.noticeImg.setImageResource(noticeImg.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }*/

