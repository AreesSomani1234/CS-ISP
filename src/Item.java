public class Item {
    private String id;
    private String name;
    private String description;
    private boolean consumable;
    private boolean weapon;
    private int health;
    private int strength;

    public Item(String id, String name, String description, boolean consumable, boolean weapon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.consumable = consumable;
        if(consumable){
            strength = 0;
            health = 50;
        }
        if(weapon){
            strength = (int) (Math.random()*35)+1;
            health = 0; 
        }
        if(!weapon && !consumable){
            health = 0;
            strength = 0;
        }
    }
    public void useItem (Player player){
        if(consumable){
            player.updatePlayerHealth(health);
        }
        else if(weapon){
            player.updatePlayerStrength(strength);
        }
    }

    public boolean getConsumable(){
        return consumable;
    }
    public boolean getWeapon(){
        return weapon;
    }
    public int getHealth(){
        return health;
    }
    public int getStrength(){
        return strength;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    
}
