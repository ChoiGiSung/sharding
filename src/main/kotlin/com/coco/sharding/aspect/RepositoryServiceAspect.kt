package com.coco.sharding.aspect

import com.coco.sharding.Sharding
import com.coco.sharding.UserHolder
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Component
@Aspect
class RepositoryServiceAspect {

    @Pointcut("execution(* com.coco.sharding.repository.*RepositoryService.*(..))")
    private fun repositoryService() {
    }

    @Around("repositoryService() && @with(sharding) && args(shardKey,..)")
    fun handler(proceedingJoinPoint: ProceedingJoinPoint, sharding: Sharding, shardKey: Long): Any {
        UserHolder.setSharding(sharding.target, shardKey)

        val result = proceedingJoinPoint.proceed()

        UserHolder.clearSharding()

        return result
    }
}