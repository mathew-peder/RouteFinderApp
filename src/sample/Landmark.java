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

//Sets the information for the Landmark objects and adds the name, ID, and type
public class Landmark extends Location
{

    private String landmarkName;

    public Landmark(String landmarkname, String id, String type)
    {
        super(id, type);
        setLandmarkName( landmarkname );
    }


    public String getLandmarkName()
    {
        return landmarkName;
    }


    public void setLandmarkName(String landmarkName)
    {
        this.landmarkName = landmarkName;
    }
}
