import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class TurnPanel {
    public static void startTurn(boolean white) {
        int count =0;
        for (Unit unit : GameLists.units) {
           // System.out.println(unit);

            //white =true p1 turn check if he lost
            if(unit.iswhite!=white){
                if(Terrain.checktype(unit.location).equals("City p2")) {
                    //chcek for win condition
                    count++;
//                }else if((unit.iswhite) && Terrain.checktype(unit.location).equals("City p1")){
//                  count ++;
//                }
                }
            }
            Unit.movmentReset(white,unit);
        }if(count>=3){
            Main.whowon=GameLists.convertbolwhitetoplayer(!white);
            System.out.println("gamover "+Main.whowon+" won");
            Main.gameover=true;

        }



        while (!GameFrame.turnend) {

          //  System.out.println("Turn is running...");


            try {
                Thread.sleep(2000); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        GameFrame.turnend=false;
        System.out.println("Turn stopped.");
    }


}


