
package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.CashModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CashService {



    public int create(CashModel cashModel);

    public int createSelective(CashModel cashModel);

    public CashModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(CashModel cashModel);

    public int updateByPrimaryKeySelective(CashModel cashModel);

    public int deleteByPrimaryKey(Long id);

    public long selectCount(CashModel cashModel);


    public CashModel addCash(CashModel cashModel);

    public void agreeCash(Long id);

    public void refuseCash(Long id);

    public List<CashModel> selectPage(CashModel cashModel, Pageable pageable);


}