import java.util.HashMap;
import java.util.Map;

public class City extends Terrain {

    public static final String TYPE = "City";
    private static final int MOVMENTCOST = 1;
    private static final int BUILDING_COST = 1;
    private static final int IDNUM = 8;


    private static final int DEFENCEBOOST = 1;

    public City(String nr, String type, int idnum, int movementCost, int buildingCost, int defenceboost, Map<String, String> Terrainmap) {
        super(nr, type, IDNUM, MOVMENTCOST, BUILDING_COST, DEFENCEBOOST, Terrainmap);
    }

}
