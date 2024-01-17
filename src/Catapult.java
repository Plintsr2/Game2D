import javax.swing.*;
import java.util.Map;

public class Catapult extends Unit {
    //Unit subclass describing Infantry
    public static final int MOVEMENT = 1;
    public static final int HEALTHPOINTS = 2;
    public static final int ATTACKPOINTS = 3;

    public static final Type TYPE = Type.Catapult;
    public static int range =2;

    public static final int COST = 6;
    public Catapult(String name,Type type, int healthPoints, int attackPoints,int movment,boolean iswhite,int cost, String location, Map<String, JLabel> pieces) {
        super(name,TYPE ,HEALTHPOINTS, ATTACKPOINTS,movment,iswhite,COST,location,pieces,HEALTHPOINTS);
        range =2;

    }


}
