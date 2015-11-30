
import javax.sound.midi.*;
import java.io.*;

/**
 * @author ENMANUEL MARTINEZ GONZALEZ, ITT ,
 *         DATE JULIO 2009
 *         DERECHOS RESERVADOS
 **/

public class Audio {

    private Sequencer secuencer;

    public Audio() {

    }

    //metodo que le mandas una direcion de un midi y lo reproduce
    public void reproduce(String direccion) {
        try {
            File audioFile = new File(direccion);
            @SuppressWarnings("unused")
            MidiFileFormat mff2 = MidiSystem.getMidiFileFormat(audioFile);//se obtiene el formato de archivo
            Sequence Senc = MidiSystem.getSequence(audioFile);//se le pasa la secuencia

            secuencer = MidiSystem.getSequencer();//se obtiene la secuencia de midi del sistema
            secuencer.open();//se abre secuencia
            secuencer.setSequence(Senc);//se le asigan
            secuencer.setLoopCount(50);//se le asigna un ciclo de reproduccion de 50 veces ,cuando termina vuelv
            secuencer.start();//inicia reproduccion
        } catch (MidiUnavailableException ecc) {
        } catch (InvalidMidiDataException ecc2) {
        } catch (IOException ecc3) {
        }
    }

    //metodo que para la cansion
    public void stop() {
        secuencer.stop();
    }

}