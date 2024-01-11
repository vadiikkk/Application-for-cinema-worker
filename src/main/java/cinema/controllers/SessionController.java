package cinema.controllers;

import cinema.entities.CinemaHall;
import cinema.entities.Movie;
import cinema.entities.PlaceCondition;
import cinema.entities.Session;
import cinema.jsonDao.JsonDao;
import cinema.userInteraction.Menu;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class SessionController {
    public static final JsonDao<Session> SESSIONS =
            new JsonDao<>("sessions.json", Session.class);

    public static void addSession() throws IOException {
        String dateToParse = getDate();
        LocalDate date;
        DateTimeFormatter parser;
        try {
            parser = DateTimeFormatter.ofPattern("yyyy MM dd");
            date = LocalDate.parse(dateToParse, parser);
        } catch (DateTimeParseException exception) {
            Menu.printlnRedMessage("Date was not in correct format");
            return;
        }

        String timeToParse = getTime();
        LocalTime time;
        try {
            parser = DateTimeFormatter.ofPattern("H:mm:ss");
            time = LocalTime.parse(timeToParse, parser);
        } catch (DateTimeParseException exception) {
            Menu.printlnRedMessage("Time was not in correct format");
            return;
        }

        String title = MovieController.getTitle();
        Movie movie;
        List<Movie> movies = MovieController.MOVIES.getAll();
        int indexOfFirstMovieWithTitle = MovieController.getMovieWithTitleIndex(movies, title);
        if (indexOfFirstMovieWithTitle != -1) {
            movie = movies.get(indexOfFirstMovieWithTitle);
        } else {
            Menu.printlnRedMessage("There is no movie with such title");
            return;
        }

        Session session = new Session(getSessionId(date, time), date, time, movie, new CinemaHall());
        SESSIONS.add(session);
        Menu.printlnGreenMessage("You successfully added new session");
    }

    private static String getDate() {
        Menu.printYellowMessage("Enter date of session (in format yyyy MM dd): ");
        return Menu.scanner.nextLine();
    }

    private static String getTime() {
        Menu.printYellowMessage("Enter time of session (in format H:mm:ss): ");
        return Menu.scanner.nextLine();
    }

    private static int getSessionId(LocalDate date, LocalTime time) {
        return date.hashCode() * time.hashCode();
    }

    public static void printAllSessions() throws IOException {
        List<Session> sessions = SESSIONS.getAll();
        Menu.printlnBlueMessage("___________________________");
        for (Session session : sessions) {
            System.out.println(session);
            Menu.printlnBlueMessage("___________________________");
        }
    }

    public static void removeSession() throws IOException {
        try {
            int indexOfSessionWithId = getSessionIndexByID();
            SESSIONS.remove(indexOfSessionWithId);
            Menu.printlnGreenMessage("You successfully removed session");

        } catch (NumberFormatException exception) {
            Menu.printlnRedMessage("There is no session with such ID");
        }

    }

    public static int getSessionIndexByID() throws IOException, NumberFormatException {
        List<Session> sessions = SESSIONS.getAll();
        int ID = getID();
        int indexOfSessionWithId = getSessionWithIdIndex(sessions, ID);
        if (indexOfSessionWithId != -1) {
            return indexOfSessionWithId;
        } else {
            throw new NumberFormatException();
        }
    }

    private static int getID() {
        Menu.printYellowMessage("Enter session ID: ");
        String ID = Menu.scanner.nextLine();
        return Integer.parseInt(ID);
    }

    private static int getSessionWithIdIndex(List<Session> sessions, int ID) {
        int indexOfSession = -1;
        for (int i = 0; i < sessions.size(); ++i) {
            if (sessions.get(i).ID() == ID) {
                indexOfSession = i;
                break;
            }
        }
        return indexOfSession;
    }

    public static void markVisitor() throws IOException {
        int indexOfSessionWithId;
        try {
            indexOfSessionWithId = SessionController.getSessionIndexByID();
        } catch (NumberFormatException exception) {
            Menu.printlnRedMessage("There is no session with such ID");
            return;
        }

        Session session = SessionController.SESSIONS.getAll().get(indexOfSessionWithId);
        System.out.println(session);

        int line;
        int column;
        try {
            line = getLineNumber();
            column = getColumnNumber();
        } catch (NumberFormatException exception) {
            Menu.printlnRedMessage("Seat number was not a number");
            return;
        }

        try {
            if (session.hall().getSeat(line, column) == PlaceCondition.BOUGHT) {
                session.hall().setSeat(line, column, PlaceCondition.OCCUPIED);
                SESSIONS.remove(indexOfSessionWithId);
                SESSIONS.add(session);
                Menu.printlnGreenMessage("You successfully marked seat (" + line + " " + column + ") as occupied");
            } else if (session.hall().getSeat(line, column) == PlaceCondition.FREE) {
                Menu.printlnRedMessage("Place must be bought before being occupied");
            } else {
                Menu.printlnRedMessage("Place is already occupied");
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            Menu.printlnRedMessage("There is no such seat");
        }

    }

    public static int getLineNumber() {
        Menu.printYellowMessage("Choose occupied seat line number: ");
        String line = Menu.scanner.nextLine();
        return Integer.parseInt(line);
    }

    public static int getColumnNumber() {
        Menu.printYellowMessage("Choose occupied seat column number: ");
        String line = Menu.scanner.nextLine();
        return Integer.parseInt(line);
    }
}
