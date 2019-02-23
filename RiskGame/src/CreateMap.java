import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Vikram
 * @date 2019-02-20
 */
public class CreateMap {

}


/*
* Creation of Adjacency Matrix
* */
class Graph{
    int [][] countryMatrix;
    int size;

    Graph(int size){
        this.size = size;
        countryMatrix = new int[size][size];
        for(int[] single : countryMatrix)
            Arrays.fill(single, 0);
    }

    public void add(int source, int destination){
        countryMatrix[source][destination] = 1;
        countryMatrix[destination][source] = 1;
    }



    public ArrayList<Integer> findAdjacent(int index){
        ArrayList<Integer> adjacent = new ArrayList<Integer>();
        for(int i=1;i<size;i++){
            if(countryMatrix[index][i] == 1)
                adjacent.add(i);
        }
        return adjacent;
    }

    public boolean isConnected(int source, int destination){
        if(countryMatrix[source][destination] == 1 || countryMatrix[destination][source] == 1)
            return true;
        return false;
    }
}
