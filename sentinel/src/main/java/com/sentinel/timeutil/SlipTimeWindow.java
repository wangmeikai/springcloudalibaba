package com.sentinel.timeutil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 滑动时间窗口的实现
 *
 * @USER: WangMeiKai
 * @DATE: 2021/4/18
 * @TIME: 19:29
 **/

public class SlipTimeWindow {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new LinkedList<>();

        int threadCount = 0;

        Random random = new Random();

        while (true) {
            int TimeWindowThreadCount = (list.size() == 0 ? 0 : list.get(list.size() - 1) - list.get(0));
            if (TimeWindowThreadCount > 10) {
                System.out.println("限流===========");
                if (list.size() >= 10) {
                    list.remove(0);
                }
                list.add(threadCount);
                TimeUnit.SECONDS.sleep(2);
                continue;
            }
            if (list.size() >= 10) {
                list.remove(0);
            }

            threadCount += random.nextInt(20);
            list.add(threadCount);

            System.out.println(Arrays.toString(list.toArray()));

            TimeUnit.SECONDS.sleep(2);
        }

    }
}
