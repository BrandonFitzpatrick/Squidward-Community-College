package View;

import java.util.Arrays;
import java.util.List;

import Model.Majors;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

//subclass of ViewPane for the student view
public class StudentPane extends ViewPane {
	private ComboBox<Majors> comboBox = new ComboBox<Majors>();
	
	private static StudentPane studentPane;
	
	public static StudentPane getStudentPane() {
		if (studentPane == null) {
			studentPane = new StudentPane();
		}
		return studentPane;
	}
	
	private StudentPane() {
		super();
		getStrField1().setPromptText("First Name");
		getStrField2().setPromptText("Last Name");
		getDoubleField().setPromptText("GPA");
		getTitle().setText("STUDENTS");
		
		List<Majors> majors = Arrays.asList(Majors.values());
		comboBox.setMinSize(150, 25);
		//setting the initial value of the ComboBox to the first major in the list, so that major can never be empty
		comboBox.setValue(Majors.ACC); 
		comboBox.setItems(FXCollections.observableArrayList(majors));
		
		getFields2().getChildren().add(comboBox);
	}

	public ComboBox<Majors> getComboBox() {
		return comboBox;
	}
	
	//uses the superclass method to set the shared text fields, in addition to setting the major for the subclass-exclusive ComboBox
	public void setFields(String str1, String str2, String str3, Majors major) {
		super.setFields(str1, str2, str3);
		comboBox.setValue(major);
	}
}
