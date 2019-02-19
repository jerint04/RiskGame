import java.util.ArrayList;
import java.util.List;

public class Continent {
	String continentName;
	int controlValue;
	List<String> Countries= new ArrayList<>();
	List<String> Continents= new ArrayList<>();
	
	public Continent(String name , int point) {
		this.continentName = name;
		this.controlValue = point;
	}
	
	public void InsertCountry(String countryName) {
		this.Countries.add(countryName);
	}

	public void RemoveCountry(String countryName){
		this.Countries.remove(new String("countryName"));
	}
	
	
	
	

}
