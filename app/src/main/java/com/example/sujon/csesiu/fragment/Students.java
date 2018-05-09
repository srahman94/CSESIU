package com.example.sujon.csesiu.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sujon.csesiu.R;
import com.example.sujon.csesiu.activityes.StudentDetails;
import com.example.sujon.csesiu.adapter.MemberAdapter;
import com.example.sujon.csesiu.controller.Controller;
import com.example.sujon.csesiu.model.DatatModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuJoN on 5/4/2018.
 */

public class Students extends Fragment{
    private static final String TAG = Students.class.getSimpleName();

    private static final String url = "https://api.myjson.com/bins/x1q8y";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESC = "desc";
    //public static final String EXTRA_DATE = "date";
    public static final String EXTRA_IMGURL = "img";

    private List<DatatModel> stdListModel = new ArrayList<>();
    private ProgressDialog pDialog;
    MemberAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);
        ListView studentList = view.findViewById(R.id.student_list);
        adapter = new MemberAdapter(this.getActivity(), stdListModel);
        studentList.setAdapter(adapter);
        studentList.setNestedScrollingEnabled(true);

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), StudentDetails.class);
                DatatModel clickedItem = stdListModel.get(i);
                intent.putExtra(EXTRA_TITLE, clickedItem.getEventTitle());
                intent.putExtra(EXTRA_DESC, clickedItem.getEventDesc());
                //intent.putExtra(EXTRA_DATE, clickedItem.getDate());
                intent.putExtra(EXTRA_IMGURL, clickedItem.getEventImgUrl());
                startActivity(intent);
            }
        });

        pDialog = new ProgressDialog(getContext());
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

                                // adding movie to movies array
                                stdListModel.add(datatModel);

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
        return view;
    }

    @Override
    public void onDestroy() {
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
