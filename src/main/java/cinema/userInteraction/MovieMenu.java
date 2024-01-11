package cinema.userInteraction;

import cinema.controllers.MovieController;

import java.io.IOException;

public class MovieMenu extends Menu {

    public MovieMenu() {
        options = new String[4];
        options[0] = "1 - Add movie";
        options[1] = "2 - Remove movie";
        options[2] = "3 - Show movies";
        options[3] = "4 - Exit this menu";
    }

    @Override
    protected void processCurrentChoice(String choice) {
        switch (choice) {
            case ("1"):
                try {
                    MovieController.addMovie();
                } catch (IOException exception) {
                    Menu.printlnRedMessage("Fix movies.json file, please :(");
                }
                break;
            case ("2"):
                try {
                    MovieController.removeMovie();
                } catch (IOException exception) {
                    Menu.printlnRedMessage("Fix movies.json file, please :(");
                }
                break;
            case("3"):
                try {
                    MovieController.printAllMovies();
                } catch (IOException exception) {
                    Menu.printlnRedMessage("Fix movies.json file, please :(");
                }
                break;
            case ("4"):
                shouldBeTerminated = true;
                break;
            default:
                printlnRedMessage("\nPlease, choose option from suggested");
        }
    }
}
