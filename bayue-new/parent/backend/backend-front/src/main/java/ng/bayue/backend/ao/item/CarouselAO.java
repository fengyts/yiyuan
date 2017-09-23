package ng.bayue.backend.ao.item;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.common.Page;
import ng.bayue.fastdfs.ImageUrlUtil;
import ng.bayue.item.domain.CarouselDO;
import ng.bayue.item.service.CarouselService;
import ng.bayue.service.DfsService;

@Service
public class CarouselAO {

	private static final Logger logger = LoggerFactory.getLogger(CarouselAO.class);
	
	@Value("${dfs.uploadTempPath}")
	private String uploadTempPath;
	
	@Autowired
	private DfsService dfsService;
	@Autowired
	private ImageUrlUtil imageUrlUtil;

	@Autowired
	private CarouselService carouselService;

	public Page<CarouselDO> queryPage(CarouselDO carouselDO, Integer pageNo, Integer pageSize) {
		Page<CarouselDO> page = carouselService.queryPageListByCarouselDOAndStartPageSize(carouselDO, pageNo, pageSize);
		return page;
	}

	public CarouselDO selectById(Long id) {
		if (null == id) {
			return null;
		}
		CarouselDO carouselDO = carouselService.selectById(id);
		carouselDO.setPicture(imageUrlUtil.getFileFullUrl(carouselDO.getPicture()));
		return carouselDO;
	}
	
	public ResultMessage save(CarouselDO carouselDO, Map<String, MultipartFile> map){
		if(null == carouselDO){
			return ResultMessage.validParameterNull(new String[0]);
		}
		Date date = new Date();
		carouselDO.setCreateTime(date);
		carouselDO.setModifyTime(date);
		Long userId = UserHandler.getUser().getId();
		carouselDO.setCreateUserId(userId);
		carouselDO.setModifyUserId(userId);
		
		// 上传图片
		if (null != map && !map.isEmpty()) {
			String image = uploaImage(map);
			if (StringUtils.isEmpty(image)) {
				return new ResultMessage(ResultMessage.Failure, "上传图片失败");
			}
			carouselDO.setPicture(image);
		} else {
			return new ResultMessage(ResultMessage.Failure, "图片不能为空");
		}
		
		Long id = carouselService.insert(carouselDO);
		if(id == null || id < 1){
			return ResultMessage.serverInnerError();
		}
		return new ResultMessage();
	}
	
	public ResultMessage update(CarouselDO carouselDO, Map<String, MultipartFile> map){
		if(null == carouselDO){
			return ResultMessage.validParameterNull(new String[0]);
		}
		carouselDO.setModifyTime(new Date());
		carouselDO.setModifyUserId(UserHandler.getUser().getId());
		
		if (null != map && !map.isEmpty()) {
			String image = uploaImage(map);
			if (StringUtils.isEmpty(image)) {
				return new ResultMessage(ResultMessage.Failure, "上传图片失败");
			}
			carouselDO.setPicture(image);
		}
		
		int res = carouselService.update(carouselDO, false);
		if(res < 1){
			return ResultMessage.serverInnerError();
		}
		return new ResultMessage();
	}
	
	private String uploaImage(Map<String, MultipartFile> map) {
		String savePath = uploadTempPath;
		if(StringUtils.isEmpty(savePath)){
			logger.info("图片临时上传到本地时获取路径错误!");
			return null;
		}
		
		MultipartFile mf = null;
		String fileName = null;
		String dfsPath = null;
		for(Map.Entry<String, MultipartFile> entry : map.entrySet()){
			mf = entry.getValue();
			fileName = mf.getOriginalFilename();
			File file = new File(savePath, fileName);
			if(!file.exists()){
				Boolean b = file.mkdirs();
				if(!b) {
					logger.info("添加轮播图失败,上传图片异常,创建文件失败"+fileName);
					return null;
				}
			}
			try {
				mf.transferTo(file);
				dfsPath = dfsService.uploadFile1(file);
				return dfsPath;
			} catch (Exception e) {
				logger.info("添加轮播图失败,上传图片dfs异常:{}",e);
			} finally {
				FileUtils.deleteQuietly(file);
			}
		}
//		String image = "http://pic56.nipic.com/file/20141227/19674963_215052431000_2.jpg";
//		return image;
		return null;
	}

}
