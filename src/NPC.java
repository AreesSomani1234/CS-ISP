public class NPC {
    private String name;
    private int health;
    private boolean friendly;
    private String description;
    private String size;
    private int attackPower;
    private String RoomID;
    private boolean living;
    private Room room;

    public NPC(String name, boolean friendly, String description, String size, String RoomID, Room room){
        this.RoomID = RoomID;
        this.room = room;
        this.name = name;
        this.friendly = friendly;
        this.description = description; //description about general behavior
        this.size = size;
        living = true;
        if (!friendly) //because friendly don't attack
        {
            if(size.equals("Big")){
                this.health = (int) ((Math.random()* 26)+75);
                this.attackPower = (int) ((Math.random()* 26)+20); //20-45 damage
            }
            else if(size.equals("Medium")){
                this.health = (int) ((Math.random()* 35)+40);
                this.attackPower = (int) ((Math.random()* 11)+20); // 20-30 damage
            }
            else if(size.equals("Small")){
                this.health = (int) ((Math.random()* 30)+10);
                this.attackPower = (int) ((Math.random()* 11)+10); // 10-20 damage
            }
            else //Buffer if non valid input
            { 
                System.out.println("non valid input");
            }
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
        if((player.getCurrentRoomId().equals(getNPCRoomID())) && living && getNPCFriendly()) {
            System.out.println("The " + getNPCname() + " is attacking you!" );
            player.playerHit(attackPower);
            System.out.println("You lost: " + getattackPower() + " health points");
            System.out.println("Your current health is: " + player.getPlayerHealth());
        }
    }
    public void NPCDeath(){
        living = false;
        room.setNPC(null);
        System.out.println(getNPCname() + " is dead!");
    }
}