

public class DownloadTorrent_Test {

	public static void main(String[] args) {
        String fileURL="http://rarbg.to/download.php?id=qzdb4ls&f=VickyAtHome.17.05.05.My.Holes.XXX.1080p.MP4-KTR-[rarbg.to].torrent";
        BtInfo bt = new BtInfo();
        bt.id="qzdb4ls";
        bt.addedTime="2018-01-01 1:1:1";
        bt.name="VickyAtHome.17.05.05.My.Holes.XXX.1080p.MP4-KTR";
		DownloadTorrent.download(bt);
		DownloadTorrent.flush();
	}

}
