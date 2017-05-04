package com.wooxun.geekdol.geekstore.vo;
/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863SOFT-QZG
 * @CreateDate 2016年7月27日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 tj	2016年7月27日  上午11:58:25 		创建
 *==========================================================
 * 
 */
public class GoodsRecommedVo {

    
    private int id;
    private String   thumbnail;
    private String   goodsname;   
   private String  preferentialprice;
    private String  sellprice;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public String getGoodsname() {
        return goodsname;
    }
    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }
    public String getPreferentialprice() {
        return preferentialprice;
    }
    public void setPreferentialprice(String preferentialprice) {
        this.preferentialprice = preferentialprice;
    }
    public String getSellprice() {
        return sellprice;
    }
    public void setSellprice(String sellprice) {
        this.sellprice = sellprice;
    }
    
    
}
