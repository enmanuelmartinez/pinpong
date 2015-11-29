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

    public static class Audio{

    }

    public static class Image{
        
    }
 
}
