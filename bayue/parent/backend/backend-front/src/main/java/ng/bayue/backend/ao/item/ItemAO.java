package ng.bayue.backend.ao.item;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.backend.ao.basedata.SpecGroupAO;
import ng.bayue.base.domain.SpecGroupDO;
import ng.bayue.item.domain.DetailSpecDO;
import ng.bayue.item.dto.DetailSpecDTO;
import ng.bayue.item.service.DetailSpecService;

@Service
public class ItemAO {

	@Autowired
	private DetailSpecService detailSpecService;
	@Autowired
	private SpecGroupAO specGroupAO;

	public List<DetailSpecDTO> listSpecGroups(Long detailId) {
		if (null == detailId) {
			return null;
		}
		DetailSpecDO specDO = new DetailSpecDO();
		specDO.setDetailId(detailId);
		List<DetailSpecDO> list = detailSpecService.selectDynamic(specDO);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<Long> groupIds = new ArrayList<Long>();
		for (DetailSpecDO ds : list) {
			groupIds.add(ds.getSpecGroupId());
		}
		List<SpecGroupDO> listSpecs = specGroupAO.listByGroupIds(groupIds);
		List<DetailSpecDTO> result = new ArrayList<DetailSpecDTO>();
		for (DetailSpecDO dspec : list) {
			long groupId = dspec.getSpecGroupId();
			DetailSpecDTO dto = new DetailSpecDTO();
			dto.setId(dspec.getId());
			dto.setDetailId(detailId);
			dto.setSort(dspec.getSort());
			dto.setSpecGroupId(dspec.getSpecGroupId());
			for (SpecGroupDO gd : listSpecs) {
				long id = gd.getId();
				if (id == groupId) {
					dto.setGroupName(gd.getName());
					dto.setGroupNameAlias(gd.getAlias());
					result.add(dto);
				}
			}
		}
		return result;
	}

}
