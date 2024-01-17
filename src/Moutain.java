import java.util.HashMap;
import java.util.Map;

public class Moutain extends Terrain {
    public static final String TYPE = "Mountain";
    private static final int MOVMENTCOST = 2;
    private static final int BUILDING_COST = 2;
    private static final int IDNUM = 5;

    static Map<String, String> Terrainmap = new HashMap<String, String>();

    private static final int DEFENCEBOOST = 2;

    public Moutain(String nr, String type, int idnum, int movementCost, int buildingCost, int defenceboost, Map<String, String> Terrainmap) {
        super(nr, type, IDNUM, MOVMENTCOST, BUILDING_COST, DEFENCEBOOST, Terrainmap);
    }
}
