package sheng.yang.wu.classLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyTest {

    public static void main(String[] args) throws Exception {
        //读取本地的class文件内的字节码，转换成字节码数组
        File file = new File(".");
        InputStream input = new FileInputStream(file.getCanonicalPath()+"\\src\\main\\java\\sheng\\yang\\wu\\classLoader\\Programmer.class");
        byte[] result = new byte[1024];

        int count = input.read(result);
        //使用自定义的类加载器将byte字节码数组转换为对应的class对象
        MyClassLoader loader = new MyClassLoader();
        Class clazz = loader.defineMyClass(result, 0, count);
        //测试加载是否成功郭，打印class对象的名称
        System.out.println(clazz.getCanonicalName());

        //实例化一个Programmer对象
        Object o = clazz.newInstance();
        clazz.getMethod("code",null).invoke(o,null);
        System.out.println(o);


    }
}
