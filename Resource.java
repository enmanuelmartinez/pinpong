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
            rootPath = file.getCanonicalPath() + File.separator;
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

    }
 
}
