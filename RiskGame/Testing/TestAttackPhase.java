
import Controller.AttackPhase;
import Controller.GameController;
import Controller.ReadMap;
import Controller.ValidateMap;
import Model.GameModel;
import Model.Player;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static Controller.AttackPhase.AutoRollDice;
import static Controller.AttackPhase.RollDice;
import static Model.GameModel.playerHashMap;
import static org.junit.Assert.assertEquals;


public class TestAttackPhase {
    static int[] val;



    @BeforeClass
    public static void BeforeClass()
    {

        //AttackPhase.PlayerAttackTurn(0);
       val = AutoRollDice("ind",40,"pak",2);

    }

    /*
     * This method runs after all test cases were ran

     */
    @AfterClass
    public static void AfterClass() {
        System.out.println("Left TestAttackPhase  Class");
    }

    @Test
    public void TestCase1()
    {
   assertEquals(2,val.length);


    }

   @Test
    public void TestCase2()
   {
   assertEquals(0,val[1]);
   }
}


