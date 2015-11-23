package com.example.keon.fragmentapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BottomSectionFragment extends Fragment{

    private static TextView topMemeText;
    private static TextView bottomMemeText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.bottom_picture_fragment, container, false); //the method returns the type of view (our layout). it generates the View, which is the screen containing bottom pic fragment from its xml file
        topMemeText = (TextView) view.findViewById(R.id.top_meme_text);
        bottomMemeText = (TextView) view.findViewById(R.id.bottom_meme_text);

        return view;
    }

    public void setMemeText(String top, String bottom){
        topMemeText.setText(top);
        bottomMemeText.setText(bottom);
    }

}
