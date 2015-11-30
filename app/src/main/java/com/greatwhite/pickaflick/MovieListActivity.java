package com.greatwhite.pickaflick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import com.greatwhite.pickaflick.dummy.DummyContent;
import java.util.ArrayList;



/**
 * An activity representing a list of Movies. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MovieDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link MovieListFragment} and the item details
 * (if present) is a {@link MovieDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link MovieListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class MovieListActivity extends FragmentActivity
        implements MovieListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_movie_list);
        setContentView(R.layout.activity_movie_twopane);

        DummyContent.ITEMS.clear();
        DummyContent.ITEM_MAP.clear();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String InternetExceptionMessage = bundle.getString("Internet Exception"), InterruptionExceptionMessage = bundle.getString("Interruption Exception");
            boolean NoErrors = InternetExceptionMessage.equals("") && InterruptionExceptionMessage.equals("");
            if (NoErrors) {
                int i = 0;
                ArrayList<String> movieTitles, movieUrls;
                movieTitles = bundle.getStringArrayList("movieTitles");
                movieUrls = bundle.getStringArrayList("movieUrls");
                if (movieTitles != null) {
                    for (String title : movieTitles)
                        DummyContent.addItem(new DummyContent.DummyItem(String.valueOf(i), title, movieUrls.get(i++)));
                }
                if (i == 0)  DummyContent.addItem(new DummyContent.DummyItem(String.valueOf(999), "No Movies Found", ""));

            } else {
                TextView ErrorView = (TextView) findViewById(R.id.ErrorText);
                String ErrorMessage = "";
                if (InternetExceptionMessage.equals("")) ErrorMessage = InterruptionExceptionMessage;
                else ErrorMessage = InternetExceptionMessage;
                ErrorView.setText(ErrorMessage);
            }
        }

        if (findViewById(R.id.movie_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((MovieListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.movie_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link MovieListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(MovieDetailFragment.ARG_ITEM_ID, id);
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, MovieDetailActivity.class);
            detailIntent.putExtra(MovieDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }


//    //a quick method to show some of the features of the movieAttributes Class
//    protected void temporaryMovieDisplayer(TextView textView, List<MovieAttributes> movieList){
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//                                                                            // You may change its size by passing in two additional int parameters
//        String s = "";
//        if(movieList != null && movieList.size() != 0){
//            imageView = movieList.get(0).getImageView(imageView, 400, 400);    //this is how you can get the movie poster (in this case, for the 0th movie in the list).
//            for(int i = 0; i < movieList.size(); i++)
//                s = s + movieList.get(i).getTitle() + " (" + movieList.get(i).getReleaseDate().substring(0,4) + ")\n"; //print the title and release dates of the movies
//        }
//        else{
//            s = "No movies to display";
//        }
//        textView.setText(s);
//    }
}
