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

//Superclass for landmark and road classes. Creates ID and types for both classes as strings
public class Location
{

    private String id;
    private String type;


    public Location(String id, String type)
    {
        setId( id );
        setType( type );
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }







}
