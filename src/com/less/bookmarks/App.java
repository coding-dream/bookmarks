package com.less.bookmarks;

import java.io.IOException;

import com.less.bookmarks.util.LayoutInflater;

import fi.iki.elonen.NanoHTTPD;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = LayoutInflater.inflate("activity_main");
			Scene scene = new Scene(root, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		startServer();
		launch(args);
	}

	private static void startServer() throws IOException {
		CoreServer coreServer = new CoreServer(8080);
		coreServer.start(NanoHTTPD.SOCKET_READ_TIMEOUT, true);
	}
}
