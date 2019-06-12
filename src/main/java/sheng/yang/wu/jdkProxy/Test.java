package sheng.yang.wu.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {

        //该设置用于输出jdk动态代理产生的类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ElectricCar car = new ElectricCar();
        //1.获取对应的ClassLoader
        ClassLoader classLoader = car.getClass().getClassLoader();
        //2.获取ElectricCar所实现的所有接口
        Class[] interfaces = car.getClass().getInterfaces();
        //3.设置一个来自代理传过来的方法调用请求处理器，处理所有的代理对象上的方法调用
        InvocationHandler handler = new InvocationHandlerImpl(car);
        /*
         * 4.根据上面提供的信息，创建代理对象 在这个过程中，
         *    a.JDK会通过根据传入的参数信息动态地在内存中创建和.class文件等同的字节码
         *    b.然后根据相应的字节码转换成对应的class,
         *    c.然后调用newInstance()创建实例
         */
        Object o = Proxy.newProxyInstance(classLoader, interfaces, handler);
        Vehicle vehicle = (Vehicle) o;
        vehicle.drive();
        Rechargable rechargable = (Rechargable) o;
        rechargable.recharge();

        //生成动态代理类的.class文件
//        ProxyUtils.generateClassFile(ElectricCar.class,"ElectricCarProxy");

    }
}
