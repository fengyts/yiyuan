
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ng.bayue.util.FileUtils;

public class IndexTest {

	@Test
	public void testFrontCategoryImport() {
		try {
			File file = new File("E://test/fc.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			StringBuffer data = new StringBuffer();
			while (null != (line = br.readLine())) {
				data.append(line);
			}
			String res = data.toString();
			
//			String encoding = FileUtils.getEncoding(file);
//			System.out.println(encoding);
			JSONObject object = (JSONObject) JSONObject.parse(res);
//			System.out.println(object.toJSONString());
			JSONArray array = (JSONArray) object.get("data");
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
