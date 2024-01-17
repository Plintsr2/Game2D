import javax.swing.*;

public class Events {
    // activates when discovering new teretory
    // 10 - eventuw
    //1-  dostejsz 2 gwiazdki
    //2 - jednostka sie leczy
    //3 - jednostka obrywa -1
    //4 - plus 1 do ruchu
    //5 - potwur (3 w grze)
    //6 - dostajesz 1 gwiazdke
    //7 - tez potwur
    //8
    //9
    //10
    //50 / 50 ze event sie wydarzy.
    public static void Event(Unit unit){
        int randomnumber = (int)(Math.random()*21);
        System.out.println("randomnumber"+randomnumber);
        if(randomnumber<11){
            String message="";
            switch(randomnumber){
                case 1:
                    if(Main.iswhiteturn){
                        GameLists.p1_money+=2;
                        GameFrame.monylabelx(true);
                    }else {
                        GameLists.p2_money+=2;
                        GameFrame.monylabelx(false);
                    }

                    message = "<html><div style='text-align: center;'> unit found resources <br> +2 currency";
                    break;
                case 2:
                    Unit.healToUnit(unit,0);

                    message = "<html><div style='text-align: center;'> unit found food and people <br> hp restored";
                    break;
                case 3:
                    Unit.damageToUnit(unit,1);
                    Unit.chaeckIfUnitDead(unit);

                    message = "<html><div style='text-align: center;'> unit found and fought barbarians <br> -1 hp";
                    break;
                case 4:
                    Unit.movmentReset(Main.iswhiteturn,unit);

                    message = "<html><div style='text-align: center;'> unit found motivation <br> can move again";
                    break;
                case 5,6:
                    message = unit.location;

                    Unit.damageToUnit(unit,10);
                    Unit.chaeckIfUnitDead(unit);
                    if(Monster.addMonster(message)==0){
                        Event(unit);
                    }

                    message = "<html><div style='text-align: center;'> Monster appears <br> unit have been eaten";
                    break;
                case 7:
                    if(Main.iswhiteturn){
                        GameLists.p1_money+=1;
                        GameFrame.monylabelx(true);
                    }else {
                        GameLists.p2_money+=1;
                        GameFrame.monylabelx(false);
                    }

                    message = "<html><div style='text-align: center;'> unit found a bit of resources <br> +1 currency";
                    break;
                case 8:








                default:
                    //throw new IllegalStateException("Unexpected value: " + randomnumber);
            }
            JDialog popup = new JDialog();
            popup.setTitle("Event happened");
            popup.setSize(200,200);
            JPanel panel = new JPanel();
            JLabel label = new JLabel(message);
            panel.add(label);
            popup.add(panel);
            popup.setVisible(true);
            popup.setLocationRelativeTo(null);
            popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }

    }
}
