import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

import static java.lang.Thread.sleep;

public class GridPanel extends JPanel {
    public static Map<String, JLabel> Pieces = new HashMap<>();
    public static Map<JLabel, String> Terrainmap = new HashMap<JLabel, String>();
    public static boolean isMouseListenerEnabled = true;

    public static String firstclick = null;
    public static String unitadder = null;
    private String unitloca = null;
    public static boolean resetFirstclick = false;
    public static int indexc =0;
    public static Farmland farmland = new Farmland("0",Farmland.TYPE,0,0,-1,0, Farmland.Terrainmap);
    public static Plain plain = new Plain("1",Plain.TYPE,1,0,0,0,Plain.Terrainmap);
    public static Forest forest = new Forest("2",Forest.TYPE,2,1,-1,1,Forest.Terrainmap);
    public static Swamp swamp = new Swamp("3",Swamp.TYPE,3,2,2,-1,Swamp.Terrainmap);
    public static Lake lake = new Lake("4",Lake.TYPE,4,99,99,-99,Lake.Terrainmap);
    public static Moutain moutanin = new Moutain("5",Moutain.TYPE,5,2,2,2,Moutain.Terrainmap);
    public static Hill hill = new Hill("6",Hill.TYPE,6,1,1,1,Hill.Terrainmap);

    public static City city = new City("11","City p1",7,0,999,1,Hill.Terrainmap);
    public static City city2 = new City("22","City p2",8,0,999,1,Hill.Terrainmap);

    public GridPanel() {

        setLayout(new GridLayout(10, 20));


        GameLists.terrain.add(farmland);
        GameLists.terrain.add(forest);
        GameLists.terrain.add(swamp);
        GameLists.terrain.add(plain);
        GameLists.terrain.add(moutanin);
        GameLists.terrain.add(lake);
        GameLists.terrain.add(hill);

        int[] arr = new int[6];
        int[] arr2 = new int[10];
        Random random = new Random();
        for(int i =0;i<arr.length;i++){
            arr[i]=i+1;
        }
        for (int i = arr.length - 1; i > 0; i--) {

            int index = random.nextInt(i + 1);

            // Swap array[i] and array[index]
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }

        for(int i =0;i<6;i++){
            arr2[i]=i+1;
        }
        for(int i =6;i<arr2.length;i++){
            arr2[i]=arr[i-6];
        }
        for (int i = arr2.length - 1; i > 0; i--) {

            int index = random.nextInt(i + 1);

            // Swap array[i] and array[index]
            int temp = arr2[i];
            arr2[i] = arr2[index];
            arr2[index] = temp;
        }


        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 20; col++) {
                String squareLabel = String.format("%c%d", 'A' + col, 10 - row);
                JLabel square = new JLabel(squareLabel);
                square.setOpaque(true);
                square.setHorizontalAlignment(SwingConstants.CENTER);
                square.setVerticalAlignment(SwingConstants.CENTER);
                square.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
                square.setForeground((row + col) % 2 == 0 ? Color.BLACK : Color.WHITE);

                square.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                add(square);
                Pieces.put(squareLabel, square);
                //Terrain.Farmland
                // prawa i lewa strona


                if(col<=3 || col>=16){
                    addterrainXD(square,squareLabel,0);
                }
                //sektor 1
                else if (col<6 && row<4||col>13&& row>5) {
                    addterrainXD(square,squareLabel,arr[0]);
                }
                //sektor 2
                else if (col<6 && row<6||col>13&& row>3) {
                    addterrainXD(square,squareLabel,arr[1]);
                }
                //sektor 3
                else if (col<6 ||col>13) {
                    addterrainXD(square,squareLabel,arr[2]);
                }
                //sektor 4
                else if (col<8 && row<4||col>11 && row>5) {
                    addterrainXD(square,squareLabel,arr[3]);
                }
                //sektor 5
                else if (col<8 && row<6||col>11 && row>3) {
                    addterrainXD(square,squareLabel,arr[4]);
                }
                //sektor 6
                else if (col<8 ||col>11) {
                    addterrainXD(square,squareLabel,arr[5]);
                }
                // minisektory
                else if(col<10 &&row<2) {
                    addterrainXD(square,squareLabel,arr2[0]);
                    }
                else if(col<10 &&row<4) {
                    addterrainXD(square,squareLabel,arr2[1]);
                }
                else if(col<10 &&row<6) {
                    addterrainXD(square,squareLabel,arr2[2]);
                }
                else if(col<10 &&row<8) {
                    addterrainXD(square,squareLabel,arr2[3]);
                }
                else if(col<10) {
                    addterrainXD(square,squareLabel,arr2[4]);
                }
                else if(col<12 &&row<2) {
                    addterrainXD(square,squareLabel,arr2[5]);
                }
                else if(col<12 &&row<4) {
                    addterrainXD(square,squareLabel,arr2[6]);
                }
                else if(col<12 &&row<6) {
                    addterrainXD(square,squareLabel,arr2[7]);
                }
                else if(col<12 &&row<8) {
                    addterrainXD(square,squareLabel,arr2[8]);
                }
                else if(col<12) {
                    addterrainXD(square,squareLabel,arr2[9]);
                }
                if(((col == 1) && ((row == 2) || (row == 4) || (row == 5) || (row == 7))) || ((col == 2) && ((row == 4) || (row == 5)))) {
                    addterrainXD(square,squareLabel,7);
                }
                if(((col == 18) && ((row == 2) || (row == 4) || (row == 5) || (row == 7))) || ((col == 17) && ((row == 4) || (row == 5)))) {
                    addterrainXD(square,squareLabel,8);
                }

                square.addMouseListener(new CustomMouseListener());


            }
        }


        CustomMouseListener customMouseListener = new CustomMouseListener();
        addMouseListener(customMouseListener);
    }

    public static void setMouseListenerEnabled(boolean enabled) {
        isMouseListenerEnabled = enabled;
    }

    // here are issues. Mouse listener below works until i add a unit then for some unknown reason it stops
    // and i cant put it back on. and since its the only way to move units game becomes unplayable
    public class CustomMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
           // System.out.println(e);
            if(!isMouseListenerEnabled){
                System.out.println("disabled");
                return;
            }
            JLabel clickedSquare = (JLabel) e.getComponent();
            String clickedLabel = null;
            for (Map.Entry<String, JLabel> entry : Pieces.entrySet()) {
                if (entry.getValue() == clickedSquare) {
                    clickedLabel = entry.getKey();
                   // System.out.println("got entry key");
                    break;
                }
            }
//            if (resetFirstclick){
//                firstclick=null;
//                resetFirstclick=false;
//            }

            if (clickedLabel != null) {
                //System.out.println("clicked label done");
                //System.out.println(firstclick);
                if (firstclick == null) {
                    firstclick = clickedLabel;
                    System.out.println("First Clicked on square: " + clickedLabel);
                    GameFrame.firstclickLabel1x();
                    unitadder=firstclick;
                    if (Unit.isAnotherPieceInLocation(clickedLabel)) {
                        for (Unit unit : GameLists.units) {
                            if (unit.location.equals(clickedLabel)) {
                                unitloca = clickedLabel;
                            }
                        }
                    }
                    else{
                        firstclick=null;
                    }

                } else if ((unitloca != null && !clickedLabel.equals(unitloca))) {
                    System.out.println("Second clicked square: " + clickedLabel);
                    for (Unit unit : GameLists.units) {
                        if (unit.location.equals(unitloca)&&unit.getType()!= Unit.Type.Monster) {
                            unit.move(clickedLabel);
                        }
                        // Additional logic for handling clicks
                    }
                    firstclick = null;
                    unitloca = null;
                }
            }






        }

    }
    public void setTextToSquere(){

    }
    public void addterrainXD(JLabel square,String squareLabel,int id){

        switch (id){
            case 0:
                GameLists.Terrainmap.put(squareLabel,farmland);
                setTexttoSquare(square,squareLabel,Farmland.TYPE);
                square.setText("<html><div style='text-align: center;'>" + squareLabel + "<br> " + " " + "<br> " + " " + "<br>" + Farmland.TYPE + "</div></html>");
                break;
            case 1:
                GameLists.Terrainmap.put(squareLabel,plain);
                setTexttoSquare(square,squareLabel,Plain.TYPE);
                GameLists.uncharted.add(squareLabel);
               // square.setText("<html><div style='text-align: center;'>" + squareLabel + "<br> " + " " + "<br> " + " " + "<br>" + Plain.TYPE + "</div></html>");
                break;
            case 2:
                GameLists.Terrainmap.put(squareLabel,forest);
                setTexttoSquare(square,squareLabel,Forest.TYPE);
                GameLists.uncharted.add(squareLabel);
                //square.setText("<html><div style='text-align: center;'>" + squareLabel + "<br> " + " " + "<br> " + " " + "<br>" + Forest.TYPE + "</div></html>");
                break;
            case 3:
                GameLists.Terrainmap.put(squareLabel,swamp);
                setTexttoSquare(square,squareLabel,Swamp.TYPE);
                GameLists.uncharted.add(squareLabel);
               // square.setText("<html><div style='text-align: center;'>" + squareLabel + "<br> " + " " + "<br> " + " " + "<br>" + Swamp.TYPE + "</div></html>");
                break;
            case 4:
                GameLists.Terrainmap.put(squareLabel,lake);
                setTexttoSquare(square,squareLabel,Lake.TYPE);
                GameLists.uncharted.add(squareLabel);
               // square.setText("<html><div style='text-align: center;'>" + squareLabel + "<br> " + " " + "<br> " + " " + "<br>" + Lake.TYPE + "</div></html>");
                break;
            case 5:
                GameLists.Terrainmap.put(squareLabel,moutanin);
                setTexttoSquare(square,squareLabel,Moutain.TYPE);
                GameLists.uncharted.add(squareLabel);
                //square.setText("<html><div style='text-align: center;'>" + squareLabel + "<br> " + " " + "<br> " + " " + "<br>" + Moutain.TYPE + "</div></html>");
                break;
            case 6:
                GameLists.Terrainmap.put(squareLabel,hill);
                setTexttoSquare(square,squareLabel,Hill.TYPE);
                GameLists.uncharted.add(squareLabel);
                //square.setText("<html><div style='text-align: center;'>" + squareLabel + "<br> " + " " + "<br> " + " " + "<br>" + Hill.TYPE + "</div></html>");
                break;
            case 7:
                GameLists.Terrainmap.put(squareLabel,city);
                setTexttoSquare(square,squareLabel,city.type);
                square.setText("<html><div style='text-align: center;'>" + squareLabel + "<br> " + " " + "<br> " + " " + "<br>" + city.type+ "</div></html>");
                break;
            case 8:
                GameLists.Terrainmap.put(squareLabel,city2);
                setTexttoSquare(square,squareLabel,city2.type);
                square.setText("<html><div style='text-align: center;'>" + squareLabel + "<br> " + " " + "<br> " + " " + "<br>" + city2.type + "</div></html>");
                break;

        }


    }
    public static void setTexttoSquare(JLabel square,String squareLabel, String type ){
        //square.setText("<html><div style='text-align: center;'>" + squareLabel + "<br> " + " " + "<br> " + " " + "<br>" + type + "</div></html>");
    }


    public Map<String, JLabel> getPieces(){
        return Pieces;
    }

    public static List<String> getAdjacentLabels(String label) {
        int col = label.charAt(0) - 'A';
        int row = 10 - Integer.parseInt(label.substring(1));

        int[] rowOffsets = {-1, 1, 0, 0};
        int[] colOffsets = {0, 0, -1, 1};

        List<String> adjacentSquares = new ArrayList<>();

        for (int i = 0; i < rowOffsets.length; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];

            if (newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 20) {
                String adjacentLabel = String.format("%c%d", 'A' + newCol, 10 - newRow);
                JLabel adjacentSquare = Pieces.get(adjacentLabel);
                if (adjacentSquare != null) {
                    adjacentSquares.add(adjacentLabel);
                }
            }
        }

        return adjacentSquares;
    }
//    public static JLabel getRandomAdjacentLabel(String label) {
//        List<JLabel> adjacentSquares = getAdjacentLabels(label);
//
//
//        Random rand = new Random();
//        int randomIndex = rand.nextInt(adjacentSquares.size());
//        System.out.println(adjacentSquares.get(randomIndex));
//        return adjacentSquares.get(randomIndex);
//
//
//
//    }




}

