package View;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HeaderPane { //contains the header at the top of the window
	private HBox header = new HBox(20);
	
	private static HeaderPane headerPane;
	
	public static HeaderPane getHeaderPane() {
		if (headerPane == null) {
			headerPane = new HeaderPane();
		}
		return headerPane;
	}
	
	private HeaderPane() {
		Image banner = new Image("file:" + System.getProperty("user.dir").replace("\\", "/") + "/Images/SCC Banner.jpg".replace(" ", "%20"));
		
		ImageView bannerViewLeft = new ImageView(banner);
        bannerViewLeft.setFitWidth(100);
        bannerViewLeft.setFitHeight(100);
		
        ImageView bannerViewRight = new ImageView(banner);
        bannerViewRight.setFitWidth(100);
        bannerViewRight.setFitHeight(100);
        
		Text title = new Text("SQUIDWARD COMMUNITY COLLEGE");
		title.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
		title.setFill(Paint.valueOf("White"));

		header.setStyle("-fx-background-color: linear-gradient(to bottom, navy, white)");
		header.setAlignment(Pos.CENTER);
		header.getChildren().addAll(bannerViewLeft, title, bannerViewRight);
	}

	public HBox getHeader() {
		return header;
	}
}
