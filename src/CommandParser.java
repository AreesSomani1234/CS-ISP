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
                        player.setCurrentRoomId(nextRoomId);
                        System.out.println("You move " + direction + ".");
                        currentRoom = rooms.get(player.getCurrentRoomId());
                        System.out.println(currentRoom.getLongDescription());

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
                System.out.println("Available commands: go [direction], look, take [item], drop [item], inventory, help");
                return false;
            case "quit":
                System.out.println("good game");
                return true;

case "use":
    if (words.length < 2) {
        System.out.println("Use what?");
    } 
    else {
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

        // for (NPC npc : currentRoom1.getNPC()) {
        //     if (npc.getNPCname().equalsIgnoreCase(npcName)) {
        //         targetNPC = npc;
        //         break;
        //     }
        // }

        if (targetNPC != null && targetNPC.getNPCname().equalsIgnoreCase(npcName)) { //loop above not needed, max 1 npc per room
            Item weapon = null;
            for (Item item : player.getInventory()) {
                if (item.getWeapon()) {
                    weapon = item;
                    break;
                }
            }

            if (weapon != null) {
                player.PlayerAttack(targetNPC, weapon);
                targetNPC.NPCDeath();
            } else {
                System.out.println("You have no weapon to attack with.");
            }
        } else {
            System.out.println("There is no " + npcName + " here.");
        }
    }
    return false;


            default:
                System.out.println("I don't understand that command.");
                return false;
        }
    }
}
