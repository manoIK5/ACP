package getIp;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class getIpAddress {
	
	static String IpAddress;

	public static void main(String[] args) {
		
	}
	
	public static String getIp() {
		
        try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			IpAddress = inetAddress.getHostAddress();
			return IpAddress;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
