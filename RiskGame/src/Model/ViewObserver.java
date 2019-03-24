package Model;

import Controller.GameController;

import java.util.Observable;
import java.util.Observer;

public class ViewObserver implements Observer {
    String phaseView = "";
    //DisplayGuiHelp gui1=new DisplayGuiHelp();


    @Override
    public void update(Observable o, Object arg) {

        // TODO Auto-generated method stub
        //TurnOrganizer turnOrganizer = (TurnOrganizer) o;

        Player player=(Player) o;

            if (((Player) o).getGamePhase() != "Initialisation")
        {
            phaseView = "Current Phase: " + player.getGamePhase() + "\n" + "Current Player: "
                    + player.getName();
            GameController.MssageOnGUI(phaseView);

//            gui1.theText.setText(phaseView);
//            gui1.dispose();


        }
        else{
            phaseView = "Current Phase: " + player.getGamePhase();
//                gui1.theText.setText(phaseView);
                GameController.MssageOnGUI(phaseView);



        }



        System.out.println("_____Phase View_____");
        System.out.println(phaseView);
        System.out.println("_____End of Phase View_____");
    }

    public void GetPhaseView() {
        System.out.println(phaseView);
    }

}


