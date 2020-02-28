package cn.codertian.picup;

import cn.codertian.commons.Response;

import java.io.File;

public class Picture {
    private final int PICTURE_QiNiu=101;
    private QiNiuConfig qiNiuConfig;
    private int nowPicture;

    /**
     * 图片上传的构造方法
     * @param qiNiuConfig 七牛云的配置类
     */
    public Picture(QiNiuConfig qiNiuConfig){
        this.qiNiuConfig=qiNiuConfig;
        nowPicture=PICTURE_QiNiu;
    }

    public Response upload(File file){
        String backLink;
        switch (nowPicture){
            case PICTURE_QiNiu:{
                try{
                    backLink=qiNiuConfig.upload(file);
                }catch(Exception ex){
                    return new Response(ex);
                }
            }break;
            default:
                backLink="";
        }
        if(backLink==null||backLink.equals("")){
            return new Response(new Exception("文件上传失败！"));
        }
        return new Response(backLink);
    }

}
