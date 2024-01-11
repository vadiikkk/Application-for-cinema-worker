package cinema.userInteraction;

import cinema.controllers.SessionController;

import java.io.IOException;

public class SessionMenu extends Menu {

    public SessionMenu() {
        options = new String[4];
        options[0] = "1 - Add session";
        options[1] = "2 - Remove session";
        options[2] = "3 - Show sessions";
        options[3] = "4 - Exit this menu";
    }

    @Override
    protected void processCurrentChoice(String choice) {
        switch (choice) {
            case ("1"):
                try {
                    SessionController.addSession();
                } catch (IOException exception) {
                    Menu.printlnRedMessage("Fix sessions.json file, please :(");
                }
                break;
            case ("2"):
                try {
                    SessionController.removeSession();
                } catch (IOException exception) {
                    Menu.printlnRedMessage("Fix sessions.json file, please :(");
                }
                break;
            case("3"):
                try {
                    SessionController.printAllSessions();
                } catch (IOException exception) {
                    Menu.printlnRedMessage("Fix sessions.json file, please :(");
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
