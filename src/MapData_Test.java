
public class MapData_Test {
    public static void main(String[] args) {
    	MapData<BtInfo> tb= new MapData<BtInfo>("C:\\a.txt",BtInfo.class);
    	BtInfo d= new BtInfo();
    	d.id="21";
    	d.name="AA";
    	d.addedTime="t1";
    	d.size=12.2f;
    	d.seeder=2;
    	d.leech=3;
    	tb.put(d);
    	d= new BtInfo();
    	d.id="22";
    	d.name="BB";
    	d.addedTime="t2";
    	d.size=13.2f;
    	d.seeder=2;
    	d.leech=3;
    	tb.put(d);
    	tb.saveToFile("C:\\b.txt");
    }
}
