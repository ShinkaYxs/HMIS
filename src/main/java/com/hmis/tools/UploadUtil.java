/**
 * 文件名：UploadUtil
 * 作者：闫喜深
 * 描述：
 */

package com.hmis.tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * @author 闫喜深
 * @version 1.0
 * @date 2019/5/12 17:37
 */
public class UploadUtil {
    public static String uploadFile(MultipartFile file, String path) throws Exception{
        //定义src下的图片资源存储路径
        String pathSrc = "D:\\GIT_GitHub\\HMIS\\src\\main\\webapp\\images\\headPortrait";
        String name = file.getOriginalFilename();                       //上传文件的真实名称
//        String prefixName = name.substring(0, name.lastIndexOf(".")); //去掉后缀名的文件名
//        String suffixName = name.substring(name.lastIndexOf("."));    //获取后缀名
//        String hash = Integer.toHexString(new Random().nextInt());    //自定义随机数 字母+数字作为文件名
//        String fileName = hash + suffixName;
        String fileName = name;
        File tempFile = new File(path, fileName);

        if (!tempFile.getParentFile().exists()){
            tempFile.getParentFile().mkdir();
        }
        if(tempFile.exists()){
            tempFile.delete();
        }

        tempFile.createNewFile();
        file.transferTo(tempFile);

        copy(path+"\\"+name, pathSrc+"\\"+name);

        return tempFile.getName();
    }

    private static void copy(String url1, String url2) throws Exception {
        FileInputStream in = new FileInputStream(new File(url1));
        FileOutputStream out = new FileOutputStream(new File(url2));
        byte[] buff = new byte[512];
        int n = 0;
        System.out.println("复制文件：" + "\n" + "源路径：" + url1 + "\n" + "目标路径：" + url2);
        while ((n = in.read(buff)) != -1) {
            out.write(buff, 0, n);
        }
        out.flush();
        in.close();
        out.close();
        System.out.println("复制完成");
    }
}
