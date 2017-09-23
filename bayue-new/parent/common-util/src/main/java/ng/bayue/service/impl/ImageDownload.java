package ng.bayue.service.impl;

import java.io.IOException;
import java.util.Map;

import ng.bayue.fastdfs.ImageUrlUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * <pre>
 * 图片下载地址生成组件，用于ftp页面当中生成图片路径
 * </pre>
 *
 * @author fengyts
 * @version $Id: ImageDownload.java, v 0.1 2016年7月25日 下午12:00:30 fengyts Exp $
 */
public class ImageDownload implements TemplateDirectiveModel {

	@Autowired
	private ImageUrlUtil imageUrlUtil;

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		// 图片key
		Object codeObj = params.get("code");
		// 图片宽度
		Object width = params.get("width");
		if (codeObj == null) {

		} else {
			String code = codeObj.toString();
			if (code.indexOf("http://") != -1) {
				env.getOut().write(code);
			} else {
				Integer w = null;
				if (null != width) {
					w = Integer.valueOf(width.toString());
				}
				if (w == null) {
					String group = imageUrlUtil.getStartGroups().get(0);
					String domain = imageUrlUtil.getDomainMap().get(group);
					if (StringUtils.isNotBlank(domain)) {
						String[] domainArr = domain.split(",");
						domain = domainArr[0];
					}
					System.out.println(domain);
					if (domain.indexOf("http://") != -1) {
						env.getOut().write(domain + "/" + code);
					} else {
						env.getOut().write("http://" + domain + "/" + code);
					}
					return;
				}
				String result = imageUrlUtil.getSnapshotUrl(code, w);
				env.getOut().write(result);
			}

		}
	}
}
