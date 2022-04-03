import lombok.Data;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Description :
 * @Date : 2022-04-03
 */
@Data
public class ActiveMQServer {
    private static ActiveMQServer instance = new ActiveMQServer();
    private Connection connection;
    public static ActiveMQServer getInstance() {
        return instance;
    }
    
    /**
     * @Description: 启动MQ服务
     *
     * @param
     * @return void
     * @Date: 2022/4/3
     */
    public void start() {
        //ConnectionFactory:连接工厂，JMS用它创建连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //构造从工厂得到连接对象
        try {
            connection = connectionFactory.createConnection();
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 创建一个会话
     *
     * @param 
     * @return 
     * @Date: 2022/4/3
     */
    public Session createSession() {
        Session session = null;
        if(null == connection) {
            start();
        }

        try {
            session =  connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return session;
    }

    public void close() {
        if(null != connection) {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
