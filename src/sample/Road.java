/*

This is a WIP and has multiple bugs and incomplete portions of code. Please see Main.java for a short assessment of the issues in this project
Bugs/Issues/Incomplete:
1. Works as intended. No issues to note

 */

package sample;

/**
 * @author Mathew Peder
 * <p>
 * Applied Computing - 20073231
 */

//Sets the information for the Road objects and adds the name, ID, type and distance
public class Road extends Location
{

    private String roadName;
    private int distance;

    public Road(String roadName, String id, String type, int distance)
    {
        super(id, type);
        setRoadName( roadName );
        setDistance( distance );
    }


    public String getRoadName()
    {
        return roadName;
    }


    public void setRoadName(String roadname)
    {
        this.roadName = roadname;
    }

    public int getDistance()
    {
        return distance;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }
}
