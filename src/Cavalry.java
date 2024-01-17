
import javax.swing.*;
import java.util.Map;

public class Cavalry extends Unit {
    //Unit subclass describing cavalry
    public static final int MOVEMENT = 4;
    public static final int HEALTHPOINTS = 2;
    public static final int ATTACKPOINTS = 2;

    public static final Type TYPE = Type.Cavalry;
    public static final int COST = 4;


    public Cavalry(String name,Type type, int healthPoints, int attackPoints,int movment,boolean iswhite,int cost, String location, Map<String, JLabel> pieces) {
        super(name,TYPE, HEALTHPOINTS, ATTACKPOINTS,movment,iswhite,COST,location,pieces,HEALTHPOINTS);

    }





}
