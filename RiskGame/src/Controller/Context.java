package Controller;

/**
 * @author Hemanshu
 * @date 2019-04-07
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public void executeArmyCalculation(int playerId){
         strategy.armyCalculationDuringReinforcement(playerId);
    }

    public void executeArmyPlacement(int playerId){
        strategy.armyPlacementDuringReinforcement(playerId);
    }

    public void executeExchangeCards(int playerId){
        strategy.exchangeCardsForArmies(playerId);
    }

    public void executePlayerAttack(int playerId){
        strategy.playerAttack(playerId);
    }

    public boolean executeFortificationPhase(int playerId){
        return strategy.fortificationPhase(playerId);
    }
}
