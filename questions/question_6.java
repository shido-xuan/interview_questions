package questions;

/**
* 6.有一个水池，水池的容量是固定 的500L,一边为进水口,一边为出水口.要求,进水与放水不能同时进行.水池一旦满了不能继续注水,
 * 一旦放空了,不可以继续放水.进水的速度5L/s ,  放水的速度2L/s 默认水池是满的。
 *
 *    用线程模拟上述过程。
*/
class Poll {
    public int capacity = 50;  // 水池容量
    public int currentCapacity = 0;    // 当前的水池容量
    public int addSpeed = 5;    // 进水速度是5L/s
    public int releaseSpeed = 2;    // 放水速度是2L/s
    public boolean overCapacity = false;    // 设置初始化水池容量是否溢出的信号

    public Poll(int current) {
        this.currentCapacity = current; // 初始化容量
        if (current > this.capacity){
            this.overCapacity = true;
            System.out.println("超过水池容量！");
        }
    }
}
// 进水
class AddWater implements Runnable{
    Poll poll;

    public AddWater(Poll poll){
        this.poll = poll;
    }

    @Override
    public void run() {
        synchronized (poll){
            while (poll.currentCapacity < poll.capacity ){
                System.out.println("·水池以每秒5L的速度进水");
                poll.currentCapacity += poll.addSpeed;
            }
        }
        if (poll.overCapacity == false){
            System.out.println("----- 水池满了 -----");
        }
    }
}
// 放水
class ReleaseWater implements Runnable{
    Poll poll;

    public ReleaseWater(Poll poll){
        this.poll = poll;
    }

    @Override
    public void run() {
        synchronized (poll){
            while (poll.currentCapacity > 0 && poll.currentCapacity <= poll.capacity){
                System.out.println("·水池以每秒2L的速度放水");
                poll.currentCapacity -= poll.releaseSpeed;
            }
            if (poll.overCapacity == false){
                System.out.println("----- 水池空了 -----");
            }
        }
    }
}
public class question_6 {
    public static void main(String[] args) {
        Poll poll = new Poll(30);
        AddWater addWater = new AddWater(poll);
        ReleaseWater releaseWater = new ReleaseWater(poll);
        new Thread(addWater).start();
        new Thread(releaseWater).start();
    }
}
