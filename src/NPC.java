public class NPC {
    private String name;
    private int health;
    private boolean friendly;
    private String description;
    private String size;
    private int Attackpower;

    public NPC(String name, boolean friendly, String description, String size){
        this.name = name;
        this.friendly = friendly;
        this.description = description;
        this.size = size;
        if(size == "big"){
            health = (int) ((Math.random()* 26)+75);
            Attackpower = (int) ((Math.random()* 26)+25);
        }
        if(size == "medium"){
            health = (int) ((Math.random()* 35)+40);
            Attackpower = (int) ((Math.random()* 11)+10);
        }
        if(size == "small"){
            health = (int) ((Math.random()* 30)+10);
            Attackpower = (int) ((Math.random()* 10)+1);
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
    public int getAttackPower(){
        return Attackpower;
    }
}