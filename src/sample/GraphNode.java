/*

This is a WIP and has multiple bugs and incomplete portions of code. Please see Main.java for a short assessment of the issues in this project
Bugs/Issues/Incomplete:
1. This portion was implemented using the in-class notes given as a reference.
2. Djikstra's algorithm is not implemented, but was attempted as can be seen in the Controller.java class

 */

package sample;

import java.util.ArrayList;

/**
 * @author Mathew Peder
 * <p>
 * Applied Computing - 20073231
 */

//Graphnode class implemented using class notes as reference.
public class GraphNode<Loc>
{
    public Loc data;
    public ArrayList<Loc> Location;


    public GraphNode(Loc data)
    {
        this.setAdjList( new ArrayList<>() );
        this.setData( data );
    }

    public ArrayList<Loc> getAdjList()
    {
        return Location;
    }

    public void setAdjList(ArrayList<Loc> adjList)
    {
        this.Location = adjList;
    }


    public Loc getData()
    {
        return data;
    }

    public void setData(Loc data)
    {
        this.data = data;
    }

}