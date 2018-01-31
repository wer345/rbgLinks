import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindPage {
	WebDriver driver;
	
	FindPage(WebDriver driver) {
		this.driver=driver;
	}
	
	boolean gotoPage(int pageId) {
		try {
			driver.get("http://rarbg.to/torrents.php?category=1%3B4?page="+pageId);
    		String url=driver.getCurrentUrl();
    		int pos=url.indexOf("category=1%3B4");
    		if(pos>0) {
    			int posPage=url.indexOf("page=",pos);
    			int posEnd=url.indexOf("&",posPage);
    			if(posPage>0) {
    				String szPage;
    				if(posEnd<posPage)
    					szPage=url.substring(posPage+5);
    				else {
    					szPage=url.substring(posPage+5,posEnd);
    				}
    				int idxPage=Integer.parseInt(szPage);
    				if(idxPage==pageId)
    					return true;
    			}
    		}
		}
		catch(Exception e) {
			
		}
		return false;
	}
	
	public boolean inList(List<BtInfo> bts,String time) {
		int size=bts.size();
		if(size==0)
			return false;
		
		if (time.compareTo(bts.get(0).addedTime)==1) {
			// time is later than the first
			return false;
		}
		if (time.compareTo(bts.get(size-1).addedTime)==-1) {
			// time is early than the last
			return false;
		}
		return true;
	}
	
	public int findByTime(String time) {
        int basePage=2;
        int lastPage=128;
        ListPage list= new ListPage(driver);
		boolean foundList=false;
		
		if(gotoPage(basePage)) {
    		String xpath_list="/html/body/table[3]/tbody/tr/td[2]/div/table/tbody/tr[2]/td/table[2]";
            List<BtInfo> bts=list.getTable(xpath_list);
            if(bts!=null) {
            	for(BtInfo bt:bts) {
            		System.out.println("time "+bt.addedTime);
            	}
            	if(inList(bts,time)) 
            		System.out.println("time is in page "+basePage);
            	else 
            		System.out.println("time is NOT in page "+basePage);
            }
            else 
            	System.out.println("No BT list found");
		}
		
		return 2;
	}
	
	public static void main(String[] args) {
    	WebDriver driver=StartPage.start();
    	if(driver==null)
    		return;
    	driver.get("http://rarbg.to/torrents.php?category=1;4");
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	FindPage fp=new FindPage(driver);
        String timeToFind="2018-01-23 00.17.28";
    	fp.findByTime(timeToFind);
    	

	}

}
