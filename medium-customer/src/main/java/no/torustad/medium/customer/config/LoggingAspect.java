package no.torustad.medium.customer.config;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    ObjectMapper om = new ObjectMapper();

    // @Pointcut("within(@no.torustad.medium.customer.config.LoggingEnabled *) ")
    // public void loggingEnabled() {};

    // @Around("loggingEnabled()")
    @Around("execution(* no.torustad.medium.customer..*(..)) " +
            "&& !within(no.torustad.medium.customer.config. .*)" +
            "&& !within(no.torustad.medium.customer.controller.CustomerControllerAdvice)")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        logMethodInvocationAndParameters(proceedingJoinPoint);
        
        final StopWatch stopWatch = new StopWatch();

        // Measure execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        // Log method execution time
        logMethodInvocationAndParameters(proceedingJoinPoint, result, stopWatch.getTotalTimeMillis());

        return result;
    }

    private void logMethodInvocationAndParameters(ProceedingJoinPoint proceedingJoinPoint, Object result, long totalTimeMillis) {
        try {
            MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
            String classname = methodSignature.getDeclaringType().getSimpleName();
            String methodName = methodSignature.getName();
            ObjectWriter writer = om.writer();
            log.info("<- {}.{} returns:{}. tidsbruk={} ms",
                    classname,
                    methodName,
                    writer.writeValueAsString(result),
                    totalTimeMillis);
            // log.info("logMethodInvocationAndParameters: ");
        } catch (JsonProcessingException jpe) {
            log.error("Unable to write logvalue: {}", jpe.getMessage(), jpe);
        }
    }

    private void logMethodInvocationAndParameters(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            String argnames[] = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
            Object[] values = proceedingJoinPoint.getArgs();
            Map<String, Object> params = new HashMap<>();
            if (argnames!=null && argnames.length>0) {
                for (int i=0; i<argnames.length; i++) {
                    params.put(argnames[i], values[i]);
                }
            }
            ObjectWriter ow = om.writer();
            if (ow != null) {
                String classname = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
                String methodname = proceedingJoinPoint.getSignature().getName();
                // log.info("************ logMethodInvocationAndParameters: ");
                log.info("-> {}.{} invocation. params: {}",
                        classname,
                        methodname,
                        ow.writeValueAsString(params));
            }
        } catch (JsonProcessingException jpe) {
            // } catch (Exception jpe) {
            log.error("Unable to write log value: {}",
                    jpe.getMessage(), 
                    jpe);
        }
    }
}