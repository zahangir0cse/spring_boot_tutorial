package com.springboottutorial.spring_boot_tutorial.aspect;

import com.springboottutorial.spring_boot_tutorial.dto.Response;
import com.springboottutorial.spring_boot_tutorial.service.impl.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Configuration
public class TestAspect {
    private static final Logger logger = LogManager.getLogger(TestAspect.class.getName());

    @Around("allController() ")
    public Response testLog(ProceedingJoinPoint joinPoint){
        Object[] signatures = joinPoint.getArgs();
        HttpServletRequest incomingRequest = null;
        for (int i = 0; i < signatures.length; i++) {
            if(signatures[i] instanceof HttpServletRequest){
                incomingRequest = (HttpServletRequest) signatures[i];
                break;
            }
        }
        if(incomingRequest != null){
            logger.info(incomingRequest.getRequestURI()+ " IP: "+ incomingRequest.getRemoteAddr());
        }
        try {
            Response response = (Response) joinPoint.proceed();
            logger.info("Aspect call ended");
            return response;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


    @Pointcut("(execution(public * com.springboottutorial.spring_boot_tutorial.controller.*.*(..)) " +
            "|| execution(public * com.springboottutorial.spring_boot_tutorial.service.impl.*.*(..))) " +
            "&& !execution(public * com.springboottutorial.spring_boot_tutorial.service.impl.CustomUserDetailsService.*(..)))")
    public void allControllerAndService() {
    }


    @Pointcut("(execution(public * com.springboottutorial.spring_boot_tutorial.controller.*.*(..))  && args(..)  && !execution(public * com.springboottutorial.spring_boot_tutorial.controller.EmployeeController.*(..)) && !execution(public * com.springboottutorial.spring_boot_tutorial.controller.AuthController.getPdf(..)) && !execution(public * com.springboottutorial.spring_boot_tutorial.controller.SpringBootTestController.*(..))&& !execution(public * com.springboottutorial.spring_boot_tutorial.controller.UserController.*(..)))")
    public void allController() {
    }
}
