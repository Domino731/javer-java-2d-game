package javer.src.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_ATLAS_NAME = "player_sprites.png";
    public static final String LEVEL_ATLAS_NAME = "outside_sprites.png";

    public static BufferedImage getSpriteAtlas(String fileName){
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        BufferedImage img = null;
        try {
           img = ImageIO.read(is);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}
