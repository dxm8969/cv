package weatherstationg2;



/**
 * An <code>Observer</code> interface to be implemented by a class that wants to
 * be informed of changes in observable objects.
 *
 * @author Kristina Marasovic <kristina.marasovic@rit.edu>
 */
public interface Observer {

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's observers
     * notified of the change by invoking this method.
     */
    void update();
}
