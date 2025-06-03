public class Item {
    private String id;
    private String name;
    private String description;
    private boolean consumable;
    private boolean weapon;
    private int health;
    private int strength;
    private int weight;
    private int pointsGiven;

    public Item(String id, String name, String description, boolean consumable, boolean weapon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.consumable = consumable;
        this.weapon = weapon;
        weight = 5;
        if(consumable){ //sets health boost for consumables
            strength = 0;
            health = 50;
            this.pointsGiven = 20;
        }
        if(weapon){ //sets strength for weapons
            strength = 100;
            health = 0; 
            this.pointsGiven = 20;
        }
        if(!weapon && !consumable){
            health = 0;
            strength = 0;
            this.pointsGiven = 35;
        }
    }
    public void useItem (Player player){ //using item
        if(consumable){
            player.updatePlayerHealth(health);
        }
        else if(weapon){
            player.updatePlayerStrength(100);
        }
    }

    public boolean getConsumable(){
        return consumable;
    }
    public int getPointsGiven(){
        return pointsGiven;
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
    public int getItemWeight(){
        return weight;
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
