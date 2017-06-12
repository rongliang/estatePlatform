package com.kerry.dao;

import com.kerry.system.model.UserSysModel;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by wangshen on 2017/6/9.
 */
@Repository
public interface UserSysDao extends BaseMapper<UserSysModel> {

    @SqlStatement(params = "userId")
    int deleteByUserId(String userId);
}
