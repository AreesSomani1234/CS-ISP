public class NPC {
    private String name;
    private int health;
    private boolean friendly;
    private String description;
    private String size;
    private int attackPower;
    private String RoomID;

    public NPC(String name, boolean friendly, String description, String size, String RoomID){
        this.RoomID = RoomID;
        this.name = name;
        this.friendly = friendly;
        this.description = description; //description about general behavior
        this.size = size;
        if(size.equals("big")){
            health = (int) ((Math.random()* 26)+75);
            attackPower = (int) ((Math.random()* 26)-50);
        }
        else if(size.equals("medium")){
            health = (int) ((Math.random()* 35)+40);
            attackPower = (int) ((Math.random()* 11)-20);
        }
        else if(size.equals("small")){
            health = (int) ((Math.random()* 30)+10);
            attackPower = (int) ((Math.random()* 10)-11);
        }
        else //Buffer if non valid input
        { 
            System.out.println("non valid input");
        }
    }
    public String getNPCRoomID(){
        return RoomID;
    }

    public String getNPCname(){
        return name;
    }
    public boolean getNPCFriendly(){
        return friendly;
    }
    public int getNPChealth(){
        return health;
    }
    public String getNPCdescription(){
        return description;
    }
    public String getSize(){
        return size;
    }
    public int getattackPower(){
        return attackPower;
    }
    public void NPCAttack(Player player){
        if(player.getCurrentRoomId().equals(getNPCRoomID())) {
            System.out.println("The " + getNPCname() + "is attacking you!" );
            player.updatePlayerHealth(getattackPower());
            System.out.println("You lost: " + getattackPower() + "health points");
            System.out.println("Your current health is: " + player.getPlayerHealth());
        }
    }
}