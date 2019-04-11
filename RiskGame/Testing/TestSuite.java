import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({TestHelper.class,TestReinforcementArmies.class, TestMapFile2.class,
               TestContinent.class,TestAttackPhase.class,TestPlayer.class,TestStartupPhase.class,TestFortification.class,
               TestCountry.class,TestMapFile.class,TestValidateMap.class,TestTournamentMode.class,TestSaveGame.class,TestSaveGame.class})
public class TestSuite {


}


