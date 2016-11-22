package ng.bayue.other.learn.designmodel.observer.advance;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileMonitor {
	
	private static final String EXAMPLE_PATH = "E:/test/test.txt";

	private static final String PARENT_DIR = "E:/test/test";

	private static final String NEW_DIR = "E:/test/test/newDir";

	private static final String NEW_FILE = "E:/test/test/newFile.txt";

	public void monitorExample() {
		FileAlterationMonitor monitor = new FileAlterationMonitor(1);
		FileAlterationObserver observer = new FileAlterationObserver(PARENT_DIR);
		observer.addListener(new FileAlterationListenerAdaptor() {
			@Override
			public void onFileCreate(final File file) {
				System.out.println("file has created:" + file.getName());
			}

			@Override
			public void onFileChange(final File file) {
				System.out.println("file has changed:" + file.getName());
			}

			@Override
			public void onFileDelete(final File file) {
				System.out.println("file has deleted:" + file.getName());
			}
		});
		monitor.addObserver(observer);

		try {
			monitor.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FileMonitor monitor = new FileMonitor();
		monitor.monitorExample();
	}

}
