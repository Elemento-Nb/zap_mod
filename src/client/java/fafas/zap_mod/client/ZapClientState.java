package fafas.zap_mod.client;

public class ZapClientState {
    private static boolean isUsingSkill1 = false;
    public static boolean isUsingSkill1(){
        return isUsingSkill1;
    }
    public static void setUsingSkill1(boolean using){
        isUsingSkill1 = using;
    }
}
