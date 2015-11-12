package com.example.keon.imdbproject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;

import info.movito.themoviedbapi.model.Discover;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by Keon on 11/4/2015.
 */
public class TmdbObject {


    public class GenreException extends Exception{
        public GenreException(String Genre){
            super("This Genre doesn't exist: " + Genre);
        }
    }

    private int NumberOfPages = 1;
    private Discover discover;
    private Map<String,String> map;
    private MovieResultsPage movieResultsPage;
    protected List<MovieDb> moviesList;
    protected int era;

    TmdbObject(String MPAA_Rating, String genre, int era, float minRating) throws GenreException{
        this.era = era;
        setHashMap();
        setDiscoverProperties(MPAA_Rating,genre,era,minRating);
    }
    //certifications: E,G,PG,M,MA15+,R18+,X18+,RC,14A,18A,R,A,NR,PG-13,NC-17. see http://docs.themoviedb.apiary.io/#reference/certifications/certificationmovielist/get
    private void setDiscoverProperties(String MPAA_Rating, String Genre, int decade, float minRating) throws GenreException{
        discover = new Discover();
        String genre = map.get(Genre);
        if(genre == null) throw new GenreException(Genre);
        else{
            discover.page(NumberOfPages)
                    .language("en")
                    .withGenres(genre)
                    .voteAverageGte(minRating)
                    .sortBy("popularity.desc")
                    .certificationCountry("US")
                    .certificationLte(MPAA_Rating)
                    .releaseDateGte(String.valueOf(decade) + "-01-01")
                    .releaseDateLte(String.valueOf(decade + 9) + "-12-31");
        }
    }

    private void setHashMap(){
        String temp;
        map = new HashMap<String,String>();
        temp = map.put("Action", "28");
        temp = map.put("Adventure", "12");
        temp = map.put("Animation", "16");
        temp = map.put("Comedy", "35");
        temp = map.put("Crime", "80");
        temp = map.put("Documentary", "99");
        temp = map.put("Drama", "18");
        temp = map.put("Family", "10751");
        temp = map.put("Fantasy", "14");
        temp = map.put("Foreign", "10769");
        temp = map.put("History", "36");
        temp = map.put("Horror" , "27");
        temp = map.put("Music" , "10402");
        temp = map.put("Mystery" , "9648");
        temp = map.put("Romance" , "10749");
        temp = map.put("Science Fiction" , "878");
        temp = map.put("TV Movie" , "10770");
        temp = map.put("Thriller" , "53");
        temp = map.put("War" , "10752");
        temp = map.put("Western" , "37");
    }

    public Discover getDiscover(){
        return this.discover;
    }

    /*public List<MovieDb> getMoviesList(){
        return moviesList;
    }

    public void setMoviesList(List<MovieDb> moviesList){
        this.moviesList = moviesList;
    }*/

}

