import java.util.HashMap;
import java.util.Map;

public class Fort extends Terrain {

    public static final String TYPE = "Fort";
    private static final int MOVMENTCOST = 1;
    public static final int BUILDING_COST = 3;
    private static final int IDNUM = 7;


    private static final int DEFENCEBOOST = 1;

    public static Map<String, String> Terrainmap;


    public Fort(String nr, String type, int idnum, int movementCost, int buildingCost, int defenceboost, Map<String, String> Terrainmap) {
        super(nr, type, IDNUM, MOVMENTCOST, BUILDING_COST, DEFENCEBOOST, Terrainmap);
    }

}
