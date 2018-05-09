package com.example.sujon.csesiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.sujon.csesiu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by SuJoN on 4/19/2018.
 */

public class GridViewAdapter extends BaseAdapter{

    Context context;

    private List<String> listImageURLs;

    public GridViewAdapter(Context context, List<String> listImageURLs){
        this.context = context;
        this.listImageURLs = listImageURLs;
    }

    @Override
    public int getCount() {
        return listImageURLs.size();
    }

    @Override
    public Object getItem(int position) {
        return listImageURLs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Picasso.with(context)
                .load(listImageURLs.get(position))
                .into(viewHolder.imageView);

        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
    }
}


/*public Integer[] galleryImg={
            R.drawable.me,
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_gallery,
            R.drawable.me,
            R.drawable.ic_menu_manage,
            R.drawable.ic_menu_send,
            R.drawable.ic_menu_share,
            R.drawable.ic_menu_slideshow,
    };

    public GridViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return galleryImg.length;
    }

    @Override
    public Object getItem(int i) {
        return galleryImg[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(galleryImg[i]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return imageView;
    }*/
