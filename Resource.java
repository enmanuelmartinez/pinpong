import java.io.*;
/*--------------------------------------------------
 *
 *  @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
 *  DATE     JULIO 2009
 *  DERECHOS RESERVADOS
 -------------------------------------------------*/
public final class Resource
{
    public static String getRootPath()
    {
        File file = null;
        String rootPath = null;
        try{
            file = new File(".");
            rootPath = file.getCanonicalPath()+ File.separator + "resources" + File.separator;
        }catch (Exception e){
            e.printStackTrace();
        }
        return rootPath;
    }

    public static class Media{
        // Dowloaded from https://freemidi.org/
        public static final String MEDIA_ROOT =  Resource.getRootPath() + "media" + File.separator;
        public static final String SONG_ZELDAOVERWORLD_MIDI = MEDIA_ROOT + "songs" + File.separator + "ZeldaOverworld.mid";
        public static final String SONG_MULE_THEME_MIDI = MEDIA_ROOT + "songs" + File.separator + "M.U.L.E.Theme.mid";

    }

    public static class Image{
        public static final String IMAGES_ROOT =  Resource.getRootPath() + "images" + File.separator;
        public static final String BASEBALL_BALL = IMAGES_ROOT + "balls" + File.separator + "baseball_ball.png";
        public static final String BACKGROUND_MARIO = IMAGES_ROOT + "backgrounds" + File.separator + "mario.jpg";
        public static final String TRUNK_BAR = IMAGES_ROOT + "trunk.png";
    }
 
}
