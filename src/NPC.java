public class NPC {
    private String name;
    private int health;
    private boolean friendly;
    private String description;

    public NPC(String name, boolean friendly, String description){
        this.name = name;
        this.friendly = friendly;
        health = (int) ((Math.random()* 100)+1);
        this.description = description;
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
}
