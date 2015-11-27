package com.greatwhite.pickaflick;
import info.movito.themoviedbapi.model.Discover;

/**
 * Created by Keon on 11/4/2015.
 * Modified by Jason on 2015-11-27 -- Added handling for low and high limits for era
 */
public class TmdbObject {

    private int NumberOfPages = 5;      //retrieves a total of at most 100 movies to begin with. Will eventually narrow down the list to 10.
    protected int era_low;
    protected int era_high;
    private Discover discover;
    //private Map<String,String> map;

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
                .voteAverageGte(minRating)
                .releaseDateGte(String.valueOf(era_low) + "-01-01")
                .releaseDateLte(String.valueOf(era_high) + "-12-31")
                .certificationCountry("US")
                .certificationLte(MPAA_Rating);
    }
    public Discover getDiscover(){
        return this.discover;
    }

    /*  //Use as reference
    private void setHashMap(){
        map = new HashMap<String,String>();
        map.put("","");
        map.put("Action", "28");
        map.put("Adventure", "12");
        map.put("Animation", "16");
        map.put("Comedy", "35");
        map.put("Crime", "80");
        map.put("Documentary", "99");
        map.put("Drama", "18");
        map.put("Family", "10751");
        map.put("Fantasy", "14");
        map.put("Foreign", "10769");
        map.put("History", "36");
        map.put("Horror" , "27");
        map.put("Music" , "10402");
        map.put("Mystery" , "9648");
        map.put("Romance" , "10749");
        map.put("Science Fiction" , "878");
        map.put("TV Movie" , "10770");
        map.put("Thriller" , "53");
        map.put("War" , "10752");
        map.put("Western" , "37");
    }
    */
}

