package com.example.keon.imdbproject;

import java.util.HashMap;
import java.util.Map;
import info.movito.themoviedbapi.model.Discover;

/**
 * Created by Keon on 11/4/2015.
 */
public class TmdbObject {

    private int NumberOfPages = 1;
    protected int era;
    private Discover discover;
    private Map<String,String> map;

    TmdbObject(String MPAA_Rating, String genre, String subgenre, int era, float minRating) throws GenreException{
        this.era = era;
        setHashMap();
        setDiscoverProperties(MPAA_Rating,genre,subgenre,era,minRating);
    }

    private void setDiscoverProperties(String MPAA_Rating, String Genre, String Subgenre, int decade, float minRating) throws GenreException{

        discover = new Discover();
        String genreID = map.get(Genre);
        String subgenreID = map.get(Subgenre);

        if((genreID.equals(null) || subgenreID.equals(null))) throw new GenreException(Genre);      //The Genre or the Subgenre doesn't exist.
        else if(subgenreID.equals(genreID)) throw new GenreException();                             //When Genre and Subgenre are the same, returns an exception. This is not allowed.

        else{                                                                                       //this is where we set our filtering criteria (not calling from database here)
            discover.page(NumberOfPages)
                    .language("en")
                    .withGenres(DiscoverGenreInputCreator(genreID,subgenreID))
                    .voteAverageGte(minRating)
                    .sortBy("popularity.desc")
                    .certificationCountry("US")
                    .certificationLte(MPAA_Rating)
                    .releaseDateGte(String.valueOf(decade) + "-01-01")
                    .releaseDateLte(String.valueOf(decade + 9) + "-12-31");
        }
    }

    private String DiscoverGenreInputCreator(String genreID, String subgenreID){
        if(subgenreID != "") return genreID + " , " + subgenreID;
        else return genreID;
    }

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

    public Discover getDiscover(){
        return this.discover;
    }

    public class GenreException extends Exception{
        public GenreException(String Genre) {
            super("Failed to find the specified Genre: " + Genre);
        }
        public GenreException(){
            super("The Genre and Subgenre cannot be the same.");
        }
    }
}

