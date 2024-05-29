package projectAlg2;

public class App {
	public static void main(String[] args) {
		ViewMenuHome menuHome = new ViewMenuHome();
		ViewHeadFooter head = new ViewHeadFooter();
		
		head.project(false);
		menuHome.menu();
		head.project(true);
	}	
}