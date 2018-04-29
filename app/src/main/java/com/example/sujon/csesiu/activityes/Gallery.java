package com.example.sujon.csesiu.activityes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.sujon.csesiu.R;
import com.example.sujon.csesiu.adapter.GalleryAdapter;

public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GridView galleryImg = findViewById(R.id.grid_view);

        galleryImg.setAdapter(new GalleryAdapter(this));

        galleryImg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent passImg = new Intent(getApplicationContext(), FullImageActivity.class);
                passImg.putExtra("id", position);
                startActivity(passImg);
            }
        });
    }
}
