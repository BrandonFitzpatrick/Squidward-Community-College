package View;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//contains the homepage, which will appear in the center of the window when you first run the program
public class HomepagePane { 
	private VBox homepage = new VBox();

	private static HomepagePane homepagePane;

	public static HomepagePane getHomepagePane() {
		if (homepagePane == null) {
			homepagePane = new HomepagePane();
		}
		return homepagePane;
	}

	private HomepagePane() {
		Image scc = new Image("file:" + System.getProperty("user.dir").replace("\\", "/") + "/Images/Squidward Community College.png".replace(" ", "%20"));
		ImageView sccView = new ImageView(scc);

		sccView.setFitWidth(425);
		sccView.setFitHeight(319);

		Image squidward = new Image("file:" + System.getProperty("user.dir").replace("\\", "/") + "/Images/Squidward_Tentacles.png".replace(" ", "%20"));
		ImageView squidwardView = new ImageView(squidward);

		squidwardView.setFitWidth(200);
		squidwardView.setFitHeight(400);

		Text title = new Text("Welcome to the SCC Database!");
		title.setFill(Paint.valueOf("Navy"));
		title.setFont(Font.font("Georgia", FontWeight.BOLD, 20));

		Text description = new Text("Manage Student, Instructor, and Textbook Information");
		description.setFill(Paint.valueOf("Navy"));

		HBox imageBox = new HBox(20, squidwardView, sccView);
		imageBox.setAlignment(Pos.CENTER);

		homepage.setAlignment(Pos.CENTER);
		homepage.getChildren().addAll(title, description, imageBox);
	}

	public VBox getHomepage() {
		return homepage;
	}
}
