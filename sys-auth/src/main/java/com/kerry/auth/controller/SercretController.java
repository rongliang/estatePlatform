package com.kerry.auth.controller;

import com.kerry.auth.service.SercretService;
import com.kerry.model.ClientUser;
import com.kerry.model.ValidateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 服务连接密钥
 * Created by wangshen on 2017/5/24.
 */
@RestController
@RequestMapping("/sys/auth")
public class SercretController {

    @Autowired
    private SercretService sercretService;

    /**
     * 生成前端UI连接校验信息
     * @param clientType
     * @param uiSign
     * @return
     */
    @RequestMapping(value = "/{clientType}/conn/{uiSign}", method = RequestMethod.GET)
    public String generater(@PathVariable("clientType") String clientType,@PathVariable("uiSign") String uiSign){
        return sercretService.generater(clientType,uiSign);
    }

    /**
     * 用户退出或者刷新系统后，清空服务端用户信息
     * @param accessToken
     * @return
     */
    @RequestMapping(value = "/clear/{accessToken}", method = RequestMethod.GET)
    public String clear(@PathVariable("accessToken") String accessToken){
        return sercretService.clear(accessToken);
    }

    /**
     * 获取客户端用户信息
     * @param accessToken
     * @return
     */
    @RequestMapping(value = "/get/{accessToken}", method = RequestMethod.GET)
    public ClientUser getClientUser(@PathVariable("accessToken") String accessToken) {
        return sercretService.getClientUser(accessToken);
    }

    /**
     * 更新客户端用户信息
     * @param clientUser
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateClientUser(@RequestBody ClientUser clientUser) {
        return sercretService.updateClientUser(clientUser);
    }

    /**
     * 校验客户端URL
     * @param validateClient
     * @return
     */
    @RequestMapping(value = "/validate/url", method = RequestMethod.POST)
    public String validateUrl(@RequestBody ValidateClient validateClient){
        return sercretService.validateUrl(validateClient);
    }

}
