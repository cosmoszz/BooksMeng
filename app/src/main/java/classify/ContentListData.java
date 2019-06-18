package classify;

public class ContentListData {
    private String contentTitle;
    private int contengPic;
    private String itemUrl;

    public ContentListData()
    {

    }

    public ContentListData(String contentTitle, int contengPic,String itemUrl) {
        this.contentTitle = contentTitle;
        this.contengPic = contengPic;
        this.itemUrl=itemUrl;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public int getContengPic() {
        return contengPic;
    }

    public void setContengPic(int contengPic) {
        this.contengPic = contengPic;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
}
