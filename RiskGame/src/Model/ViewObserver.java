package Model;

import Controller.GameController;

import java.util.Observable;
import java.util.Observer;

/**
 * ViewObserver class
 *
 * @author Jerin
 * @version 1.0.0
 */
public class ViewObserver implements Observer {
    String phaseView = "";

    @Override
    /**
     * This method is used to update the changes to Observable Class
     * @param o, Observable type
     * @param arg, Object type
     */
    public void update(Observable o, Object arg) {

        // TODO Auto-generated method stub
        Player player = (Player) o;

        if (((Player) o).getGamePhase() != "Initialisation") {
            phaseView = "Current Phase: " + player.getGamePhase() + "\n" + "Current Player: "
                    + player.getName() + "\n" + "Information about the phase :" + player.infoAboutAction;
            GameController.mssageOnGUI(phaseView);
        } else {
            phaseView = "Current Phase: " + player.getGamePhase();
            GameController.mssageOnGUI(phaseView);
        }
       /* System.out.println("_____Phase View_____");
        System.out.println(phaseView);
        System.out.println("_____End of Phase View_____");*/
    }

    /**
     * This method is used to get the Phase View
     */
    public void getPhaseView() {
        // System.out.println(phaseView);
    }

}


