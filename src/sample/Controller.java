/*

This is a WIP and has multiple bugs and incomplete portions of code. Please see Main.java for a short assessment of the issues in this project
Bugs/Issues/Incomplete:
1. Path finding mostly works, however it doesn't yet print it to the Scenebuilder window in readable format
2. User can add a landmark/road, but cannot add link to these yet
3. Djikstra's algorithm is attempted
4. Scroll action for the Route text field ha not been completed.
5. The connections between nodes can be printed, however they are not in human readable format and do not yet print to the route textfield
6. Route textfield is not yet implemented


 */

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Mathew Peder
 * <p>
 * Applied Computing - 20073231
 */

public class Controller
{

    private ArrayList<GraphNode> nodeCollection = new    ArrayList<GraphNode>();


    @FXML
    TextArea routeText;
    @FXML
    BorderPane borderPane;
    @FXML
    SplitPane splitPane;
    @FXML
    Button navigateButton;
    @FXML
    TextField startText;
    @FXML
    TextField finishText;
    @FXML
    ScrollBar routeScroll;
    @FXML
    TextField nameText;
    @FXML
    TextField idText;
    @FXML
    TextField typeText;
    @FXML
    TextField distanceText;
    @FXML
    Button addNewbutton;
    @FXML
    Button loadCsvbutton;



    private File save=new File("/home/mat/Downloads/Assignment2/src/sample/save.txt"); //finds the file path for the saved cv and saves it again

    //Method to load the CSV
    public void loadCSV(ActionEvent actionEvent)
    {


        try
        {
            FileInputStream fis = new FileInputStream  (save);
            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
            String str;

            do
                {
                str = br.readLine();
                if(str!=null)
                {
                    String[] tokens = str.split(",");
                    //System.out.println(str); //Print used to find a bug in the code. Keeping it here to use when adding other features

                    //searches through each type in the CSV for types called Landmark and adds thm to the graphnode
                    if(tokens[0].equalsIgnoreCase( "Landmark" ))
                    {
                        String type = tokens[0];
                        String id = tokens[1];
                        String name = tokens[2];

                        Landmark newLandmark = new Landmark(name, id, type);
                        GraphNode graphNode = new GraphNode( newLandmark );
                        nodeCollection.add( graphNode );


                    }

                    //searches through each type in the CSV for types called Road and adds them to the graphnode
                    else if(tokens[0].equalsIgnoreCase( "Road" ))
                    {
                        String type = tokens[0];
                        String id = tokens[1];
                        String name = tokens[2];
                        int distance = Integer.parseInt(tokens[3]);

                        Road newRoad = new Road(name, id, type, distance);
                        GraphNode graphNode = new GraphNode( newRoad );
                        nodeCollection.add( graphNode );
                    }

                    //searches through each type in the CSV for all other types (link) and adds them to the graphnode
                    else
                    {
                        String id = tokens[1];
                        GraphNode landmark = searchLandmarkid( id );


                        for(int i =2;i<tokens.length;i+=1)
                        {
                            GraphNode road = searchRoadid( tokens[i] );
                            landmark.getAdjList().add( road );
                            road.getAdjList().add( landmark );
                        }
                    }
                }

            }
            while(str!=null);

            br.close();


            //loops through the node colection to get the nodes and the data, then loops through the node collection adjointed list
            for(int i=0; i < nodeCollection.size(); i+=1 )
            {
                System.out.println(nodeCollection.get( i ).getData());

                for(int j=0; j < nodeCollection.size(); j+=1)
                {
                    System.out.println(nodeCollection.get( i ).getAdjList().get( j ));
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Searches the Graphnode for the ID corresponding to Landmarks and returns the data for landmarks
    public GraphNode searchLandmarkid(String id)
    {
        for(int i=0; i < nodeCollection.size(); i+=1 )
        {
            try
            {
                GraphNode<Landmark> landmark = nodeCollection.get( i );

                if (id.equalsIgnoreCase( landmark.getData().getId() ))
                {
                    System.out.println( "Found Location " + landmark.getData().getLandmarkName() );

                    return landmark;
                }
            }
            catch(Exception e)
            {
            }

        }
        System.out.println( "Landmark ID not found" );

        return null;
    }

    //Searches the Graphnode for the ID corresponding to Roads and returns the data for Roads
    public GraphNode searchRoadid(String id)
    {
        for(int i=0; i < nodeCollection.size(); i+=1 )
        {
            try
            {
                GraphNode<Road> road = nodeCollection.get( i );

                if (id.equalsIgnoreCase( road.getData().getId() ))
                {
                    System.out.println( "Found Location " + road.getData().getRoadName());

                    return road;
                }
            }
            catch(Exception e)
            {
            }

        }
        System.out.println( "Road ID not found" );

        return null;
    }



    //Retrieves the text in the textfields for start and finish, then runs the search methods for each before running the method for the pathfinding
    public void navigateTo(ActionEvent event)
    {
        startText.getText();
        finishText.getText();
        if(startText.getText() != null && finishText.getText() != null )
        {
            GraphNode start = searchStart();
            GraphNode finish = searchFinish();
            findPath(start, finish);
        }

    }


    //Search method for the text fields. Finds the names in the node collection then retrieves the data for the start field
    //The start and finish searches could have been combined into one method for simplicity,
    public GraphNode searchStart()
    {
        for(int i=0; i < nodeCollection.size(); i+=1 )
        {
            try
            {
                GraphNode<Landmark> landmark = nodeCollection.get( i );

                if (startText.getText().equalsIgnoreCase( landmark.getData().getLandmarkName() ))
                {
                    System.out.println( "Found Location " + landmark.getData().getLandmarkName() );

                    return landmark;
                }
            }
            catch(Exception e)
            {
            }

        }
        System.out.println( "Please enter a valid landmark name for start of route" );

        return null;
    }

    //Search method for the text fields. Finds the names in the node collection then retrieves the data for the finish field
    public GraphNode searchFinish()
    {
        for(int i=0; i < nodeCollection.size(); i+=1 )
        {
            try
            {


                GraphNode<Landmark> landmark = nodeCollection.get( i );

                if (finishText.getText().equalsIgnoreCase( landmark.getData().getLandmarkName() ))
                {
                    System.out.println( "Found Location " + landmark.getData().getLandmarkName() );

                    return landmark;
                }
            }
            catch(Exception e)
            {
            }

        }
        System.out.println( "Please enter a valid landmark name for end of route" );

        return null;
    }

    //Findpath method connecting start to finish and printing the resulting paths. Finds all paths from the start node and uses the clearDeadEndPaths to remove all paths not relevant to start and finish
    //Some outside sources such as class notes, W3Schools, and stackoverflow were needed as references and guides
    public Path findPath(GraphNode start, GraphNode finish)
    {
        if (start != null && finish != null)
        {
            Path first = new Path(start);
            ArrayList<Path> currentPaths = breathSearch(first);
            Path firstPath = isDest(finish,currentPaths);
            if(firstPath!=null)
            {
                return firstPath;
            }
            boolean found = false;
            while(!found)
            {
                currentPaths = clearDeadEndPaths(currentPaths);
                for(int j = 0; j < currentPaths.size(); j+=1)
                {
                    currentPaths.addAll(breathSearch(currentPaths.get(j)));
                }

                        //Used to make sure that the paths were found and shows what and all paths found, if any.
                       /* for(int i = 0; i < currentPaths.size(); i+=1)
                       {
                            String str = "";
                            for(int j = 0; j < currentPaths.get(i).getPath().size(); j+=1)
                            {
                                str = str +"----->" +currentPaths.get(i).getPath().get(j).getData().toString();
                            }
                            System.out.println(str);
                            System.out.println("-----------------------");
                        }
                        */
                Path correctPath = isDest(finish, currentPaths);
                if(correctPath!=null)
                {
                    return correctPath;
                }
            }
        }
        return null;
    }


    //Clears the paths that don't include the destination/finish node and prints "found" if it found the path. Then it returns the one that works into the current paths
    //Some outside sources such as class notes, W3Schools, and stackoverflow were needed as references and guides
    public ArrayList<Path> clearDeadEndPaths(ArrayList<Path> currentPaths)
    {
        for (int i = 0; i < currentPaths.size(); i += 1)
        {
            Path path = currentPaths.get(i);
            int max = path.getPath().size() - 1;
            if (path.getPath().get(max).getAdjList().size() == 1)
            {
                currentPaths.remove(i);
            }
        }
        return currentPaths;
    }

    //finds the finish node and uses the path that connects the dest node to the start node, adding it to the currentpaths. If it's not, the it returns it as null so it won't be used.
    //Some outside sources such as class notes, W3Schools, and stackoverflow were needed as references and guides
    public Path isDest(GraphNode finish, ArrayList<Path> currentPaths)
    {
        for(int i = 0; i < currentPaths.size(); i+=1)
        {
            Path path = currentPaths.get(i);
            int max = path.getPath().size()-1;
            if(path.getPath().get(max).equals(finish))
            {
                System.out.println("Your Route: " + path.toString()); //Prints the path found using a toString in the Path.java class
                return path;
            }
        }
        return null;
    }



    //Breadth first search through the nodes in order to find the shortest path through the nodes. Uses the currentPath found to find the shortest path from start to finish by finding the "cost" of each path travelled
    //Some outside sources such as class notes, W3Schools, and stackoverflow were needed as references and guides
    public ArrayList<Path> breathSearch(Path currentPath)
    {
        ArrayList<Path> newPaths = new ArrayList<>();
        int max = currentPath.getPath().size()-1;
        GraphNode currentNode = currentPath.getPath().get(max);
        ArrayList<GraphNode> connections = currentNode.getAdjList(); //calls the currentNode in the adjList connections and adds each connection to the path travelled using a for loop to loop through each node after the previous
        for(int i =0; i <  connections.size(); i+=1)   //creates a new path in the list and checks each node to see if it is on the correct path, if not, it moves to the next
        {
            Path newPath = new Path(null);
            ArrayList<GraphNode> startingList = (ArrayList<GraphNode>) currentPath.getPath().clone();
            newPath.setPath(startingList);
            GraphNode road = connections.get(i);
            if (!isTraveled(road, newPath)) //uses a boolean to determine if the road and path traveled is part of the new path found, then adds the road to the path in the array list
            {
                newPath.getPath().add(road);
                if (road.getData() instanceof Road)
                    newPath.setCost(newPath.getCost() + (((Road) road.getData()).getDistance()));
                ArrayList<GraphNode> roadList = road.getAdjList();
                GraphNode nextNode = null;
                for (int j = 0; j < road.getAdjList().size(); j += 1)
                {
                    if (!road.getAdjList().get(j).equals(currentNode))
                    {
                        nextNode = roadList.get(j);
                    }
                }
                if (nextNode != null) // makes sure that if the next node isn't empty, the loop continues until there isn't a next node to move through
                    newPath.getPath().add(nextNode);
                newPaths.add(newPath);
            }
        }
        //System.out.print("Your Route: " + newPaths + "\n" ); //used to determine if all paths are found
        return newPaths;
    }


    //Boolean to determine if the road found is on the path
    public boolean isTraveled(GraphNode road, Path newPath)
    {
        for(int i = 0; i < newPath.getPath().size(); i +=1)
        {
            if(road.equals(newPath.getPath().get(i))){
                return true;
            }
        }
        return false;
    }



    //Scroll for the text printed to the route information. Not completed yet
    public void scrollRoute(MouseEvent mouseEvent)
    {

    }


    //Method for the button to add information about a road or landmark. Type is what determines how its saved and what fields to use.
    public void addNew(ActionEvent actionEvent)
    {
        nameText.getText();
        idText.getText();
        typeText.getText();
        distanceText.getText();



        //Leaves out the distance field if the user types in "Landmark" as the type
        if(nameText.getText() != null && idText.getText() != null && typeText.getText() != null)
        {
            if(typeText.getText().equalsIgnoreCase( "Landmark" ))
            {
                Landmark newLandmark = new Landmark(nameText.getText(), idText.getText(), typeText.getText());
                GraphNode graphNode = new GraphNode( newLandmark );
                nodeCollection.add( graphNode );
                saveTocsv();

            }

            //if the distance field is used and the type is "road" then it knows to save as a road
            else if (distanceText.getText() != null && typeText.getText().equalsIgnoreCase( "Road"))
            {
                int distance = Integer.parseInt(distanceText.getText());
                Road newRoad = new Road(nameText.getText(), idText.getText(), typeText.getText(), distance);
                GraphNode graphNode = new GraphNode( newRoad );
                nodeCollection.add( graphNode ); //adds to the collection

                saveTocsv(); //calls the save method to save the info to the csv file
            }
        }
        for(int i=0; i < nodeCollection.size(); i+=1 )
        {
            System.out.println(nodeCollection.get( i )); //prints the information from the csv file to confirm the new information added
        }

    }

    //Saves the information to a CSV file
    public void saveTocsv()
    {
        try
        {
            FileWriter fw = new FileWriter(save.getAbsoluteFile(), true); //calls the new filewriter
            String str = "\n" + typeText.getText() +","+ idText.getText() +","+ nameText.getText(); //adds the input information as a string on a new line
            if(typeText.getText().equalsIgnoreCase( "Road" )) //if the type road is used, then it retrieves distance as well
            {
             str = str +","+  distanceText.getText();
            }
            fw.append( str );
            fw.close();
        }
        catch(Exception e)
        {
            System.out.println( e );
        }

    }



    //My attempts at converting the Djikstras algorithm from in-class notes.
//    public static void traverseGraphDepthFirst(GraphNode<?> from, List<GraphNode<?>> encountered ){
//        System.out.println(from.data);
//        if(encountered==null) encountered=new ArrayList<>(); //First node so create new (empty) encountered list
//        encountered.add(from);
//        for(GraphNode<?> adjNode : from.adjList)
//            if(!encountered.contains(adjNode)) traverseGraphDepthFirst(adjNode, encountered );


//    public static void traverseGraphBreadthFirst(List<GraphNode<?>> agenda, List<GraphNode<?>> encountered ){
//        if(agenda.isEmpty()) return;
//        GraphNode<?> next=agenda.remove(0);
//        System.out.println(next.data);
//        if(encountered==null) encountered=new ArrayList<>();
//        encountered.add(next);
//        for(GraphNode<?> adjNode : next.getAdjList())
//            if(!encountered.contains(adjNode) && !agenda.contains(adjNode)) agenda.add(adjNode);
//        traverseGraphBreadthFirst(agenda, encountered ); //Tail call
//    }
}
