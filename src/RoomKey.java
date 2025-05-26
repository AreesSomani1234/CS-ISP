public class RoomKey {
    private boolean stClairEnteranceKey;
    private boolean queenEnteranceKey;
    private boolean exitKey1;
    private boolean exitKey2;
    private boolean exitKey3;

    public RoomKey(boolean stClairEnteranceKey,boolean queenEnteranceKey, boolean exitKey1, boolean exitKey2, boolean exitKey3){
        this.stClairEnteranceKey = stClairEnteranceKey;
        this.queenEnteranceKey = queenEnteranceKey;
        this.exitKey1 = exitKey1;
        this.exitKey2 = exitKey2;
        this.exitKey3 = exitKey3;
    }

    public boolean getstClairEnteranceKey(){
        return stClairEnteranceKey;
    }
    public boolean getqueenEnteranceKey(){
        return queenEnteranceKey;
    }
    public boolean getexitKey1(){
        return exitKey1;
    }
    public boolean getexitKey2(){
        return exitKey2;
    }
    public boolean getexitKey3(){
        return exitKey3;
    }
}

