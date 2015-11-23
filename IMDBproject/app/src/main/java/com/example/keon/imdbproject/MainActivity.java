package com.example.keon.imdbproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String API_ID = "d2148dac5e85b1dee3f0fe5e2c3a83ab";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textview = (TextView) findViewById(R.id.textView);


        //test
        textview.setText(fetchMoviesTest("A", "Comedy", "Romance", 2000, 8));

    }


    /**
     *  THIS IS THE METHOD THAT YOU MUST USE, after you have gotten all of your filters. You must make sure to use try-catch blocks for the exceptions. The only serious exception that will require actual handling is the no-Internet exception. In the test-method I have demonstrated its usage
     * @param mpaa_Ratings: A String from the following list: E,G,PG,M,MA15+,R18+,X18+,RC,14A,18A,R,A,NR,PG-13,NC-17 ... . NOTE: The MPAA rating will return movies with that rating AND lower. So if the filter is R, you will also get movies that are PG, E, etc. See http://docs.themoviedb.apiary.io/#reference/certifications/certificationmovielist/get for the details and full list.
     * @param genre: A String from the following list: Adventure, Animation, Comedy, Crime, Documentary, Drama, Family, Fantasy, Foreign, History, Horror, Music, Mystery, Romance, Science Fiction, TV Movie, Thriller, War, Western
     * @param subgenre: Any string from the genre list. You may also pass in an empty string if you don't care. Furthermore, Genre and Subgenre Cannot be the same, or an exception is thrown.
     * @param era: A 4 digit integer indicating the decade at which the movie(s) was/were released. Ranges from 1900 to 2090. For example, movies from the 2000s will need the integer 2000. Movies from the 1980s will need the integer 1980, and so on. (Don't try years that don't end with a 0)
     * @param minRating: A float from 0.0 to 10.0. This will be the minimum rating in the list of movies that you get. So if it is 8.0, you will get movies whose ratings range from 8 to 10.
     * @return A list containing movie attributes of all filtered movies. See the MovieAttribute.java class for details.
     * @throws TmdbObject.GenreException: If the supplied genre doesn't exist, or the genre and subgenre are identical, this is the error you get. This shouldn't be an issue for this project.
     * @throws TmdbRunnable.NoInternetConnectionException: If there is no internet or very slow internet. See TmdbRunnable's hostIsReachable() method. You probably just want to display the message from the exception in the catch block. See the test-method below.
     * @throws InterruptedException: if the image(s) fail to download. No particular handling should be needed for this. Just use an empty catch block.
     */
    protected List<MovieAttributes> fetchMoviesList(String mpaa_Ratings, String genre, String subgenre, int era, float minRating) throws TmdbRunnable.NoInternetConnectionException, InterruptedException, TmdbObject.GenreException{
        TmdbMoviesObject tmdbMoviesobject = null; //the "main container" of all movie-related items
            tmdbMoviesobject = new TmdbMoviesObject(mpaa_Ratings, genre, subgenre, era, minRating);
            Thread thread = new Thread(new TmdbRunnable(tmdbMoviesobject, API_ID)); //all the network calls for image retrieval and movie list retrieval will be done on this thread
            thread.start();
            thread.join();
            if(tmdbMoviesobject.getMoviesList()== null){
                throw new TmdbRunnable.NoInternetConnectionException();
            }
            else return tmdbMoviesobject.getMoviesList();
    }




    //test-method
    protected String fetchMoviesTest(String mpaa_Ratings, String genre, String subgenre, int era, float minRating){
        String s = "";
        List<MovieAttributes> list = null; //get the list of filtered movies list

        try {
            list = fetchMoviesList(mpaa_Ratings,  genre, subgenre,  era, minRating);

            //test code --
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView = list.get(0).getImageView(imageView, 400, 400);    //this is how you can get the movie poster (in this case, for the 0th movie in the list).
            // You may change its size by passing in two additional int parameters
            if(list != null && list.size() != 0){
                for(int i = 0; i < list.size(); i++)
                    s = s + list.get(i).getTitle() + " (" + list.get(i).getReleaseDate().substring(0,4) + ")\n"; //print the title and release dates of the movies
            }
        } catch (TmdbRunnable.NoInternetConnectionException e) {
            s = e.getMessage();
        } catch (InterruptedException e) {
            s = e.getMessage();
        } catch (TmdbObject.GenreException e) {
            s = e.getMessage();
        }
        return s;
    }
}

