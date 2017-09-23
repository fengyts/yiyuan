package ng.bayue.test;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.Test;

import ng.bayue.factory.GeneralFactory;

public class FactoryTest {

	@Test
	public void test() {
		GeneralFactory<FileAlterationObserver> factory = new GeneralFactory<FileAlterationObserver>(
				FileAlterationObserver.class);
		FileAlterationObserver observer = factory.getInstance("E:/test/test");
		System.out.println(observer);
	}

}
