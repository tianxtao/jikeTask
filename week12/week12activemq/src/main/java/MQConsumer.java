import javax.jms.*;

/**
 * @Description :
 * @Date : 2022-04-03
 */
public class MQConsumer {
    public static void main(String[] args) {
        ActiveMQServer server = ActiveMQServer.getInstance();
        Session session = server.createSession();
        //Destination:消息的目的地；消息发送给谁
        Destination destination = null;
        MessageConsumer consumer;
        try {
            //destination = session.createTopic("testTopic");
            destination = session.createQueue("testQueue");
            consumer  = session.createConsumer(destination);

            while (true) {
                //设置接收者接受消息的时间，设置100s
                TextMessage message = (TextMessage) consumer.receive(100000);
                if(null != message){
                    System.out.println("收到消息" + message.getText());
                }else {
                    break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            server.close();
        }

    }
}
