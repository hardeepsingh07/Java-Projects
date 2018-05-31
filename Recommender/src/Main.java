import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Movie> nowPlaying;
    public static ArrayList<Movie> recommendation;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        MovieDatabaseAPI movieDatabaseAPI = new MovieDatabaseAPI();

        //Get Now Playing Movie
        nowPlaying = movieDatabaseAPI.getNowPlaying();
        nowPlaying = movieDatabaseAPI.updateRatingGenre(nowPlaying);

        //Print Movie Object
        movieDatabaseAPI.print(nowPlaying);

        //Get Recommendation Movie
        while(true) {
            System.out.print("Enter Movie for Recommendation: ");
            String id = sc.nextLine();
            recommendation = movieDatabaseAPI.getRecommendation(id);
            recommendation = movieDatabaseAPI.updateRatingGenre(recommendation);
            movieDatabaseAPI.print(recommendation);
        }
    }
}