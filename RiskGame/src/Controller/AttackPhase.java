package Controller;

import Model.GameModel;
import Model.Player;
import Model.ViewObserver;

import java.util.*;

/**
 * Class Attack Phase
 *
 * @author Hemanshu
 */
public class AttackPhase {


    /**
     * This method performs Dice Roll
     *
     * @param good      , String
     * @param goodArmy, int
     * @param Bad,      String
     * @param badArmy,  int
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
     *
     * @param good      , String
     * @param goodArmy, int
     * @param Bad,      String
     * @param badArmy,  int
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

//    public static void main(String[] args) {
//        RollDice("yahoo", 10, "wow", 7);
//    }


}
