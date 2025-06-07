package org.example;

public class Runner implements Runnable {
    private static int runnerCount;
    private final int id;
    private final int timeOut;
    private int barriersCount;
    private int finishedBarriers;
    public Runner(int barriersCount) {
        runnerCount++;
        this.id = runnerCount;
        this.barriersCount = barriersCount;
        this.timeOut = (int) (Math.random() * 100);
    }

    public int getId() {
        return id;
    }

    public int getTimeOut() {
        return timeOut;
    }

    @Override
    public void run() {
        System.out.println("Бегун " + id + " стартовал");
        while(finishedBarriers < barriersCount) {
            try {
                Thread.sleep(timeOut);
                finishedBarriers++;
                System.out.println("Бегун " + id + " пробежал барьер " + finishedBarriers);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}