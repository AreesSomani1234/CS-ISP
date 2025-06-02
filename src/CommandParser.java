import java.util.Map;

public class CommandParser {
    public boolean parse(String input, Player player, Map<String, Room> rooms) {
        String[] words = input.trim().toLowerCase().split("\\s+");
        if (words.length == 0) {
            System.out.println("Please enter a command.");
            return false;
        }

        String command = words[0];

        switch (command) {
            case "go":
                if (words.length < 2) {
                    System.out.println("Go where?");
                } else {
                    String direction = words[1];
                    Room currentRoom = rooms.get(player.getCurrentRoomId());
                    String nextRoomId = currentRoom.getExits().get(direction);
                    if (nextRoomId != null) {
                        Room nextRoom = rooms.get(nextRoomId);
                        // if (currentRoom.getId().equals("escape")) {
                        //     return true;
                        // }
                        if (currentRoom.getId().equals("U Area2") && nextRoom.getId().equals("escape")) {
                            int count = 0;
                            for (Item i : player.getInventory()) {
                                if (i instanceof RoomKey) {
                                    RoomKey rk = (RoomKey) i;
                                    int keyNum = rk.GetExitKeyNumber();
                                    if (keyNum == 2 || keyNum == 3 || keyNum == 4) {
                                        count++;
                                    }
                                }
                            }
                            if (count == 3) {
                                nextRoom.setLocked(false,nextRoom.type()); //  Unlock the escape room
                            }
                        }
                        if (nextRoom.isLocked()) {
                            boolean hasKey = false;
                            for (Item i : player.getInventory()) {
                                if (i instanceof RoomKey) {
                                    RoomKey rk = (RoomKey) i;
                                    if (rk.GetExitKeyNumber() == nextRoom.type()) {
                                        hasKey = true;
                                        break;
                                    }
                                }
                            }
                            if (!hasKey) {
                                System.out.println("The door is locked. You need a specific key to enter.");
                                break;
                            }
                        }
                        player.setCurrentRoomId(nextRoomId);
                        System.out.println("You move " + direction + ".");
                        currentRoom = rooms.get(player.getCurrentRoomId());
                        System.out.println(currentRoom.getLongDescription());
                        NPC npc = currentRoom.getNPC();
                        if (currentRoom.getNPC() != null && nextRoomId.equals(npc.getNPCRoomID())) {
                            npc.NPCAttack(player);
                        }

                    } else {
                        System.out.println("You can't go that way.");
                    }
                }
                return false;
            case "look":
                Room currentRoom = rooms.get(player.getCurrentRoomId());
                System.out.println(currentRoom.getLongDescription());
                return false;
            case "inventory":
                if (player.getInventory().isEmpty()) {
                    System.out.println("Your inventory is empty.");
                } else {
                    System.out.println("You are carrying:");
                    for (Item item : player.getInventory()) {
                        System.out.println("- " + item.getName());
                    }
                }
                return false;
            case "take":
                if (words.length < 2) {
                    System.out.println("Take what?");
                } else {
                    String itemName = words[1];
                    Room room = rooms.get(player.getCurrentRoomId());
                    Item itemToTake = null;
                    for (Item item : room.getItems()) {

                        if (item.getName().equalsIgnoreCase(itemName)) {
                            itemToTake = item;
                            break;
                        }
                    }
                    if (itemToTake != null) {
                        room.removeItem(itemToTake);
                        player.addItem(itemToTake);
                        System.out.println("You take the " + itemToTake.getName() + ".");
                    } else {
                        System.out.println("There is no " + itemName + " here.");
                    }
                }
                return false;
            case "drop":
                if (words.length < 2) {
                    System.out.println("Drop what?");
                } else {
                    String itemName = words[1];
                    Item itemToDrop = null;
                    for (Item item : player.getInventory()) {
                        if (item.getName().equalsIgnoreCase(itemName)) {
                            itemToDrop = item;
                            break;
                        }
                    }
                    if (itemToDrop != null) {
                        player.removeItem(itemToDrop);
                        Room room = rooms.get(player.getCurrentRoomId());
                        room.addItem(itemToDrop);
                        System.out.println("You drop the " + itemToDrop.getName() + ".");
                    } else {
                        System.out.println("You don't have a " + itemName + ".");
                    }
                }
                return false;
            case "help":
                System.out
                        .println("Available commands: go [direction], look, take [item], drop [item], inventory, help, quit, use, attack, health, strength, weight");
                return false;
            case "quit":
                System.out.println("good game");
                return true;

            case "use":
                if (words.length < 2) {
                    System.out.println("Use what?");
                } else {
                    String itemName = words[1];
                    Item itemToUse = null;
                    for (Item item : player.getInventory()) {
                        if (item.getName().equalsIgnoreCase(itemName)) {
                            itemToUse = item;
                            break;
                        }
                    }
                    if (itemToUse != null) {
                        if (itemToUse.getConsumable()) {
                            System.out.println("You consume the " + itemToUse.getName() + ".");
                            itemToUse.useItem(player);
                            player.removeItem(itemToUse);
                        } else if (itemToUse.getWeapon()) {
                            System.out.println("You equip the " + itemToUse.getName() + ".");
                            System.out.println("Your strength increases by " + itemToUse.getStrength() + " points.");
                            itemToUse.useItem(player);
                        } else {
                            System.out.println("You can't use the " + itemToUse.getName() + " that way.");
                            System.out.println(itemToUse.getDescription());
                        }
                    } else {
                        System.out.println("You don't have " + itemName + ".");
                    }
                }
                return false;

            case "attack":
                if (words.length < 2) {
                    System.out.println("Attack what?");
                } else {
                    String npcName = words[1];
                    Room currentRoom1 = rooms.get(player.getCurrentRoomId());
                    NPC targetNPC = currentRoom1.getNPC();
                    if (targetNPC != null && targetNPC.getNPCname().equalsIgnoreCase(npcName)) { // loop above not
                                                                                                 // needed, max 1 npc
                                                                                                 // per room
                        Item weapon = null;
                        for (Item item : player.getInventory()) {
                            if (item.getWeapon()) {
                                weapon = item;
                                item.useItem(player);
                                break;
                            }
                        }

                        if (weapon != null) {
                            player.PlayerAttack(targetNPC, weapon);
                        } else {
                            System.out.println("You have no weapon to attack with.");
                        }
                    } else {
                        System.out.println("There is no " + npcName + " here.");
                    }
                }
                return false;

            case "health": // gets health of player
                System.out.println("Your current Health is: " + player.getPlayerHealth());
                return false;

            case "strength": // gets strength of player
                System.out.println("Your current strength is: " + player.getPlayerStrength());
                return false;
                
            case "weight":
                System.out.println("You inventory weight is: " + player.inventoryWeight());
                return false;

            default:
                System.out.println("I don't understand that command.");
                return false;
        }
        return false; // idk
    }
}
