package cinema.controllers;

import cinema.entities.Account;
import cinema.jsonDao.JsonDao;
import cinema.userInteraction.Menu;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AuthorizationController {
    private static final JsonDao<Account> USER_ACCOUNTS =
            new JsonDao<>("userAccounts.json", Account.class);

    public static boolean isValidatedUser = false;

    public static void createNewAccount() throws IOException {
        String login = getUniqueLogin();
        String password = getPassword();
        int encodedPassword = getEncoded(password);
        Account account = new Account(login, encodedPassword);
        USER_ACCOUNTS.add(account);
        Menu.printlnGreenMessage("You successfully created new account");
        isValidatedUser = true;
    }

    private static String getUniqueLogin() throws IOException {
        String login;
        boolean isLoginAlreadyExists;
        do {
            login = getLogin();
            List<Account> accounts = USER_ACCOUNTS.getAll();
            isLoginAlreadyExists = isLoginTaken(login, accounts);
            if (isLoginAlreadyExists) {
                Menu.printlnRedMessage("Such login is already taken. Please, choose another one");
            }
        } while (isLoginAlreadyExists);
        return login;
    }

    private static String getLogin() {
        Menu.printYellowMessage("Enter your login: ");
        return Menu.scanner.nextLine();
    }

    private static boolean isLoginTaken(String login, List<Account> accounts) {
        boolean isTaken = false;
        if (accounts != null) {
            for (Account account : accounts) {
                if (Objects.equals(account.login(), login)) {
                    isTaken = true;
                    break;
                }
            }
        }
        return isTaken;
    }

    private static String getPassword() {
        Menu.printYellowMessage("Enter your password: ");
        return Menu.scanner.nextLine();
    }

    private static int getEncoded(String word) {
        return word.hashCode();
    }

    public static void logInExistingAccount() throws IOException {
        String login = getLogin();
        Optional<Account> account = getAccountWithLogin(login);

        if (account.isEmpty()) {
            Menu.printlnRedMessage("There is no such user in system");
            return;
        }

        Account user = account.get();
        String password = getPassword();
        validatePasswordForAccount(password, user);
    }

    private static Optional<Account> getAccountWithLogin(String login) throws IOException {
        List<Account> accounts = USER_ACCOUNTS.getAll();
        return accounts.stream()
                .filter(account -> login.equals(account.login()))
                .findFirst();
    }

    private static void validatePasswordForAccount(String password, Account account) {
        if (password.hashCode() == account.password()) {
            Menu.printlnGreenMessage("You successfully logged in");
            isValidatedUser = true;
        } else {
            Menu.printlnRedMessage("Wrong password");
        }
    }
}
