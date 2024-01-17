import javax.swing.*;
import java.util.Map;

public class StormTroops extends Unit {
    //Unit subclass describing Infantry
    public static final int MOVEMENT = 2;
    public static final int HEALTHPOINTS = 2;
    public static final int ATTACKPOINTS = 3;

    public static final Type TYPE = Type.StormTroops;

    public static final int COST = 6;
    public StormTroops(String name,Type type, int healthPoints, int attackPoints,int movment,boolean iswhite,int cost, String location, Map<String, JLabel> pieces) {
        super(name,TYPE ,HEALTHPOINTS, ATTACKPOINTS,movment,iswhite,COST,location,pieces,HEALTHPOINTS);

    }


}
