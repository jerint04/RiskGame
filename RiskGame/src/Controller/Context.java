package Controller;

/**
 * @author Hemanshu
 */
public class Context {
    private Strategy strategy;

    /**
     * Constructor used to initialise strategy interface type
     * @param strategy, Strategy interface type
     */
    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    /**
     * This method is used to execute army Calculation
     * @param playerId, id of the player
     */
    public void executeArmyCalculation(int playerId){
         strategy.armyCalculationDuringReinforcement(playerId);
    }

    /**
     * This method is used to execute army placement
     * @param playerId, id of the player
     */
    public void executeArmyPlacement(int playerId){
        strategy.armyPlacementDuringReinforcement(playerId);
    }

    /**
     * This method is used to execute exchanging of Cards
     * @param playerId, id of the player
     */
    public void executeExchangeCards(int playerId){
        strategy.exchangeCardsForArmies(playerId);
    }

    /**
     * This method is used to execute player attack
     * @param playerId, id of the player
     */
    public void executePlayerAttack(int playerId){
        strategy.playerAttack(playerId);
    }

    /**
     * This method is used to execute fortification Phase
     * @param playerId, id of the player
     * @return boolean
     */
    public boolean executeFortificationPhase(int playerId){
        return strategy.fortificationPhase(playerId);
    }
}
