package vitruvian.ptu_trainer_app;

/**
 * Created by Vitruvian on 03/03/2018.
 */

public class SendTraining {

    private static int mods[] = {0,0};
    private static String recKey;

    SendTraining(int mod1, int mod2, String key){
        mods[0] = mod1;
        mods[1] = mod2;
        recKey = key;
    }

    public static int[] getMods(){
        return mods;
    }

    public static String getKey(){
        return recKey;
    }

}
