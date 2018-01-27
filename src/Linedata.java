import java.util.List;

public interface  Linedata {

	public boolean decode(String line);
	public String encode();
	public String getId();
	public String[] getFieldNames();
	public String[] getFieldTypes();
}
