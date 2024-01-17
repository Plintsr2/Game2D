import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameLists {
    // lists units
    public static List<Unit> units = new ArrayList<>();
    public static List<Terrain> terrain = new ArrayList<>();
    public static Map<String,Terrain> Terrainmap = new HashMap<>();
    public static int p1_money = 3;
    public static int p2_money = 3;

    public static List<String> uncharted = new ArrayList<>();

    public static String convertbolwhitetoplayer(boolean white){
        if(white){
            return "white";
        }else{
            return "black";
        }
    }

}
