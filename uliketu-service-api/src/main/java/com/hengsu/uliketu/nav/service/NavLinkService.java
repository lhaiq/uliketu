
package com.hengsu.uliketu.nav.service;

import com.hengsu.uliketu.nav.model.NavLinkModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface NavLinkService {

    public int create(NavLinkModel navLinkModel);

    public int createSelective(NavLinkModel navLinkModel);

    public NavLinkModel findByPrimaryKey(Long id);

    public void clickLink(Long id, Long userId);

    public int updateByPrimaryKey(NavLinkModel navLinkModel);

    public int updateByPrimaryKeySelective(NavLinkModel navLinkModel);

    public int deleteByPrimaryKey(Long id);

    public long selectCount(NavLinkModel navLinkModel);

    public List<NavLinkModel> selectPage(NavLinkModel navLinkModel, Pageable pageable);

}