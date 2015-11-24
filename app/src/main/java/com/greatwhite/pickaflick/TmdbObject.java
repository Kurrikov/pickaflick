package com.greatwhite.pickaflick;
import info.movito.themoviedbapi.model.Discover;

/**
 * Created by Keon on 11/4/2015.
 */
public class TmdbObject {

    private int NumberOfPages = 1;
    protected int era;
    private Discover discover;
    //private Map<String,String> map;

    TmdbObject(String MPAA_Rating, String genre, String era, String minRating){
        this.era = Integer.parseInt(era);
        setDiscoverProperties(MPAA_Rating,genre,this.era,Float.parseFloat(minRating));
    }

    private void setDiscoverProperties(String MPAA_Rating, String GenreList,  int decade, float minRating){
        discover = new Discover(); //The Genre doesn't exist.//this is where we set our filtering criteria (not calling from database here)
        discover.page(NumberOfPages)
                .language("en")
                .withGenres(GenreList)
                .voteAverageGte(minRating)
                .sortBy("popularity.desc")
                .certificationCountry("US")
                .certificationLte(MPAA_Rating)
                .releaseDateGte(String.valueOf(decade) + "-01-01")
                .releaseDateLte(String.valueOf(decade + 9) + "-12-31");
    }
    public Discover getDiscover(){
        return this.discover;
    }

    /*
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
