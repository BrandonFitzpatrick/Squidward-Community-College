package View;

import java.util.Arrays;
import java.util.List;

import Model.Ranks;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

//subclass of ViewPane for the instructor view
public class InstructorPane extends ViewPane {
	private ComboBox<Ranks> comboBox = new ComboBox<Ranks>();
	
	private static InstructorPane instructorPane;
	
	public static InstructorPane getInstructorPane() {
		if (instructorPane == null) {
			instructorPane = new InstructorPane();
		}
		return instructorPane;
	}
	
	private InstructorPane() {
		super();
		getStrField1().setPromptText("First Name");
		getStrField2().setPromptText("Last Name");
		getDoubleField().setPromptText("Salary");
		getTitle().setText("INSTRUCTORS");
		
		List<Ranks> ranks = Arrays.asList(Ranks.values());
		comboBox.setMaxSize(150, 25);
		//setting the initial value of the ComboBox to the first rank in the list, so that rank can never be empty
		comboBox.setValue(Ranks.INSTRUCTOR);
		comboBox.setItems(FXCollections.observableArrayList(ranks));
		
		getFields2().getChildren().add(comboBox);
	}

	public ComboBox<Ranks> getComboBox() {
		return comboBox;
	}
	
	// uses the superclass method to set the shared text fields, in addition to setting the rank for the subclass-exclusive ComboBox
	public void setFields(String str1, String str2, String str3, Ranks rank) {
		super.setFields(str1, str2, str3);
		comboBox.setValue(rank);
	}
}
