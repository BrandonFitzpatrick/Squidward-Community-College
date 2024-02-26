package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ViewSwitchPane { //contains the buttons that allow the user to switch views, which are at the bottom of the window
	private Button toStudentViewBtn = new Button("TO STUDENT VIEW");
	private Button toInstructorViewBtn = new Button("TO INSTRUCTOR VIEW");
	private Button toBookViewBtn = new Button("TO TEXTBOOK VIEW");
	private Button toHomepageBtn = new Button("TO HOMEPAGE");
	
	private HBox viewSwitch = new HBox(25, toStudentViewBtn, toInstructorViewBtn, toBookViewBtn);
	
	private static ViewSwitchPane viewSwitchPane;
	
	public static ViewSwitchPane getViewSwitchPane() {
		if (viewSwitchPane == null) {
			viewSwitchPane = new ViewSwitchPane();
		}
		return viewSwitchPane;
	}
	
	private ViewSwitchPane() {
		toStudentViewBtn.setPrefSize(150, 25);
		toInstructorViewBtn.setPrefSize(150, 25);
		toBookViewBtn.setPrefSize(150, 25);
		toHomepageBtn.setPrefSize(150, 25);
		
		viewSwitch.setPadding(new Insets(25));
		viewSwitch.setStyle("-fx-background-color: linear-gradient(to top, navy, white)");
		viewSwitch.setAlignment(Pos.CENTER);
	}
	
	public HBox getViewSwitch() {
		return viewSwitch;
	}

	public Button getToStudentViewBtn() {
		return toStudentViewBtn;
	}

	public Button getToInstructorViewBtn() {
		return toInstructorViewBtn;
	}

	public Button getToBookViewBtn() {
		return toBookViewBtn;
	}

	public Button getToHomepageBtn() {
		return toHomepageBtn;
	}
}
