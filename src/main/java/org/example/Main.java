package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AppConfig config = new AppConfig();

        int barriersCount = Integer.parseInt(config.getProperty("app.runners"));
        int runnersCount = Integer.parseInt(config.getProperty("app.barrriers"));

        Runner[] runners = new Runner[runnersCount];
        Thread[] runnerThreads = new Thread[runnersCount];

        for (int i = 0; i < runnersCount; i++) {
            runners[i] = new Runner(barriersCount);
            runnerThreads[i] = new Thread(runners[i]);
        }

        for (int i = 0; i < runnersCount; i++) {
            runnerThreads[i].start();
        }

        for (int i = 0; i < 3; i++) {
            runnerThreads[i].join();
        }

        Runner winner = runners[0];
        Runner loser = runners[0];

        for (int i = 0; i < 3; i++) {
            if (runners[i].getTimeOut() < winner.getTimeOut()) {
                winner = runners[i];
            }
            if (runners[i].getTimeOut() > loser.getTimeOut()) {
                loser = runners[i];
            }
        }

        // Вывод результатов
        System.out.println("Победитель: Спортсмен № " + winner.getId());
        System.out.println("Аутсайдер: Спортсмен № " + loser.getId());


    }
}