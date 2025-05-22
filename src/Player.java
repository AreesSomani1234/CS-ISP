import java.util.ArrayList;
import java.util.List;

public class Player {
    private String currentRoomId;
    private List<Item> inventory;
    private int playerHealth;
    private int playerStrength;

    public Player(String startingRoomId) {
        this.currentRoomId = startingRoomId;
        this.inventory = new ArrayList<>();
        this.playerHealth = 100;
        this.playerStrength = 15;
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

    public void updatePlayerHealth(int healthUpdate){
        playerHealth += healthUpdate;
        System.out.println("Your health is: " + playerHealth);
    }
    public void updatePlayerStrength(int strengthUpdate){
        playerStrength += strengthUpdate;
    }
}