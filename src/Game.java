import java.util.Map;
import java.util.Scanner;

public class Game {
    private Map<String, Room> rooms;
    private Player player;
    private CommandParser commandParser;

    public Game() {
        RoomLoader roomLoader = new RoomLoader();
        rooms = roomLoader.loadRooms("rooms.json");
        player = new Player("entrance");
        commandParser = new CommandParser();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Text Adventure Game!");
        Room currentRoom = rooms.get(player.getCurrentRoomId());
        System.out.println(currentRoom.getLongDescription());
        boolean gameOver = false;
        while (!gameOver) {
            
            System.out.print("> ");
            String input = scanner.nextLine();
            commandParser.parse(input, player, rooms);
        }
    }
}
