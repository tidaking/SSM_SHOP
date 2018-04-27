package com.robin.shop.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.robin.pojo.TbSeller;
import com.robin.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * pinyougou_parent
 * robin
 */
//@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Reference
    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbSeller seller = sellerService.findOne(username);
        if(seller != null){
            System.out.println("收到了2:"+seller.getSellerId());
            System.out.println("收到了2:"+seller.getPassword());
            System.out.println("收到了2:"+seller.getStatus());

            if(!"1".equals(seller.getStatus())){
                //2.如果有用户的对象  还需要验证这个用户是否已经被审核通过  status 1  没有通过 返回null
                return null;
            }
            Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
            roles.add(new SimpleGrantedAuthority("ROLE_SELLER"));
            User user = new User(username, seller.getPassword(), roles);
            return user;
        }else{
            System.out.println("返回了空...");
            return null;
        }
    }
}
