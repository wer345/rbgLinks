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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
public class MapData <T extends Linedata>{
	public Map<String,T> map;
	String tbFileName;
	Class<T> clazz;

	@SuppressWarnings ("unchecked")
	public MapData(String fileName, Class<T> clazz) {
		this.clazz= clazz;
		tbFileName=fileName;
		loadFromFile(fileName);

//		Type type = getClass().getGenericSuperclass();
//		ParameterizedType paramType = (ParameterizedType) type;
//		this.clazz= (Class<T>)paramType.getActualTypeArguments()[0];

	}

	public boolean contains(String id) {
		return map.get(id)!=null;
	}

	public void put(T data) {
    	map.put(data.getId(), data);
	}

    T newRecord()
    {
        try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    boolean arrayEquals(String[] a, String[] b) {
    	if(a.length==b.length) {
    		for(int i=0;i<a.length;i++) {
    			if(!a[i].equals(b[i]))
    				return false;
    		}
    		return true;
    	}
    	return false;
    }
    
	public void loadFromFile(String fileName) {
		Path file = Paths.get(fileName);
		map = new HashMap<String,T>();
		try (InputStream in = Files.newInputStream(file);
		    BufferedReader reader =   new BufferedReader(new InputStreamReader(in))) {
		    String line = null;
			boolean first=true;
		    while ((line = reader.readLine()) != null) {
//		        System.out.println(line);
		    	T data= newRecord();
		    	if(first) {
		    		first=false;
		    		boolean match=false;
		    		String[] names = line.split("\t");
		    		if(arrayEquals(data.getFieldNames(),names)) {
		    			line = reader.readLine();
		    			if(line!=null) {
		    				String[] types = line.split("\t");
				    		if(arrayEquals(data.getFieldTypes(),types)) {
				    			line = reader.readLine();
				    			if(line!=null) {
				    				match=true;
				    			}	
				    		}
		    			}
		    		}
		    		if(!match)
		    			return;
		    	}
		    	data.decode(line);
		    	map.put(data.getId(), data);
		    }
		} catch (IOException x) {
		    System.err.println(x);
		}
	}

	String getLine(String[] fields) {
		String line=null;
		for(String field:fields) {
			if(line==null)
				line=field;
			else
				line+="\t"+field;
		}
		return line;
	}

	public void saveToFile(String fileName) {
		Path file = Paths.get(fileName);
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
			boolean first=true;
			for(String id:map.keySet()) {
				T data = map.get(id);
				String s;
				if(first) {
					first=false;
					s=getLine(data.getFieldNames())+"\n";
				    writer.write(s, 0, s.length());
					s=getLine(data.getFieldTypes())+"\n";
				    writer.write(s, 0, s.length());
				}
				s = data.encode()+"\n";
			    writer.write(s, 0, s.length());
			}
			writer.close();
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
}
