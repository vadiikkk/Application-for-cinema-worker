package cinema.userInteraction;

import cinema.controllers.AuthorizationController;

public class UserInteraction {
    public static void executeApplication() {
        AuthorizationMenu authorization = new AuthorizationMenu();
        authorization.executeMenu();

        if (!AuthorizationController.isValidatedUser) {
            return;
        }

        MainMenu menu = new MainMenu();
        menu.executeMenu();
    }
}
