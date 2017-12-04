package ng.bayue.service;

import ng.bayue.common.model.TokenModel;
import ng.bayue.service.common.GeneralRedisModelService;

/**
 * <pre>
 * token 服务类，token key值实际为 (tokenType + '_' + key)
 * </pre>
 *
 * @author fengyts
 * @version $Id: TokenService.java, v 0.1 2017年12月2日 下午3:41:24 fengyts Exp $
 */
public interface TokenService extends GeneralRedisModelService<TokenModel>{

}
