package com.adventure;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private int id;//Unique identifier for the room
    private String name;//Name of the room
    private String description;//Description of the room
    private boolean visited;//Flag indicating whether the room has been visited
    private Map<String, Integer> exits;//Map of exits from the room (direction -> destination room ID)

    public Room(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.visited = false;//Initially, the room is not visited
        this.exits = new HashMap<>();//Initialize the map of exits
    }
    //Getter methods for room properties
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    // Adds an exit to the room direction the direction of the exit
    //destinationRoomID the ID of the room that the exit leads to
    public void addExit(String direction, int destinationRoomId) {
        exits.put(direction.toLowerCase(), destinationRoomId);
    }
 //Gets the destination room ID for a given direction.
 //direction The direction of the exit
 //The ID of the destination room, or null if no exit exists in that direction

    public Integer getExit(String direction) {
        return exits.get(direction.toLowerCase());// Retrieve the exit
    }
//Prints the information about the room, including its name, description, and available exits.
    public void printRoomInfo() {
        System.out.println(name + (visited ? " (Visited)" : " (Not visited)"));
        System.out.println(description);//Print room description
        System.out.print("Exits: ");
        for (String direction : exits.keySet()) { //Print available exits
            System.out.print(direction.toUpperCase() + " ");
        }
        System.out.println();
    }
}
