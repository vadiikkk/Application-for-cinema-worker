package cinema.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public record Session(int ID, LocalDate date, LocalTime time, Movie movie, CinemaHall hall) {

    @Override
    public String toString() {
        return "ID: " + ID + "\n" +
                "Date: " + date + "\n" +
                "Time: " + time + "\n" +
                "Movie: " + movie.title() + "\n" +
                hall.toString();
    }
}
