import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlTools {
	public static Pattern ptn_homePage = Pattern.compile("http://rarbg.to/torrents.php.r=[\\d]+");

	public static boolean isHomePage(String url) {
		return ptn_homePage.matcher(url).matches();
	}

	public static void main(String[] args) {
		// http://rarbg.to/torrents.php?r=80433449
		Matcher m = ptn_homePage.matcher("http://rarbg.to/torrents.php?r=80433449");
		boolean b = m.matches();
		System.out.println("Matches?"+b);
	}

}
