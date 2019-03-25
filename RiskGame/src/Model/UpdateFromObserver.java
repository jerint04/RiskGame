package Model;

import java.util.Observable;
import java.util.Observer;

/**
 * UpdateFromObserver class
 *
 * @author Jerin
 * @version 1.0.0
 */
public class UpdateFromObserver implements Observer {

    @Override
    /**
     * This method is used to update from observer
     * @param o, Observable type
     * @param arg, Object type
     */
    public void update(Observable o, Object arg) {
        System.out.println("The name of the player is"+((Player)o).getName()+" and number of infantary is:"+((Player)o).getNumberOfInfantry());

        for (int i=0;i<((Player)o).Cards.size();i++) {

            System.out.println(i + " : " + ((Player)o).Cards.get(i));
        }

    }




}
