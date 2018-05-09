package com.example.sujon.csesiu.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.sujon.csesiu.activityes.TeachersDetails;
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

public class Teachers extends Fragment{
    private static final String TAG = Students.class.getSimpleName();

    private static final String url = "https://api.myjson.com/bins/15oxya";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESC = "desc";
    public static final String EXTRA_MAIL = "mail";
    public static final String EXTRA_IMGURL = "img";
    public static final String EXTRA_MOBILE = "mobile";

    private List<DatatModel> teacherListModel = new ArrayList<>();
    MemberAdapter adapter;
    ListView teacherList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_teachers, container, false);
        teacherList = (ListView) v.findViewById(R.id.teacher_list);
        adapter = new MemberAdapter(this.getActivity(), teacherListModel);
        teacherList.setAdapter(adapter);

        teacherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), TeachersDetails.class);
                DatatModel clickedItem = teacherListModel.get(i);
                intent.putExtra(EXTRA_TITLE, clickedItem.getEventTitle());
                intent.putExtra(EXTRA_DESC, clickedItem.getEventDesc());
                intent.putExtra(EXTRA_MAIL, clickedItem.getMail());
                intent.putExtra(EXTRA_IMGURL, clickedItem.getEventImgUrl());
                intent.putExtra(EXTRA_MOBILE, clickedItem.getMobile());
                startActivity(intent);
            }
        });

        // Creating volley request obj
        JsonArrayRequest dataReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                DatatModel datatModel = new DatatModel();
                                datatModel.setEventTitle(obj.getString("title"));
                                datatModel.setEventImgUrl(obj.getString("image"));
                                datatModel.setEventDesc(obj.getString("desc"));
                                datatModel.setMail(obj.getString("mail"));
                                datatModel.setMobile(obj.getInt("mobile"));

                                // adding movie to movies array
                                teacherListModel.add(datatModel);

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
            }
        });

        // Adding request to request queue
        Controller.getInstance().addToRequestQueue(dataReq);
        return v;
    }

}
