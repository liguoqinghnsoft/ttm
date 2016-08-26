package com.ttm.basic.aop;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Modifier;

/**
 * Created by liguoqing on 2016/6/7.
 */
@Aspect
@Configuration
public class LoggerAop {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAop.class);

    @Pointcut(value = "execution(* com.ttm.basic.service.impl..*.*(..))")
    public void executeService() {
        logger.info("LoggerAop.executeService()");
    }

    @AfterThrowing(pointcut = "executeService()",throwing = "e")
    public void executeAfterThrowable(JoinPoint joinPoint,Exception e){
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        //throw new GlobalException(e.getCause());
        logger.error("executeAfterThrowable->"+declaringTypeName + "." + methodName + "()",e);
    }

    @AfterReturning(pointcut = "executeService()",returning = "result")
    public void executeAfterReturn(JoinPoint joinPoint,Object result){
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("executeAfterReturn->"+declaringTypeName + "." + methodName + "(),result:{}", result);
    }

    @Before("executeService()")
    //@Around("executeService()")
    public void executeServiceMethod(JoinPoint joinPoint) throws Throwable{
        Object[] objects = joinPoint.getArgs();
        Class<?> classZ = joinPoint.getTarget().getClass();
        String className = classZ.getName();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String[] paramNames = getFiledName(this.getClass(), className, methodName);
        StringBuffer bufferParam = new StringBuffer();
        for (int i=0;i<objects.length;i++) {
            bufferParam.append(paramNames[i]+":"+objects[i]+",");
        }
        if(bufferParam.length() > 0) {
            logger.info("executeServiceMethod->"+declaringTypeName + "." + methodName + "() " + bufferParam.toString().substring(0,bufferParam.length()-1));
        }
    }

    private String[] getFiledName(Class cls, String className, String methodName) {
        ClassPool classPool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        classPool.insertClassPath(classPath);
        String[] paramNames = null;
        try {
            CtClass ctClass = classPool.get(className);
            CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);
            MethodInfo methodInfo = ctMethod.getMethodInfo();
            CodeAttribute attribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute localVariableAttribute = (LocalVariableAttribute) attribute.getAttribute(LocalVariableAttribute.tag);
            paramNames = new String[ctMethod.getParameterTypes().length];
            int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
            for(int i=0;i<paramNames.length;i++){
                paramNames[i] = localVariableAttribute.variableName(i + pos);
            }
        } catch (NotFoundException e) {
            logger.error(cls.getSimpleName()+" getFiledName() error.",e);
        }
        return paramNames;
    }

}
