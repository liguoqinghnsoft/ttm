package com.ttm.basic.drools;

import org.kie.api.io.ResourceType;

/**
 * Created by liguoqing on 2016/6/28.
 */
public class DroolsResource {

    public DroolsResource(String path,ResourcePathType resourcePathType,ResourceType resourceType){
        this.path = path;
        this.resourcePathType = resourcePathType;
        this.resourceType = resourceType;
    }

    public DroolsResource(String path,ResourcePathType resourcePathType,ResourceType resourceType,String userName,String password){
        this.path = path;
        this.resourcePathType = resourcePathType;
        this.resourceType = resourceType;
        this.userName = userName;
        this.password = password;
    }

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private ResourcePathType resourcePathType;
    private ResourceType resourceType;
    private String userName;
    private String password;

    public ResourcePathType getResourcePathType() {
        return resourcePathType;
    }

    public void setResourcePathType(ResourcePathType resourcePathType) {
        this.resourcePathType = resourcePathType;
    }

    enum ResourcePathType{
        CLASSPATH,
        FILE,
        URL
    }

}
