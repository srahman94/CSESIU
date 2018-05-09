package com.example.sujon.csesiu.activityes;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.sujon.csesiu.R;
import com.example.sujon.csesiu.adapter.ImageViewPagerAdapter;
import com.example.sujon.csesiu.fragment.ImageFragment;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImageViewPagerAdapter viewPagerAdapter;
    private List<Fragment> listFragments = new ArrayList<>();
    private List<String> listImageURLs = new ArrayList<>();
    Toolbar tl;

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        tl= (Toolbar) findViewById(R.id.gl_tl);
        setSupportActionBar(tl);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getArguments();
        createFragments();

        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ImageViewPagerAdapter(getSupportFragmentManager(), listFragments);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);
    }

    private void getArguments(){
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            listImageURLs = bundle.getStringArrayList("imageURLs");
            position = bundle.getInt("position");
        }
    }

    private void createFragments(){
        for(int i=0;i<listImageURLs.size();i++){
            Bundle bundle = new Bundle();
            bundle.putString("imageURL", listImageURLs.get(i));
            ImageFragment imageFragment = new ImageFragment();
            imageFragment.setArguments(bundle);
            listFragments.add(imageFragment);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), Gallery.class);
        startActivity(i);
        finish();
    }*/
}

/*PagerAdapter{
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
*/