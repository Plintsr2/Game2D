import java.util.HashMap;
import java.util.Map;

public class Farmland extends Terrain {
    public static final String TYPE = "Farmland";
    private static final int MOVMENTCOST = -1;
    private static final int BUILDING_COST = -1;
    private static final int IDNUM = 0;

    static Map<String, String> Terrainmap = new HashMap<String, String>();

    private static final int DEFENCEBOOST = 0;


    public Farmland(String nr, String type, int idnum, int movmentCost, int building_cost, int defenceboost, Map<String, String> Terrainmap) {
        super(nr, type, IDNUM, MOVMENTCOST, BUILDING_COST, DEFENCEBOOST, Terrainmap);

    }
}













