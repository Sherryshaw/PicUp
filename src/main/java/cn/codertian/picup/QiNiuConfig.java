package cn.codertian.picup;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;

import java.io.File;
import java.util.UUID;


public class QiNiuConfig {
    private String ACCESS_KEY;
    private String SECRET_KEY;
    private String BUCKET;
    private UploadManager uploadManager;
    private BucketManager bucketManager;
    private Auth auth;

    /**
     * 七牛云配置的构造方法
     * @param ACCESS_KEY 七牛云账号的AK
     * @param SECRET_KEY 七牛云账号的SK
     * @param BUCKET 七牛云的对象存储名
     */
    public QiNiuConfig(String ACCESS_KEY,String SECRET_KEY,String BUCKET) {
        this.ACCESS_KEY = ACCESS_KEY;
        this.SECRET_KEY = SECRET_KEY;
        this.BUCKET = BUCKET;
        Configuration configuration=new Configuration();
        uploadManager=new UploadManager(configuration);
        auth=Auth.create(ACCESS_KEY,SECRET_KEY);
        bucketManager=new BucketManager(auth,configuration);
    }

    /**
     * 七牛云文件上传的方法
     * @param file 需要上传的文件
     * @return
     */
    public String upload(File file) throws QiniuException {
        String returnValue="";
        String suffix=file.getName().substring(file.getName().lastIndexOf("."));
        String filename= UUID.randomUUID().toString().replace("-", "")+suffix;
        String upToken=auth.uploadToken(BUCKET,filename);
        Response response=uploadManager.put(file,filename,upToken);
        DefaultPutRet putRet=new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
        String key=putRet.key;
        String[] domain=bucketManager.domainList(BUCKET);
        FileInfo fileInfo=bucketManager.stat(BUCKET,key);
        String header="http://";
        returnValue=header+domain[0]+"/"+key;
        return returnValue;
    }
}
