public class RoomKey extends Item{
    private int keyType;
    private static final int ST_CLAIR_ENTRANCE = 0;
    private static final int QUEEN = 1;
    private static final int EXIT_1 = 2;
    private static final int EXIT_2 = 3;
    private static final int EXIT_3 = 4;




    public RoomKey(String id, String name, String description, int keyType){
        super(id, name, description, false, false);
        this.keyType = keyType;
    }

    public boolean getstClairEnteranceKey(){
        return keyType == ST_CLAIR_ENTRANCE;
    }
    public boolean getqueenEnteranceKey(){
        return keyType == QUEEN;
    }
    public boolean getexitKey1(){
        return keyType == EXIT_1;
    }
    public boolean getexitKey2(){
        return keyType == EXIT_2;
    }
    public boolean getexitKey3(){
        return keyType == EXIT_3;
    }
}

