package com.robin.shop.controller;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * pinyougou_parent
 * robin
 */
public class Test2 {


    public static void main(String[] args) throws Exception {
        System.out.println("测试");
        // 1、加载配置文件，配置文件中的内容就是 tracker 服务的地址。
        ClientGlobal.init("D:\\3_data\\1_java\\code\\1_codelibrary\\pinyougou_parent\\pinyougou_shop_web\\src\\main\\resources\\config\\fastdfs_client.conf");
        // 2、创建一个TrackerClient对象。直接 new 一个。
        TrackerClient trackerClient = new TrackerClient();
        // 3、使用TrackerClient对象创建连接，获得一个TrackerServer对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        // 4、创建一个StorageServer的引用，值为 null
        StorageServer storageServer = null;
        // 5、创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        // 6、使用StorageClient对象上传图片。
        //扩展名不带“.”
        String[] strings = storageClient.upload_file("D:\\8_mytemp\\IMG_3493.JPG", "jpg",
                null);
        // 7、返回数组。包含组名和图片的路径。
        for (String string : strings) {
            System.out.println(string);
        }




    }
}
