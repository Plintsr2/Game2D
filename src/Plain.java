import java.util.HashMap;
import java.util.Map;

public class Plain extends Terrain {
    public static final String TYPE = "Plain";
    private static final int MOVMENTCOST = 0;
    private static final int BUILDING_COST = 0;
    private static final int IDNUM = 1;

    static Map<String, String> Terrainmap = new HashMap<String, String>();

    private static final int DEFENCEBOOST = 0;

    public Plain(String nr, String type, int idnum, int movementCost, int buildingCost, int defenceboost, Map<String, String> Terrainmap) {
        super(nr, type, IDNUM, MOVMENTCOST, BUILDING_COST, DEFENCEBOOST, Terrainmap);
    }
}
