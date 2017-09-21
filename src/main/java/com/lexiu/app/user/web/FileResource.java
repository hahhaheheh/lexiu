package com.lexiu.app.user.web;

import com.bm.entity.HealthForm;
import com.bm.model.ResultModel;
import com.bm.service.HealthFormService;
import com.bm.service.PushService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2016/10/15.
 */
@Controller
@Api(value = "文件模块",description = "图片上传")
public class FileResource {

    @Value("${filePath}")
    private String filePath;

    @Autowired
    private HealthFormService healthFormService;

    @Autowired
    private PushService pushService;

    /**
     * 上传材料
     * @param request
     * @return
     */
    @RequestMapping({"/pic/upload"})
    @ResponseBody
    @ApiOperation(value = "体检表图片上传",httpMethod = "POST")
    public ResultModel uploadMaterial(HttpServletRequest request, @RequestParam String userId, MultipartFile file) {

        String cid = request.getParameter("cid");
        String templateId = request.getParameter("templateId");

        if (!file.isEmpty()) {

            String originalName = file.getOriginalFilename();

            String contentType = file.getContentType();

            String suffix = contentType.substring(contentType.lastIndexOf("/")+1);

            try {
                File targetDir = new File(filePath + userId);
                if (!targetDir.exists()) {
                    targetDir.mkdirs();
                }
                String fileName = System.currentTimeMillis()+"";
                File targetFile = new File(filePath+userId+File.separator+fileName+"."+suffix);
                if (!targetFile.exists()){
                    targetFile.createNewFile();
                }
                file.transferTo(targetFile);
                HealthForm healthForm = new HealthForm();
                healthForm.setFilePath(filePath+userId+File.separator+fileName+"."+suffix);
                healthForm.setStatus("init");
                healthForm.setUploadTime(new Date());
                healthForm.setUserId(Long.valueOf(userId));
                healthFormService.saveHelthForm(healthForm);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResultModel(1,"上传图片异常",new LinkedHashMap());
            }

//            if (!StringUtils.isEmpty(cid)&&!StringUtils.isEmpty(templateId)){
//                pushService.pushMsg(cid,Long.valueOf(templateId));
//            }
            pushService.pushMsg("",1l);

        }
        return new ResultModel(0,"success",new LinkedHashMap());

    }

    /**
     * 上传材料
     * @param request
     * @return
     */
    @RequestMapping({"/file/upload"})
    @ResponseBody
    @ApiOperation(value = "文件上传接口",httpMethod = "POST")
    public ResultModel uploadPic(HttpServletRequest request,  MultipartFile file) {


        if (!file.isEmpty()) {

            String originalName = file.getOriginalFilename();

            String contentType = file.getContentType();

            String suffix = contentType.substring(contentType.lastIndexOf("/")+1);

            String fileName = System.currentTimeMillis()+"";

            try {
                File targetDir = new File(filePath + "0");
                if (!targetDir.exists()) {
                    targetDir.mkdirs();
                }

                File targetFile = new File(filePath+"0"+File.separator+fileName+"."+suffix);
                if (!targetFile.exists()){
                    targetFile.createNewFile();
                }
                file.transferTo(targetFile);

            } catch (IOException e) {
                e.printStackTrace();
                return new ResultModel(1,"上传图片异常",new LinkedHashMap());
            }

            return new ResultModel(0,"success",new LinkedHashMap()).put("url","/picture/"+fileName+"/"+suffix);
        }
        return new ResultModel(1,"图片大小不能为空",new LinkedHashMap());
    }

    /**
     * 下载材料
     * @return
     */
    @RequestMapping({"/healthForm/picture/{id}"})
    @ApiOperation(value = "图片下载",httpMethod = "GET")
    public void downloadMaterial(@PathVariable("id") Long id, HttpServletResponse response) {

        HealthForm healthForm = healthFormService.getById(id);
        File file = new File(healthForm.getFilePath());

        //读出文件到i/o流
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedInputStream buff = new BufferedInputStream(fis);

        byte[] b = new byte[1024];//相当于我们的缓存

        long k = 0;//该值用于计算当前实际下载了多少字节

        //从response对象中得到输出流,准备下载

        OutputStream myout = null;
        try {
            myout = response.getOutputStream();


            //开始循环下载

            while (k < file.length()) {

                int j = buff.read(b, 0, 1024);

                k += j;

                //将b中的数据写到客户端的内存

                myout.write(b, 0, j);
            }
            //将写入到客户端的内存的数据,刷新到磁盘

            myout.flush();
        } catch (IOException e) {

        }

    }

    /**
     * 下载材料
     * @return
     */
    @RequestMapping({"/picture/{fileName}/{suffix}"})
    @ApiOperation(value = "文件下载",httpMethod = "GET")
    public void downloadPic(@PathVariable String fileName,@PathVariable String suffix, HttpServletResponse response) {

        File file = new File(filePath+"0"+File.separator+fileName+"."+suffix);

        //读出文件到i/o流
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedInputStream buff = new BufferedInputStream(fis);

        byte[] b = new byte[1024];//相当于我们的缓存

        long k = 0;//该值用于计算当前实际下载了多少字节

        //从response对象中得到输出流,准备下载

        OutputStream myout = null;
        try {
            myout = response.getOutputStream();


            //开始循环下载

            while (k < file.length()) {

                int j = buff.read(b, 0, 1024);

                k += j;

                //将b中的数据写到客户端的内存

                myout.write(b, 0, j);
            }
            //将写入到客户端的内存的数据,刷新到磁盘

            myout.flush();
        } catch (IOException e) {

        }

    }

}
