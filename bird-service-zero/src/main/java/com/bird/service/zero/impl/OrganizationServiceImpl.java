package com.bird.service.zero.impl;

import com.bird.core.Check;
import com.bird.core.mapper.CommonSaveParam;
import com.bird.core.service.AbstractServiceImpl;
import com.bird.core.service.TreeDTO;
import com.bird.core.utils.DozerHelper;
import com.bird.service.zero.OrganizationService;
import com.bird.service.zero.dto.OrganizationDTO;
import com.bird.service.zero.mapper.OrganizationMapper;
import com.bird.service.zero.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuxx on 2017/11/1.
 */
@Service
@CacheConfig(cacheNames = "zero_organization")
@com.alibaba.dubbo.config.annotation.Service(interfaceName = "com.bird.service.zero.OrganizationService")
public class OrganizationServiceImpl extends AbstractServiceImpl<Organization> implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private DozerHelper dozerHelper;

    /**
     * 获取组织机构信息
     *
     * @return
     */
    @Override
    public OrganizationDTO getOrganization(Long id) {
        Check.GreaterThan(id, 0L, "id");
        Organization organization = queryById(id);
        return dozerHelper.map(organization, OrganizationDTO.class);
    }

    /**
     * 保存组织机构
     *
     * @param dto
     */
    @Override
    public void saveOrganization(OrganizationDTO dto) {
        Check.NotNull(dto,"dto");

        if (dto.getParentId() > 0) {
            Organization parent = queryById(dto.getParentId());
            dto.setParentIds(parent.getParentIds() + "," + dto.getParentId());
        } else {
            dto.setParentIds("0");
        }
        CommonSaveParam param = new CommonSaveParam(dto, OrganizationDTO.class);
        save(param);
    }
}
