package com.springboottutorial.spring_boot_tutorial.aspect;

import com.springboottutorial.spring_boot_tutorial.dto.Response;
import com.springboottutorial.spring_boot_tutorial.util.ResponseBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Configuration
public class DataValidationAspect {
    private static final Logger logger = LogManager.getLogger(DataValidationAspect.class.getName());

    @Around("@annotation(com.springboottutorial.spring_boot_tutorial.annotations.ValidateData) && args(..)")
    public Response validateData(ProceedingJoinPoint joinPoint){
        Object[] signatures = joinPoint.getArgs();
        BindingResult result = null;
        for (int i = 0; i < signatures.length; i++) {
            if(signatures[i] instanceof BindingResult){
                result = (BindingResult) signatures[i];
                break;
            }
        }
        if(result.hasErrors()){
            return ResponseBuilder.getFailureResponse(result, "Bean Binding error");
        }
        try {
            return (Response) joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage());
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}
