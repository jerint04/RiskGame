package Model;

import View.DisplayGuiHelp;

import java.util.Observable;
import java.util.Observer;

/**
 * VieObserver class
 * Initiate the risk game view in java swings
 *
 * @author Jerin
 * @version 1.0.0
 */
public class ViewObserver implements Observer {
    String phaseView = "";
    @Override
    /**
     * Update method called by the observable object to perform all the actions
     */
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        //TurnOrganizer turnOrganizer = (TurnOrganizer) o;
        Player player=(Player) o;
        if (((Player) o).getGamePhase() != "Initialisation")
        {
            phaseView = "Current Phase: " + player.getGamePhase() + "\n" + "Current Player: "
                    + player.getName();
            DisplayGuiHelp gui1 = new DisplayGuiHelp(phaseView);

        }
        else
        {
            phaseView = "Current Phase: " + player.getGamePhase();
            DisplayGuiHelp gui1 = new DisplayGuiHelp(phaseView);
        }



        System.out.println("_____Phase View_____");
        System.out.println(phaseView);
        System.out.println("_____End of Phase View_____");
    }

    /**
     * This method gets the view of the current phase
     */
    public void GetPhaseView() {
        System.out.println(phaseView);
    }

}


