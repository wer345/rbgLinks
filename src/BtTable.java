import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class BtTable {
	public Map<String,BtInfo> map;
	String tbFileName;

	public BtTable(String fileName) {
		tbFileName=fileName;
		loadFromFile(fileName);
	}

	public boolean contains(String id) {
		return map.get(id)!=null;
	}
	
	public void put(BtInfo data) {
    	map.put(data.id, data);
	}
	public void loadFromFile(String fileName) {
		Path file = Paths.get(fileName);
		map = new HashMap<String,BtInfo>();
		try (InputStream in = Files.newInputStream(file);
		    BufferedReader reader =   new BufferedReader(new InputStreamReader(in))) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    	BtInfo data= new BtInfo();
		    	data.decode(line);
		    	map.put(data.id, data);
		    }
		} catch (IOException x) {
		    System.err.println(x);
		}
	}

	public void saveToFile(String fileName) {
		Path file = Paths.get(fileName);
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
			for(String id:map.keySet()) {
				BtInfo data = map.get(id);
				String s = data.encode()+"\n";
			    writer.write(s, 0, s.length());
			}
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
}
