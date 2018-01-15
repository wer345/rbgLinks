import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DownloadFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String link="http://rarbg.to/download.php?id=yu3wbnp&f=Beyond.Scared.Straight.S05E05.Fulton.County.GA.720p.WEB.h264-CRiMSON[rartv]-[rarbg.to].torrent";
		URL website;
		//link="http://www.google.com";
		try {
			website = new URL(link);
		Path target= Paths.get("C:\\a.torrent");
			try (InputStream in = website.openStream()) {
			    Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
			    System.out.println("Done");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
