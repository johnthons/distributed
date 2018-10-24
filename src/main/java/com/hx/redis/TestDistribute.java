package com.hx.redis;

public class TestDistribute {
    public static void main(String[] args) {
        final DistributeLockService service = new DistributeLockService();

        for (int i = 0; i < 50; i++) {
        /*    new Thread() {
                @Override
                public void run() {
                    service.seckill();
                }
            }.start();
        }*/

           new Thread(new Runnable() {
               public void run() {
                   service.seckill();
               }
           }).start();
        }
    }

}
