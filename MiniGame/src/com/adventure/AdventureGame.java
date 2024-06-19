package com.adventure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdventureGame {
    private static Map<Integer, Room> rooms = new HashMap<>();// Map of room IDs to Room objects
    private static Room currentRoom;// The room the player is currently in

    public static void main(String[] args) {
        loadRooms("data/rooms.txt");// Load room data from the file
        Scanner scanner = new Scanner(System.in);// Scanner for reading user input
        currentRoom = rooms.get(1);  // Start at the first room
        currentRoom.printRoomInfo();// Print initial room information

        while (true) {
            System.out.print("\nWhat would you like to do? ");
            String input = scanner.nextLine().trim().toLowerCase();// Read user input and convert to lowercase
            if (input.equals("x")) {
                System.out.println("Thank you for playing my Game.");// Exit the game
                break;
            } else if (input.equals("look")) {
                currentRoom.printRoomInfo(); // Print current room information
            } else {
                Integer nextRoomId = currentRoom.getExit(input);// Get the destination room for the given direction
                if (nextRoomId != null) {
                    currentRoom.setVisited(true); // Mark the current room as visited
                    currentRoom = rooms.get(nextRoomId); // Move to the next room
                    currentRoom.printRoomInfo();// Print the new room's information
                } else {
                    System.out.println("Invalid direction entered");// Handle invalid directions
                }
            }
        }

        scanner.close();
    }
//Loads the room data from a file into the internal data structure.
    private static void loadRooms(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("ID")) {
                    int id = Integer.parseInt(line.split("=")[1].trim());// Read room ID
                    String name = br.readLine().split("=")[1].trim();// Read room name
                    StringBuilder description = new StringBuilder();
                    while (!(line = br.readLine()).equals("exits")) {
                        description.append(line).append("\n"); // Read room description
                    }
                    Room room = new Room(id, name, description.toString().trim());// Create a new Room object
                    while ((line = br.readLine()) != null && !line.isEmpty()) {
                        String[] exitParts = line.split("=");
                        room.addExit(exitParts[0].trim(), Integer.parseInt(exitParts[1].trim()));// Add exits to the room
                    }
                    rooms.put(id, room);// Add the room to the map
                }
            }
        } catch (IOException e) {
            e.printStackTrace();// Handle exceptions
        }
    }
}

