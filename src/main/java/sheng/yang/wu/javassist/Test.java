package sheng.yang.wu.javassist;

import javassist.*;
import sheng.yang.wu.dynamic.Station;
import sheng.yang.wu.dynamic.TicketService;

import java.lang.reflect.Constructor;

public class Test {

    public static void main(String[] args) throws Exception {
        createProxy();
//        TicketService ticketService = new StationProxy(new Station());
//        ticketService.inquire();
    }

    /**
     * 手动创建字节码
     */
    private static void createProxy() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("sheng.yang.wu.dynamic.StationProxy");

        //设置接口
        CtClass interface1 = pool.get("sheng.yang.wu.dynamic.TicketService");
        cc.setInterfaces(new CtClass[]{interface1});

        //设置Field
        CtField field = CtField.make("private sheng.yang.wu.dynamic.Station station;", cc);

        cc.addField(field);

        CtClass stationClass = pool.get("sheng.yang.wu.dynamic.Station");
        CtClass[] arrays = new CtClass[]{stationClass};
        CtConstructor ctc = CtNewConstructor.make(arrays,null,CtNewConstructor.PASS_NONE,null,null,cc);
        //设置构造函数内部信息
        ctc.setBody("{this.station=$1;}");
        cc.addConstructor(ctc);

        //创建收取手续 takeHandlingFee方法
        CtMethod takeHandlingFee = CtMethod.make("private void takeHandlingFee() {}",cc);
        takeHandlingFee.setBody("System.out.println(\"收取手续费，打印发票.....\");");
        cc.addMethod(takeHandlingFee);

        //创建showAlertInfo方法
        CtMethod showInfo = CtMethod.make("private void showAlertInfo(String info) {}", cc);
        showInfo.setBody("System.out.println($1);");
        cc.addMethod(showInfo);

        //创建sellTicket方法
        CtMethod sellTicket = CtMethod.make("public void sellTicket() {}", cc);
        sellTicket.setBody("{this.showAlertInfo(\"xxxx您正在使用车票代售点进行购票，每张票将会收取5元手续费！xxxx\");"
        +"station.sellTicket();"
        +"this.takeHandlingFee();"
        +"this.showAlertInfo(\"xxxx欢迎您的光临，再见！xxxx\\n\");}");
        cc.addMethod(sellTicket);

        //添加inquire方法
        CtMethod inquire = CtMethod.make("public void inquire() {}", cc);
        inquire.setBody("{this.showAlertInfo(\"xxxx欢迎光临本代售点，问询服务不会收取任何费用，本问询信息仅供参考，具体信息以车站真实数据为准！xxxx\");"
        + "station.inquire();"
        +"this.showAlertInfo(\"xxxx欢迎您的光临，再见！xxxx\\n\");}");
        cc.addMethod(inquire);

        //添加withdraw方法
        CtMethod withdraw = CtMethod.make("public void withdraw() {}",cc);
        withdraw.setBody("{this.showAlertInfo(\"xxxx欢迎光临本代售点，退票除了扣除票额的20%外，本代理处额外加收2元手续费！xxxx\");"
        +"station.withdraw();"
        +"this.takeHandlingFee();}");
        cc.addMethod(withdraw);

        //获取动态生成的class
        Class c = cc.toClass();
        //获取构造器
        Constructor constructor = c.getConstructor(Station.class);
        //通过构造器实例化
        TicketService o = (TicketService)constructor.newInstance(new Station());
        o.inquire();

        cc.writeFile("E://code_work//idea//learn//myproxy//myproxy//src//main//java");


    }
}
