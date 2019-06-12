package sheng.yang.wu.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 实现了方法拦截器接口
 */
public class Hacker implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("xxxx I am hacker,Let's see what the poor programmer is doing Now...");
        methodProxy.invokeSuper(obj, args);
        System.out.println("xxxx Oh,what a poor programmer.....");
        return null;
    }
}
