package com.example.sujon.csesiu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.sujon.csesiu.R;

/**
 * Created by SuJoN on 4/19/2018.
 */

public class GalleryAdapter extends BaseAdapter{

    Context context;

    public Integer[] galleryImg={
            R.drawable.me,
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_gallery,
            R.drawable.me,
            R.drawable.ic_menu_manage,
            R.drawable.ic_menu_send,
            R.drawable.ic_menu_share,
            R.drawable.ic_menu_slideshow,
    };

    public GalleryAdapter(Context context) {
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
    }
}
