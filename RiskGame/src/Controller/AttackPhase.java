package Controller;
import Model.GameModel;
import Model.Player;
import Model.ViewObserver;

import java.util.*;

/**
 * Class Attack Phase
 * @author Hemanshu
 */
public class AttackPhase {

    /**
     * This method indicates the turn of the Player to attack
     * @param playerId, int
     *              id of the player
     */
    public static void PlayerAttackTurn(int playerId) {
        Player playerView=GameModel.playerHashMap.get(playerId);
        playerView.GamePhase="Attack Phase";
        ViewObserver VOb=new ViewObserver();
        playerView.addObserver(VOb);
        playerView.updatingObserver();
        String infoOnActions= "";
        int playGame = 1;
        while (playGame != -1) {
            List<String> countriesOwned = GameModel.playerHashMap.get(playerId).getCountriesOwned();
            HashMap<String, Integer> countriesThatCanAttack = new HashMap<String, Integer>();
            List<String> countriesTemp = new ArrayList<>();
            boolean addCountryToOwn = false;
            HashMap<String, Integer> allCountriesAttackTo = new HashMap<String, Integer>();
            HashMap<String, Integer> countriesAttackTo = new HashMap<String, Integer>();
            for (String Country : countriesOwned) {
                addCountryToOwn = false;
                if (GameModel.countryHashMap.get(Country).getNumberOfSoldiers() > 1) {
                    countriesTemp = GameModel.countryHashMap.get(Country).getAdjacentCountries();
                    for (String countryName : countriesTemp) {
                        if (GameModel.countryHashMap.get(countryName).PlayerId != playerId) {
                            addCountryToOwn = true;
                            allCountriesAttackTo.put(countryName, GameModel.countryHashMap.get(countryName).getNumberOfSoldiers());
                        }
                    }
                }
                if (addCountryToOwn) {
                    countriesThatCanAttack.put(Country, GameModel.countryHashMap.get(Country).getNumberOfSoldiers());
                }
            }

            System.out.println("Counties That Can Attack");
            for (String country : countriesThatCanAttack.keySet()) {
                System.out.println(country + "->" + countriesThatCanAttack.get(country));
            }
            if (countriesThatCanAttack.size() != 0) {
                System.out.println("Enter the Full Name of The country :");
                Scanner sc = new Scanner(System.in);
                String from = sc.nextLine();
                countriesAttackTo = new HashMap<>();
                countriesTemp = GameModel.countryHashMap.get(from).getAdjacentCountries();
                for (String countryName : countriesTemp) {
                    if (GameModel.countryHashMap.get(countryName).PlayerId != playerId) {
                        countriesAttackTo.put(countryName, GameModel.countryHashMap.get(countryName).getNumberOfSoldiers());
                    }
                }
                System.out.println("Counties Attack to");
                for (String country : countriesAttackTo.keySet()) {
                    System.out.println(country + "->" + allCountriesAttackTo.get(country));
                }
                /*if this list is empty go to next Stage and give correct error*/

                System.out.println("Enter the Full Name of The country :");
                String to = sc.nextLine();
                System.out.println("Number of Army to use (Leave Atleast One Army behind):");
                int fromArmy = sc.nextInt();
                infoOnActions = "Player "+GameModel.playerHashMap.get(playerId).getName()+" attacks using country "+ from+" to "+ to+" using "+ fromArmy+" armies !" ;
                int toArmy = countriesAttackTo.get(to);
                playerView.infoAboutAction=infoOnActions;
                playerView.addObserver(VOb);
                playerView.updatingObserver();
                System.out.println("1.To roll Dice one after the other ");
                System.out.println("2.To Auto Roll and get the output");
                System.out.println("Enter Digit :");
                int type = sc.nextInt();
                int[] remainingArmy = new int[2];
                if (type == 1) {
                    remainingArmy = RollDice(from, fromArmy, to, toArmy);
                } else {
                    remainingArmy = AutoRollDice(from, fromArmy, to, toArmy);
                }
                int x = GameModel.countryHashMap.get(from).getNumberOfSoldiers();
                if (remainingArmy[0] == 0) {
                    System.out.println("Attacking Country " + from + " lost all its armies");
                    GameModel.countryHashMap.get(from).setNumberOfSoldiers(x - fromArmy);
                    GameModel.countryHashMap.get(to).setNumberOfSoldiers(remainingArmy[1]);
                } else {
                    System.out.println("Attacking Country " + from + " won " + to + " country");
                    System.out.println("Remaining Armies :" + fromArmy);
                    System.out.println("How many armies do you want to leave in " + to + ":");
                    int leave = sc.nextInt();
                    GameModel.countryHashMap.get(to).setNumberOfSoldiers(leave);
                    GameModel.countryHashMap.get(from).setNumberOfSoldiers((x - fromArmy) + (remainingArmy[0] - leave));
                    /*Updating modals for players */
                    int defeatedPlayerId = GameModel.countryHashMap.get(to).getPlayerId();
                    int wonPlayerId = playerId;
                    Player defeated = GameModel.playerHashMap.get(defeatedPlayerId);
                    defeated.countriesOwned.remove(to);
                    Player won = GameModel.playerHashMap.get(wonPlayerId);
                    won.countriesOwned.add(to);
                    GameModel.countryHashMap.get(to).setPlayerId(wonPlayerId);
                    GameModel.playerHashMap.get(wonPlayerId).setShouldGetTheCard(true);
                }

                System.out.println("Do you want too attack (Enter -1 to discontinue):");
                playGame = sc.nextInt();
            } else {
                System.out.println("You do not have any country which is eligible to attack ! Going to next step..");
            }
        }

    }

    /**
     * This method performs Dice Roll
     * @param good , String
     * @param goodArmy, int
     * @param Bad, String
     * @param badArmy, int
     * @return int[], integer type array
     */
    public static int[] RollDice(String good, int goodArmy, String Bad, int badArmy) {
        Scanner sc = new Scanner(System.in);
        while (goodArmy >= 1 && badArmy >= 1) {
            int goodDice = 0;
            int badDice = 0;
            if (goodArmy > 4) {
                System.out.println("Number of Dice to use (1 to 3):");
                goodDice = sc.nextInt();
            } else if (goodArmy == 3) {
                System.out.println("Number of Dice to use (1 or 2):");
                goodDice = sc.nextInt();
            } else if (goodArmy == 1 || goodArmy == 2) {
                System.out.println("Using 1 Dice:");
                goodDice = 1;
            }

            if (badArmy > 3) {
                System.out.println("Using 2 Dice for Opponent");
                badDice = 2;
            } else if (badArmy == 1 || badArmy == 2) {
                System.out.println("Using 1 Dice:");
                badDice = 1;
            }


            Random rand = new Random();
            Integer[] goodArmyRolls = new Integer[3];
            Integer[] badArmyRolls = new Integer[2];
            for (int i = 0; i < 3; i++) {
                goodArmyRolls[i] = 0;
            }
            for (int i = 0; i < 2; i++) {
                badArmyRolls[i] = 0;
            }
            System.out.println("Attacker Dice :");
            for (int i = 0; i < goodDice; i++) {
                int a = rand.nextInt(6) + 1;
                goodArmyRolls[i] = a;
                System.out.println(a);

            }

            System.out.println("Defender Dice :");
            for (int i = 0; i < badDice; i++) {
                int b = rand.nextInt(6) + 1;
                badArmyRolls[i] = b;
                System.out.println(b);
            }
            Arrays.sort(goodArmyRolls, Collections.reverseOrder());
            Arrays.sort(badArmyRolls, Collections.reverseOrder());

            if (badDice == 1) {
                if (badArmyRolls[0] >= goodArmyRolls[0]) {
                    goodArmy--;
                } else {
                    badArmy--;
                }
            } else if (badDice == 2 && goodDice == 1) {
                if (badArmyRolls[0] >= goodArmyRolls[0]) {
                    goodArmy--;
                } else {
                    badArmy--;
                }
            } else {
                if (badArmyRolls[0] >= goodArmyRolls[0]) {
                    goodArmy--;
                } else {
                    badArmy--;
                }

                if (badArmyRolls[1] >= goodArmyRolls[1]) {
                    goodArmy--;
                } else {
                    badArmy--;
                }
            }
            System.out.println("Army count of Attacker after Battle :" + goodArmy);
            System.out.println("Army count of Defender after Battle :" + badArmy);
        }
        int[] val = {goodArmy, badArmy};
        return val;
    }

    /**
     * This method is used to perform auto roll of the Dice
     * @param good , String
     * @param goodArmy, int
     * @param Bad, String
     * @param badArmy, int
     * @return int[], integer type array
     */
    public static int[] AutoRollDice(String good, int goodArmy, String Bad, int badArmy) {
        while (goodArmy >= 1 && badArmy >= 1) {
            int goodDice = 0;
            int badDice = 0;
            if (goodArmy > 4) {
                System.out.println("Using 3 Dice for Attacker:");
                goodDice = 3;
            } else if (goodArmy == 3) {
                System.out.println("Using 2 Dice for Attacker:");
                goodDice = 2;
            } else if (goodArmy == 1 || goodArmy == 2) {
                System.out.println("Using 1 Dice for Attacker:");
                goodDice = 1;
            }

            if (badArmy > 3) {
                System.out.println("Using 2 Dice for Opponent");
                badDice = 2;
            } else if (badArmy == 1 || badArmy == 2) {
                System.out.println("Using 1 Dice:");
                badDice = 1;
            }


            Random rand = new Random();
            Integer[] goodArmyRolls = new Integer[3];
            Integer[] badArmyRolls = new Integer[2];
            for (int i = 0; i < 3; i++) {
                goodArmyRolls[i] = 0;
            }
            for (int i = 0; i < 2; i++) {
                badArmyRolls[i] = 0;
            }
            System.out.println("Attacker Dice :");
            for (int i = 0; i < goodDice; i++) {
                int a = rand.nextInt(6) + 1;
                goodArmyRolls[i] = a;
                System.out.println(a);

            }

            System.out.println("Defender Dice :");
            for (int i = 0; i < badDice; i++) {
                int b = rand.nextInt(6) + 1;
                badArmyRolls[i] = b;
                System.out.println(b);
            }
            Arrays.sort(goodArmyRolls, Collections.reverseOrder());
            Arrays.sort(badArmyRolls, Collections.reverseOrder());

            if (badDice == 1) {
                if (badArmyRolls[0] >= goodArmyRolls[0]) {
                    goodArmy--;
                } else {
                    badArmy--;
                }
            } else if (badDice == 2 && goodDice == 1) {
                if (badArmyRolls[0] >= goodArmyRolls[0]) {
                    goodArmy--;
                } else {
                    badArmy--;
                }
            } else {
                if (badArmyRolls[0] >= goodArmyRolls[0]) {
                    goodArmy--;
                } else {
                    badArmy--;
                }

                if (badArmyRolls[1] >= goodArmyRolls[1]) {
                    goodArmy--;
                } else {
                    badArmy--;
                }
            }
            System.out.println("Army count of Attacker after Battle :" + goodArmy);
            System.out.println("Army count of Defender after Battle :" + badArmy);
        }
        int[] val = {goodArmy, badArmy};
        return val;
    }

    public static void main(String[] args) {
        RollDice("yahoo", 10, "wow", 7);
    }


}
