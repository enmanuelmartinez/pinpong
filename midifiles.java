
import javax.sound.midi.*;
import java.io.*;

// @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
// DATE JULIO 2009
// DERECHOS RESERVADOS

//esta clase es para la reproduccion de sonidos  en midi
public class midifiles {
    //aqui estan todas las direcciones de los sonidos de fondo
    String direcsongs[] = {Datos.Ruta() + "sounds" + File.separator + "Mario_2.mid", Datos.Ruta() + "sounds" + File.separator + "Spelunker-Level1_Arranged.mid"
            , Datos.Ruta() + "sounds" + File.separator + "StarmanRock1.mid", Datos.Ruta() + "sounds" + File.separator + "Zelda2ov.mid"
            , Datos.Ruta() + "sounds" + File.separator + "z2temple.mid", Datos.Ruta() + "sounds" + File.separator + "El_Padrino.mid"
            , Datos.Ruta() + "sounds" + File.separator + "Mirame.mid", Datos.Ruta() + "sounds" + File.separator + "SMB-OverworldPanic-PanickingMushrooms.mid"};
    Datos fg;
    Sequencer sequa;

    public midifiles() {

    }
    //metodo que le mandas una direcion de un midi y lo reproduce
    public void repro(String direccion) {

        try {

            File arch = new File(direccion);//AQUI SE OBTIENE EL MIDI EN ESA DIRECCION

            @SuppressWarnings("unused")
            MidiFileFormat mff2 = MidiSystem.getMidiFileFormat(arch);//se obtiene el formato de archivo

            Sequence Senc = MidiSystem.getSequence(arch);//se le pasa la secuencia

            sequa = MidiSystem.getSequencer();//se obtiene la secuencia de midi del sistema

            sequa.open();//se abre secuencia

            sequa.setSequence(Senc);//se le asigan
            sequa.setLoopCount(50);//se le asigna un ciclo de reproduccion de 50 veces ,cuando termina vuelve
            //a iniciar

            sequa.start();//inicia reproduccion
        } catch (MidiUnavailableException ecc) {
        } catch (InvalidMidiDataException ecc2) {
        } catch (IOException ecc3) {
        }
    }

    //este metodo reproduce un numero de cancion en las direcciones especificadas
    public void repronum(int num) {
        repro(direcsongs[num]);
    }

    //metodo que para la cansion
    public void stop() {
        sequa.stop();
    }

}