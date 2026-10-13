package fafas.zap_mod.client.render;

import com.sun.jna.platform.win32.WinBase;
import fafas.zap_mod.ZapMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.joml.Matrix3x2fStack;
import net.minecraft.util.RandomSource;

import java.util.ArrayList;
import java.util.List;

public class CallRenderEntrypoint implements ClientModInitializer {

    private static final Identifier[] SPRITE_TEXTURE =  new  Identifier[]{
        Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "textures/gui/call_sprite1.png"),
        Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "textures/gui/call_sprite2.png"),
        Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "textures/gui/call_sprite3.png"),
        Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "textures/gui/call_sprite4.png"),
        Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "textures/gui/call_sprite5.png")
    };
    private float partialTickTimer = 0f;
    private static final RandomSource random = RandomSource.create();
    private static boolean renderizado = false;
    private static int qtosquadrados = 0;
    private static final List<SquareData> SQUARES = new ArrayList<>();
    public float rotation = 0f;

    public static class SquareData{
        public final float randomPosX;
        public final float randomPosY;
        public final float randomScale;
        public final Identifier texture;

        public SquareData(float randomPosX, float randomPosY, float randomScale, Identifier texture){
            this.randomPosX = randomPosX;
            this.randomPosY = randomPosY;
            this.randomScale = randomScale;
            this.texture = texture;
        }
    }


    @Override
    public void onInitializeClient(){
        HudElementRegistry.attachElementAfter(VanillaHudElements.BOSS_BAR, Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "last_element"), this.hudLayer());
    }

    public static void spawnSquares(int squareNum, int screenWidth, int screenHeight){
        qtosquadrados = squareNum;
        SQUARES.clear();
        for (int i = 0; i < squareNum; i++){
            float randomScale = random.nextFloat()/1.7f + 2f;
            float maxWidth = 64f * randomScale;
            float maxHeight = 64f * randomScale;
            float maxRenderX = Math.max(1, (int) screenWidth - maxWidth);
            float maxRenderY = Math.max(1, (int) screenHeight - maxHeight);
            float randomPosX = random.nextInt(0, (int) maxRenderX);
            float randomPosY = random.nextInt(0, (int) maxRenderY);

            Identifier randomTexture = SPRITE_TEXTURE[random.nextInt(SPRITE_TEXTURE.length)];

            SQUARES.add(new SquareData(randomPosX, randomPosY, randomScale, randomTexture));
        }
        renderizado = true;
    }

    public static void sqrClear(){
        renderizado = false;
        SQUARES.clear();
    }


    public static void toggleSquares(int squareNum, int screenWidth, int screenHeight){
        if(renderizado){
            sqrClear();
        }
        else {
            spawnSquares(squareNum, screenWidth, screenHeight);
        }
    }
    private HudElement hudLayer(){
        return ((guiGraphicsExtractor, deltaTracker) -> {
            if(!renderizado ||SQUARES.isEmpty()) return;

            Matrix3x2fStack matrices = guiGraphicsExtractor.pose();

            for (SquareData squareData : SQUARES){
                matrices.pushMatrix();

                matrices.translate(squareData.randomPosX, squareData.randomPosY);
                matrices.scale(squareData.randomScale);
                if (this.rotation <= 6 * Math.PI * qtosquadrados){
                    matrices.translate(32f,32f);
                    matrices.rotate((float) Math.sin(this.rotation*2)/35);
                    matrices.translate(-32f, -32f);
                }
                if(this.rotation >= 20 * Math.PI * qtosquadrados){this.rotation = 0;}
                this.rotation++;
                guiGraphicsExtractor.blit(
                        RenderPipelines.GUI_TEXTURED,
                        squareData.texture,
                        0,0,
                        0, 0,
                        64, 64,
                        64, 64);
                matrices.popMatrix();
            }


            });
    }
}
