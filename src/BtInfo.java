
public class BtInfo implements Linedata{
	String name=null;
	String id=null;
	String addedTime=null;
	float size=0; //MB
	int seeder=0;
	int leech=0;
	@Override
	public void decode(String line) {
		String[] fields=line.split(",");
		if(fields.length==6) {
			id=fields[0];
			name=fields[1];
			addedTime=fields[2];
			size=Float.parseFloat(fields[3]);
			seeder=Integer.parseInt(fields[4]);
			leech=Integer.parseInt(fields[5]);
		}
	}

	@Override
	public String encode() {
		if(id==null)
			return null;
		return id+","+name+","+addedTime+","+size+","+seeder+","+leech;
	}
}
