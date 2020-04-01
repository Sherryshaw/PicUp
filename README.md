

## 什么是PicUp

主要为了实现Java程序可以便捷的将图片上传至图床并获取的上传链接的功能。

## 愿景

希望在经过版本的迭代后，可以适配多家图床的API，从而达到一站式上传图片至图床，并且方便更换图床的目标。

## 已适配的图床

* 七牛云

## 快速开始

```java
		//你的ACCESS_KEY。
        String ACCESS_KEY="ACCESS_KEY";
		//你的SECRET_KEY。
        String SECRET_KEY="SECRET_KEY";
		//你的BUCKET。
        String BUCKET="BUCKET";

		//新建一个Picture类并且引入七牛云的配置类。
        Picture picture=new Picture(new QiNiuConfig(ACCESS_KEY,SECRET_KEY,BUCKET));
		//上传你的file类图片，并得到Response结果。
        Response response=picture.upload(new File("C:\\Users\\94471\\Desktop\\nginx-banner.png"));
		//图片上传成功时，获取到上传至图床的链接。
        if(response.success){
            System.out.println(response.getPictureLink());
        }
```

