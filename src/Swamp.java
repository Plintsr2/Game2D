import java.util.HashMap;
import java.util.Map;

public class Swamp extends Terrain {
    public static final String TYPE = "Swamp";
    private static final int MOVMENTCOST = 2;
    private static final int BUILDING_COST = 2;
    private static final int IDNUM = 3;

    static Map<String, String> Terrainmap = new HashMap<String, String>();

    private static final int DEFENCEBOOST = -1;

    public Swamp(String nr, String type, int idnum, int movementCost, int buildingCost, int defenceboost, Map<String, String> Terrainmap) {
        super(nr, type, IDNUM, MOVMENTCOST, BUILDING_COST, DEFENCEBOOST, Terrainmap);
    }
}
