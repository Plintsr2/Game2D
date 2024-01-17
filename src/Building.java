import javax.swing.*;
import java.util.Map;

public class Building {

    private String type;
    private int defence;
    private int income;


    public boolean iswhite;
    public String location;
    private Map<String, JLabel> pieces;

    public Building(String type, int defence, int income, boolean iswhite, String location, Map<String, JLabel> pieces) {

        this.type = type;

        this.defence = defence;
        this.income = income;
        this.iswhite = iswhite;
        this.location = location;
        this.pieces = pieces;

    }

    public void addBuilding(String squareLabel) {
        JLabel square = pieces.get(location);
        if (square != null) {



           // square.setText("<html><div style='text-align: center;'>" + name  + "<br>" + iswhite + "</div></html>");
        }
    }
}
