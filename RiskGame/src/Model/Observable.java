package Model;

/**
 * Observable interface
 *
 * @author Jerin
 * @version 1.0.0
 */
public interface Observable {

    public void registerObserver(Observer observer);
    public void notifyObservers();
    public void removeObserver(Observer observer);
}
