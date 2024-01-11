package cinema.controllers;

import cinema.entities.Movie;
import cinema.jsonDao.JsonDao;
import cinema.userInteraction.Menu;

import java.io.IOException;
import java.util.List;

public class MovieController {

    public static final JsonDao<Movie> MOVIES =
            new JsonDao<>("movies.json", Movie.class);

    public static void addMovie() throws IOException {
        String movieTitle = getTitle();
        String moviesDirector = getDirector();
        Movie movie = new Movie(movieTitle, moviesDirector);
        MOVIES.add(movie);
        Menu.printlnGreenMessage("You successfully added new movie");
    }

    public static String getTitle() {
        Menu.printYellowMessage("Enter movie title: ");
        return Menu.scanner.nextLine();
    }

    private static String getDirector() {
        Menu.printYellowMessage("Enter director name: ");
        return Menu.scanner.nextLine();
    }

    public static void printAllMovies() throws IOException {
        List<Movie> movies = MOVIES.getAll();
        Menu.printlnBlueMessage("___________________________");
        for (Movie movie : movies) {
            Menu.printlnBlueMessage(movie.toString());
            Menu.printlnBlueMessage("___________________________");
        }
    }

    public static void removeMovie() throws IOException {
        List<Movie> movies = MOVIES.getAll();
        String title = getTitle();
        int indexOfFirstMovieWithTitle = getMovieWithTitleIndex(movies, title);
        if (indexOfFirstMovieWithTitle != -1) {
            MOVIES.remove(indexOfFirstMovieWithTitle);
            Menu.printlnGreenMessage("You successfully removed first movie with title: " + title);
        } else {
            Menu.printlnRedMessage("There is no movie with such title");
        }
    }

    public static int getMovieWithTitleIndex(List<Movie> movies, String title) {
        int indexOfMovie = -1;
        for (int i = 0; i < movies.size(); ++i) {
            if (movies.get(i).title().equals(title)) {
                indexOfMovie = i;
                break;
            }
        }
        return indexOfMovie;
    }
}
