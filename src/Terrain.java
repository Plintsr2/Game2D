import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Terrain {
    String type;
    String nr;
    private  int movementCost;
    private int buildingCost;
    private int defenceboost;



    //static Map<String,String> Terrainmap;
    private int idnum;





    public Terrain(String nr, String type, int idnum, int movementCost, int buildingCost, int defenceboost , Map<String, String> Terrainmap) {
        this.nr = nr;
        this.type = type;
        this.idnum= idnum;
        this.movementCost = movementCost;
        this.buildingCost = buildingCost;
        this.defenceboost = defenceboost;

       // this.Terrainmap = Terrainmap;
    }

    public  int getmovmentcost() {
        return movementCost;
    }
    public int getdefeceboost() {
        return defenceboost;
    }
    public int getbuildingCost() {
        return buildingCost;
    }

    public String getType() {
        return type;
    }
    public static int checkTerrainmovcost(String locationtocheck){
        for(Terrain terrain: GameLists.terrain){
            if (GameLists.Terrainmap.containsKey(locationtocheck)) {
                return (GameLists.Terrainmap.get(locationtocheck).movementCost);
            }
        }
        return 0;
    }

    public static String checktype(String locationtocheck){
//        System.out.println("daziala");
        for(Terrain terrain: GameLists.terrain) {
//            System.out.println(terrain);
//            System.out.println(Farmland.TYPE);
//            System.out.println(Forest.TYPE);
//            System.out.println();
            if (GameLists.Terrainmap.containsKey(locationtocheck)) {
                return (GameLists.Terrainmap.get(locationtocheck).type);
            }


        }
        return "nic";
    }
    public static int checkdefenceboost(String locationtocheck){
        for(Terrain terrain: GameLists.terrain){
            if (GameLists.Terrainmap.containsKey(locationtocheck)) {
                return (GameLists.Terrainmap.get(locationtocheck).defenceboost);
            }
        }

        return 0;
    }
    public static int checkbuildingcost(String locationtocheck){
        for(Terrain terrain: GameLists.terrain){
            if (GameLists.Terrainmap.containsKey(locationtocheck)) {
                return (GameLists.Terrainmap.get(locationtocheck).buildingCost);
            }
        }

        return 0;
    }
    public void addTerrain(String squareLabel) {

    }

    public static String farmland;
    //static Terrain Farmland = new Terrain(type,0,0,-1,0, location, pieces);


//    public static void addTerrain(String squareLabel) {
//        JLabel square = pieces.get(squareLabel);
//        System.out.println(" dodajemy teren");
//        if (square != null) {
//
//            square.setText("<html><div style='text-align: center;'>" + squareLabel + "<br> " + " " + "<br> " + " " + "<br>" + Terrain.type + "</div></html>");
//        }
//    }


}


