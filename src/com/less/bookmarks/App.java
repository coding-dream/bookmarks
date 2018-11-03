package com.less.bookmarks;

import com.less.bookmarks.util.LayoutInflater;
import fi.iki.elonen.NanoHTTPD;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
		// launch(args);
		// 注意,startServer必须在launch之后启动.
		startServer();
	}

	private static void startServer() throws IOException {
		CoreServer coreServer = new CoreServer(8080);
		coreServer.start(NanoHTTPD.SOCKET_READ_TIMEOUT, true);
	}
}
