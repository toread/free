package com.toread.core.service.auth.rbac;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.toread.common.tree.ResourceTree;
import com.toread.common.tree.TreeNode;
import com.toread.common.tree.easyui.EasyUiNode;
import com.toread.core.domain.auth.rbac.Resource;
import com.toread.core.domain.auth.rbac.User;
import com.toread.core.service.BaseServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceServiceTest  extends BaseServiceTest{

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;


    @Autowired
    private UserService userService;

    @Test
    @Rollback(false)
    public void addChild() throws JsonProcessingException {
        List<Resource> all = resourceService.findAll();
        resourceService.delete(all);

        Resource resource = new Resource();
        resource.setName("1212");
        resource.setDate(Calendar.getInstance().getTime());
        resourceService.save(resource);
        Resource child = new Resource();
        child.setName("1212xx");
        child.setDate(Calendar.getInstance().getTime());
        resourceService.addChild(resource, child);
        Resource childx = new Resource();
        childx.setName("1212xx");
        childx.setDate(Calendar.getInstance().getTime());
        resourceService.addChild(resource, childx);
        Resource childxx = new Resource();
        childxx.setName("1212xx");
        child.setDate(Calendar.getInstance().getTime());
        resourceService.addChild(resource, childxx);
        Resource childxxx = new Resource();
        child.setName("1212xx");
        child.setDate(Calendar.getInstance().getTime());
        resourceService.addChild(resource, childxxx);
        Resource child2 = new Resource();
        child2.setName("1212xx");
        child2.setDate(Calendar.getInstance().getTime());
        resourceService.addChild(child, child2);
        Pageable pageable = new PageRequest(0,20);
        Map<String,Object> maps = new HashMap<String,Object>();
        maps.put("like$name","1212");
        Page<Resource> resources = resourceService.query(pageable,maps);
        List<Resource> resourceList = resourceService.findAll();
        ResourceTree resourceTree = new ResourceTree(resourceList);
        EasyUiNode easyUiNode = resourceTree.toEasyNode();
        System.out.println(easyUiNode);
        resourceTree.getRoot();
        SimplePropertyPreFilter[] SIMPLE_PROPERTY_PRE_FILTER =null;
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(ResourceTree.class,"root");
        SimplePropertyPreFilter treeNode = new SimplePropertyPreFilter(TreeNode.class,"childes","data");
        SIMPLE_PROPERTY_PRE_FILTER  = new SimplePropertyPreFilter[]{filter,treeNode};
        String value = JSONObject.toJSONString(easyUiNode, SerializerFeature.WriteMapNullValue);


        for(int i=0;i<100;i++){
            User user = new User();
            user.setName("黎志兵"+i);
            user.setAge(i);
            user.setAddress("黎志兵" + i);
            user.setSex("男");
            userService.save(user);
        }
    }
}