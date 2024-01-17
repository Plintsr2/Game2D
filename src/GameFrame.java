import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class GameFrame extends JFrame {
    static GridPanel gridPanel;
    private JButton createUnitButton1; // New Unit for player 1
    private JButton createUnitButton2; // New Unit for player 2
    private JButton createFortButton1; // New Fort for player 1
    private JButton createFortButton2; // New Fort for player 2
    private JButton endTurnButton;
    public static JLabel moneyLabel1; // Money label for player 1
    public static JLabel moneyLabel2; // Money label for player 2

    public static JLabel firstclickLabel1;
    public static JLabel turnLabel2;

    public static int infnumber=0;
    public static int cavnumber=0;
    public static int hinfnumber=0;
    public static int stormnum=0;
    public static int catnum=0;
    public static int archnum=0;
    public static Fort fort = new Fort("7",Fort.TYPE,7,1,2,1,Fort.Terrainmap);

    public static boolean turnend = false;
    public static byte player;
    public static String type;
    public static String locationToPlace;
 //   ======================================================================real gameframe
    public static void monylabelx(boolean p1){
        if(p1){
            moneyLabel1.setText("Money (Player 1): "+GameLists.p1_money);
        }else{
            moneyLabel2.setText("Money (Player 2): "+GameLists.p2_money);
        }
    }
    public static void firstclickLabel1x(){
        //Firstclick
        firstclickLabel1.setText("Firstclick: "+GridPanel.firstclick);
    }
    public static void turnLabel2x(String whoturn){
        //whosturn
        if(whoturn.equals("white")){
            turnLabel2.setText("Turn: "+whoturn);
        }else{
            turnLabel2.setText("Turn: "+whoturn);
        }
    }


    public GameFrame() {
        // Initialize the GridPanel and other components
        gridPanel = new GridPanel();
        createUnitButton1 = new JButton("New Unit (Player 1)");
        createUnitButton2 = new JButton("New Unit (Player 2)");
        createFortButton1 = new JButton("New Fort (Player 1)");
        createFortButton2 = new JButton("New Fort (Player 2)");
        endTurnButton = new JButton("End Turn");
        moneyLabel1 = new JLabel("Money (Player 1): " + GameLists.p1_money);
        moneyLabel2 = new JLabel("Money (Player 2): " + GameLists.p2_money);
        firstclickLabel1 = new JLabel("Firstclick: ");
        turnLabel2 = new JLabel("Turn: ");

// Set the layout of the frame to BorderLayout
        setLayout(new BorderLayout());

// Create a panel for money labels and buttons
        JPanel moneyAndButtonPanel = new JPanel(new GridLayout(1, 5));

// Create a panel for player 1's money label and buttons on the left
        JPanel player1Panel = new JPanel(new BorderLayout());
        player1Panel.add(moneyLabel1, BorderLayout.NORTH);
        JPanel player1ButtonPanel = new JPanel(new GridLayout(2, 1));
        player1ButtonPanel.add(createUnitButton1);
        player1ButtonPanel.add(createFortButton1);
        player1Panel.add(player1ButtonPanel, BorderLayout.CENTER);
        moneyAndButtonPanel.add(player1Panel);

// Create a panel for the spacer label between player 1 and end turn
        JPanel spacerPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //JLabel spacerLabel1 = new JLabel("Firstclick: "); // You can set text if needed
        spacerPanel1.add(firstclickLabel1);
        moneyAndButtonPanel.add(spacerPanel1);

// Create a panel for the "End Turn" button in the center
        JPanel endTurnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        endTurnPanel.add(endTurnButton);
        moneyAndButtonPanel.add(endTurnPanel);

// Create a panel for the spacer label between end turn and player 2
        JPanel spacerPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
       // JLabel spacerLabel2 = new JLabel("Turn:"); // You can set text if needed
        spacerPanel2.add(turnLabel2);
        moneyAndButtonPanel.add(spacerPanel2);

// Create a panel for player 2's money label and buttons on the right
        JPanel player2Panel = new JPanel(new BorderLayout());
        player2Panel.add(moneyLabel2, BorderLayout.NORTH);
        JPanel player2ButtonPanel = new JPanel(new GridLayout(2, 1));
        player2ButtonPanel.add(createUnitButton2);
        player2ButtonPanel.add(createFortButton2);
        player2Panel.add(player2ButtonPanel, BorderLayout.CENTER);
        moneyAndButtonPanel.add(player2Panel);

// Add the GridPanel to the center
        add(gridPanel, BorderLayout.CENTER);

// Add the money label and button panel to the bottom
        add(moneyAndButtonPanel, BorderLayout.SOUTH);

        // Add action listeners for the buttons
        createUnitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exx) {
                if(Main.iswhiteturn) {
                    GridPanel.setMouseListenerEnabled(false);
                    player = 1;
                    locationToPlace = whereToPutUnit();
                    if(Terrain.checktype(locationToPlace).equals("City p1")){
                        showUnitSelectionDialog();
                    }
                    GridPanel.setMouseListenerEnabled(true);

                }
            }
        });

        createUnitButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exx) {
                if(!Main.iswhiteturn) {
                    GridPanel.setMouseListenerEnabled(false);
                    player = 2;
                    locationToPlace = whereToPutUnit();
                    if(Terrain.checktype(locationToPlace).equals("City p2")){
                        showUnitSelectionDialog();
                    }
                }
            }
        });

        createFortButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exx) {
                boolean isgoodclick = false;
                if(Main.whosturn.equals("white"))isgoodclick = true;
                locationToPlace = whereToPutUnit();
                if (Unit.isAnotherPieceInLocationwhite(locationToPlace)&&isgoodclick){
                    int bonus = Terrain.checkdefenceboost(locationToPlace);
                    Terrain exterain = GameLists.Terrainmap.get(locationToPlace);
                    GameLists.Terrainmap.replace(locationToPlace,fort);
                    Unit unit = Unit.whatPieceisInLocation(locationToPlace);
                    if(GameLists.p1_money>=Fort.BUILDING_COST+exterain.getbuildingCost()){
                        if(unit!=null) {
                            Unit.addTerrain(locationToPlace).setText("<html><div style='text-align: center;'>" + unit.name + "<br>" + unit.getHealthPoints() + "<br>" + unit.iswhite + "<br>" + Fort.TYPE + "</div></html>");
                            GameLists.p1_money-=Fort.BUILDING_COST;
                            GameLists.p1_money -=exterain.getbuildingCost();

                        }
                    }else{
                        //GameLists.p1_money =0;
                        System.out.println("not enough money");
                    }
                    monylabelx(true);

                }
                // Handle the "New Fort" button for player 1
                // Add your logic here
            }
        });

        createFortButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exx) {
                boolean isgoodclick = false;
                locationToPlace = whereToPutUnit();
                if(Main.whosturn.equals("notwhite")&& !Terrain.checktype(locationToPlace).equals(City.TYPE)&&!Terrain.checktype(locationToPlace).equals(Fort.TYPE))isgoodclick=true;
                if (!Unit.isAnotherPieceInLocationwhite(locationToPlace)&&isgoodclick){
                    GameLists.Terrainmap.replace(locationToPlace,fort);
                    Unit unit = Unit.whatPieceisInLocation(locationToPlace);
                    Terrain exterain = GameLists.Terrainmap.get(locationToPlace);

                    if(GameLists.p2_money>=Fort.BUILDING_COST+exterain.getbuildingCost()){
                        if(unit!=null) {
                            Unit.addTerrain(locationToPlace).setText("<html><div style='text-align: center;'>" + unit.name + "<br>" + unit.getHealthPoints() + "<br>" + unit.iswhite + "<br>" + Fort.TYPE + "</div></html>");
                            GameLists.p2_money -=exterain.getbuildingCost();
                            GameLists.p2_money -= fort.getbuildingCost();
                        }
                    }else{
                        //GameLists.p2_money =0;
                        System.out.println("not enough money");
                    }
                    monylabelx(false);
                }
                // Handle the "New Fort" button for player 2
                // Add your logic here
            }
        });

        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exxx) {
                System.out.println("endturn pressed");
                turnend = true;
                // Handle the "End Turn" button
                // Add your logic here
            }
        });

        // Set frame properties
        setTitle("2D war game");
        setSize(2000,1100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    // heres the second part of issue, after this method runs and after placing unit the mouse listener
    // responisble for movment stops working
    private static void showUnitSelectionDialog() {
        JFrame dialogFrame = new JFrame("Select Unit Type");
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setSize(300, 150);

        JButton infantryButton = new JButton("Infantry: 1");
        JButton cavalryButton = new JButton("Cavalry: 4");
        JButton havyInfantryButton = new JButton("havyInfantry: 4");
        JButton stormTroopsButton = new JButton("StormTroops: 6");
        JButton catapultButton = new JButton("Catapult: 6");
        JButton archerButton = new JButton("Archer: 3");




        infantryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                dialogFrame.dispose();
                GridPanel.setMouseListenerEnabled(true);
                //check and subtract money

                type = "Infantry";
                if(Unit.isAnotherPieceInLocation(locationToPlace)){
                    System.out.println("cant add unit. another unit in place");
                    GridPanel.setMouseListenerEnabled(true);
                }else {
                    if(player==1){
                        if(GameLists.p1_money < Infantry.COST){
                            return;
                        }else{
                            GameLists.p1_money-=Infantry.COST;
                        }
                    }else{
                        if(GameLists.p2_money < Infantry.COST){
                            return;
                        }else{
                            GameLists.p2_money-=Infantry.COST;
                        }
                    }
                    monylabelx(player==1);
                    System.out.println("Selected: " + type);
                    System.out.println("dodajemy unitea " + type + " na pole " + locationToPlace);
                    Infantry unit = new Infantry("Infantry"+infnumber, Unit.Type.Infantry, Infantry.HEALTHPOINTS, Infantry.ATTACKPOINTS,0, player == 1, Infantry.COST,locationToPlace, gridPanel.getPieces());
                    unit.addUnit(locationToPlace);
                    GameLists.units.add(unit);
                    type = null;

                    infnumber++;


                }


            }
        });

        cavalryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                dialogFrame.dispose();
                GridPanel.setMouseListenerEnabled(true);

                type = "Cavalry";

                if(Unit.isAnotherPieceInLocation(locationToPlace)) {
                    System.out.println("cant add unit. another unit in place");
                    GridPanel.setMouseListenerEnabled(true);
                }else {
                    if(player==1){
                        if(GameLists.p1_money < Cavalry.COST){
                            return;
                        }else{
                            GameLists.p1_money-=Cavalry.COST;
                        }
                    }else{
                        if(GameLists.p2_money < Cavalry.COST){
                            return;
                        }else{
                            GameLists.p2_money-=Cavalry.COST;
                        }
                    }
                    monylabelx(player==1);
                    System.out.println("Selected: " + type);
                    System.out.println("dodajemy unitea " + type + " na pole " + locationToPlace);
                    Cavalry unit = new Cavalry("Cavalry"+cavnumber, Unit.Type.Cavalry, Cavalry.HEALTHPOINTS, Cavalry.ATTACKPOINTS, 0, player == 1,Cavalry.COST ,locationToPlace, gridPanel.getPieces());
                    unit.addUnit(locationToPlace);
                    GameLists.units.add(unit);
                    type = null;
                    cavnumber++;
                }
            }
        });

        havyInfantryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                dialogFrame.dispose();
                GridPanel.setMouseListenerEnabled(true);

                type = "HavyInfantry";
                if(Unit.isAnotherPieceInLocation(locationToPlace)){
                    System.out.println("cant add unit. another unit in place");
                    GridPanel.setMouseListenerEnabled(true);
                }else {
                    if(player==1){
                        if(GameLists.p1_money < HavyInfantry.COST){
                            return;
                        }else{
                            GameLists.p1_money-=HavyInfantry.COST;
                        }
                    }else{
                        if(GameLists.p2_money < HavyInfantry.COST){
                            return;
                        }else{
                            GameLists.p2_money-=HavyInfantry.COST;
                        }
                    }
                    monylabelx(player==1);
                    System.out.println("Selected: " + type);
                    System.out.println("dodajemy unitea " + type + " na pole " + locationToPlace);
                    HavyInfantry unit = new HavyInfantry("HavyInfantry"+hinfnumber, Unit.Type.HavyInfantry, HavyInfantry.HEALTHPOINTS, HavyInfantry.ATTACKPOINTS,0, player == 1, HavyInfantry.COST,locationToPlace, gridPanel.getPieces());
                    unit.addUnit(locationToPlace);
                    GameLists.units.add(unit);
                    type = null;
                    hinfnumber++;


                }


            }
        });

        stormTroopsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                dialogFrame.dispose();
                GridPanel.setMouseListenerEnabled(true);

                type = "StormTroops";
                if(Unit.isAnotherPieceInLocation(locationToPlace)){
                    System.out.println("cant add unit. another unit in place");
                    GridPanel.setMouseListenerEnabled(true);
                }else {
                    if(player==1){
                        if(GameLists.p1_money < StormTroops.COST){
                            return;
                        }else{
                            GameLists.p1_money-=StormTroops.COST;
                        }
                    }else{
                        if(GameLists.p2_money < StormTroops.COST){
                            return;
                        }else{
                            GameLists.p2_money-=StormTroops.COST;
                        }
                    }
                    monylabelx(player==1);
                    System.out.println("Selected: " + type);
                    System.out.println("dodajemy unitea " + type + " na pole " + locationToPlace);
                    HavyInfantry unit = new HavyInfantry("StormTroops"+stormnum, Unit.Type.StormTroops, StormTroops.HEALTHPOINTS, StormTroops.ATTACKPOINTS,0, player == 1, StormTroops.COST,locationToPlace, gridPanel.getPieces());
                    unit.addUnit(locationToPlace);
                    GameLists.units.add(unit);
                    type = null;
                    stormnum++;


                }


            }
        });

        catapultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                dialogFrame.dispose();
                GridPanel.setMouseListenerEnabled(true);

                type = "Catapult";
                if(Unit.isAnotherPieceInLocation(locationToPlace)){
                    System.out.println("cant add unit. another unit in place");
                    GridPanel.setMouseListenerEnabled(true);
                }else {
                    if(player==1){
                        if(GameLists.p1_money < Catapult.COST){
                            return;
                        }else{
                            GameLists.p1_money-=Catapult.COST;
                        }
                    }else{
                        if(GameLists.p2_money < Catapult.COST){
                            return;
                        }else{
                            GameLists.p2_money-=Catapult.COST;
                        }
                    }
                    monylabelx(player==1);
                    System.out.println("Selected: " + type);
                    System.out.println("dodajemy unitea " + type + " na pole " + locationToPlace);
                    HavyInfantry unit = new HavyInfantry("Catapult"+catnum, Unit.Type.Catapult, Catapult.HEALTHPOINTS, Catapult.ATTACKPOINTS,0, player == 1, Catapult.COST,locationToPlace, gridPanel.getPieces());
                    unit.addUnit(locationToPlace);
                    GameLists.units.add(unit);
                    type = null;
                    catnum++;


                }


            }
        });
        archerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                dialogFrame.dispose();
                GridPanel.setMouseListenerEnabled(true);
                //check and subtract money

                type = "Archer";
                if(Unit.isAnotherPieceInLocation(locationToPlace)){
                    System.out.println("cant add unit. another unit in place");
                    GridPanel.setMouseListenerEnabled(true);
                }else {
                    if(player==1){
                        if(GameLists.p1_money < Archer.COST){
                            return;
                        }else{
                            GameLists.p1_money-=Archer.COST;
                        }
                    }else{
                        if(GameLists.p2_money < Archer.COST){
                            return;
                        }else{
                            GameLists.p2_money-=Archer.COST;
                        }
                    }
                    monylabelx(player==1);
                    System.out.println("Selected: " + type);
                    System.out.println("dodajemy unitea " + type + " na pole " + locationToPlace);
                    Archer unit = new Archer("Archer"+archnum, Unit.Type.Archer, Archer.HEALTHPOINTS, Archer.ATTACKPOINTS,0, player == 1, Archer.COST,locationToPlace, gridPanel.getPieces());
                    unit.addUnit(locationToPlace);
                    GameLists.units.add(unit);
                    type = null;

                    archnum++;


                }


            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(infantryButton);
        panel.add(cavalryButton);
        panel.add(havyInfantryButton);
        panel.add(stormTroopsButton);
        panel.add(catapultButton);
        panel.add(archerButton);
        dialogFrame.add(panel);
        dialogFrame.setLocationRelativeTo(null); // Center the dialog on the screen

        dialogFrame.setVisible(true);
    }
    public String whereToPutUnit(){
        GridPanel.resetFirstclick=true;
        return GridPanel.unitadder;
    }

}