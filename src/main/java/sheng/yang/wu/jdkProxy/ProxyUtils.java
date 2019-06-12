package sheng.yang.wu.jdkProxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class ProxyUtils {

    /**
     * 将根据类信息 动态生成的二进制字节码保存到硬盘中，
     * 默认的时clazz目录下
     * @param clazz 需要生成动态代理类的类，即被代理类
     * @param proxyName 为动态生成的代理类的名称
     */
    public static void generateClassFile(Class clazz,String proxyName) {
        //根据类信息和提供的代理类名称，生成字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName,clazz.getInterfaces());
        String paths = clazz.getResource(".").getPath();
        System.out.println(paths);

        try (
                //保留到硬盘中
                FileOutputStream out = new FileOutputStream(paths+proxyName+".class");
                ){
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
