package com.example.keon.fragmentapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;


public class TopSectionFragment extends Fragment{

    private static EditText topTextInput;
    private static EditText bottomTextInput;



    public interface TopSectionListener{
        public void createMeme(String top, String bottom); //to be used by mainactivity (will be made in it)
    }

    TopSectionListener activityCommander; //an object with one virtual class in it/ the class will be an intermediary between the two fragments and will e implemented in mainactivity

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            activityCommander = (TopSectionListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.top_section_fragment, container, false); //the method returns the type of view (our layout)

        topTextInput = (EditText) view.findViewById(R.id.topTextInput);
        bottomTextInput = (EditText) view.findViewById(R.id.bottomTextInput);
        final Button button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(v);
                    }

                }

        );


        return view;
    }

    public void buttonClicked(View v){
        activityCommander.createMeme(topTextInput.getText().toString(), bottomTextInput.getText().toString() );

    }
}
