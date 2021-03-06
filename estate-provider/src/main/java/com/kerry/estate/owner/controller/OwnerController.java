package com.kerry.estate.owner.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.dto.WechatCache;
import com.kerry.estate.dto.AuthDto;
import com.kerry.estate.owner.inter.IOwnerInter;
import com.kerry.estate.owner.model.OwnerModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 业主信息管理
 * Created by wangshen on 2017/7/21.
 */
@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private IOwnerInter ownerInter;

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{code}/list", method = RequestMethod.POST)
    public PageQuery findByPage(@RequestBody SearchParams params, @PathVariable("code") String code) throws Exception{
        ServiceInstance instance = client.getLocalServiceInstance();
        if(params.getParams()==null){
            params.setParams(new HashMap());
        }
        params.getParams().put("authCode", code);
        PageQuery query = ownerInter.findByPage(params);
        logger.info("/owner/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 保存
     * @param ownerModel
     * @param code
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{code}/save", method = RequestMethod.POST)
    public String insert(@RequestBody OwnerModel ownerModel, @PathVariable("code") String code) throws Exception {
        ownerModel.setAuthCode(code);
        return ownerInter.insert(ownerModel);
    }

    /**
     * 修改
     * @param ownerModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody OwnerModel ownerModel) throws Exception {
        return ownerInter.update(ownerModel);
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception{
        return ownerInter.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public OwnerModel selectById(@PathVariable("id") String id) throws Exception {
        return ownerInter.selectById(id);
    }

    /**
     * 业主认证
     * @param authDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String ownAuth(@RequestBody AuthDto authDto) throws Exception {
        return ownerInter.ownAuth(authDto);
    }

    /**
     * 获取用户认证信息
     * @param token
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/auth/{token}", method = RequestMethod.GET)
    public WechatCache getOwnAuth(@PathVariable("token") String token) throws Exception {
        return ownerInter.getOwnAuth(token);
    }

    /**
     * 获取业主家庭信息
     * @param ownId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list/family/{ownId}", method = RequestMethod.GET)
    public List<OwnerModel> findFamily(@PathVariable("ownId") String ownId) throws Exception {
        return ownerInter.findFamily(ownId);
    }
}
