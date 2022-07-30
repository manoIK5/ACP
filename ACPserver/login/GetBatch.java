package login;

import java.io.IOException;

public class GetBatch {
	
	protected static void get() {
		try {
			String[] command = {"cmd.exe", "/c", "Start", "C:ACP//start.bat"};
            @SuppressWarnings("unused")
			Process p =  Runtime.getRuntime().exec(command);  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
 