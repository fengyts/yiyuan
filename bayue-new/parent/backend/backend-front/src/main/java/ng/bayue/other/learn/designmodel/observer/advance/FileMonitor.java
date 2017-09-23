package ng.bayue.other.learn.designmodel.observer.advance;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileMonitor {

	public static void main(String[] args) throws Exception {

		String filePath = ("E:/test/test/");
		
		Map<WatchKey,Path> keys = new HashMap<WatchKey,Path>();

		// 获取文件系统的WatchService对象
		WatchService watchService = FileSystems.getDefault().newWatchService();
		Path path = Paths.get(filePath);
		@SuppressWarnings("rawtypes")
		WatchEvent.Kind[] events = {StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY};
		WatchKey key = path.register(watchService, events);
		keys.put(key, path);
		
//		File file = new File(filePath);
//		LinkedList<File> fList = new LinkedList<File>();
//		fList.addLast(file);
//		while (fList.size() > 0) {
//			File f = fList.removeFirst();
//			if (f.listFiles() == null)
//				continue;
//			for (File file2 : f.listFiles()) {
//				if (file2.isDirectory()) {// 下一级目录
//					fList.addLast(file2);
//					// 依次注册子目录
//					Paths.get(file2.getAbsolutePath()).register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
//							StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
//				}
//			}
//		}

		while (true) {
			// 获取下一个文件改动事件
			WatchKey watchKey = watchService.take();
			List<WatchEvent<?>> pollEvents = watchKey.pollEvents();
			for (WatchEvent<?> event : pollEvents) {
				if(event.kind().name().equals(StandardWatchEventKinds.OVERFLOW.name())){
					continue;
				}
				System.out.println(event.context() + " --> " + event.kind());
			}
			// 重设WatchKey
			boolean valid = watchKey.reset();
			if (!valid) {
                // 移除不可访问的目录
                // 因为有可能目录被移除，就会无法访问
                keys.remove(watchKey);
                // 如果待监控的目录都不存在了，就中断执行
                // 如果重设失败，退出监听
                if (keys.isEmpty()) {
                    break;
                }
            }
			
		}
	}

}
