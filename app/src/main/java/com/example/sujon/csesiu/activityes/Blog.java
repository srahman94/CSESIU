package com.example.sujon.csesiu.activityes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sujon.csesiu.R;
import com.example.sujon.csesiu.adapter.BlogAdapter;
import com.example.sujon.csesiu.controller.Controller;
import com.example.sujon.csesiu.fragment.PostFragment;
import com.example.sujon.csesiu.model.DatatModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Blog extends AppCompatActivity {

    private static final String TAG = Blog.class.getSimpleName();

    private static final String url = "https://api.myjson.com/bins/1drypv";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESC = "desc";
    public static final String EXTRA_DATE = "date";
    public static final String EXTRA_IMGURL = "img";

    private ProgressDialog pDialog;
    private List<DatatModel> blogList = new ArrayList<>();
    private ListView listView;
    private BlogAdapter adapter;
    android.support.v4.app.FragmentManager fragmentManager;
    RelativeLayout relativeLayout;
    Toolbar tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);
        relativeLayout = findViewById(R.id.relativeLayout);

        tl= (Toolbar) findViewById(R.id.blog_tl);
        setSupportActionBar(tl);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        FloatingActionButton add = findViewById(R.id.fab);

        if(haveNetworkConnection()==false){
            //AlertMessage.showMessage(con,"Alert","No Internet Connection","OK",R.drawable.alertpic);
            Snackbar.make(relativeLayout,"No Internet Connection",Snackbar.LENGTH_LONG)
                    .show();
        }

        fragmentManager = getSupportFragmentManager();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.container, new PostFragment()).commit();
            }
        });

        listView = findViewById(R.id.bolgs_list);
        adapter = new BlogAdapter(this, blogList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, BlogDetails.class);
            DatatModel clickedItem = blogList.get(i);
            intent.putExtra(EXTRA_TITLE, clickedItem.getEventTitle());
            intent.putExtra(EXTRA_DESC, clickedItem.getEventDesc());
            intent.putExtra(EXTRA_DATE, clickedItem.getDate());
            intent.putExtra(EXTRA_IMGURL, clickedItem.getEventImgUrl());
            startActivity(intent);
        });

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // changing action bar color
        /*getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#1b1b1b")));*/

        // Creating volley request obj
        JsonArrayRequest dataReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
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
                                blogList.add(datatModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
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

}

