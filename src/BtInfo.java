import java.util.List;

public class BtInfo extends LinedataBase{
	static String[] fieldNames={"Id","Name","AddedTime","Size(MB)","Seeder","Leech"};
	static String[] fieldTypes={typeString,typeString,typeString,typeSFloat,typeInt,typeInt};
	String name=null;
	String addedTime=null;
	float size=0; //MB
	int seeder=0;
	int leech=0;
	@Override
	public boolean decode(String line) {
		String[] fields=line.split("\t");
		if(fields.length==6) {
			try {
				id=fields[0];
				name=fields[1];
				addedTime=fields[2];
				size=Float.parseFloat(fields[3]);
				seeder=Integer.parseInt(fields[4]);
				leech=Integer.parseInt(fields[5]);
				return true;
			}
			catch (Exception e) {

			}
		}
		return false;
	}

	@Override
	public String encode() {
		if(id==null)
			return null;
		return id+"\t"+name+"\t"+addedTime+"\t"+size+"\t"+seeder+"\t"+leech;
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
