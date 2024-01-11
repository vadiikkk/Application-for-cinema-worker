package cinema.userInteraction;

import cinema.controllers.AuthorizationController;

import java.io.IOException;

public class AuthorizationMenu extends Menu {

    AuthorizationMenu() {
        options = new String[3];
        options[0] = "1 - Log in to your account";
        options[1] = "2 - Create new account";
        options[2] = "3 - Exit the application";
    }

    @Override
    protected void processCurrentChoice(String choice) {
        switch (choice) {
            case ("1"):
                try {
                    AuthorizationController.logInExistingAccount();
                    shouldBeTerminated = AuthorizationController.isValidatedUser;
                } catch (IOException exception) {
                    Menu.printlnRedMessage("Fix userAccounts.json file, please :(");
                }
                break;
            case ("2"):
                try {
                    AuthorizationController.createNewAccount();
                    shouldBeTerminated = AuthorizationController.isValidatedUser;
                } catch (IOException exception) {
                    Menu.printlnRedMessage("Fix userAccounts.json file, please :(");
                }
                break;
            case ("3"):
                shouldBeTerminated = true;
                printlnYellowMessage("\nThank you, bye!");
                break;
            default:
                printlnRedMessage("\nPlease, choose option from suggested");
        }
    }
}
