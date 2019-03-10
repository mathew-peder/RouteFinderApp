/*

This is a WIP and has multiple bugs and incomplete portions of code. Please see Main.java for a short assessment of the issues in this project
Bugs/Issues/Incomplete:
1. Path finding mostly works, however it doesn't print it to the Scenebuilder window in readable format
2. No other issues to note

 */

package sample;

import java.util.ArrayList;

/**
 * @author Mathew Peder
 * <p>
 * Applied Computing - 20073231
 */
public class Path
{

    private ArrayList<GraphNode> path;
    private int cost;

    //adds a new arraylist when path is called
    public Path(GraphNode start)
    {
        path = new ArrayList<>();
        path.add(start);
        this.setPath(path);
        this.setCost(0);
    }


    //method used to find the cost of the path taken as an int
    public int getCost()
    {
        return cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public ArrayList<GraphNode> getPath()
    {
        return path;
    }

    public void setPath(ArrayList<GraphNode> path)
    {
        this.path = path;
    }


    //toString for printing the data and size of the node and path taken
    @Override
    public String toString()
    {
        String str = "";
        for(int i = 0; i < getPath().size(); i+=1)
        {
            str = str + "->" + getPath().get(i).getData().Landmark.getLandmarkName.toString();
        }
        return str;
    }
}