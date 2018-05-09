package com.example.sujon.csesiu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sujon.csesiu.R;
import com.example.sujon.csesiu.activityes.Result;
import com.squareup.picasso.Picasso;

/**
 * Created by SuJoN on 5/5/2018.
 */

public class ImageFragment extends Fragment {
    private String imageURL;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private GestureDetector.OnDoubleTapListener doubleTapListener = null;
    ImageView imageView;

    public ImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        imageView = view.findViewById(R.id.imageView);
        mScaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
        imageURL = getArguments().getString("imageURL");
        Picasso.with(getActivity())
                .load(imageURL)
                .into(imageView);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mScaleGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
        return view;
    }

    public GestureDetector.OnDoubleTapListener getDoubleTapListener() {
        return doubleTapListener;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }
}
