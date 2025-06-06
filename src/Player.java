import java.util.ArrayList;
import java.util.List;

public class Player {
    private String currentRoomId;
    private List<Item> inventory;
    private int playerHealth;
    private int playerStrength;
    private int keyCount;
    private final int playerMaxHealth = 100;
    private final int maxWeight = 30;
    private int playerPoints;


    private static final String ESCAPE_ROOM_ID = "escape";
    private static final int REQUIRED_EXIT_KEYS = 3;

    public Player(String startingRoomId) {
        this.currentRoomId = startingRoomId;
        this.inventory = new ArrayList<>();
        this.playerHealth = 100;
        this.playerStrength = 0;
        this.playerPoints = 0;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }
    public int getPlayerStrength(){
        return playerStrength;
    }
    public int getPlayerPoints(){
        return playerPoints;
    }
    public void addPlayerPoints(int points){ // adds player points
        playerPoints += points;
    }

    public String getCurrentRoomId() {
        return currentRoomId;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void updatePlayerStrength(int val) { 
        playerStrength += val;
    }

    public void playerHit(int num) { //- Player gets hit
        playerHealth -= num;
    }

    public int GetKeyCount() {
        return keyCount;
    }

    public boolean canEnter(Room room) { // -Arees, checks if you can eneter a room,-> right keys
        if (!room.isLocked()) {
            return true;
        }
        for (Item i : inventory) {
            if (i instanceof RoomKey) {
                RoomKey key = (RoomKey) i;
                if (key.GetExitKeyNumber() == room.type()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int inventoryWeight() { // -Arees-gets the weight of the inventory
        int weight = 0;
        List<Item> playerInventory = getInventory();
        for (Item i : playerInventory) {
            weight += i.getItemWeight();
        }
        return weight;

    }

    public void removeWeightExceededItem() { // -Arees, if inventory has to much weight removes last item piked up.
        List<Item> playerInventory = getInventory();
        if (inventoryWeight() > maxWeight) {
            addPlayerPoints(-20); //-Arees if weight exceeded player loses 30 points
            for (int i = playerInventory.size() - 1; i >= 0; i--) {
                Item item = playerInventory.get(i);
                if (!(item instanceof RoomKey)) {
                    playerInventory.remove(i);
                    System.out.println("Weight exceeded! " + item.getName() + " was removed from your inventory.");
                    System.out.println("drop item to make room for new item.");
                    break; // breaks the loop so that more than 1 item isnt removed
                }
            }
        }

    }

    public void updatePlayerHealth(int healthUpdate) {
        playerHealth += healthUpdate;
        if (playerHealth >= playerMaxHealth) {
            playerHealth = playerMaxHealth;
        }

        System.out.println("Your health is: " + playerHealth);
    }

    public void setCurrentRoomId(String RoomId) {
        this.currentRoomId = RoomId;
    }

    public int playerExitKeyCount() { // return player exit key count-Arees
        int exitKeyCount = 0;
        List<Item> Playerinventory = getInventory();
        for (Item i : Playerinventory) {
            if (i instanceof RoomKey) {
                RoomKey rk = (RoomKey) i;
                int keyNum = rk.GetExitKeyNumber();
                if (keyNum == 2 || keyNum == 3 || keyNum == 4) {
                    exitKeyCount++;
                }
            }
        }
        return exitKeyCount;
    }

    public int playerStClairKeyCheck() { // return player StClair key count-Arees
        int STCKeyCount = 0;
        List<Item> Playerinventory = getInventory();
        for (Item i : Playerinventory) {
            if (i instanceof RoomKey) {
                RoomKey rk = (RoomKey) i;
                int keyNum = rk.GetExitKeyNumber();
                if (keyNum == 0) {
                    STCKeyCount++;
                }
            }
        }
        return STCKeyCount;
    }

    public int playerQueenCheck() { // return player queen key count-Arees
        int QueenKeyCount = 0;
        List<Item> Playerinventory = getInventory();
        for (Item i : Playerinventory) {
            if (i instanceof RoomKey) {
                RoomKey rk = (RoomKey) i;
                int keyNum = rk.GetExitKeyNumber();
                if (keyNum == 0) {
                    QueenKeyCount++;
                }
            }
        }
        return QueenKeyCount;
    }

    public void PlayerAttack(NPC npc, Item item) { //-Arees, allows player to attack NPC if they got weapon
        if (playerStrength > 0) {
            System.out.println("you are attacking the " + npc.getNPCname() + " with your weapon");
            addPlayerPoints(npc.getPointsGiven());
            npc.NPCDeath();
        }
    }

    // public boolean CheckWin(Room currentRoom, NPC guardian) {
    //     if (!currentRoom.getId().equalsIgnoreCase(ESCAPE_ROOM_ID)) {
    //         return false;
    //     }
    //     if (guardian == null || !guardian.getNPCname().equalsIgnoreCase("guardian")) {
    //         return false;
    //     }
    //     List<Item> playerInventory = getInventory();
    //     for (int i = playerInventory.size() - 1; i >= 0; i--) {
    //             Item item = playerInventory.get(i);
    //             if(item instanceof RoomKey){
    //                 RoomKey rk = (RoomKey) item; // downcast to access child methods
    //                 int keyNum = rk.GetExitKeyNumber();
    //                 if (keyNum == 1 || keyNum == 2 || keyNum == 3){
    //                     keyCount++;
    //                 }
    //             }
    //         }
    //     if (keyCount == REQUIRED_EXIT_KEYS) {
    //         return true;
    //     }
    //     return false;
    // }
}