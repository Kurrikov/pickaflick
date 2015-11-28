package com.greatwhite.pickaflick;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;


public class Genre extends AppCompatActivity {
    ArrayList<CheckBox> checkList = new ArrayList<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        final Intent intent = new Intent(Genre.this, Era.class);

        final Bundle bundle = new Bundle();

        // Initialize checkbox variables
        final CheckBox action_check      = (CheckBox) findViewById(R.id.action_check);
        final CheckBox adventure_check   = (CheckBox) findViewById(R.id.adventure_check);
        final CheckBox animation_check   = (CheckBox) findViewById(R.id.animation_check);
        final CheckBox comedy_check      = (CheckBox) findViewById(R.id.comedy_check);
        final CheckBox crime_check       = (CheckBox) findViewById(R.id.crime_check);
        final CheckBox documentary_check = (CheckBox) findViewById(R.id.documentary_check);
        final CheckBox drama_check       = (CheckBox) findViewById(R.id.drama_check);
        final CheckBox family_check      = (CheckBox) findViewById(R.id.family_check);
        final CheckBox fantasy_check     = (CheckBox) findViewById(R.id.fantasy_check);
        final CheckBox foreign_check     = (CheckBox) findViewById(R.id.foreign_check);
        final CheckBox history_check     = (CheckBox) findViewById(R.id.history_check);
        final CheckBox horror_check      = (CheckBox) findViewById(R.id.horror_check);
        final CheckBox music_check       = (CheckBox) findViewById(R.id.music_check);
        final CheckBox mystery_check     = (CheckBox) findViewById(R.id.mystery_check);
        final CheckBox romance_check     = (CheckBox) findViewById(R.id.romance_check);
        final CheckBox scifi_check       = (CheckBox) findViewById(R.id.scifi_check);
        final CheckBox tv_movie_check    = (CheckBox) findViewById(R.id.tv_movie_check);
        final CheckBox thriller_check    = (CheckBox) findViewById(R.id.thriller_check);
        final CheckBox war_check         = (CheckBox) findViewById(R.id.war_check);
        final CheckBox western_check     = (CheckBox) findViewById(R.id.western_check);

        // populate list
        checkList.add(action_check);
        checkList.add(adventure_check);
        checkList.add(animation_check);
        checkList.add(comedy_check);
        checkList.add(crime_check);
        checkList.add(documentary_check);
        checkList.add(drama_check);
        checkList.add(family_check);
        checkList.add(fantasy_check);
        checkList.add(foreign_check);
        checkList.add(history_check);
        checkList.add(horror_check);
        checkList.add(music_check);
        checkList.add(mystery_check);
        checkList.add(romance_check);
        checkList.add(scifi_check);
        checkList.add(tv_movie_check);
        checkList.add(thriller_check);
        checkList.add(war_check);
        checkList.add(western_check);

        // Allow the user to select all the checkboxes quickly
        Button all_button = (Button) findViewById(R.id.all_button);
        all_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = 0; i < checkList.size(); i++) {
                    checkList.get(i).setChecked(true);
                }
            }
        });

        //  Allow the user to deselect all the checkboxes quickly
        Button none_button = (Button) findViewById(R.id.none_button);
        none_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = 0; i < checkList.size(); i++) {
                    checkList.get(i).setChecked(false);
                }
            }
        });


        Button continue_button = (Button) findViewById(R.id.continue_button);
        continue_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Build string containing the genre ID numbers
                StringBuilder genreIDs = new StringBuilder();
                if(action_check.isChecked()){
                    genreIDs.append("28,");
                }
                if(adventure_check.isChecked()){
                    genreIDs.append("12,");
                }
                if(animation_check.isChecked()){
                    genreIDs.append("16,");
                }
                if(comedy_check.isChecked()){
                    genreIDs.append("35,");
                }
                if(crime_check.isChecked()){
                    genreIDs.append("80,");
                }
                if(documentary_check.isChecked()){
                    genreIDs.append("99,");
                }
                if(drama_check.isChecked()){
                    genreIDs.append("18,");
                }
                if(family_check.isChecked()){
                    genreIDs.append("10751,");
                }
                if(fantasy_check.isChecked()){
                    genreIDs.append("14,");
                }
                if(foreign_check.isChecked()){
                    genreIDs.append("10769,");
                }
                if(history_check.isChecked()){
                    genreIDs.append("36,");
                }
                if(horror_check.isChecked()){
                    genreIDs.append("27,");
                }
                if(music_check.isChecked()){
                    genreIDs.append("10402,");
                }
                if(mystery_check.isChecked()){
                    genreIDs.append("9648,");
                }
                if(romance_check.isChecked()){
                    genreIDs.append("10749,");
                }
                if(scifi_check.isChecked()){
                    genreIDs.append("878,");
                }
                if(tv_movie_check.isChecked()){
                    genreIDs.append("10770,");
                }
                if(thriller_check.isChecked()){
                    genreIDs.append("53,");
                }
                if(war_check.isChecked()){
                    genreIDs.append("10752,");
                }
                if(western_check.isChecked()){
                    genreIDs.append("37");
                }

                if(genreIDs == null || genreIDs.toString().equals("")){
                    // Place empty string in bundle
                    bundle.putString("genre", "");
                }
                else{
                    // trim the final comma if necessary
                    if(genreIDs.substring(genreIDs.length()-1).equals(",")){
                        genreIDs.replace(genreIDs.length()-1, genreIDs.length(), "");
                    }

                    // Place string in bundle
                    bundle.putString("genre", genreIDs.toString());
                }

                intent.putExtras(bundle);

                //Start Next Activity
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_genre, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
