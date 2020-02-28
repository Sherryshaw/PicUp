package cn.codertian.commons;

public class Response {
    public final boolean success;
    private String pictureLink;
    private Exception uploadException;

    public Response(String pictureLink) {
        success=true;
        this.pictureLink = pictureLink;
    }

    public Response(Exception uploadException) {
        success=false;
        this.uploadException = uploadException;
    }

    public String getPictureLink() {
        return pictureLink;
    }
}
