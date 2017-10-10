package com.wu.akka;

import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import akka.actor.UntypedActor;

public class WorkActor extends UntypedActor {

	@Override
	@SuppressWarnings("unchecked")
	public void onReceive(Object mess) throws Exception {
		List<String> list = (List<String>) mess;
		System.out.println("接收到任务是" + mess);
		int result = 0;
		for (String string : list) {
			URL url = new URL(string);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			java.io.InputStream inStream = conn.getInputStream();
			byte[] data = readInputStream(inStream);
			result += data.length;
		}
		MasterActor.send(result);
	}
	
	public static byte[] readInputStream(java.io.InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1204];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
}
