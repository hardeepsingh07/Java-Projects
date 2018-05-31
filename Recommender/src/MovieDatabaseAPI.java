import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.oracle.tools.packager.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hardeepsingh on 4/26/17.
 */
public class MovieDatabaseAPI {

    private static final String API_KEY = "5812e4b63553d1273a420416fddeed72";
    private static String POPULAR_URL;
    private static String GENRE_URL;
    private static String OMDB_RATING_URL;
    private static String RECOMMENDATION_URL;

    //Genre List
    private HashMap<String, String> genres;

    public MovieDatabaseAPI() throws Exception {
        POPULAR_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY + "&language=en-US&page=1&region=US";
        GENRE_URL = "https://api.themoviedb.org/3/genre/movie/list?api_key=" + API_KEY + "&language=en-US";
        OMDB_RATING_URL = "http://www.omdbapi.com/?t=";
        RECOMMENDATION_URL = "https://api.themoviedb.org/3/movie/*movie_id*/recommendations?api_key=" + API_KEY + "&language=en-US&page=1";

        //Get Genres
        this.genres = getGenres();
    }

    //  Make Request
    public String sendRequest(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public HashMap<String, String> getGenres() throws Exception {
        System.out.println("\nGetting Movie Genres...");
        String jsonGenre = sendRequest(GENRE_URL);
        return parseGenreJSON(jsonGenre);
    }

    public ArrayList<Movie> getNowPlaying() throws Exception {
        System.out.println("\nGetting Popular Movies...");
        String jsonNowPlaying = sendRequest(POPULAR_URL);
        return parseMovieJSON(jsonNowPlaying);
    }

    public HashMap<String, String> getRatings(String movieTitle) throws Exception {
        Log.info("\nGetting Movie Ratings OMDB...");
        String jsonRating = sendRequest(OMDB_RATING_URL + movieTitle.replaceAll(" ", "%20"));
        return parseRatingJSON(jsonRating);
    }

    public ArrayList<Movie> getRecommendation(String movieID) throws Exception {
        System.out.println("\nGetting Movie Recommendations...");
        String jsonRecommendation = sendRequest(RECOMMENDATION_URL.replace("*movie_id*", movieID));
        return parseMovieJSON(jsonRecommendation);
    }

    public ArrayList<Movie> parseMovieJSON(String json) throws Exception {
        if (json.contains("results")) {
            JsonElement jelement = new JsonParser().parse(json);
            JsonObject jobject = jelement.getAsJsonObject();
            JsonArray jarray = jobject.getAsJsonArray("results");
            Type type = new TypeToken<List<Movie>>() {
            }.getType();
            ArrayList<Movie> movies = new Gson().fromJson(jarray.toString(), type);
            return movies;
        } else {
            System.out.println("Result not found!");
        }
        return null;
    }

    public HashMap<String, String> parseRatingJSON(String json) throws Exception {
        if (json.contains("Ratings")) {
            JsonElement jelement = new JsonParser().parse(json);
            JsonObject jobject = jelement.getAsJsonObject();
            JsonArray jarray = jobject.getAsJsonArray("Ratings");

            HashMap<String, String> ratings = new HashMap<>();
            for (int i = 0; i < jarray.size(); i++) {
                String id = jarray.get(i).getAsJsonObject().get("Source").toString();
                String name = jarray.get(i).getAsJsonObject().get("Value").toString();
                ratings.put(id, name);
            }
            return ratings;
        } else {
            System.out.println("Ratings not found!");
        }
        return null;
    }

    public HashMap<String, String> parseGenreJSON(String json) throws Exception {
        if (json.contains("genres")) {
            JsonElement jelement = new JsonParser().parse(json);
            JsonObject jobject = jelement.getAsJsonObject();
            JsonArray jarray = jobject.getAsJsonArray("genres");

            HashMap<String, String> genres = new HashMap<>();
            for (int i = 0; i < jarray.size(); i++) {
                String id = jarray.get(i).getAsJsonObject().get("id").toString();
                String name = jarray.get(i).getAsJsonObject().get("name").toString();
                genres.put(id, name);
            }
            return genres;
        } else {
            System.out.println("Genres not found!");
        }
        return null;
    }

    public ArrayList<Movie> updateRatingGenre(ArrayList<Movie> movies) throws Exception {
        for (Movie movie : movies) {
            movie.setGenreHash(genres);
            movie.setRatings(getRatings(movie.getTitle()));
        }
        return movies;
    }

    public void print(List<?> iList) {
        for (Object o : iList) {
            System.out.println(o.toString());
        }
    }
}
