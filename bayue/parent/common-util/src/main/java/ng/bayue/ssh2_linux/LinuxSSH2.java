package ng.bayue.ssh2_linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class LinuxSSH2 {

	private static final Logger logger = LoggerFactory.getLogger(LinuxSSH2.class);

	private static final String UTF8 = "UTF-8";

	private static Session session = null;

	private static void initConnection() {
		JSch jsch = new JSch();
		String host = "192.168.235.128";
		try {
			session = jsch.getSession("root", host);
			session.setPassword("123456");

			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			// session.setConfig("StrictHostKeyChecking", "no");

			session.connect();

		} catch (JSchException e) {
			logger.error("", e);
		}
	}

	private static String writeOutput(InputStream is, String charset) {
		if (null == is) {
			logger.info("paramter InputStream is null,please check it");
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		BufferedReader br = null;
		try {
			if (null == charset || "".equals(charset)) {
				charset = UTF8;
			}
			br = new BufferedReader(new InputStreamReader(is, charset));
			String line = null;
			while (null != (line = br.readLine())) {
				// if(line.contains("PID")){
				// continue;
				// }
				System.out.println(line);
				buffer.append(line);
			}
			return buffer.toString();
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				if (null != br) {
					br.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		return null;
	}

	public static String[] remoteExecCommand(String command) {
		InputStream is = null;
		BufferedReader br = null;
		try {
			initConnection();

			Channel channel = session.openChannel("exec");
			channel.setInputStream(null);
			ChannelExec ce = (ChannelExec) channel;
			ce.setCommand(command);

			ce.connect();
			is = ce.getInputStream();
			// String data = writeOutput(is, UTF8);

			List<String> fileIds = new ArrayList<String>();
			br = new BufferedReader(new InputStreamReader(is, UTF8));
			String line = null;
			while (null != (line = br.readLine())) {
				fileIds.add(line);
			}

			ce.disconnect();
			return fileIds.toArray(new String[0]);

		} catch (JSchException | IOException e) {
			logger.error("execu exception: {}", e);
		} finally {
			try {
				if (null != session)
					session.disconnect();
				if (null != is)
					is.close();
				if (null != br)
					br.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		LinuxSSH2 lssh = new LinuxSSH2();
		String script = "sh /usr/local/test/test.sh";// 执行脚本
		String command = "ls /usr/local/test";// 执行命令
		String storage = "ls /usr/local/fastdfs/storage/data/00/00";
		String[] fileIds = remoteExecCommand(storage);
		for (String str : fileIds) {
			System.out.println(str);
		}

	}

}
