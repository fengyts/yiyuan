/***
 * Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) 
 *
 **/
package ng.bayue.fastdfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageUrlUtil {
	
	private Map<String, String> domainMap = new HashMap<String,String>();
	private List<String> startGroups = new ArrayList<String>();

	public ImageUrlUtil() {
	}

	public String getFileFullUrl(String url) {
		if ((null != url) && (!"".equals(url.trim())) && (!this.startGroups.isEmpty())) {
			for (String groupName : this.startGroups) {
				if ((null != groupName) && (!"".equals(groupName.trim()))
						&& (url.startsWith(groupName))) {
					String domainUrls = (String) this.domainMap.get(groupName);
					if(domainUrls.contains(":22122")){
						domainUrls = domainUrls.replace(":22122", "");
					}
					String httpStr = getRandomString(domainUrls).trim();
					if ((null != httpStr) && (!"".equals(httpStr))
							&& (httpStr.startsWith("http://"))) {
						if (httpStr.endsWith("/")) {
							return httpStr + url;
						}
						return httpStr + "/" + url;
					}
					if (httpStr.endsWith("/")) {
						return "http://" + httpStr + url;
					}
					return "http://" + httpStr + "/" + url;
				}
			}
		}
		return url;
	}

	private String getFileSnapshotUrl(String url, int width) {
		if ((null != url) && (!"".equals(url.trim())) && (url.contains(".")) && (width > 0)) {
			String[] urls = url.split("\\.");
			String prefix = urls[0];
			String suffix = urls[1];
			return prefix + "_" + width + "." + suffix;
		}
		return url;
	}

	public String getSnapshotUrl(String url, int width) {
		return getFileFullUrl(getFileSnapshotUrl(url, width));
	}

	private static final String getRandomString(String str) {
		if ((null != str) && (!"".equals(str.trim())) && (str.contains(","))) {
			String[] strArr = str.split(",");
			int len = strArr.length;
			int rand = (int) (Math.random() * len);
			return strArr[rand];
		}
		return str;
	}

	public Map<String, String> getDomainMap() {
		return this.domainMap;
	}

	public void setDomainMap(Map<String, String> domainMap) {
		this.domainMap = domainMap;
	}

	public List<String> getStartGroups() {
		return this.startGroups;
	}

	public void setStartGroups(List<String> startGroups) {
		this.startGroups = startGroups;
	}

//	public static void main(String[] args) {
//		ImageUrlUtil dfsDomainUtil = new ImageUrlUtil();
//
//		Map<String, String> domainMap = getMap();
//		List<String> startGroups = getList();
//
//		dfsDomainUtil.setDomainMap(domainMap);
//		dfsDomainUtil.setStartGroups(startGroups);
//		System.out.println(dfsDomainUtil
//				.getFileFullUrl("group1/M00/00/0B/rBABm1S7nbqAMcK8AE6PY3fCE94253_640.jpg"));
//		System.out.println(dfsDomainUtil
//				.getFileFullUrl("group2/M00/00/01/rBABnFS8noOAEfdqAE6PY3fCE94632.jpg"));
//
//		System.out.println(dfsDomainUtil.getSnapshotUrl("group1/M00/00/a.jpg", 40));
//	}

//	static List<String> getList() {
//		List<String> startGroups = new ArrayList<String>();
//		startGroups.add("group1");
//		startGroups.add("group2");
//		return startGroups;
//	}

//	static Map<String, String> getMap() {
//		Map<String, String> domainMap = new HashMap<String,String>();
//		domainMap
//				.put("group1",
//						"http://img01.meitun.local/,http://img02.meitun.local/,http://img03.meitun.local/,http://img04.meitun.local/");
//		domainMap.put("group2", "http://img02.meitun.local/");
//		return domainMap;
//	}
}