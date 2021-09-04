import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class main {

	public static void main(String[] args) {
		
		File outputfile = new File("image.jpg");
		try {
			ImageIO.write(new BufferedImage(720,210,BufferedImage.TYPE_BYTE_BINARY), "jpg", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
