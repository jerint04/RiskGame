import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Hemanshu
 * @date 2019-02-22
 */
public class GameDriver {
    static int playerNumber;
    static ArrayList<Player> PlayerList = new ArrayList<Player>();


    public static void InitialisePlayers(){
        System.out.println("Number of Players who want to play :");
        Scanner input = new Scanner(System.in);
        playerNumber = input.nextInt();


        for(int i = 0; i< playerNumber; i++){
            System.out.println("Player "+(i+1)+" name :");
            String name = input.next();
            PlayerList.add(new Player(name));
        }


    }

    public static void StartOrLoadGame(){
        System.out.println("Select 1 to Start the game and create map, Select 2 to Load the Game :");
        int localvariable;
        Scanner input1 = new Scanner(System.in);
        localvariable = input1.nextInt();
        if(localvariable == 1){
//            CreateMap();
        } else if (localvariable == 2) {
//            LoadMap();
        }else{
            System.out.println("Please enter Relevant option");
        }
    }






    


    public static void main(String[] args) {
        InitialisePlayers();
        StartOrLoadGame();
    }
}
