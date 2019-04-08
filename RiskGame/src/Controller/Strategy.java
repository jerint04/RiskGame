package Controller;

import Model.GameModel;
import Model.Player;
import Model.ViewObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Model.GameModel.playerHashMap;

/**
 * @author Hemanshu
 * @date 2019-04-07
 */
public interface  Strategy {

    public  void armyCalculationDuringReinforcement(int playerId);

    public  void armyPlacementDuringReinforcement(int playerId);

    public  void exchangeCardsForArmies(int PlayerId);

    public  void playerAttack(int playerId);

    public  boolean fortificationPhase(int playerId);
}
