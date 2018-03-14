package com.meitun.scheduler.item;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.meitun.comment.domain.ItemReviewBabytreeDO;
import com.meitun.comment.domain.ItemReviewDO;
import com.meitun.comment.enums.CheckStatus;
import com.meitun.comment.service.remote.RemoteItemReviewService;
import com.meitun.item.domain.DetailDO;
import com.meitun.item.domain.result.BtProductInfoResult;
import com.meitun.item.service.DetailService;
import com.meitun.redis.util.JedisCacheUtil;


/***
 * 拉取宝宝树 商品评论
 * 
 * 
 * @author caihui
 *
 */
public class ItemCommentFromBabyTreeJob {
	
	private Logger logger = LoggerFactory.getLogger(ItemCommentFromBabyTreeJob.class);
	
	
	private static final String RUN_COMMETN_KEY = "TASK_ITEMCOMMET_BABYBOX";//每个定时任务一个key

	private static final String BABYBOX_PRODUCT_ID_PREFIXED = "meitun_babybox_comment_";
	
	private  static final String BABYBOX_PRODUCT_URL_PARM_KEY ="&key=";
	
	private  static final String BABYBOX_PRODUCT_URL_PARM_PAGE ="&page=";
	
	private  static final String BABYBOX_PRODUCT_URL_PARM_PRODUCT_KEY ="&crm_code=";
	
	private static final int  BABYTREE_DEFALUT_PAGE_SIZE=100;
	
	@Value("#{config['babyboxcommenturl']}")
	private String babyBoxCommentPath;
	
	@Autowired
	private RemoteItemReviewService  remoteItemReviewService;
	
	@Autowired
	private DetailService detailService;
	
	@Autowired
	JedisCacheUtil jedisCacheUtil;
	
	
	
	/***
	 * 拉取宝宝树 评论主方法
	 */
	public void itemCommentFromBabyTreeService(){
		boolean lock = jedisCacheUtil.lock(RUN_COMMETN_KEY);// 获得锁  
		if(!lock){
			logger.info("run itemCommentFromBabyTree job failed because of last time job didn't finished !!!!!");
			return;
		}
		try{
			//获取所有item_detail 有产品code 字段的 商品信息   
			logger.info("start  ItemCommentFromBabyTreeJob time :".concat(DateFormatUtils.format(new Date(), "yyyy-MM-dd'T'HH:mm:ss")));
			int pullTotalCount = detailService.selectBabyTreeProductCount().intValue();  
			if(pullTotalCount > 0 && pullTotalCount < BABYTREE_DEFALUT_PAGE_SIZE){
				pullTotalCount =BABYTREE_DEFALUT_PAGE_SIZE;
			}
			for(int i =1;i*BABYTREE_DEFALUT_PAGE_SIZE <= pullTotalCount ;i++){
				List<BtProductInfoResult> resultList = detailService.selectBabyTreeProductListByPageValue(i);
				if(CollectionUtils.isNotEmpty(resultList)){
					//处理
					for(BtProductInfoResult queryResult:resultList){
						if(null == queryResult.getBtLastUpdatePage() ){
							queryResult.setBtLastUpdatePage(1);
						}
						String pullUrl = babyBoxCommentPath.concat(BABYBOX_PRODUCT_URL_PARM_PRODUCT_KEY).concat(queryResult.getBtProCode().trim()).concat(BABYBOX_PRODUCT_URL_PARM_KEY).concat(encodeByMD5(queryResult.getBtProCode().trim())).concat(BABYBOX_PRODUCT_URL_PARM_PAGE).concat(queryResult.getBtLastUpdatePage().toString());
						logger.info("start pull babybox comment data,procode is:".concat(queryResult.getBtProCode()).concat("pull url is :").concat(pullUrl));
						JSONObject returnInfo = getHtmlJsonByUrl(pullUrl);
						logger.info(queryResult.getBtProCode().concat(" returnInfo jsonString is:").concat(returnInfo.toString()));
						//确认返回值
						if(returnInfo != null && !returnInfo.isEmpty()){
							if(checkDataStatus(returnInfo)){
									//拆分数据  插入
									babyBoxCommentDataInsert(returnInfo,queryResult);
									//判断有多少个page 最多返回每页 1000条
									JSONObject dataObject = (JSONObject) returnInfo.get("data");
									int lastUpdatePage = queryResult.getBtLastUpdatePage();
									if(dataObject != null && dataObject.get("total") != null){
										int allCount = Integer.valueOf(dataObject.get("total").toString());
										if(allCount > BABYTREE_DEFALUT_PAGE_SIZE){
											for(int j=queryResult.getBtLastUpdatePage();(j-1)*BABYTREE_DEFALUT_PAGE_SIZE< allCount;j++){
												logger.info(" start  pull babybox  page comment data,procode is:".concat(queryResult.getBtProCode()).concat(" babybox page is : ").concat(String.valueOf(j).toString()));
													//TODO:替换分页参数
													JSONObject pageReturn = getHtmlJsonByUrl(pullUrl.concat(BABYBOX_PRODUCT_URL_PARM_PAGE).concat(String.valueOf(j)));
													if(checkDataStatus(pageReturn)){
														babyBoxCommentDataInsert(pageReturn,queryResult);
													}
													lastUpdatePage = j;
												 logger.info(" end   pull babybox page comment data,procode is:".concat(queryResult.getBtProCode()).concat(" babybox page is : ").concat(String.valueOf(j).toString()));		
												}	
											}
									}
									//更新商品库prdid+bt_code 状态
									try {
											DetailDO 	updateDO = new DetailDO();
											updateDO.setBtLastUpdateTime(new Date());
											updateDO.setPrdid(queryResult.getPrdid());
											updateDO.setBtProCode(queryResult.getBtProCode());
											//设置最后更新page
											updateDO.setBtLastUpdatePage(lastUpdatePage);
										    detailService.updateBtLastUpdateTimeByPrdidAndBtCode(updateDO);
										} catch (Exception e) {
											logger.error("update-BtLastUpdateTime-ByPrdidAndBtCode:".concat(e.getMessage()));
									}
							}else{
								logger.info("babybox return comment data exception.");
							}
						}else{
							logger.info(queryResult.getBtProCode().concat(": get empty data from babytree."));
						}
						logger.info("finshed pull babybox comment of item bt_code:".concat(queryResult.getBtProCode()));
					}
					logger.info("finshed pull babybox comment data :".concat(String.valueOf((i)).concat(" page of item bt_code  : perpage  100count")));
				}
			}
			logger.info("end ItemCommentFromBabyTreeJob time :".concat(DateFormatUtils.format(new Date(), "yyyy-MM-dd'T'HH:mm:ss")));	
		}catch (Exception e) {
			logger.error("exception",e);
			logger.info("ItemCommentFromBabyTreeJob job exception and message is:".concat(e.getMessage()));
		}finally{
			if (lock) {
				jedisCacheUtil.unLock(RUN_COMMETN_KEY);// 释放锁
			}
		}
   }

	/***
	 * 数据状态校验
	 * @param returnInfo
	 * @return
	 */
	private boolean checkDataStatus(JSONObject returnInfo) {
		if(returnInfo.get("status") != null &&  returnInfo.get("status").equals(1)){
			return true;
		}
		return false;
	}
	
	
	
	/***
	 *  宝宝树 评论过滤逻辑 和插入逻辑
	 * @param jsonData
	 * @param resultInfo
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private  void  babyBoxCommentDataInsert(JSONObject jsonData,BtProductInfoResult resultInfo) throws IOException{
		
		try {
			JSONObject dataObject = (JSONObject) jsonData.get("data");
			if(dataObject != null  && !dataObject.isEmpty() && dataObject.get("comment_list") != null){
				JSONObject jobj = JSONObject.fromObject(dataObject.get("comment_list") == null ? "{}"  
		                : dataObject.get("comment_list"));  
				
				logger.info(resultInfo.getBtProCode().concat("comment_list is :").concat(jobj.toString()));
				
				List<JSONObject> convertList = new ArrayList<>();
				Iterator it = jobj.keys();  
				 while (it.hasNext()){
					 String key = it.next().toString();  
					 convertList.add(jobj.getJSONObject(key));
				 } 
				//宝宝树评论ID 最大值验证
				Long  maxBabyCommentId  =  remoteItemReviewService.selectMaxItemReviewBbtId_babytree(resultInfo.getBtProCode());
				if(convertList != null &&  CollectionUtils.isNotEmpty(convertList)){
						List<ItemReviewBabytreeDO> saveList  = new ArrayList<>();
						List<ItemReviewDO> saveMeitunList  = new ArrayList<>();
						for(JSONObject convert : convertList){
							ItemReviewBabytreeDO saveModel = new ItemReviewBabytreeDO();
							ItemReviewDO saveMeitunComment = new ItemReviewDO();
							if(StringUtils.isBlank(convertJsonObjectToSaveModel(convert, saveModel,resultInfo,saveMeitunComment))){
								if(null==maxBabyCommentId){
									saveList.add(saveModel);
									saveMeitunList.add(saveMeitunComment);
								}else{
									if(saveModel.getItemReviewBbtId() != null && saveModel.getItemReviewBbtId().longValue() > maxBabyCommentId.longValue()){
										saveList.add(saveModel);
									}
									/*if(saveMeitunComment.getItemReviewBbtId() != null && saveMeitunComment.getItemReviewBbtId().longValue() > maxBabyCommentId.longValue()){
										saveMeitunList.add(saveMeitunComment);
									}*/
								}
							}
						}
						if(CollectionUtils.isNotEmpty(saveList)){
							remoteItemReviewService.saveForBatch_babytree(saveList);
						}
						/*if(CollectionUtils.isNotEmpty(saveMeitunList)){
							remoteItemReviewService.saveForBatch_meitun(saveMeitunList);
						}*/
					}
				}
		} catch (Exception e) {
			logger.error("exception:",e);
			logger.info("babyBoxCommentDataInsert  exception btcode is ".concat(resultInfo.getBtProCode()).concat("and message is:").concat(e.getMessage()));
		}
	}
	
	
	
	
	/***
	 *  json object  转换成 保存数据对象
	 */
	private String convertJsonObjectToSaveModel(JSONObject convert,ItemReviewBabytreeDO saveModel,BtProductInfoResult resultInfo,ItemReviewDO  saveMeitunComment){
			try{
				
					//评论内容
					if(convert.get("content")!=null){
						String lastContent=filterOffUtf8Mb4(xssEncode(convert.get("content").toString()));
						if(StringUtils.isNotBlank(lastContent) && lastContent.length() >=500){
							lastContent=lastContent.substring(0, 499);
						}
						saveModel.setContent(lastContent);
						saveMeitunComment.setContent(lastContent);
					}
					//用户头像
					if(convert.get("user_img_url")!=null){
							String imageUrl = convert.get("user_img_url").toString();
							saveModel.setUserImgUrl(imageUrl);
							saveMeitunComment.setUserImgUrl(imageUrl);
					}
					//用户昵称
					if(convert.get("user_nickname") != null){
						String nickName = convert.get("user_nickname").toString();
						saveModel.setNickName(nickName);
						saveMeitunComment.setUserName(nickName);
					}
					//email
					saveModel.setEmail(convert.get("user_email")==null?"":convert.get("user_email").toString());
					
					//baby userid
					if(convert.get("user_uid")!=null){
						String encUserId = convert.get("user_uid").toString();
						saveModel.setEncUserId(encUserId);
						saveMeitunComment.setEncUserId(encUserId);
					}
					
					//评论创建时间 时间戳
					if(convert.get("create_ts")!=null){
						Date getDate = longSecondsToDate(Long.valueOf(convert.get("create_ts").toString()));
						saveModel.setCreateTime(getDate);
						saveMeitunComment.setCreateTime(getDate);
					}else{
						saveModel.setCreateTime(new Date());
						saveMeitunComment.setCreateTime(new Date());
					}
					
					//宝宝树 评论ID
					if(convert.get("comment_id")!=null){
						saveModel.setItemReviewBbtId(Long.valueOf(convert.get("comment_id").toString()));
						saveMeitunComment.setItemReviewBbtId(Long.valueOf(convert.get("comment_id").toString()));
					}
					//宝宝树 评论得分
					saveModel.setAveragePoint(convert.get("average_point")==null?null:Double.valueOf(convert.get("average_point").toString()));
					//用户手机
					if(convert.get("user_phone")!=null){
						String mobile = convert.get("user_phone").toString();
						saveModel.setMobile(mobile);
					}
					saveModel.setSpu(resultInfo.getSpu());
					saveModel.setPid(resultInfo.getPrdid());
					saveMeitunComment.setPid(resultInfo.getPrdid());
					
					saveModel.setBtCode(resultInfo.getBtProCode());
					saveMeitunComment.setBtCode(resultInfo.getBtProCode());
					
					//设置评论默认 状态
					saveMeitunComment.setSpu(resultInfo.getSpu());
					saveMeitunComment.setIsDelete(false);
					saveMeitunComment.setIsHide(1);
					saveMeitunComment.setPlatForm(1);
					saveMeitunComment.setIsCheck(CheckStatus.WAIT_CHECK.ordinal());
					saveMeitunComment.setHasReply(0);
				}catch (Exception e) {
				 logger.info("jsonobject convert to ItemReviewDO for saving list exception : ".concat(e.getMessage()));  
				 return "error";
			}
		return "";
	}
	
   /***
    *   对 babybox product_id 进行 MD5 加密
    * @param babyboxkey
    * @return
    */
   private  String encodeByMD5(String babyboxkey){  
	   MessageDigest messageDigest = null;  
       try {  
           messageDigest = MessageDigest.getInstance("MD5");  
           messageDigest.reset();  
           messageDigest.update(BABYBOX_PRODUCT_ID_PREFIXED.concat(babyboxkey).getBytes("UTF-8"));  
       } catch (NoSuchAlgorithmException e) {  
    	   logger.info("NoSuchAlgorithmException caught!");  
       } catch (UnsupportedEncodingException e) {  
           logger.info(e.getMessage());  
       }  
       byte[] byteArray = messageDigest.digest();  
       StringBuffer md5StrBuff = new StringBuffer();  
       for (int i = 0; i < byteArray.length; i++) {              
           if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
               md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
           else  
               md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
       }  
       logger.info("encodeByMD5 btcode : ".concat(babyboxkey).concat(" and md5 string is :").concat(md5StrBuff.toString()));  
       return md5StrBuff.toString();  
   } 
   
   /***
    * 获取baby  JSON 对象
    * @param urlTemp
    * @return
    * @throws IOException
    */
   private JSONObject getHtmlJsonByUrl(String urlTemp) throws IOException{
       URL url = null;
       InputStreamReader input = null; 
       HttpURLConnection conn = null;
       JSONObject jsonObj = null;
       try {
          url = new URL(urlTemp);
          conn = (HttpURLConnection) url.openConnection();
          conn.setRequestMethod("GET"); 
          input = new InputStreamReader(conn.getInputStream(),"utf-8");
          Scanner inputStream = new Scanner(input);   
          StringBuffer sb = new StringBuffer();
          while (inputStream.hasNext()) {    
                sb.append(inputStream.nextLine());
          }
         jsonObj = JSONObject.fromObject(sb.toString());
      } catch (Exception e) {  
    	  		logger.info("get babybox comment network exception or cannot get the link exception : ".concat(e.getMessage()));
	    	  if(conn != null){
	    		  conn.disconnect();
	    	  }
	    	  if(input != null){
	    		  input.close();
	    	  }
     }finally{
    	  if(conn != null){
    		  conn.disconnect();
    	  }
    	  if(input != null){
    		  input.close();
    	  }
     }
       return jsonObj;
}
   
   
   
   
   
   /***
    *  毫秒数 转换成 时间
    * @param seconds
    * @return
    */
   private Date longSecondsToDate(Long seconds){
	   seconds = seconds* 1000;
	   java.util.Date d = new java.util.Date();
	   d.setTime(seconds);
	   return d;
   }
   
	/***
	 * xss 过滤
	 * @param s
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
   private static String xssEncode(String s) throws UnsupportedEncodingException { 
       if (s == null || s.isEmpty()) { 
           return s; 
       } 
       StringBuilder sb = new StringBuilder(s.length() + 16); 
       for (int i = 0; i < s.length(); i++) { 
           char c = s.charAt(i); 
           switch (c) { 
           case '>': 
               sb.append(">");// 转义大于号  
               break; 
           case '<': 
               sb.append("<");// 转义小于号  
               break; 
           case '\'': 
               sb.append("'");// 转义单引号  
               break; 
           case '\"': 
               sb.append("\"");// 转义双引号  
               break; 
           case '&': 
               sb.append("&");// 转义&  
               break; 
           default: 
               sb.append(c); 
               break; 
           } 
       } 
       return sb.toString(); 
   } 
  
   
   
   
   /***
    * 处理非UTF-8字符集
    * @param text
    * @return
    * @throws UnsupportedEncodingException
    */
   public static String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException { 
       byte[] bytes = text.getBytes("utf-8"); 
       ByteBuffer buffer = ByteBuffer.allocate(bytes.length); 
       int i = 0; 
       while (i < bytes.length) { 
           short b = bytes[i]; 
           if (b > 0) { 
               buffer.put(bytes[i++]); 
               continue; 
           } 
           b += 256; // 去掉符号位 
           if (((b >> 5) ^ 0x6) == 0) { 
               buffer.put(bytes, i, 2); 
               i += 2; 
           } else if (((b >> 4) ^ 0xE) == 0) { 
        	   buffer.put(bytes, i, 3); 
               i += 3; 
           } else if (((b >> 3) ^ 0x1E) == 0) { 
               i += 4; 
           } else if (((b >> 2) ^ 0x3E) == 0) { 
               i += 5; 
           } else if (((b >> 1) ^ 0x7E) == 0) { 
               i += 6; 
           } else { 
               buffer.put(bytes[i++]); 
           } 
       } 
       buffer.flip(); 
       return new String(buffer.array(), "utf-8"); 
   } 
   

	public void close() {
		jedisCacheUtil.unLock(RUN_COMMETN_KEY);// 释放锁
	}
	
	
	
		/*@SuppressWarnings({"all"})
		public static void main(String[] args){
			ItemCommentFromBabyTreeJob job = new ItemCommentFromBabyTreeJob();
			   URL url = null;
		       InputStreamReader input = null; 
		       HttpURLConnection conn = null;
		       JSONObject jsonObj = null;
		       try {
		    	  // String urlStr = "http://xiaofei.babytree.com/product/meitun_api.php?action=get_product_comment_list&crm_code=e3e3e11&key=a45d2bdd90d8f97c6dd4307b5980f581";
//		    	   String urlStr="http://xiaofei.babytree.com/product/meitun_api.php?action=get_product_comment_list&crm_code=E0435179302000051512&key=8b26a5b78713b0e4f1a423cc596373cf";
		    	   
//		    	   String urlStr="http://xiaofei.babytree.com/product/meitun_api.php?action=get_product_comment_list&crm_code=E0101002302000031906&key=6c4e3e1ee36b8fd73ba5e4ff7e36dbd4";
		    	   String basePath="http://xiaofei.babytree.com/product/meitun_api.php?action=get_product_comment_list";
//		    	   String btProCode="070201001001";
		    	   String btProCode="E0435179302000051512";
		    	   ItemCommentFromBabyTreeJob job1 = new ItemCommentFromBabyTreeJob();
		    	   String urlStr = basePath.concat(BABYBOX_PRODUCT_URL_PARM_PRODUCT_KEY)
		    			   .concat(btProCode).concat(BABYBOX_PRODUCT_URL_PARM_KEY)
		    			   .concat(job1.encodeByMD5(btProCode));
		          url = new URL(urlStr);
		          conn = (HttpURLConnection) url.openConnection();
		          conn.setRequestMethod("GET"); 
		          input = new InputStreamReader(conn.getInputStream(),"utf-8");
		          Scanner inputStream = new Scanner(input);   
		          StringBuffer sb = new StringBuffer();
		          while (inputStream.hasNext()) {    
		                sb.append(inputStream.nextLine());
		          }
		         jsonObj = JSONObject.fromObject(sb.toString());
		         JSONObject returnInfo = job.getHtmlJsonByUrl(urlStr);
		         job.checkDataStatus(returnInfo);
		      } catch (Exception e) {  
			    	  if(conn != null){
			    		  conn.disconnect();
			    	  }
			    	  if(input != null){
			    		  try {
							input.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	  }
		     }finally{
		    	  if(conn != null){
		    		  conn.disconnect();
		    	  }
		    	  if(input != null){
		    		  try {
						input.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	  }
		     }
			
				JSONObject dataObject = (JSONObject) jsonObj.get("data");
				System.out.println(dataObject==null);
				if(dataObject != null  && !dataObject.isEmpty() && dataObject.get("comment_list") != null){
					JSONObject jobj = JSONObject.fromObject(dataObject.get("comment_list") == null ? "{}"  
			                : dataObject.get("comment_list"));  
					List<JSONObject> convertList = new ArrayList<>();
					Iterator it = jobj.keys();  
					 while (it.hasNext()){
						 String key = it.next().toString();  
						 convertList.add(jobj.getJSONObject(key));
					 } 
		       
					 if(convertList != null &&  CollectionUtils.isNotEmpty(convertList)){
						 
						 
					 }
					 
				}
		}
	*/
	
}
