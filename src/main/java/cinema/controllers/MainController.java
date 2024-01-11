package cinema.controllers;

import cinema.entities.PlaceCondition;
import cinema.entities.Session;
import cinema.userInteraction.Menu;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MainController {
    public static void sellTicket() throws IOException {
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
            line = SessionController.getLineNumber();
            column = SessionController.getColumnNumber();
        } catch (NumberFormatException exception) {
            Menu.printlnRedMessage("Seat number was not a number");
            return;
        }

        try {
            if (session.hall().getSeat(line, column) == PlaceCondition.FREE) {
                session.hall().setSeat(line, column, PlaceCondition.BOUGHT);
                SessionController.SESSIONS.remove(indexOfSessionWithId);
                SessionController.SESSIONS.add(session);
                Menu.printlnGreenMessage("You successfully marked seat (" + line + " " + column + ") as bought");
            } else {
                Menu.printlnRedMessage("Place is already bought");
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            Menu.printlnRedMessage("There is no such seat");
        }
    }

    public static void refundTicket() throws IOException {
        int indexOfSessionWithId;
        try {
            indexOfSessionWithId = SessionController.getSessionIndexByID();
        } catch (NumberFormatException exception) {
            Menu.printlnRedMessage("There is no session with such ID");
            return;
        }
        Session session = SessionController.SESSIONS.getAll().get(indexOfSessionWithId);
        if (session.date().isBefore(LocalDate.now())) {
            Menu.printlnRedMessage("Session is already going or passed, you cannot refund ticket");
            return;
        } else if (session.date().isEqual(LocalDate.now()) && session.time().isBefore(LocalTime.now())) {
            Menu.printlnRedMessage("Session is already going or passed, you cannot refund ticket");
            return;
        }
        System.out.println(session);

        int line;
        int column;
        try {
            line = SessionController.getLineNumber();
            column = SessionController.getColumnNumber();
        } catch (NumberFormatException exception) {
            Menu.printlnRedMessage("Seat number was not a number");
            return;
        }

        try {
            if (session.hall().getSeat(line, column) == PlaceCondition.FREE) {
                Menu.printlnRedMessage("Place is not bought");
                return;
            }

            session.hall().setSeat(line, column, PlaceCondition.FREE);
            SessionController.SESSIONS.remove(indexOfSessionWithId);
            SessionController.SESSIONS.add(session);
            Menu.printlnGreenMessage("You successfully marked seat (" + line + " " + column + ") as free");
        } catch (ArrayIndexOutOfBoundsException exception) {
            Menu.printlnRedMessage("There is no such seat");
        }
    }

    public static void printSessionHall() throws IOException {
        int indexOfSessionWithId;
        try {
            indexOfSessionWithId = SessionController.getSessionIndexByID();
        } catch (NumberFormatException exception) {
            Menu.printlnRedMessage("There is no session with such ID");
            return;
        }

        Session session = SessionController.SESSIONS.getAll().get(indexOfSessionWithId);
        System.out.println(session);

    }
}
