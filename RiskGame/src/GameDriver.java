import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Hemanshu
 * @date 2019-02-20
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
            CreateMap();
        } else if (localvariable == 2) {
//            LoadMap();
        }else{
            System.out.println("Please enter Relevant option");
        }
    }



    public static void CreateMap(){
        boolean exit= true;
        while(exit) {
            System.out.println("1. Create Continent");
            System.out.println("2. Create Country");
            System.out.println("3. Add Neighbour");
            System.out.println("4. Exit");
            System.out.println("Enter the task you want to perform :");
            Scanner in = new Scanner(System.in);
            int val = in.nextInt();
            switch(val){
                case 1: CreateMap.createContinent();
                    break;
                case 2: CreateMap.createCountry();
                    break;
                case 3:
                    GraphNew.printGraph();
                    GraphNew.addNeighbour();
                    GraphNew.printGraph();
                    break;
                case 4:
                    exit = false;
                    break;
            }

        }
    }



    


    public static void main(String[] args) {
        InitialisePlayers();
        StartOrLoadGame();
    }
}
