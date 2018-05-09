package com.example.sujon.csesiu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sujon.csesiu.activityes.Blog;
import com.example.sujon.csesiu.activityes.Event;
import com.example.sujon.csesiu.activityes.Gallery;
import com.example.sujon.csesiu.activityes.Login;
import com.example.sujon.csesiu.activityes.Members;
import com.example.sujon.csesiu.activityes.NewsfeedDetails;
import com.example.sujon.csesiu.activityes.Notice;
import com.example.sujon.csesiu.activityes.Result;
import com.example.sujon.csesiu.activityes.Routine;
import com.example.sujon.csesiu.activityes.ViewProfile;
import com.example.sujon.csesiu.adapter.HomePageListAdapter;
import com.example.sujon.csesiu.controller.Controller;
import com.example.sujon.csesiu.model.DatatModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String url = "https://api.myjson.com/bins/1drypv";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESC = "desc";
    public static final String EXTRA_DATE = "date";
    public static final String EXTRA_IMGURL = "img";

    private ProgressDialog pDialog;
    private ArrayList<DatatModel> newsfeedList = new ArrayList<>();
    private HomePageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Newsfeed");

        if(!haveNetworkConnection()){
            //AlertMessage.showMessage(con,"Alert","No Internet Connection","OK",R.drawable.alertpic);
            Snackbar.make(findViewById(R.id.toolbar),"No Internet Connection",Snackbar.LENGTH_LONG)
                    .show();
        }

        ListView showNewsfeed = findViewById(R.id.newsfeed_list);
        adapter = new HomePageListAdapter(this, newsfeedList);
        showNewsfeed.setAdapter(adapter);
        parseJSON();
        showNewsfeed.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, NewsfeedDetails.class);
            DatatModel clickedItem = newsfeedList.get(i);
            intent.putExtra(EXTRA_TITLE, clickedItem.getEventTitle());
            intent.putExtra(EXTRA_DESC, clickedItem.getEventDesc());
            intent.putExtra(EXTRA_DATE, clickedItem.getDate());
            intent.putExtra(EXTRA_IMGURL, clickedItem.getEventImgUrl());
            startActivity(intent);
        });

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        Button viewProfile = header.findViewById(R.id.view_btn);
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewProfile.class);
                startActivity(intent);
            }
        });
    }

    private void parseJSON(){
        //listView
        JsonArrayRequest dataReq = new JsonArrayRequest(url,
                response -> {
                    Log.d(TAG, response.toString());
                    hidePDialog();

                    // Parsing json
                    for (int i = 0; i < response.length(); i++) {
                        try {

                            JSONObject obj = response.getJSONObject(i);
                            DatatModel datatModel = new DatatModel();
                            datatModel.setEventTitle(obj.getString("title"));
                            datatModel.setEventImgUrl(obj.getString("image"));
                            datatModel.setEventDesc(obj.getString("desc"));
                            datatModel.setDate(obj.getInt("date"));


                            // adding movie to movies array
                            newsfeedList.add(datatModel);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    adapter.notifyDataSetChanged();
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        Controller.getInstance().addToRequestQueue(dataReq);
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = Objects.requireNonNull(cm).getAllNetworkInfo();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_login) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, Gallery.class);
            startActivity(intent);
        } else if (id == R.id.nav_notice) {
            Intent intent = new Intent(this, Notice.class);
            startActivity(intent);
        } else if (id == R.id.nav_blog) {
            Intent intent = new Intent(this, Blog.class);
            startActivity(intent);
        } else if (id == R.id.nav_result) {
            Intent intent = new Intent(this, Result.class);
            startActivity(intent);
        } else if (id == R.id.nav_routine) {
            Intent intent = new Intent(this, Routine.class);
            startActivity(intent);
        } else if (id == R.id.nav_event) {
            Intent intent = new Intent(this, Event.class);
            startActivity(intent);
        }else if (id == R.id.nav_members) {
            Intent intent = new Intent(this, Members.class);
            startActivity(intent);
        }

        //item.setChecked(true);
        // Set action bar title
        //setTitle(item.getTitle());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}







//recyclerview
/*
mRequestQueue = Volley.newRequestQueue(this);
@Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, NewsfeedDetails.class);
        DatatModel clickedItem = newsfeedList.get(position);
        intent.putExtra(EXTRA_TITLE, clickedItem.getEventTitle());
        intent.putExtra(EXTRA_DESC, clickedItem.getEventDesc());
        intent.putExtra(EXTRA_DATE, clickedItem.getDate());
        intent.putExtra(EXTRA_IMGURL, clickedItem.getEventImgUrl());
        startActivity(intent);
    }

    private void parseJSON() {
        String url = "https://api.myjson.com/bins/1drypv";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String eventTitle = hit.getString("title");
                                String eventDesc = hit.getString("desc");
                                String eventImgUrl = hit.getString("image");
                                int date = hit.getInt("date");

                                newsfeedList.add(new DatatModel(eventTitle, eventDesc, eventImgUrl, date));
                            }

                            adapter = new HomePageListAdapter(MainActivity.this, newsfeedList);
                            showNewsfeed.setAdapter(adapter);
                            adapter.setOnItemClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }*/