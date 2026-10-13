package fafas.zap_mod.client;


public class ZapClientState {
    private static java.util.UUID castingPlayerUuid = null;

    public static boolean isUsingSkill1(java.util.UUID playerUuid) {
        return castingPlayerUuid != null && castingPlayerUuid.equals(playerUuid);
    }

    public static void setUsingSkill1(java.util.UUID playerUuid, boolean using) {
        if (using) {
            castingPlayerUuid = playerUuid;
        } else if (castingPlayerUuid != null && castingPlayerUuid.equals(playerUuid)) {
            castingPlayerUuid = null;
        }
    }
}