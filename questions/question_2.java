package questions;

/**
/* 2.子线程循环10次，接着主线程循环100，接着有回到子线程循环10次，接着在回到主线程有循环100，如此循环50次，请写出程序。
*/

class Function{
    private boolean isChildTurn = true;  // 定义一个线程执行信号
    public synchronized void childThread(int i) {   // 子线程，i表示循环次数
        while(!isChildTurn) { // 主线程执行
            try {
                this.wait();    // 子线程进入等待状态
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j = 1; j <= 10 ; j++) {
            System.out.println("子线程"+j+"：第"+i+"轮");
        }
        isChildTurn = false;  // 子线程执行完毕，将执行主线程
        this.notify();  //启动上面正在等待的线程
    }
    public synchronized void mainThread(int i) {    // 主线程
        while(isChildTurn) {  // 子线程执行
            try {
                this.wait();    // 主线程等待
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j = 1; j <= 100; j ++) {
            System.out.println("主线程"+j+"：第"+i+"轮");
        }
        isChildTurn = true;
        this.notify();
    }
}

public class question_2 {
    public static void main(String[]args) {
        Function function = new Function();
        // 子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程");
                for(int i = 1; i <= 50; i ++) {
                    function.childThread(i);
                }
            }
        }
        ).start();
        // 主线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("主线程");
                for(int i = 1; i <= 50; i ++) {
                    function.mainThread(i);
                }
            }
        }
        ).start();
        System.out.print("");

        // 主线程
        /*System.out.println("主线程");
        for (int i = 1; i <= 50; i ++){
            function.mainThread(i);
        }*/
    }
}

