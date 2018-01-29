import java.util.List;

public class MagnetInfo extends LinedataAddedtime{
	static String[] fieldNames={"Id","AddedTime","name","HashInfo"};
	static String[] fieldTypes={typeString,typeString,typeString,typeString};

	String name;
	String hashInfo;

	@Override
	public boolean decode(String line) {
		String[] fields=line.split("\t");
		if(fields.length==fieldNames.length) {
			id=fields[0];
			addedTime=fields[1];
			name=fields[2];
			hashInfo=fields[3];
			return true;
		}
		return false;
	}

	@Override
	public String encode() {
		if(id==null)
			return null;
		return id+"\t"+addedTime+"\t"+name+"\t"+hashInfo;
	}


	@Override
	public String[] getFieldNames() {
		return fieldNames;
	}

	@Override
	public String[] getFieldTypes() {
		return fieldTypes;
	}
}
