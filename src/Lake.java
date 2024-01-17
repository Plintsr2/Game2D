import java.util.HashMap;
import java.util.Map;

public class Lake extends Terrain {
    public static final String TYPE = "Lake";
    private static final int MOVMENTCOST = 99;
    private static final int BUILDING_COST = 99;
    private static final int IDNUM = 4;

    static Map<String, String> Terrainmap = new HashMap<String, String>();

    private static final int DEFENCEBOOST = -99;

    public Lake(String nr, String type, int idnum, int movementCost, int buildingCost, int defenceboost, Map<String, String> Terrainmap) {
        super(nr, type, IDNUM, MOVMENTCOST, BUILDING_COST, DEFENCEBOOST, Terrainmap);
    }
}
