package test;

import java.io.File;

import ng.bayue.backend.ao.utils.DfsAO;
import ng.bayue.ssh2_linux.LinuxSSH2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-beans.xml",
		"classpath:spring/spring-web.xml" })
public class DfsTest {
	
	@Autowired
	private DfsAO dfsAO;
	
	@Test
	public void testDfsDelete(){
//		String filePath = "E:/test/Koala.jpg";
//		File file = new File(filePath);
//		String path = dfsAO.upload(file);
//		System.out.println(path);
		
		String fileId = "group1/M00/00/00/wKjrgFeQgQqACazkAAvqH_kipG8490_big.jpg";
		String fileId1 = "group1/M00/00/00/wKjrgFeQgQqACazkAAvqH_kipG8490.jpg";
//		dfsAO.getFileMetaData(fileId1);
		String[] fileIds = LinuxSSH2.remoteExecCommand("ls /usr/local/fastdfs/storage/data/00/00");
		String fileIdPrefix = "group1/M00/00/00/";
		int length = fileIds.length;
		String[] files = new String[length];
		for (int i = 0; i < length; i++) {
			System.out.println(fileIdPrefix + fileIds[i]);
			files[i] = fileIdPrefix + fileIds[i];
		}
		boolean res = dfsAO.batchDeleteFile(files);
	}
	
	@Test
	public void test1(){
		String fileId = "group1/M00/00/00/wKjrgFeQy52AROqOAAvqH_kipG818..jpg";
		int res = dfsAO.deleteFile(fileId);
		System.out.println(res);
	}
	
	@Test
	public void testDfsUpload(){
		File[] files = new File[2];
		String path = "E:/test/Koala.jpg";
		files[0] = new File(path);
		files[1] = new File(path);
		String[] dfsPath = dfsAO.batchUploadFile(files);
		System.out.println(dfsPath[0]);
		System.out.println(dfsPath[1]);
	}
	
	
	

}
