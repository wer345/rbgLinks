import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlTools {


	public static void main(String[] args) {
		// http://rarbg.to/torrents.php?r=80433449
		Pattern p = Pattern.compile("http://rarbg.to/torrents.php.r=[\\d]+");
		Matcher m = p.matcher("http://rarbg.to/torrents.php?r=80433449");
		boolean b = m.matches();
		System.out.println("Matches?"+b);
	}

}
