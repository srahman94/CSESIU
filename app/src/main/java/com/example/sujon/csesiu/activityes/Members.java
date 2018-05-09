package com.example.sujon.csesiu.activityes;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.sujon.csesiu.R;
import com.example.sujon.csesiu.fragment.Alumni;
import com.example.sujon.csesiu.fragment.MemberViewPageFrag;
import com.example.sujon.csesiu.fragment.Students;
import com.example.sujon.csesiu.fragment.Teachers;

public class Members extends AppCompatActivity {

    LinearLayout linearLayout;
    Toolbar tl;
    ViewPager viewPager;
    TabLayout tabLayout;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        linearLayout = findViewById(R.id.members_ll);
        tl = findViewById(R.id.members_tl);
        setSupportActionBar(tl);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        this.addPages();

        tabLayout= (TabLayout) findViewById(R.id.tab);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

        if(haveNetworkConnection()==false){
            //AlertMessage.showMessage(con,"Alert","No Internet Connection","OK",R.drawable.alertpic);
            Snackbar.make(linearLayout,"No Internet Connection",Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private void addPages()
    {
        MemberViewPageFrag pagerAdapter=new MemberViewPageFrag(this.getSupportFragmentManager());
        pagerAdapter.addFragment(new Students());
        pagerAdapter.addFragment(new Teachers());
        pagerAdapter.addFragment(new Alumni());

        //SET ADAPTER TO VP
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
