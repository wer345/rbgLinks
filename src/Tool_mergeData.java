import java.util.Map;

public class Tool_mergeData {

	public static void main(String[] args) {
		String latestTime=DownloadTorrent.mapFound.getLatestTime();
		System.out.println("Latest time in found is "+latestTime);
		String oldestTime=DownloadTorrent.mapFound.getOldestTime();
		System.out.println("Oldest time in found is "+oldestTime);

		latestTime=DownloadTorrent.mapMagnet.getLatestTime();
		System.out.println("Latest time in magnet is "+latestTime);
		oldestTime=DownloadTorrent.mapMagnet.getOldestTime();
		System.out.println("Oldest time in magnet is "+oldestTime);

		Map<String,BtInfo> mp=DownloadTorrent.mapLoaded.map;
		for(String id:mp.keySet()) {
			BtInfo data = mp.get(id);
			DownloadTorrent.mapFound.put(data);
		}
		DownloadTorrent.mapFound.saveToFile(DownloadTorrent.path+DownloadTorrent.fnFound);

		Map<String,MagnetInfo> mp1=DownloadTorrent.mapMagnetNew.map;
		for(String id:mp1.keySet()) {
			MagnetInfo data = mp1.get(id);
			DownloadTorrent.mapMagnet.put(data);
		}
		DownloadTorrent.mapMagnet.saveToFile(DownloadTorrent.path+DownloadTorrent.fnMagnet);

	}
}
