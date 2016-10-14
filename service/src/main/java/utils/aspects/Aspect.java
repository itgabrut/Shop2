package utils.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by ilya on 14.10.2016.
 */

@org.aspectj.lang.annotation.Aspect
public class Aspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.ilya.service..get*(..))")
    public void getEntityPointcut(){
    }

    @Pointcut("execution(* com.ilya.service..delete*(..))")
    public void deleteEntityPointcut(){
    }

    @Pointcut("execution(* com.ilya.service..add*(..))")
    public void addEntityPointcut(){
    }

    @Pointcut("execution(* com.ilya.service..update*(..))")
    public void updateEntityPointcut(){
    }

    @Before("getEntityPointcut()")
    public void loggingBeforeGetItems(JoinPoint joinPoint){
        logger.info(String.format("A method: {%s} is called, " +
                "arguments passed: {%s},", joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }
    @After("getEntityPointcut()")
    public void loggingAfterSuccess(JoinPoint joinPoint){
        logger.info(String.format("Exit get method: {%s}, Entity {%s} got",joinPoint.toString(),Arrays.toString(joinPoint.getArgs())));
    }

    @Before("deleteEntityPointcut()")
    public void deleteEntity(JoinPoint joinPoint){
        logger.warn(String.format("Trying to delete {%s} ",joinPoint.toString()));
    }
    @After("deleteEntityPointcut()")
    public void deleteEntitySuccess(JoinPoint joinPoint){
        logger.info(String.format("Success delete of {%s}",Arrays.toString(joinPoint.getArgs())));
    }

    @Before("addEntityPointcut()")
    public void beforeAddEntity(JoinPoint joinPoint){
        logger.info(String.format("Method {%s} trying to add or redact {%s} ",joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }

    @After("addEntityPointcut()")
    public void addedEntitySuccess(JoinPoint joinPoint){
        logger.info(String.format("Success added or updated by method {%s}",joinPoint.toString()));
    }

    @After("updateEntityPointcut()")
    public void afterUpdateSuccess(JoinPoint joinPoint){
        logger.info(String.format("Success updated {%s} from method: {$s}",joinPoint.getArgs(),joinPoint.toString()));
    }

    @AfterThrowing(value = "execution(* com.ilya.service..*(..))", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint, Exception ex){
        logger.error(String.format("Exception thrown at the method: %s, the message is {%s}", joinPoint.toString(), ex.getMessage()));
        logger.error("The stack trace is below");
        for (StackTraceElement b : ex.getStackTrace()) {
            logger.error("at " + b);
        }
    }

}
