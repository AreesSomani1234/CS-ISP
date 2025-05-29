import java.io.FileReader;
import java.util.*;
import com.google.gson.*;

public class RoomLoader {
    public Map<String, Room> loadRooms(String filePath) {
        Map<String, Room> rooms = new HashMap<>();
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(new FileReader(filePath), JsonObject.class);

            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                String roomId = entry.getKey();
                JsonObject roomData = entry.getValue().getAsJsonObject();

                String name = roomData.get("name").getAsString();
                String description = roomData.get("description").getAsString();

                Map<String, String> exits = new HashMap<>();
                JsonObject exitsJson = roomData.getAsJsonObject("exits");
                for (Map.Entry<String, JsonElement> exit : exitsJson.entrySet()) {
                    exits.put(exit.getKey(), exit.getValue().getAsString());
                }

                List<Item> items = new ArrayList<>();
                JsonArray itemsJson = roomData.getAsJsonArray("items");
                for (JsonElement itemElement : itemsJson) {
                    JsonObject itemObj = itemElement.getAsJsonObject();
                    String itemId = itemObj.get("id").getAsString();
                    String itemName = itemObj.get("name").getAsString();
                    String itemDescription = itemObj.get("description").getAsString();
                    boolean isConsumable = itemObj.get("consumable").getAsBoolean();
                    boolean isWeapon = itemObj.get("weapon").getAsBoolean();
                    boolean isKey = itemObj.get("isKey").getAsBoolean();
                    if (isKey) {
                        int keyType = itemObj.get("keyType")!= null ? itemObj.get("keyType").getAsInt() : -1;
                        items.add(new RoomKey(itemId, itemName, itemDescription, keyType));
                    } else {
                        items.add(new Item(itemId, itemName, itemDescription, isConsumable, isWeapon));
                    }
                }

                Room room = new Room(roomId, name, description, exits, items, null, false);
                JsonObject npcObj = roomData.getAsJsonObject("NPC");
                if (npcObj != null) {
                    String npcName = npcObj.get("name").getAsString();
                    boolean npcFriendly = npcObj.get("friendly").getAsBoolean();
                    String npcdescription = npcObj.get("description").getAsString();
                    String npcSize = npcObj.get("size").getAsString();
                    // String npcID = npcObj.get("id").getAsString();
                    NPC npc = new NPC(npcName, npcFriendly, npcdescription, npcSize, roomId, room);
                    room.setNPC(npc);
                }
                rooms.put(roomId, room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
