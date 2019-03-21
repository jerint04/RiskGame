package Model;

import java.util.Observable;
import java.util.Observer;

public class UpdateFromObserver implements Observer {



    @Override
    public void update(Observable o, Object arg) {
        System.out.println("The name of the player is"+((Player)o).getName()+" and number of infantary is:"+((Player)o).getNumberOfInfantry());

        for (int i=0;i<((Player)o).Cards.size();i++) {

            System.out.println(i + " : " + ((Player)o).Cards.get(i));
        }

    }




}
