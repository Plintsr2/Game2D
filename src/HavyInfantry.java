import javax.swing.*;
import java.util.Map;

public class HavyInfantry extends Unit {
    //Unit subclass describing Infantry
    public static final int MOVEMENT = 2;
    public static final int HEALTHPOINTS = 4;
    public static final int ATTACKPOINTS = 1;

    public static final Type TYPE = Type.HavyInfantry;

    public static final int COST = 4;
    public HavyInfantry(String name,Type type, int healthPoints, int attackPoints,int movment,boolean iswhite,int cost, String location, Map<String, JLabel> pieces) {
        super(name,TYPE ,HEALTHPOINTS, ATTACKPOINTS,movment,iswhite,COST,location,pieces,HEALTHPOINTS);

    }


}
