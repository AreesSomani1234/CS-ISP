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
        System.out.println();
        System.out.println("Welcome to the abandoned TTC");
        System.out.println("you are stuck underground in the abandoned tracks at Finch station");
        System.out.println("Your goal is to find travel around each station and find 3 hidden keys to bring them to Union station to escape.");
        System.out.println("Be Careful! there are unfriendly challenges present at each station.");
        System.out.println();
        System.out.println();
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
