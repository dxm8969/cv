//imports

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Savepoint;

/*
    Class Restaurant
 */
public class Restaurant {
    //attributes
    private int id;
    private String name;
    private String about;
    private String imageUrl;
    private int type;
    private String website;
    private Location location;


    //Default constructor
    public Restaurant() {
        this.id = 0;
        this.name = null;
        this.about = null;
        this.imageUrl = null;
        this.type = 0;
        this.website = null;
        this.location = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return about;
    }

    public void setDescription(String description) {
        this.about = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /*
            Method that sets the data from results to attributes
         */
    public void sendResultsData(ArrayList<String> row) throws DLException {
        try {
            this.id = Integer.parseInt(row.get(0));
            this.name = row.get(1);
            this.about = row.get(2);
            this.website = row.get(3);
            this.imageUrl = row.get(4);
            this.type = Integer.parseInt(row.get(5));
            this.location = new Location(Double.parseDouble(row.get(6)),Double.parseDouble(row.get(7)));

        } catch (IndexOutOfBoundsException e) {
            Map<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("Description: ", "Index of the ArrayList is out of bounds");
            hashMap.put("Localized message: ", "" + e.getLocalizedMessage());
            throw new DLException(e, hashMap);
        }
    }

    public boolean fetch(MySQLDatabase database) throws DLException {
        String selectSQL = "SELECT * FROM restaurant WHERE restaurant.id = " + this.id;
        ArrayList<ArrayList<String>> fetchData = database.getData(selectSQL);
        if (fetchData == null) {
            return false;
        } else {
            this.sendResultsData(fetchData.get(0));
            return true;
        }
    }

    /*
        Put method - Updates the database values, for that object’s equipmentId, using the object’s attribute values and setData method
     */
    public boolean put(MySQLDatabase database) throws DLException {
        String selectSQL = "";
        return database.setData(selectSQL);
    }

    /*
        Post method - Inserts the object's attribute values into the database as a new record using setData method
     */
    public boolean post(MySQLDatabase database) throws DLException {
        String selectSQL = "";
        return database.setData(selectSQL);
    }

    /*
        Delete method - deletes from the database any data row corresponding to the object's equipmentId using setData method
     */
    public boolean delete(MySQLDatabase database) throws DLException {
        String selectSQL = "";
        return database.setData(selectSQL);
    }


}
