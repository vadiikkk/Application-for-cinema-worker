package cinema.userInteraction;

import cinema.controllers.MainController;
import cinema.controllers.SessionController;

import java.io.IOException;

public class MainMenu extends Menu {

    public MainMenu() {
        options = new String[7];
        options[0] = "1 - Sell ticket";
        options[1] = "2 - Refund ticket";
        options[2] = "3 - Print cinema hall";
        options[3] = "4 - Change movies' list";
        options[4] = "5 - Change sessions' list";
        options[5] = "6 - Mark visitor";
        options[6] = "7 - Exit the application";
    }

    @Override
    protected void processCurrentChoice(String choice) {
        switch (choice) {
            case ("1"):
                try {
                    MainController.sellTicket();
                } catch (IOException exception) {
                    Menu.printlnRedMessage("Fix sessions.json file, please :(");
                }
                break;
            case ("2"):
                try {
                    MainController.refundTicket();
                } catch (IOException exception) {
                    Menu.printlnRedMessage("Fix sessions.json file, please :(");
                }
                break;
            case ("3"):
                try {
                    MainController.printSessionHall();
                } catch (IOException exception) {
                    Menu.printlnRedMessage("Fix sessions.json file, please :(");
                }
                break;
            case ("4"):
                MovieMenu movieMenu = new MovieMenu();
                movieMenu.executeMenu();
                break;
            case ("5"):
                SessionMenu sessionMenu = new SessionMenu();
                sessionMenu.executeMenu();
                break;
            case ("6"):
                try {
                    SessionController.markVisitor();
                } catch (IOException exception) {
                    Menu.printlnRedMessage("Fix sessions.json file, please :(");
                }
                break;
            case ("7"):
                shouldBeTerminated = true;
                printlnYellowMessage("\nThank you, bye!");
                break;
            default:
                printlnRedMessage("\nPlease, choose option from suggested");
        }
    }
}
