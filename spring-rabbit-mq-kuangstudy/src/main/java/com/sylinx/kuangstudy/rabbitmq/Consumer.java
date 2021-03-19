package com.sylinx.kuangstudy.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            ConnectionFactory connectionFactory = null;
            Channel channel = null;
            Connection connection =null;
            try {
                connectionFactory = new ConnectionFactory();
                connectionFactory.setHost("localhost");
                connectionFactory.setPort(5672);
                connectionFactory.setUsername("admin");
                connectionFactory.setPassword("admin123");
                connectionFactory.setVirtualHost("/");
                connection = connectionFactory.newConnection("simpleProducer");
                channel = connection.createChannel();

                /**
                 * @param1 队列名称
                 * @param2 是否持久化
                 * @param3 排他性 是否独占独立
                 * @param4 是否自动删除 随着最后一个消费者消息完毕之后是否把队列删除
                 * @param5 携带附属参数
                 * 可以存在没有交换机的队列么？不能，虽然没有指定，但是一定会有一个默认交换机
                 */
                String queueName = Thread.currentThread().getName();
                channel.basicConsume(queueName, true, new DeliverCallback() {
                    @Override
                    public void handle(String s, Delivery delivery) throws IOException {

                        System.out.println(queueName + " : " + delivery.getEnvelope().getDeliveryTag());
                        System.out.println("message : " + new String(delivery.getBody(), "UTF-8"));
                    }
                }, new CancelCallback() {
                    @Override
                    public void handle(String s) throws IOException {
                        System.out.println("message recieve fail!");
                    }
                });
                System.out.println("start consumer");
                System.in.read();

            }catch(Exception e) {
                e.printStackTrace();
            }finally {
                if(channel != null&& channel.isOpen()) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                }
                if(connection!= null) {
                    try {
                        connection.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    };

    public static void main(String[] args) throws IOException, TimeoutException {

        new Thread(runnable, "queue1").start();
        new Thread(runnable, "queue2").start();
        new Thread(runnable, "queue3").start();
        new Thread(runnable, "queue4").start();
        new Thread(runnable, "queue5").start();
        new Thread(runnable, "queue6").start();
        new Thread(runnable, "queue7").start();
    }
}

