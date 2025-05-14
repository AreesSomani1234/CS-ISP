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
                    items.add(new Item(itemId, itemName, itemDescription));
                }

                Room room = new Room(roomId, name, description, exits, items);
                JsonObject npcObj = roomData.getAsJsonObject("npc");
                if (npcObj != null) {
                    String npcName = npcObj.get("name").getAsString();
                    boolean npcFriendly = Boolean.parseBoolean(npcObj.get("friendly").getAsString());
                    NPC npc = new NPC(npcName, npcFriendly);
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
