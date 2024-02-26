package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//abstract superclass which holds all of the fields, buttons, and containers that are shared between all three of the main views
//this greatly reduces the length of the student, instructor, and textbook panes, and minimizes the amount of excess code
public abstract class ViewPane { 
	private TextField strField1 = new TextField();
	private TextField strField2 = new TextField();
	private TextField doubleField = new TextField();
	private TextField searchField = new TextField();
	private TextField removeField = new TextField();
	private TextField updateField = new TextField();
	
	private Text title = new Text();
	
	private Button insertBtn = new Button("INSERT");
	private Button updateBtn = new Button("UPDATE");
	private Button searchBtn = new Button();
	private Button removeBtn = new Button();
	
	private HBox fields1 = new HBox(50, strField1, strField2);
	private HBox fields2 = new HBox(50, doubleField);
	private VBox searchAndRemove = new VBox(10);
	private HBox insertAndUpdate = new HBox(25);
	
	private BorderPane centerPane = new BorderPane();
	
	public ViewPane() {
		title.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
		title.setFill(Paint.valueOf("Navy"));
		
		insertBtn = new Button("INSERT");
		insertBtn.setPrefSize(70, 25);
		
		updateBtn = new Button("UPDATE");
		updateBtn.setPrefSize(70, 25);
		
		fields1.setAlignment(Pos.CENTER);
		fields2.setAlignment(Pos.CENTER);
		
		searchField.setPromptText("Search By ID");
		searchField.setMaxSize(110, 25);
		searchBtn.setPrefSize(25, 25);
		
		Image searchImage = new Image("file:" + System.getProperty("user.dir").replace("\\", "/") + "/Images/SearchIcon.jpg".replace(" ", "%20"));
		ImageView searchImageView = new ImageView(searchImage);
		searchImageView.setFitWidth(25);
        searchImageView.setFitHeight(25);
		searchBtn.setGraphic(searchImageView);
		
		HBox search = new HBox(5, searchBtn, searchField);
		search.setAlignment(Pos.TOP_RIGHT);
		
		removeField.setPromptText("Remove By ID");
		removeField.setMaxSize(110, 25);
		removeBtn.setPrefSize(25, 25);
		
		Image removeImage = new Image("file:" + System.getProperty("user.dir").replace("\\", "/") + "/Images/RemoveIcon.jpg".replace(" ", "%20"));
		ImageView removeImageView = new ImageView(removeImage);
		removeImageView.setFitWidth(25);
        removeImageView.setFitHeight(25);
		removeBtn.setGraphic(removeImageView);
		
		HBox remove = new HBox(5, removeBtn, removeField);
		remove.setAlignment(Pos.TOP_RIGHT);
		
		searchAndRemove.setAlignment(Pos.TOP_RIGHT);
		searchAndRemove.getChildren().addAll(search, remove);
		
		insertAndUpdate.setAlignment(Pos.CENTER);
		updateField.setPromptText("ID");
		updateField.setPrefSize(50, 25);
		Region spacer = new Region();
		spacer.setPrefWidth(75);
		insertAndUpdate.getChildren().addAll(insertBtn, spacer, updateBtn, updateField);

		VBox fieldBox = new VBox(50, title, fields1, fields2, insertAndUpdate);
		fieldBox.setAlignment(Pos.CENTER);

	    //nested BorderPane is used to ensure that the searchAndRemove VBox will always be in the top right corner of the window, regardless of window size
	    BorderPane.setAlignment(searchAndRemove, Pos.TOP_RIGHT);
	    BorderPane.setMargin(searchAndRemove, new Insets(10));
	    BorderPane.setMargin(fieldBox, new Insets(10));
	    
	    centerPane.setTop(searchAndRemove);
	    centerPane.setCenter(fieldBox);
	}

	public TextField getStrField1() {
		return strField1;
	}

	public TextField getStrField2() {
		return strField2;
	}
	
	public TextField getDoubleField() {
		return doubleField;
	}
	
	public TextField getSearchField() {
		return searchField;
	}

	public TextField getRemoveField() {
		return removeField;
	}
	
	public TextField getUpdateField() {
		return updateField;
	}

	public Text getTitle() {
		return title;
	}

	public Button getInsertBtn() {
		return insertBtn;
	}

	public Button getSearchBtn() {
		return searchBtn;
	}

	public Button getRemoveBtn() {
		return removeBtn;
	}

	public Button getUpdateBtn() {
		return updateBtn;
	}

	public HBox getFields1() {
		return fields1;
	}

	public HBox getFields2() {
		return fields2;
	}

	public VBox getSearchAndRemove() {
		return searchAndRemove;
	}
	
	public HBox getInsertAndUpdate() {
		return insertAndUpdate;
	}

	public BorderPane getCenterPane() {
		return centerPane;
	}

	//clears all of the shared text fields
	public void clear() { 
		strField1.clear();
		strField2.clear();
		doubleField.clear();
	}

	//takes in String parameters and sets them to the respective text fields
	public void setFields(String str1, String str2, String str3) { 
		strField1.setText(str1);
		strField2.setText(str2);
		doubleField.setText(str3);
	}

	//takes in String parameters which are used to create and display an alert
	public void customAlertThrowing(String str1, String str2, String str3) { 
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(str1);
		alert.setHeaderText(str2);
		alert.setContentText(str3);
		alert.showAndWait();
	}
}
