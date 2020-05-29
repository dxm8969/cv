package weatherstationg2;

import java.util.ArrayList;

/**
 * This class represents a Subject object in the Observer Pattern. It can be
 * subclassed to represent an object that the application wants to have
 * observed.
 *
 * A Subject object can have one or more observers. An observer may be any
 * object that implements the interface <tt>Observer</tt>. After a Subject
 * instance changes, the <code>Observable</code>'s <code>notify</code> method
 * causes all of its observers to be notified of the change by a call to their
 * <code>update</code> method.
 *
 * @author Kristina Marasovic <kristina.marasovic@rit.edu>
 */
public class Subject {

    private ArrayList<Observer> observers = new ArrayList();

    /**
     * Adds an observer to the set of observers for this object, provided that
     * it is not the same as some observer already in the set.
     *
     * @param o an observer to be added.
     * @throws NullPointerException if the parameter o is null.
     */
    public void attach(Observer o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    /**
     * Removes an observer from the set of observers of this object.
     *
     * @param o the observer to be deleted.
     */
    public void dettach(Observer o) {
        observers.remove(o);
    }

    /**
     * If this object's state has changed, then invoke this method to notify all
     * of its observers.
     *
     * Each observer has its <code>update</code> method called by this method.
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
