
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utils.HTTPDownload;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class ReadRarbg {

    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
//    	System.setProperty("webdriver.firefox.marionette","C:\\software\\Selenium\\geckodriver.exe");
    	System.setProperty("webdriver.gecko.driver","C:\\software\\Selenium\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();

        String baseUrl = "http://demo.guru99.com/selenium/newtours/";
        baseUrl="http://rarbg.to/torrent/gzhqyte";
        baseUrl="http://rarbg.to";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);
        String lastUrl="";
        String saveDir = "C:\\Temp";
        String dataFolder="data";
        String fnFound="found.txt";
        String fnNew="new.txt";
        String fnLoaded="loaded.txt";
        String path=saveDir+"\\"+dataFolder+"\\";

	    BtTable tbFound=new BtTable(path+fnFound);
	    BtTable tbNew=new BtTable(path+fnNew);
	    BtTable tbLoaded=new BtTable(path+fnLoaded);
	    
        while(true) {
        	String url="";
        	try {
        		url=driver.getCurrentUrl();
        	}
        	catch (Exception e) {
        		try {
        			System.out.println("Failed to get current URL");
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
        		continue;
        	}
			System.out.println("URL:"+url);
        	if(!url.equals(lastUrl)) {
        		System.out.println("Detected new page: "+url);
                ListPage list= new ListPage(driver);

                List<BtInfo> bts=list.getTable();
                if(bts!=null) {
                	
                	for(BtInfo bt:bts) {
                		if(tbFound.contains(bt.id) || tbNew.contains(bt.id)) {
                			System.out.println("Old one: "+bt.id+":"+bt.name);
                			continue;
                		}
                		tbNew.put(bt);
                		
                		String urlT="http://rarbg.to/download.php?id="+bt.id+"&f="+
                		bt.name+"-[rarbg.to].torrent";
                		System.out.println("download: "+urlT);
                        try {
                        	String time="["+bt.addedTime.trim().replace(':','.')+"] ";
                        	String saveName=time +bt.name+" - "+bt.id+".torrent";
							int length=HTTPDownload.downloadFile(urlT, saveDir,saveName);
							if(length>0) {
								tbLoaded.put(bt);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                	}
                	tbNew.saveToFile(path+fnNew);
                	tbLoaded.saveToFile(path+fnLoaded);
                }
        		
	        	lastUrl=url;
        	}

        	try {
        		Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        //close Fire fox
        //driver.close();

    }

}