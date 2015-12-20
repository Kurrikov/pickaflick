package com.greatwhite.pickaflick;
import info.movito.themoviedbapi.model.Discover;

/**
 * Created by Keon on 11/4/2015.
 * Modified by Jason on 2015-11-27 -- Added handling for low and high limits for era
 */
public class TmdbObject {
    private int NumberOfPages = 1;      //retrieves a total of at most 100 movies to begin with. Will eventually narrow down the list.
    protected int era_low;
    protected int era_high;
    private Discover discover;

    TmdbObject(String MPAA_Rating, String genre, String era_low, String era_high, String minRating){
        this.era_low = Integer.parseInt(era_low);
        this.era_high = Integer.parseInt(era_high);
        setDiscoverProperties(MPAA_Rating, genre, era_low, era_high, Float.parseFloat(minRating));
    }

    private void setDiscoverProperties(String MPAA_Rating, String GenreList,  String era_low, String era_high, float minRating){

        discover = new Discover(); //this is where we set our filtering criteria (not calling from database here)
        discover.page(NumberOfPages)
                .language("en")
                .sortBy("popularity.desc")      //sorts the list by popularity only (descending).
                .withGenres(GenreList)
                .includeAdult(false)
                .voteAverageGte(minRating)
                .voteCountGte(10)               //don't return movies that only have a small number of ratings
                .releaseDateGte(String.valueOf(era_low) + "-01-01")
                .releaseDateLte(String.valueOf(era_high) + "-12-31")
                .certificationCountry("GB")
                .certificationLte(MPAA_Rating);
    }
    public Discover getDiscover(){
        return this.discover;
    }
}

