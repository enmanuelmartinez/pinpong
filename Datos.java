import java.io.*;
/*--------------------------------------------------
 *
 *  @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
 *  DATE     JULIO 2009
 *  DERECHOS RESERVADOS
 -------------------------------------------------*/
public class Datos
{
    // Devuelve nuestra propia ruta + File.separator,esto es para localizarme
    // y buscar las imagenes y sonidos	
    public static String Ruta()
    {
        File appPath = new File(System.getProperty("java.class.path"));
        try
        {
            appPath = appPath.getCanonicalFile().getParentFile();
        }
        catch (IOException e) { 
        }
        String ret =appPath.toString() +File.separator;
        
        return ret;
    }
 
}
