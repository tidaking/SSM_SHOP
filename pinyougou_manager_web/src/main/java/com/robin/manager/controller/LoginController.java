package com.robin.manager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * pinyougou_parent
 * robin
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/name")
    public Map<String,String> name()
    {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();

        Map<String, String> map = new HashMap<>();
        map.put("loginName",name);
        return map;
    }

}
