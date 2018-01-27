
public class LinedataBase implements Linedata{
	static String typeString="String";
	static String typeSFloat="Float";
	static String typeInt="Int";
	String id=null;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public boolean decode(String line) {
		return false;
	}

	@Override
	public String encode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFieldTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
