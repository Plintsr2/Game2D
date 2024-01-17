import javax.swing.*;
import java.util.Map;
public class Monster extends Unit {
    //Unit subclass not finished, may be deleted
    public static final int MOVEMENT = 3000;
    public static final int HEALTHPOINTS = 20;
    public static final int ATTACKPOINTS = 3;
    public static final Type TYPE = Type.Monster;
    public static int Count = 0;


    public Monster(String name,Type type, int healthPoints, int attackPoints,int movment,boolean iswhite,int cost, String location, Map<String, JLabel> pieces) {
        super(name,TYPE ,HEALTHPOINTS, ATTACKPOINTS,MOVEMENT,iswhite,cost,location,pieces,HEALTHPOINTS);

    }

    public static int addMonster(String locationToPlace){
        if(Count>2) return 0;
        Monster.Count++;
        Monster unit = new Monster("Monster "+Monster.Count, Type.Monster,Monster.HEALTHPOINTS, Monster.ATTACKPOINTS, 0, true,0,locationToPlace, GameFrame.gridPanel.getPieces());
        unit.addUnit(locationToPlace);
        GameLists.units.add(unit);
        return 1;


    }

    public static void monstersMove(){
        for (Unit unit : GameLists.units) {

            if (unit.getType() == Type.Monster) {
                    randomMove(unit);
                    randomMove(unit);
            }
        }
    }





}
