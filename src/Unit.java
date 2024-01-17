import javax.swing.*;
import java.util.List;
import java.util.Map;


public class Unit {
    public String name;
    //private String type;
    private Type type;
    private int healthPoints;
    private int attackPoints;
    private int movment;

    private int maxHealthPoints;

    private int cost;

    public enum Type {
        Infantry,
        Cavalry,
        Monster,
        HavyInfantry,
        StormTroops,
        Catapult,
        Archer
    }

    public boolean iswhite;
    public String location;
    private static Map<String, JLabel> pieces;

    public Unit(String name, Type type, int healthPoints, int attackPoints, int movment, boolean iswhite, int cost, String location, Map<String, JLabel> pieces, int maxHealthPoints) {
        this.name = name;
        this.type = type;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.movment = movment;
        this.iswhite = iswhite;
        this.cost = cost;
        this.location = location;
        this.pieces = pieces;
        this.maxHealthPoints = maxHealthPoints;

    }

    public Type getType() {
        return type;
    }

    public int citycaptured() {
        int count = 0;

        return count;
    }


    public void addUnit(String squareLabel) {
        JLabel square = pieces.get(location);
        if (square != null) {


            square.setText("<html><div style='text-align: center;'>" + name + "<br>" + healthPoints + "<br>" + iswhite + "<br>" + Terrain.checktype(squareLabel) + "</div></html>");
            //Terrain.checktype(squareLabel)
        }
    }

    public static JLabel addTerrain(String squareLabel) {
        JLabel square = pieces.get(squareLabel);
        if (square != null) {
            return square;

            //Terrain.checktype(squareLabel)
        }
        return square;
    }

    public static void movmentReset(boolean iswhite, Unit unit) {
//        for (Unit unit : GameLists.units) {
        if (unit.iswhite == iswhite) {
            if (unit.type.equals(Type.Infantry)) {
                unit.movment = Infantry.MOVEMENT;
                System.out.println(unit.name + "  movment reseted");
            } else if (unit.type.equals(Type.Cavalry)) {
                unit.movment = Cavalry.MOVEMENT;
                System.out.println(unit.movment);
                System.out.println(unit.name + "  movment reseted");
            } else if (unit.type.equals(Type.HavyInfantry)) {
                unit.movment = HavyInfantry.MOVEMENT;
                System.out.println(unit.name + "  movment reseted");
            } else if (unit.type.equals(Type.StormTroops)) {
                unit.movment = StormTroops.MOVEMENT;
                System.out.println(unit.name + "  movment reseted");
            } else if (unit.type.equals(Type.Catapult)) {
                unit.movment = Catapult.MOVEMENT;
                System.out.println(unit.name + "  movment reseted");
            } else if (unit.type.equals(Type.Archer)) {
                unit.movment = Archer.MOVEMENT;
                System.out.println(unit.name + "  movment reseted");
            }
        } else {
            unit.movment = 0;
        }

//        }


    }

    public boolean iswhite() {
        return iswhite;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public String getlocation() {
        return location;
    }

    // Create a method to reduce health (e.g., due to damage)
    public void reduceHealth(int damage) {
        healthPoints -= damage;
    }

    public static boolean isAnotherPieceInLocation(String locationToCheck) {
        for (Unit unit : GameLists.units) {
            if (unit.location.equals(locationToCheck)) {

                return true; // There's another piece in the location
            }
        }
        return false; // No other piece found in the location
    }

    public static boolean isAnotherPieceInLocationwhite(String locationToCheck) {
        for (Unit unit : GameLists.units) {
            if (unit.location.equals(locationToCheck)) {
                if (unit.iswhite) {
                    return true; // There's another piece in the location

                }
            }
        }
        return false; // No other piece found in the location
    }

    public static Unit whatPieceisInLocation(String locationToCheck) {
        for (Unit unit : GameLists.units) {
            if (unit.location.equals(locationToCheck)) {

                return unit; // There's another piece in the location
            }
        }
        return null; // No other piece found in the location
    }

    /*public void attack(newLocation){
        for (Unit unit : GameLists.units) {
            if (unit.location.equals(newLocation)) {

                return true; // There's another piece in the location
            }
        }
    }*/
    public void attackroll() {
        int shot = (int) (Math.random() * 6) + 1;
        System.out.println("Shot on attack roll: " + shot);
        switch (shot) {
            case 1:
                //atack 0 dull dmg back pushback
                damage = 0;
                break;
            case 2, 3:
                damage = damage / 2;
                damageback = damageback / 2;
                break;
            case 4, 5:
                damageback = 0;
                break;
            case 6:
                damage = 2 * damage;
                damageback = 0;
                break;
        }
        JDialog popup = new JDialog();
        popup.setTitle("attack roll (1-6)");
        popup.setSize(200, 200);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Attack roll: " + shot);
        panel.add(label);
        popup.add(panel);
        popup.setVisible(true);
        popup.setLocationRelativeTo(null);
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    public int damage;
    public int damageback;

    public void move(String newLocation) {
        JLabel oldSquare = pieces.get(location);
        JLabel newSquare = pieces.get(newLocation);
        int remainingMovement = movment;

        if (isAnotherPieceInLocation(newLocation)) {
            for (Unit unit : GameLists.units) {
                if (unit.location.equals(location) && unit.movment > 0) {
                    damage = unit.attackPoints;
                    unit.movment = 0;
                    int oldRow = Character.getNumericValue(location.charAt(1));
                    int oldCol = location.charAt(0) - 'A';
                    int newRow = Character.getNumericValue(newLocation.charAt(1));
                    int newCol = newLocation.charAt(0) - 'A';

                    int rowDiff = Math.abs(newRow - oldRow);
                    int colDiff = Math.abs(newCol - oldCol);

                    for (Unit unit2 : GameLists.units) {

                        damageback = unit2.attackPoints;
                        if (unit2.location.equals(newLocation) && ((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 1))) {
                            attackroll();
                            if(unit2.type == Type.Monster){damageback=1;damage+=1;}
                            int temp1 = unit2.healthPoints;
                            unit2.healthPoints = (unit2.healthPoints + Terrain.checkdefenceboost(newLocation)) - (damage + how_meny_frendly_nearby(unit.iswhite, newLocation));
                            System.out.println("hp of attacked unit " + unit2.healthPoints);
                            System.out.println("defence boost of terrain " + Terrain.checkdefenceboost(newLocation));
                            System.out.println("damage " + damage);
                            System.out.println("how meny + for nearby " + how_meny_frendly_nearby(unit.iswhite, newLocation));
                            if (unit2.healthPoints > temp1) unit2.healthPoints = temp1;
                            int temp2 = unit.healthPoints;
                            unit.healthPoints = unit.healthPoints - damageback + Terrain.checkdefenceboost(location);
                            if (unit.healthPoints > temp2) unit.healthPoints = temp2;


                            if (unit2.healthPoints <= 0) {
                                if (unit2.iswhite) {
                                    GameLists.p2_money += 1;
                                } else {
                                    GameLists.p1_money += 1;
                                }
                                if (unit2.type == Type.Monster) {
                                    if (unit.iswhite) {
                                        GameLists.p1_money += 14;
                                    } else {
                                        GameLists.p2_money += 14;
                                    }
                                }
                                GameLists.units.remove(unit2);
                                newSquare.setText("<html><div style='text-align: center;'>" + newLocation + "<br>" + " " + "<br>" + " " + "<br>" + Terrain.checktype(newLocation) + "</div></html>");

                            } else {
                                newSquare.setText(("<html><div style='text-align: center;'>" + unit2.name + "<br>" + unit2.healthPoints + "<br>" + unit2.iswhite + "<br>" + Terrain.checktype(newLocation) + "</div></html>"));
                            }
                            if (unit.healthPoints <= 0) {
                                GameLists.units.remove(unit);
                                oldSquare.setText("<html><div style='text-align: center;'>" + location + "<br>" + " " + "<br>" + " " + "<br>" + Terrain.checktype(location) + "</div></html>");
                            } else {
                                oldSquare.setText(("<html><div style='text-align: center;'>" + unit.name + "<br>" + unit.healthPoints + "<br>" + unit.iswhite + "<br>" + Terrain.checktype(location) + "</div></html>"));
                            }

                            // range units
                        } else if ( (unit.type==Type.Catapult || unit.type== Type.Archer) && unit2.location.equals(newLocation)&& ((rowDiff == 2 && colDiff == 0) || (rowDiff == 0 && colDiff == 2) || (rowDiff == 2 && colDiff == 2))) {
                            //attackroll();
                            if(unit2.type == Type.Monster){damageback=0;damage+=1;}
                            int temp1 = unit2.healthPoints;
                            unit2.healthPoints = (unit2.healthPoints + Terrain.checkdefenceboost(newLocation)) - (damage + how_meny_frendly_nearby(unit.iswhite, newLocation)+1);
                            System.out.println("hp of attacked unit " + unit2.healthPoints);
                            System.out.println("defence boost of terrain " + Terrain.checkdefenceboost(newLocation));
                            System.out.println("damage " + damage);
                            System.out.println("how meny + for nearby " + how_meny_frendly_nearby(unit.iswhite, newLocation));
                            if (unit2.healthPoints > temp1) unit2.healthPoints = temp1;
//                            int temp2 = unit.healthPoints;
//                            unit.healthPoints = unit.healthPoints - damageback + Terrain.checkdefenceboost(location);
//                            if (unit.healthPoints > temp2) unit.healthPoints = temp2;



                            if (unit2.healthPoints <= 0) {
                                GameLists.units.remove(unit2);
                                newSquare.setText("<html><div style='text-align: center;'>" + newLocation + "<br>" + " " + "<br>" + " " + "<br>" + Terrain.checktype(newLocation) + "</div></html>");
                            } else {
                                newSquare.setText(("<html><div style='text-align: center;'>" + unit2.name + "<br>" + unit2.healthPoints + "<br>" + unit2.iswhite + "<br>" + Terrain.checktype(newLocation) + "</div></html>"));
                            }
                        }
                    }

                }
            }


        } else {

            if (oldSquare != null && newSquare != null) {
                int oldRow = Character.getNumericValue(location.charAt(1));
                int oldCol = location.charAt(0) - 'A';
                int newRow = Character.getNumericValue(newLocation.charAt(1));
                int newCol = newLocation.charAt(0) - 'A';

                int rowDiff = Math.abs(newRow - oldRow);
                int colDiff = Math.abs(newCol - oldCol);

                if ((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 1)) {

                    if (remainingMovement > 0) {


                        oldSquare.setText("<html><div style='text-align: center;'>" + location + "<br>" + " " + "<br>" + " " + "<br>" + Terrain.checktype(location) + "</div></html>"); // Clear the old square
                        newSquare.setText("<html><div style='text-align: center;'>" + name + "<br>" + healthPoints + "<br>" + iswhite + "<br>" + Terrain.checktype(newLocation) + "</div></html>"); // Set the new square with the unit's name
                        // Update the unit's location below | |
                        remainingMovement--; // Deduct one    \/     movement point for each square moved
                        movment = remainingMovement;
                        movment -= Terrain.checkTerrainmovcost(newLocation);// terrain dbuffs
                        if (movment < 0) movment = 0;
                        System.out.println(movment);
                        location = newLocation;
                        if (chaeckIfIsUncharted(newLocation)) {
                            for (Unit unit : GameLists.units) {
                                if (unit.location.equals(newLocation)) {
                                    Events.Event(unit);
                                }

                            }
                        }


                    } else {
                        movment = remainingMovement;
                        System.out.println("out of movment: " + movment);


                    }


                }
            }
        }


    }

    // random move may be useful later
//    public void rmove(){
//        JLabel oldSquare = pieces.get(location);
//
//        JLabel adjacentLabel = GridPanel.getRandomAdjacentLabel(location);
//
//        System.out.println(adjacentLabel.getText());
//        move(adjacentLabel.getText());
//
//
//    }
    public int how_meny_frendly_nearby(boolean whoattack, String location) {
        List<String> adjacentSquares = GridPanel.getAdjacentLabels(location);
        int count = 0;
        for (int i = 0; i < adjacentSquares.size(); i++) {
            for (Unit unit : GameLists.units) {
                System.out.println(adjacentSquares.get(i) + " " + unit.location);
                if (adjacentSquares.get(i).equals(unit.location) && (unit.iswhite == whoattack)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void randomMove(Unit unit) {
        List<String> adjacentSquares = GridPanel.getAdjacentLabels(unit.location);
        String location;
        location = adjacentSquares.get((int) (Math.random() * adjacentSquares.size()));
        unit.movment += 10;
        unit.move2(location,unit);
    }

    public static void damageToUnit(Unit unit, int damage) {
        unit.healthPoints -= damage;
        JLabel Square = pieces.get(unit.location);
        Square.setText(("<html><div style='text-align: center;'>" + unit.name + "<br>" + unit.healthPoints + "<br>" + unit.iswhite + "<br>" + Terrain.checktype(unit.location) + "</div></html>"));
    }

    public static void healToUnit(Unit unit, int heal) {
        unit.healthPoints += heal;
        JLabel Square = pieces.get(unit.location);
        if (unit.healthPoints > unit.maxHealthPoints || heal == 0) {
            unit.healthPoints = unit.maxHealthPoints;
        }
        Square.setText(("<html><div style='text-align: center;'>" + unit.name + "<br>" + unit.healthPoints + "<br>" + unit.iswhite + "<br>" + Terrain.checktype(unit.location) + "</div></html>"));
    }

    public static void chaeckIfUnitDead(Unit unit) {
        if (unit.healthPoints <= 0) {
            JLabel Square = pieces.get(unit.location);
            GameLists.units.remove(unit);
            Square.setText("<html><div style='text-align: center;'>" + unit.location + "<br>" + " " + "<br>" + " " + "<br>" + Terrain.checktype(unit.location) + "</div></html>");
        }
    }

    public static boolean chaeckIfIsUncharted(String newlocation) {
        if (GameLists.uncharted.contains(newlocation)) {
            GameLists.uncharted.remove(newlocation);
            return true;
        }
        return false;
    }

    public static boolean chaeckIfIsUncharted2(String newlocation) {
        if (GameLists.uncharted.contains(newlocation)) {
            //GameLists.uncharted.remove(newlocation);
            return true;
        }
        return false;
    }

    public void move2(String newLocation,Unit unit) {
        JLabel oldSquare = pieces.get(location);
        JLabel newSquare = pieces.get(newLocation);


        if (isAnotherPieceInLocation(newLocation)){
            damage = unit.attackPoints;

            int oldRow = Character.getNumericValue(location.charAt(1));
            int oldCol = location.charAt(0) - 'A';
            int newRow = Character.getNumericValue(newLocation.charAt(1));
            int newCol = newLocation.charAt(0) - 'A';

            int rowDiff = Math.abs(newRow - oldRow);
            int colDiff = Math.abs(newCol - oldCol);

            for (Unit unit2 : GameLists.units){
                if (unit2.location.equals(newLocation)&& ((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 1))){
                    unit2.healthPoints=unit2.healthPoints -unit.attackPoints+Terrain.checkdefenceboost(newLocation);
                    unit.healthPoints-=unit2.attackPoints;

                    if (unit.healthPoints<=0){
                        if(unit2.iswhite){
                            GameLists.p1_money+=14;
                        }else{
                            GameLists.p2_money+=14;
                        }

                        GameLists.units.remove(unit);
                        oldSquare.setText("<html><div style='text-align: center;'>" + location + "<br>" + " " + "<br>" + " " + "<br>"+Terrain.checktype(location)+"</div></html>");

                    }else{
                        oldSquare.setText(("<html><div style='text-align: center;'>" + unit.name + "<br>" + unit.healthPoints + "<br>" + unit.iswhite + "<br>"+Terrain.checktype(location)+"</div></html>"));
                    }
                    if (unit2.healthPoints<=0){
                        GameLists.units.remove(unit2);
                        newSquare.setText("<html><div style='text-align: center;'>" + newLocation + "<br>" + " " + "<br>" + " " + "<br>"+Terrain.checktype(newLocation)+"</div></html>");
                    }else{
                        newSquare.setText(("<html><div style='text-align: center;'>" + unit2.name + "<br>" + unit2.healthPoints + "<br>" + unit2.iswhite + "<br>"+Terrain.checktype(newLocation)+"</div></html>"));
                    }
                }

            }







        }
        else {

            if (oldSquare != null && newSquare != null) {
                int oldRow = Character.getNumericValue(location.charAt(1));
                int oldCol = location.charAt(0) - 'A';
                int newRow = Character.getNumericValue(newLocation.charAt(1));
                int newCol = newLocation.charAt(0) - 'A';

                int rowDiff = Math.abs(newRow - oldRow);
                int colDiff = Math.abs(newCol - oldCol);

                if ((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 1)) {
                    if(chaeckIfIsUncharted2(newLocation)){
                        newSquare.setText("<html><div style='text-align: center;'>" + name + "<br>" + healthPoints + "<br>" + iswhite + "<br>" + " Unknown " + "</div></html>");
                        if(chaeckIfIsUncharted2(location)) {
                            oldSquare.setText("<html><div style='text-align: center;'>" + location + "<br>" + " " + "<br>" + " " + "<br>" + " Unknown " + "</div></html>");
                        }else{
                            oldSquare.setText("<html><div style='text-align: center;'>" + location + "<br>" + " " + "<br>" + " " + "<br>" + Terrain.checktype(location) + "</div></html>");
                        }
                        unit.location = newLocation;



                    }else{
                        newSquare.setText("<html><div style='text-align: center;'>" + name + "<br>" + healthPoints + "<br>" + iswhite + "<br>"+Terrain.checktype(newLocation)+ "</div></html>");
                        if(chaeckIfIsUncharted2(location)) {
                            oldSquare.setText("<html><div style='text-align: center;'>" + location + "<br>" + " " + "<br>" + " " + "<br>" + " Unknown " + "</div></html>");
                        }else{
                            oldSquare.setText("<html><div style='text-align: center;'>" + location + "<br>" + " " + "<br>" + " " + "<br>" + Terrain.checktype(location) + "</div></html>");
                        }
                        unit.location = newLocation;

                    }

                }
            }
        }





    }


}
