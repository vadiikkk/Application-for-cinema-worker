package cinema.entities;

import cinema.userInteraction.Menu;

import java.util.Arrays;

public class CinemaHall {
    private final PlaceCondition[][] hall;
    private final int LINES_NUMBER = 9;
    private final int COLUMNS_NUMBER = 8;

    public PlaceCondition[][] getHall() {
        return hall;
    }

    public CinemaHall() {
        hall = new PlaceCondition[LINES_NUMBER][COLUMNS_NUMBER];
        for (PlaceCondition[] condition : hall) {
            Arrays.fill(condition, PlaceCondition.FREE);
        }
    }

    public PlaceCondition getSeat(int line, int column) {
        return hall[column - 1][line - 1];
    }

    public void setSeat(int line, int column, PlaceCondition condition) {
        hall[column - 1][line - 1] = condition;
    }

    @Override
    public String toString() {
        StringBuilder hallInString = new StringBuilder();

        hallInString.append("\n");
        for (int i = 0; i < COLUMNS_NUMBER; ++i) {
            hallInString.append(" ").append(i + 1).append(" ");

            for (PlaceCondition[] placeConditions : hall) {

                if (placeConditions[i] == PlaceCondition.FREE) {
                    hallInString.append(Menu.GREEN_COLOUR).append(" F ").append(Menu.DEFAULT_COLOUR);
                } else if (placeConditions[i] == PlaceCondition.BOUGHT) {
                    hallInString.append(Menu.YELLOW_COLOUR).append(" B ").append(Menu.DEFAULT_COLOUR);
                } else {
                    hallInString.append(Menu.RED_COLOUR).append(" O ").append(Menu.DEFAULT_COLOUR);
                }

            }

            hallInString.append(" ").append(i + 1).append(" ");
            hallInString.append("\n");
        }

        hallInString.append("   ");
        for (int i = 0; i < LINES_NUMBER; ++i) {
            hallInString.append(" ").append(i + 1).append(" ");
        }
        hallInString.append("\n");
        return hallInString.toString();
    }
}
