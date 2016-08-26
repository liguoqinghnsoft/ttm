package com.ttm.basic.drools;

import org.drools.compiler.kie.builder.impl.KieFileSystemImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.io.ResourceType;
import org.kie.internal.io.ResourceFactory;

import java.util.List;

/**
 * Created by liguoqing on 2016/6/28.
 */
public class KieServiceBeanImpl implements KieServiceBean{

    private KieServices kieServices;
    private KieFileSystem kieFileSystem;
    private DroolsResource[] droolsResources;

    public KieServiceBeanImpl(){

    }

    public KieServiceBeanImpl(String url){
        this.droolsResources = new DroolsResource[]{
                 new DroolsResource(url,
                         DroolsResource.ResourcePathType.URL,
                         ResourceType.PKG)
        };
        buildKieService(droolsResources);
    }

    public KieServiceBeanImpl(String url,String userName,String password){
        this.droolsResources = new DroolsResource[]{
                new DroolsResource(url,
                        DroolsResource.ResourcePathType.URL,
                        ResourceType.PKG,
                        userName,
                        password)
        };
        buildKieService(droolsResources);
    }

    @Override
    public Class getServiceInterface() {
        return null;
    }

    public void buildKieService(DroolsResource[] droolsResources){
        kieServices = KieServices.Factory.get();
        kieFileSystem = new KieFileSystemImpl();
        for(DroolsResource droolsResource:droolsResources){
             switch (droolsResource.getResourcePathType()){
                 case URL:
                     this.kieFileSystem.write(ResourceFactory.newUrlResource(droolsResource.getPath()));
                 case CLASSPATH:
                     this.kieFileSystem.write(ResourceFactory.newClassPathResource(droolsResource.getPath()));
                 case FILE:
                     this.kieFileSystem.write(ResourceFactory.newFileResource(droolsResource.getPath()));
             }
        }
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        if(kieBuilder.getResults().hasMessages(Message.Level.ERROR)){
            List<Message> messageList = kieBuilder.getResults().getMessages();
            StringBuffer buffer = new StringBuffer("Errors:");
            for(Message message:messageList){
                buffer.append("\n "+ printMessage(message));
            }
        }
    }

    private String printMessage(Message message){
        return  "Message:{"+
                " Id="+message.getId()+
                ", Level="+message.getLevel()+
                ", Path="+message.getPath()+
                ", Line="+message.getLine()+
                ", Column="+message.getLine()+
                ", Text="+message.getText()
                +"}";
    }

}
