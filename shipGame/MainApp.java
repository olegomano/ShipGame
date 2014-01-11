package shipGame;
import javax.swing.JFrame;

import shipGameUi.GameUi;
import shipGameUi.UiInterface;

public class MainApp {
	private AppData appData;
	private UiInterface gameUI;
	public static MyView view;
	
	public MainApp(AppData data) {
		appData = data;
		JFrame window = new JFrame();
		gameUI = new GameUi();
		view = new MyView(data, gameUI);
		MouseInputCollector mouse = new MouseInputCollector(data, view, gameUI);
		view.addMouseListener(mouse);
		view.addMouseWheelListener(mouse);
		window.setSize(840, 560);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(view);
		window.setVisible(true);
	}
	
}
