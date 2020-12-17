package com.hao.multidatabase.aspect;

import com.hao.multidatabase.annotation.RoutingWith;
import com.hao.multidatabase.config.RoutingDataSourceConfig;
import com.hao.multidatabase.constant.DataSourceConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Administrator
 */
@Aspect
@Component
public class RoutingAspect {
    @Pointcut("@annotation(RoutingWith)")
    public void routingWith() {
    }

    @Around("routingWith() && @annotation(routingWith)")
    public Object routingWithDataSource(ProceedingJoinPoint joinPoint, RoutingWith routingWith) throws Throwable {
        if (routingWith.readOnly()) {
            final String slave = loadBalance();
            RoutingDataSourceConfig.setDataSource(slave);
        } else {
            RoutingDataSourceConfig.setDataSource(DataSourceConstant.MASTER);
        }
        return joinPoint.proceed();
    }

    private String loadBalance() {
        Random random = new Random();
        final int i = random.nextInt(1) + 1;
        switch (i) {
            case 1:
                return DataSourceConstant.SLAVE;
        }
        return DataSourceConstant.SLAVE;
    }
}
