package org.chenzx.island.config.aop;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 全局统一返回格式配置
 * @date 2022/7/13 15:17
 */
@Slf4j
@Aspect
@Component
public class InterfaceExecutionTimeAop {

    @Pointcut("execution(* org.chenzx.island.controller..*.*(..))")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object timing(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object;
        Stopwatch stopwatch = Stopwatch.createStarted();

        object = joinPoint.proceed();

        log.info("execute {} success! args: {} ,execution time: {}"
                , joinPoint.getTarget().getClass().getName()
                , joinPoint.getArgs(), stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        return object;
    }

}
