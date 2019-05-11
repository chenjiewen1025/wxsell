package com.chenjiewen.wxsell.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/seller/upload")
public class UploadController {

//上传照片
    @RequestMapping(value = "/addPhoto")
    @ResponseBody
    public String uploadAreaFile(@RequestParam(value = "file", required = false)     MultipartFile file, HttpServletRequest request) throws Exception {

        Map<String,Object> result = new HashMap<String, Object>();
        try{
            //上传文件方法，这里需要改成自己项目里上传文件方法

            if (!file.isEmpty()) {
                // 文件保存路径
                String filePath = "/Users/chenjiewen/upload/" + file.getOriginalFilename();
                File file1 = new File("/Users/chenjiewen/upload/");
                if (!file1.exists()) {
                    file1.mkdirs();
                }
                // 转存文件
                file.transferTo(new File(filePath));
            }
            result.put("code", 0);
            result.put("msg", "上传成功");
            result.put("filePath", "/sell/api/file/"+file.getOriginalFilename());
            return JSONArray.fromObject(result).toString();
        }catch(Exception e){
            result.put("code", -1);
            result.put("msg", "上传失败");
            result.put("filePath", "");
            return JSONArray.fromObject(result).toString();
        }

    }


}
