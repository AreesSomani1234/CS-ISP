public class NPC {
    private String name;
    private int health;
    private boolean friendly;
    private String description;
    private String size;

    public NPC(String name, boolean friendly, String description, String size){
        this.name = name;
        this.friendly = friendly;
        this.description = description;
        this.size = size;
        if(size == "big"){
            health = (int) ((Math.random()* 26)+75);
        }
        if(size == "medium"){
            health = (int) ((Math.random()* 35)+40);
        }
        if(size == "small"){
            health = (int) ((Math.random()* 30)+10);
        }
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
}
