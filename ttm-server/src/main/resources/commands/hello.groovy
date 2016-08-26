package commands

import org.crsh.cli.Command
import org.crsh.cli.Option
import org.crsh.cli.Usage
import org.springframework.beans.factory.support.DefaultListableBeanFactory

class hello{

    @Usage("get bean from context")
    @Command
    Object get(@Option(names = ["p","beanName"]) String beanName){
        def attributes = (DefaultListableBeanFactory)context.attributes["spring.beanfactory"];
        if(null != beanName && !"".equals(beanName.trim())){
            return attributes.getBean(beanName);
        }
        return null;
    }

}