import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static boolean gameover = false;
    public static String whosturn;
    // white, notwhite
    public static String whowon;
    public static boolean iswhiteturn = true;
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GameFrame gameframe = new GameFrame();



        gameframe.setLocationRelativeTo(null);
        gameframe.setVisible(true);


        Infantry infantryUnit1= new Infantry("Infantry1", Unit.Type.Infantry, Infantry.HEALTHPOINTS,Infantry.ATTACKPOINTS,0,true,Infantry.COST,"D7",GameFrame.gridPanel.getPieces());
        infantryUnit1.addUnit("D7");
        GameLists.units.add(infantryUnit1);

        StormTroops stormTroop67 = new StormTroops("Stormtroop12",Unit.Type.StormTroops,StormTroops.HEALTHPOINTS,StormTroops.ATTACKPOINTS,0,false,StormTroops.COST,"E9",GameFrame.gridPanel.getPieces());
        stormTroop67.addUnit("E9");
        GameLists.units.add(stormTroop67);

        HavyInfantry havyinfi3 = new HavyInfantry("HavyInfantry6",Unit.Type.HavyInfantry,HavyInfantry.HEALTHPOINTS,HavyInfantry.ATTACKPOINTS,0,false,HavyInfantry.COST,"E8",GameFrame.gridPanel.getPieces());
        havyinfi3.addUnit("E8");
        GameLists.units.add(havyinfi3);

        Cavalry cavUnit1= new Cavalry("Cavalry1", Unit.Type.Cavalry, Cavalry.HEALTHPOINTS,Cavalry.ATTACKPOINTS,0,true,Cavalry.COST,"C7",GameFrame.gridPanel.getPieces());
        cavUnit1.addUnit("C7");
        GameLists.units.add(cavUnit1);

        Cavalry cavUnit2= new Cavalry("Cavalry2", Unit.Type.Cavalry, Infantry.HEALTHPOINTS,Infantry.ATTACKPOINTS,0,true,Cavalry.COST,"C6",GameFrame.gridPanel.getPieces());
        cavUnit2.addUnit("C6");
        GameLists.units.add(cavUnit2);

        Infantry infantryUnit2= new Infantry("Infantry2", Unit.Type.Infantry, Infantry.HEALTHPOINTS,Infantry.ATTACKPOINTS,0,false,Infantry.COST,"O8",GameFrame.gridPanel.getPieces());
        infantryUnit2.addUnit("O6");
        GameLists.units.add(infantryUnit2);

        Monster monster1= new Monster("M1",Monster.TYPE,Monster.HEALTHPOINTS,Monster.ATTACKPOINTS,Monster.MOVEMENT,true,0,"O6",GameFrame.gridPanel.getPieces());
        monster1.addUnit("O6");
        GameLists.units.add(monster1);

        //towns and caipitlas



        for(int i = 0; !gameover; i++){
            if(iswhiteturn){

                Monster.monstersMove();


                whosturn = "white";
                System.out.println("********** white turn ***********");
                GameFrame.turnLabel2x(whosturn);
                GameLists.p1_money+=1;
                GameFrame.monylabelx(true);

                TurnPanel.startTurn(true);




                iswhiteturn=false;
            }else{
                whosturn = "notwhite";
                System.out.println("******* not white turn *******");
                GameFrame.turnLabel2x(whosturn);
                GameLists.p2_money+=1;
                GameFrame.monylabelx(false);

                TurnPanel.startTurn(false);
                System.out.println("koniec tury: "+i);



                iswhiteturn=true;
            }


        }
//
        //while(true){

          /*  System.out.println("gdzie idzie 1");
            String a = scanner.nextLine();
            infantryUnit1.move(a);
            System.out.println("gdzie idzie 1");
            String c = scanner.nextLine();
            infantryUnit1.move(c);
            System.out.println("gdzie idzie 2");
            String b = scanner.nextLine();
            infantryUnit1.move(b);
            System.out.println("gdzie idzie 2");
            String d = scanner.nextLine();
            infantryUnit1.move(d);*/





            System.out.println(infantryUnit1.getHealthPoints());
            System.out.println(infantryUnit2.getHealthPoints());
            //infantryUnit1.move("D6");

            System.out.println(infantryUnit1.getHealthPoints());
            System.out.println(infantryUnit2.getHealthPoints());
            System.out.println(GameLists.units);
            System.out.println(GameFrame.gridPanel.getPieces());
            boolean a = Unit.isAnotherPieceInLocation("D6");
            System.out.println(a);
        //}


















    }

}