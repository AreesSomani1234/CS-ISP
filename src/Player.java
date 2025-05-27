import java.util.ArrayList;
import java.util.List;

public class Player {
    private String currentRoomId;
    private List<Item> inventory;
    private int playerHealth;
    private int playerStrength;
    private int keyCount;
    final private int playerMaxHealth = 100;
    final private int maxWeight = 25;

    private static final String ESCAPE_ROOM_ID = "escape";
    private static final int REQUIRED_EXIT_KEYS = 3;

    public Player(String startingRoomId) {
        this.currentRoomId = startingRoomId;
        this.inventory = new ArrayList<>();
        this.playerHealth = 100;
        this.playerStrength = 0;
    }

    public int getPlayerHealth(){
        return playerHealth;
    }
    public String getCurrentRoomId() {
        return currentRoomId;
    }

    public void setCurrentRoomId(String roomId) {
        this.currentRoomId = roomId;
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
    public void updatePlayerStrength(int val){
        playerStrength += val;
    }
    public void GivePlayerKey()
    {
        System.out.println("You got a key");
        keyCount ++;
    }
    public int GetKeyCount()
    {
        return keyCount;
    }
    public int inventoryWeight(){ //-Arees-gets the weight of the inventory
        int weight = 0;
        List<Item> playerInventory = getInventory();
        for(Item i : playerInventory){
            weight += i.getItemWeight();
        }
        return weight;

    }
    public void removeInventoryItem(){ // if inventory has to much weight removes last item piked up./ need to implent choose which items to drop
        List<Item> playerInventory = getInventory();
        if(inventoryWeight() > maxWeight){
            playerInventory.remove(playerInventory.size()-1);
        }

    }

    public void updatePlayerHealth(int healthUpdate){
        playerHealth += healthUpdate;
        if(playerHealth >= playerMaxHealth)
        {
            playerHealth = playerMaxHealth;
        }
        
        System.out.println("Your health is: " + playerHealth);
    }
    public void PlayerAttack(NPC npc, Item item){
        if(playerStrength > 0){
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