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

import java.util.ArrayList;

/**
 * Created by SuJoN on 4/10/2018.
 */

public class HomePageListAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<DatatModel> newsfeedItems;
    private ImageLoader imageLoader = Controller.getInstance().getImageLoader();
    private Context context;

    public HomePageListAdapter(Activity activity, ArrayList<DatatModel> newsfeedItems) {
        this.activity = activity;
        this.newsfeedItems = newsfeedItems;
    }

    public HomePageListAdapter(Context context) {
        this.context = context;
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
            imageLoader = Controller.getInstance().getImageLoader();
        NetworkImageView eventImg = (NetworkImageView) convertView
                .findViewById(R.id.event_img);
        TextView eventTitle = (TextView) convertView.findViewById(R.id.event_title);
        TextView eventDesc = (TextView) convertView.findViewById(R.id.event_desc);
        TextView eventDate = (TextView) convertView.findViewById(R.id.event_date);

        // getting movie data for the row
        DatatModel em = newsfeedItems.get(position);

        eventImg.setImageUrl(em.getEventImgUrl(), imageLoader);
        eventTitle.setText(em.getEventTitle());
        eventDesc.setText(em.getEventDesc());
        eventDate.setText(String.valueOf(em.getDate()));

        return convertView;
    }

}
//recyclerview
/*
private Context mContext;
    private ArrayList<DatatModel> newsfeedItem;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public HomePageListAdapter(Context context, ArrayList<DatatModel> newsfeedItem) {
        mContext = context;
        this.newsfeedItem = newsfeedItem;
    }

    @Override
    public HomePageListAdapter.HomePageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.event_list, parent, false);
        return new HomePageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomePageListAdapter.HomePageViewHolder holder, int position) {
        DatatModel currentItem = newsfeedItem.get(position);

        String imageUrl = currentItem.getEventImgUrl();
        String title = currentItem.getEventTitle();
        String desc = currentItem.getEventDesc();
        int date = currentItem.getDate();

        holder.eventaTitle.setText(title);
        holder.eventaDesc.setText(desc);
        holder.eventaDate.setText(date);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.eventaImg);
    }

    @Override
    public int getItemCount() {
        return newsfeedItem.size();
    }

    public class HomePageViewHolder extends RecyclerView.ViewHolder{
        public ImageView eventaImg;
        public TextView eventaTitle;
        public TextView eventaDesc;
        public TextView eventaDate;

        public HomePageViewHolder(View itemView) {
            super(itemView);
            eventaImg = itemView.findViewById(R.id.event_img);
            eventaTitle = itemView.findViewById(R.id.event_title);
            eventaDesc = itemView.findViewById(R.id.event_desc);
            eventaDate = itemView.findViewById(R.id.event_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
*/
//end
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

