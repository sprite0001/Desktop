package com.wooxun.geekdol.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wooxun.geekdol.geekstore.model.GoodsClassific;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author Administrator	
 * @CreateDate 2016年5月12日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 Administrator	2016年5月12日  下午1:33:35 		创建
 *==========================================================
 * 
 */
public class OrderTreeObject {
    private List<GoodsClassific> nodes;
    private List<Map<String,Object>> treeMap = new ArrayList<Map<String,Object>>();
    
    public OrderTreeObject(){
    }
    
    public void orderToTypetree(){
        for (GoodsClassific node : nodes) {
            if(node.getParentId()==0L){
               Map<String,Object> mp = new HashMap<String,Object>();
               mp.put("text", node.getName());
               mp.put("id", node.getId());
               treeMap.add(mp);
               build(node,mp);
            }
        }
    }
    
    private void build(GoodsClassific node,Map<String,Object> mp){
        List<GoodsClassific> children = getChildren(node);
        if (!children.isEmpty()) {
            List<Map<String,Object>> cList = new ArrayList<Map<String,Object>>();
            mp.put("children", cList);
            for (GoodsClassific child : children) {
                Map<String,Object> mc = new HashMap<String,Object>();
                mc.put("text", child.getName());
                mc.put("id", child.getId());
                cList.add(mc);
                build(child,mc);
            }
        }
    }
    
    private List<GoodsClassific> getChildren(GoodsClassific node){
        List<GoodsClassific> children = new ArrayList<GoodsClassific>();
        Long id = node.getId();
        for (GoodsClassific child : nodes) {
            if (id.equals(child.getParentId())) {
                children.add(child);
            }
        }
        return children;
    }
    
    public void setNodes(List<GoodsClassific> nodes){
        this.nodes =nodes;
    }
    public List<Map<String,Object>> getTreeMap(){
        return this.treeMap;
    }
}
