package com.example.sujon.csesiu.fragment;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sujon.csesiu.R;
import com.example.sujon.csesiu.activityes.Blog;

/**
 * Created by SuJoN on 4/17/2018.
 */

public class PostFragment extends Fragment{

    EditText title, desc, date;
    Button post;
    View v;
    Toolbar tl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.post_frag, container, false);

        tl= (Toolbar) v.findViewById(R.id.notice_tl);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tl);

        if(((AppCompatActivity)getActivity()).getSupportActionBar()!=null){
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        title = v.findViewById(R.id.input_title);
        desc = v.findViewById(R.id.input_desc);
        date = v.findViewById(R.id.input_date);
        post = v.findViewById(R.id.btn_post);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().isEmpty() || desc.getText().toString().isEmpty() || date.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Please fill the box", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(), "Publish", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), Blog.class);
                    startActivity(intent);
                }
            }
        });

        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

