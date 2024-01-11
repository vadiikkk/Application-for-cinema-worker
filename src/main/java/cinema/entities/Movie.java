package cinema.entities;

public record Movie(String title, String movieDirector) {
    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Movie director: " + movieDirector;
    }
}
