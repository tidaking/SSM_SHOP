package com.robin.shop.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * pinyougou_parent
 * robin
 */

@RequestMapping("/test")
@RestController
public class Test {

    @RequestMapping("/image")
    public Map<String,Object> image(MultipartFile imgFile,HttpServletRequest request) throws IOException {
        File file = new File(request.getRealPath("/")+imgFile.getOriginalFilename());
        imgFile.transferTo(file);

        Map<String,Object> map = new HashMap<>();
        map.put("error",0);
        map.put("url","/"+imgFile.getOriginalFilename());


        System.out.println("test:"+request.getContextPath());


        return  map;
        //imgFile.transferTo();
    }


}
