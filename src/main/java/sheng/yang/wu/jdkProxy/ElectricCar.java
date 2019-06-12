package sheng.yang.wu.jdkProxy;

/**
 * 电能车类，实现Rechargable, Vehicle接口
 */
public class ElectricCar implements Rechargable,Vehicle {
    public void recharge() {
        System.out.println("Electric Car is Recharging...");
    }

    public void drive() {
        System.out.println("Electric Car is Moving silently...");
    }
}
