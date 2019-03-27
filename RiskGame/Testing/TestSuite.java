import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({TestHelper.class,TestReinforcementArmies.class,
               TestContinent.class,TestAttackPhase.class,TestPlayer.class,TestStartupPhase.class,TestFortification.class,
               TestCountry.class,TestMapFile.class,TestValidateMap.class})
public class TestSuite {


}


