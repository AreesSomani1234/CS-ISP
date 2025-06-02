import java.util.List;
import java.util.Map;
//talk to teacher
public class Room {
    private String id;
    private String name;
    private String description;
    private Map<String, String> exits; // direction → roomId
    private List<Item> items;
    private NPC npc;
    private boolean islocked; // room needs key to go in
    private int type; //type of key needed


    public Room(String id, String name, String description, Map<String, String> exits, List<Item> items, NPC npc, boolean locked, int type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exits = exits;
        this.items = items;
        this.npc = npc;
        this.islocked = locked;
        this.type = type;
    }

    public String getId() {
        return id;
    }
    public boolean isLocked(){ //-Arees, checks if room isLocked
        return islocked;
    }
    public int type(){ //-Arees, keytype for locked room
        return type;
    }
    
    public void setLocked(boolean locked, int type){ // setLocked method-Arees
        this.islocked = locked;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return description;
    }

    public Map<String, String> getExits() {
        return exits;
    }

    public List<Item> getItems() {
        return items;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItem(Item item) {
        items.add(item);
    }
    public void setNPC(NPC npc) {
        this.npc = npc;
    }
    public NPC getNPC()
    {
        return npc;
    }


    public String getLongDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        sb.append(description).append("\n");

        if (!items.isEmpty()) {
            sb.append("You see: ");
            for (Item item : items) {
                sb.append(item.getName()).append(", ");
            }
            // Remove trailing comma and space
            sb.setLength(sb.length() - 2);
            sb.append(".\n");
        }

        if (!exits.isEmpty()) {
            sb.append("Exits: ");
            for (String direction : exits.keySet()) {
                sb.append(direction).append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append(".\n");
        }

        return sb.toString();
    }
}
