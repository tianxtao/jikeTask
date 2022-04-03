import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Description :
 * @Date : 2022-04-03
 */
public class MQProducer {
    public static void main(String[] args) {
        ActiveMQServer server = ActiveMQServer.getInstance();
        Session session = server.createSession();
        //Destination:消息的目的地；消息发送给谁
        Destination destination = null;
        MessageProducer producer;
        try {
            //destination = session.createTopic("testTopic");
            destination = session.createQueue("testQueue");
            //消息生成者（发送者）
            producer = session.createProducer(destination);
            //不持久化
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            //构造消息
            sendMessage(session, producer);
        } catch (JMSException e) {
            e.printStackTrace();
        } finally{
            server.close();
        }
    }

    private static void sendMessage(Session session, MessageProducer producer) throws JMSException {

        for (int i=1; i<=10; i++) {
            TextMessage message = session.createTextMessage("ActiveMq发送消息：" + i);
            //发送消息到目的地方
            System.out.println("发送消息：" + "ActiveMq发送的消息" + i);
            producer.send(message);
        }

    }
}
