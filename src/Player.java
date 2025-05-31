import java.util.ArrayList;
import java.util.List;

public class Player {
    private String currentRoomId;
    private List<Item> inventory;
    private int playerHealth;
    private int playerStrength;
    private int keyCount;
    private final int playerMaxHealth = 100;
    private final int maxWeight = 40;

    private static final String ESCAPE_ROOM_ID = "escape";
    private static final int REQUIRED_EXIT_KEYS = 3;

    public Player(String startingRoomId) {
        this.currentRoomId = startingRoomId;
        this.inventory = new ArrayList<>();
        this.playerHealth = 100;
        this.playerStrength = 0;
    }

    public int getPlayerHealth() {
        return playerHealth;
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

    public void playerHit(int num) {
        playerHealth -= num;
    }

    public int GetKeyCount() {
        return keyCount;
    }

    public int inventoryWeight() { // -Arees-gets the weight of the inventory
        int weight = 0;
        List<Item> playerInventory = getInventory();
        for (Item i : playerInventory) {
            weight += i.getItemWeight();
        }
        return weight;

    }

    public void removeInventoryItem() { // if inventory has to much weight removes last item piked up./ need to implent
                                        // choose which items to drop
        List<Item> playerInventory = getInventory();
        if (inventoryWeight() > maxWeight) {
            for (int i = playerInventory.size() - 1; i >= 0; i--) {
                Item item = playerInventory.get(i);
                if (!(item instanceof RoomKey)) {
                    playerInventory.remove(i);
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

    {

    }

    public void PlayerAttack(NPC npc, Item item) {
        if (playerStrength > 0) {
            System.out.println("you are attacking the " + npc.getNPCname() + " with your weapon");
            npc.NPCDeath();
        }
    }

    public boolean CheckWin(Room currentRoom, NPC guardian) {
        if (!currentRoom.getId().equalsIgnoreCase(ESCAPE_ROOM_ID)) {
            return false;
        }
        if (guardian == null || !guardian.getNPCname().equalsIgnoreCase("guardian")) {
            return false;
        }
        if (keyCount == REQUIRED_EXIT_KEYS) {
            return true;
        }
        return false;
    }
}