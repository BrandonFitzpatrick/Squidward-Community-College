package App;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Paths;

import Model.AudioPlayer;
import Model.BookBag;
import Model.PersonBag;
import Utils.Utilities;
import View.Root;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	private static final String LOCK_FILE = "lock";
    private static FileChannel lockFileChannel;
    private static FileLock lock;

	public static void main(String[] args) {
		Utilities.restore();
		launch(args);
		Utilities.backup();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//the program will not allow the gui to have several active instances at once
		//if an instance of the gui is already running when the program is run, then it will prevent another instance from being created
		//this is to ensure that there are no conflictions when backing up the bags, since the bags are backed up every time the instance is closed
        lockFileChannel = new RandomAccessFile(LOCK_FILE, "rw").getChannel();
        lock = lockFileChannel.tryLock();

        if (lock == null) {
            System.out.println("Another instance of the application is already running");
            Platform.exit();
            return;
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                lock.release();
                lockFileChannel.close();
                Files.delete(Paths.get(LOCK_FILE));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
	    
		Scene scene = new Scene(Root.getRoot().getBorderPane(), 800, 675);
		primaryStage.setScene(scene);
		AudioPlayer.start("Kelpy_G_Rocks_Out_With_Smooth_Jazz_1.wav");
		primaryStage.show();
	}
}
