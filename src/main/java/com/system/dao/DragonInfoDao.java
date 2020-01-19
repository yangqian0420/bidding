package com.system.dao;

import com.system.dto.DragonInfo;
import com.system.dto.PageInfo;

import java.util.List;

/**
 * Created by yangqian on 2020/1/16.
 */
public interface DragonInfoDao {
    public List<DragonInfo> selectCodeFromDragon(PageInfo page);
}
