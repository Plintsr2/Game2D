import java.util.HashMap;
import java.util.Map;

public class Hill extends Terrain {
    public static final String TYPE = "Hill";
    private static final int MOVMENTCOST = 1;
    private static final int BUILDING_COST = 1;
    private static final int IDNUM = 6;

    static Map<String, String> Terrainmap = new HashMap<String, String>();

    private static final int DEFENCEBOOST = 1;

    public Hill(String nr, String type, int idnum, int movementCost, int buildingCost, int defenceboost, Map<String, String> Terrainmap) {
        super(nr, type, IDNUM, MOVMENTCOST, BUILDING_COST, DEFENCEBOOST, Terrainmap);
    }
}
