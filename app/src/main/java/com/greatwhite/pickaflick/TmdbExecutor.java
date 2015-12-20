package com.greatwhite.pickaflick;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbDiscover;
import info.movito.themoviedbapi.model.Discover;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by Keon on 11/4/2015.
 * This class will handle network calls and data retrieval
 */
public class TmdbExecutor {
    private TmdbMoviesObject tmdbMoviesobject;
    private String API_ID = "";

    TmdbExecutor(TmdbMoviesObject tmdbMoviesobject, String API_ID){
        this.tmdbMoviesobject = tmdbMoviesobject;
        this.API_ID = API_ID;
    }

    /**
     * This method will test the network connection to the movie database. If a connection can be established,
     * it will pull a list of movies from the database that meet the filter parameters. The list is then filtered
     * further and sorted using the listCorrector.
     * @return void
     */
    public void execute() {
        Discover discover = tmdbMoviesobject.getDiscover();
        TmdbDiscover tmdbDiscover = null;
            // Check the network connection by connecting to the host's base address: http://api.themoviedb.org.
            // This check will prevent the program from locking up later if there is very poor/no connection
            if(hostIsReachable()) {
                tmdbDiscover = new TmdbApi(API_ID).getDiscover();
                MovieResultsPage movieResultsPage = tmdbDiscover.getDiscover(discover);
                List<MovieDb> filteredMovies = tmdbMoviesobject.listCorrector(movieResultsPage.getResults());
                tmdbMoviesobject.setMoviesList(filteredMovies);
            }
    }

    /**
     * Check if a connection can be established with The Movie Database. Exception throwing is
     * not done in this class, since execute() does not support it.
     * @return true if a connection to The Movie Database can be established; false otherwise
     */
    private boolean hostIsReachable(){
        try {
            InetAddress hostIP = InetAddress.getByAddress(new byte[]{(byte) 54, (byte) 174, (byte) 111, (byte) 151});
            SocketAddress sockaddress = new InetSocketAddress(hostIP, 80); // Create an unbound socket
            Socket socket = new Socket();
            int timeoutMs = 5000;   // Maximum timeout will be 5 seconds
            socket.connect(sockaddress, timeoutMs);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public static class NoInternetConnectionException extends Exception{
        public NoInternetConnectionException(){
            super("Could not connect to the database. Check your Internet connection.");
        }
    }
}
