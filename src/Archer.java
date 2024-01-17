import javax.swing.*;
import java.util.Map;

public class Archer extends Unit {
    //Unit subclass describing Archer
    public static final int MOVEMENT = 3;
    public static final int HEALTHPOINTS = 2;
    public static final int ATTACKPOINTS = 2;

    public static final Type TYPE = Type.Archer;

    public static int range =2;

    public static final int COST = 3;
    public Archer(String name,Type type, int healthPoints, int attackPoints,int movment,boolean iswhite,int cost, String location, Map<String, JLabel> pieces) {
        super(name,TYPE ,HEALTHPOINTS, ATTACKPOINTS,movment,iswhite,COST,location,pieces,HEALTHPOINTS);
        range = 2;

    }


}
