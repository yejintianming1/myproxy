package sheng.yang.wu.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerImpl implements InvocationHandler {

    private ElectricCar car;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("You are going to invoke "+method.getName()+" ...");
        method.invoke(car, null);
        System.out.println(method.getName()+" invocation Has Been finished...");
        return proxy;
    }

    public InvocationHandlerImpl() {
    }

    public InvocationHandlerImpl(ElectricCar car) {
        this.car = car;
    }
}
