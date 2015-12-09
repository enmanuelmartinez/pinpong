
import javax.sound.midi.Sequencer;
import javax.sound.midi.Sequence;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;

/**
 * @author ENMANUEL MARTINEZ GONZALEZ, ITT
 **/

public class Audio {

    private Sequencer secuencer;

    public Audio() {

    }

    public void reproduce(String direccion, int loopCount) {
        try {
            File audioFile = new File(direccion);
            Sequence sequence = MidiSystem.getSequence(audioFile);
            secuencer = MidiSystem.getSequencer();
            secuencer.open();
            secuencer.setSequence(sequence);
            secuencer.setLoopCount(loopCount);
            secuencer.start();
        } catch (MidiUnavailableException ecc) {
        } catch (InvalidMidiDataException ecc2) {
        } catch (IOException ecc3) {
        }
    }

    public void stop() {
        secuencer.stop();
    }

    public void start(){
        secuencer.start();
    }



}