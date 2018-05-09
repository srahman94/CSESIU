package com.example.sujon.csesiu.activityes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sujon.csesiu.R;
import com.squareup.picasso.Picasso;

import static com.example.sujon.csesiu.MainActivity.EXTRA_DATE;
import static com.example.sujon.csesiu.MainActivity.EXTRA_DESC;
import static com.example.sujon.csesiu.MainActivity.EXTRA_IMGURL;
import static com.example.sujon.csesiu.MainActivity.EXTRA_TITLE;

public class EventDetails extends AppCompatActivity {

    String title, desc, img;
    int date;
    Toolbar tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);tl= (Toolbar) findViewById(R.id.datails_tl);
        setSupportActionBar(tl);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent in = getIntent();
        img = in.getStringExtra(EXTRA_IMGURL);
        title = in.getStringExtra(EXTRA_TITLE);
        desc = in.getStringExtra(EXTRA_DESC);
        date = in.getIntExtra(EXTRA_DATE, 0);

        ImageView iv = findViewById(R.id.img);
        Picasso.with(this).load(img).placeholder(R.mipmap.ic_launcher).into(iv);
        TextView tt = findViewById(R.id.title);
        tt.setText(title);
        TextView dt = findViewById(R.id.desc);
        dt.setText(desc);
        TextView d = findViewById(R.id.date);
        d.setText(String.valueOf(date));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
