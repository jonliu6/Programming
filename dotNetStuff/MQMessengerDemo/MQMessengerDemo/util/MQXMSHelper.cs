using IBM.XMS;
using System;
using System.Collections.Generic;
using System.Text;

namespace MQMessengerDemo.util
{
    /// <summary>
    /// Encapsulate boiler-plate code of MQ Message Receiver/Consumer
    /// </summary>
    class MQXMSHelper
    {
        public enum MQTypes
        {
            TOPIC,
            QUEUE
        }

        private MQTypes _mqType;
        public MQTypes MQType
        {
            get { return _mqType; }
            set { _mqType = value; }
        }

        private String _host;
        public String Host
        {
            get { return _host; }
            set { _host = value; }
        }
        private int _port;
        public int Port
        {
            get { return _port; }
            set { _port = value; }
        }
        private String _channel;
        public String Channel
        {
            get { return _channel; }
            set { _channel = value; }
        }
        private String _queueManager;
        public String QueueManager
        {
            get { return _queueManager; }
            set { _queueManager = value; }
        }
        private String _consumerQueue;
        public String ConsumerQueue
        {
            get { return _consumerQueue; }
            set { _consumerQueue = value; }
        }
        private String _producerQueue;
        public String ProducerQueue
        {
            get { return _producerQueue; }
            set { _producerQueue = value; }
        }



        /// <summary>
        /// Event for signalling between threads during asynchronous messaging.
        /// </summary>
        //private System.Threading.AutoResetEvent receiveCompleteEvent = null;

        /// <summary>
        /// Initialize an MQ Connection Factory with the configuration: Host, Port, Queue Manager, Channel and etc.
        /// </summary>
        /// <returns></returns>
        public IConnectionFactory CreateMQConnectionFactoryWMQ()
        {
            XMSFactoryFactory factory = XMSFactoryFactory.GetInstance(XMSC.CT_WMQ);
            IConnectionFactory cf = factory.CreateConnectionFactory();
            cf.SetStringProperty(XMSC.WMQ_HOST_NAME, _host);
            cf.SetIntProperty(XMSC.WMQ_PORT, _port);
            // For .NET only (Java JMS is okay using WMQ_CM_CLIENT): managed connections to WMQ (WMQ_CM_CLIENT) will not support SSL connections, but these might be supported by using an unmanaged connection (WMQ_CM_CLIENT_UNMANAGED). Refer to the WebSphere MQ Using .NET manual for accurate information.
            // WebSphere MQ configures a CHLAUTH record by default in WebSphere MQ Version 7.1 and later that blocks all MQ admins from connecting as a client to the queue manager. The following error in the MQ error logs would be seen for this scenario:
            // by default is XMSC.WMQ_CM_CLIENT; however, connection exception due to code 2035 may throw
            cf.SetIntProperty(XMSC.WMQ_CONNECTION_MODE, XMSC.WMQ_CM_CLIENT_UNMANAGED);
            cf.SetStringProperty(XMSC.WMQ_CHANNEL, _channel);
            cf.SetStringProperty(XMSC.WMQ_QUEUE_MANAGER, _queueManager);
            // cf.SetIntProperty(XMSC.WMQ_BROKER_VERSION, XMSC.WMQ_BROKER_V1);

            return cf;
        }

        /// <summary>
        /// Overloading method of MQ Connection Facotry Initialization
        /// </summary>
        /// <param name="hostName"></param>
        /// <param name="portNumber"></param>
        /// <param name="channelName"></param>
        /// <param name="queueManagerName"></param>
        /// <returns></returns>
        public IConnectionFactory CreateMQConnectionFactoryWMQ(String hostName, int portNumber, String channelName, String queueManagerName)
        {
            XMSFactoryFactory factory = XMSFactoryFactory.GetInstance(XMSC.CT_WMQ);
            IConnectionFactory cf = factory.CreateConnectionFactory();
            cf.SetStringProperty(XMSC.WMQ_HOST_NAME, hostName);
            cf.SetIntProperty(XMSC.WMQ_PORT, portNumber);
            // For .NET only (Java JMS is okay using WMQ_CM_CLIENT): managed connections to WMQ (WMQ_CM_CLIENT) will not support SSL connections, but these might be supported by using an unmanaged connection (WMQ_CM_CLIENT_UNMANAGED). Refer to the WebSphere MQ Using .NET manual for accurate information.
            // WebSphere MQ configures a CHLAUTH record by default in WebSphere MQ Version 7.1 and later that blocks all MQ admins from connecting as a client to the queue manager. The following error in the MQ error logs would be seen for this scenario:
            // by default is XMSC.WMQ_CM_CLIENT; however, connection exception due to code 2035
            cf.SetIntProperty(XMSC.WMQ_CONNECTION_MODE, XMSC.WMQ_CM_CLIENT_UNMANAGED);
            cf.SetStringProperty(XMSC.WMQ_CHANNEL, channelName);
            cf.SetStringProperty(XMSC.WMQ_QUEUE_MANAGER, queueManagerName);
            return cf;
        }

        /// <summary>
        /// Initialize an MQ Connection from the Connection Factory
        /// </summary>
        /// <param name="cf"></param>
        /// <returns></returns>
        public IConnection CreateMQConnection(IConnectionFactory cf)
        {
            return cf.CreateConnection(); // or cf.CreateConnection(userId, password) if applicable            
        }

        /// <summary>
        /// Release/dispose the MQ Connection object.
        /// </summary>
        /// <param name="mqConnection"></param>
        public void DestroyMQConnection(IConnection mqConnection)
        {
            if (mqConnection != null)
            {
                mqConnection.Close();
                mqConnection.Dispose();
            }
        }

        /// <summary>
        /// Initialize an MQ Session from the given MQ Connection.
        /// </summary>
        /// <param name="conn"></param>
        /// <returns></returns>
        public ISession CreateMQSession(IConnection conn)
        {
            return conn.CreateSession(false, AcknowledgeMode.AutoAcknowledge);
        }

        /// <summary>
        /// Release/dispose the MQ Session object.
        /// </summary>
        /// <param name="sess"></param>
        public void DestroyMQSession(ISession sess)
        {
            if (sess != null)
            {
                sess.Close();
                sess.Dispose();
            }
        }

        /// <summary>
        /// Initialize an MQ Destination/Queue from the given MQ Session and Queue Name
        /// </summary>
        /// <param name="sess"></param>
        /// <param name="queueName"></param>
        /// <returns></returns>
        public IDestination CreateMQDestination(ISession sess, String name)
        {
            IDestination dest = null;
            if (MQTypes.QUEUE == _mqType)
            {
                dest = sess.CreateQueue(name);
                dest.SetIntProperty(XMSC.WMQ_TARGET_CLIENT, XMSC.WMQ_TARGET_DEST_MQ); // exclude RFH2 header since amqchange.exe cannot handle
            }
            else if (MQTypes.TOPIC == _mqType)
            {
                dest = sess.CreateTopic(name);
                dest.SetStringProperty(XMSC.WMQ_TOPIC_NAME, name);
            }
            
            return dest;
        }

        /// <summary>
        /// Release/dispose an MQ Destination/Queue object.
        /// </summary>
        /// <param name="dest"></param>
        public void DestroyMQDestination(IDestination dest)
        {
            if (dest != null)
            {
                dest.Dispose();
            }
        }

        /// <summary>
        /// Initialize an MQ Producer/Sender from the given MQ Session and Destination
        /// </summary>
        /// <param name="sess"></param>
        /// <param name="dest"></param>
        /// <returns></returns>
        public IMessageProducer CreateMQProducer(ISession sess, IDestination dest)
        {
            return sess.CreateProducer(dest);
        }

        /// <summary>
        /// Release/dispose the MQ Producer object.
        /// </summary>
        /// <param name="producer"></param>
        public void DestroyMQProducer(IMessageProducer producer)
        {
            if (producer != null)
            {
                producer.Close();
                producer.Dispose();
            }
        }

        /// <summary>
        /// Initialize an MQ Consumer/Receiver from the given MQ Session and Destination
        /// </summary>
        /// <param name="sess"></param>
        /// <param name="dest"></param>
        /// <returns></returns>
        public IMessageConsumer CreateMQConsumer(ISession sess, IDestination dest)
        {
            return sess.CreateConsumer(dest);
        }

        /// <summary>
        /// Initialize an MQ Consumer/Receiver for a pre-configured subscription by the given MQ Session and Destination and Subscription Name
        /// </summary>
        /// <param name="sess"></param>
        /// <param name="dest"></param>
        /// <param name="subscriptionName"></param>
        /// <returns></returns>
        public IMessageConsumer CreateMQDurableSubscriber(ISession sess, IDestination dest, string subscriptionName)
        {
            return sess.CreateDurableSubscriber(dest, subscriptionName);
        }

        /// <summary>
        /// Release/dispose the MQ Consumer object.
        /// </summary>
        /// <param name="consumer"></param>
        public void DestroyMQConsumer(IMessageConsumer consumer)
        {
            if (consumer != null)
            {
                consumer.Close();
                consumer.Dispose();
            }
        }

        /// <summary>
        /// Initialize an MQ Message based on the MQ Session, Message Type and Message Content.
        /// </summary>
        /// <param name="sess"></param>
        /// <param name="msgType"></param>
        /// <param name="msgStr"></param>
        /// <returns></returns>
        public IMessage CreateMQMessage(ISession sess, String msgType, String msgStr)
        {
            IMessage msg = null;
            switch (msgType)
            {
                case "text":
                    msg = sess.CreateTextMessage(msgStr);
                    // msg.SetIntProperty(XMSC.JMS_IBM_CHARACTER_SET, XMSC.CCSID_UTF8);
                    // msg.SetIntProperty(XMSC.JMS_IBM_ENCODING, XMSC.WMQ_ENCODING_NATIVE);
                    break;
                case "bytes": // not working with Java receiver
                    msg = sess.CreateBytesMessage();
                    ((IBytesMessage)msg).WriteUTF(msgStr); // or WriteBygtes(Encoding.UTF8.GetBytes(msgStr))
                    break;
                case "object": // not working with Java receiver
                    msg = sess.CreateObjectMessage();
                    byte[] byteText = Encoding.UTF8.GetBytes(msgStr);
                    ((IObjectMessage)msg).SetObject(byteText);
                    break;
            }

            return msg;
        }

        /// <summary>
        /// Send a given MQ Message by Type
        /// </summary>
        /// <param name="msgType"></param>
        /// <param name="msgStr"></param>
        public void SendMessage(String msgType, String msgStr)
        {
            IConnectionFactory connFactory = CreateMQConnectionFactoryWMQ();
            using (IConnection conn = CreateMQConnection(connFactory))  // ensure Dispose() even with exceptions, similar to try-catch
            {
                using (ISession sess = CreateMQSession(conn))
                {
                    using (IDestination dest = CreateMQDestination(sess, _producerQueue))
                    {
                        using (IMessageProducer msgSender = CreateMQProducer(sess, dest))
                        {
                            conn.Start();

                            IMessage msg = CreateMQMessage(sess, msgType, msgStr);
                            msgSender.Send(msg);

                            conn.Stop();
                        } // close producer
                    } // dispose destination
                } //  close session
            } // close connection

            // Console.WriteLine("Message sent.");
        }

        /// <summary>
        /// Overloading method of sending MQ message.
        /// </summary>
        /// <param name="session"></param>
        /// <param name="producer"></param>
        /// <param name="msgObj"></param>
        public void SendMessage(ISession session,  IMessageProducer producer, IMessage msgObj)
        {
            if (producer != null && session != null)
            {
                producer.Send(msgObj);
            }
        }

        /// <summary>
        /// less efficient version of receive message for proof-concept usage only
        /// </summary>
        /// <returns></returns>
        public String ReceiveMessage()
        {
            String msgStr = "";
            IConnectionFactory connFactory = CreateMQConnectionFactoryWMQ();
            using (IConnection conn = CreateMQConnection(connFactory))  // ensure Dispose() even with exceptions, similar to try-catch
            {
                using (ISession sess = CreateMQSession(conn))
                {
                    using (IDestination dest = CreateMQDestination(sess, _consumerQueue))
                    {
                        using (IMessageConsumer consumer = CreateMQConsumer(sess, dest))
                        {
                            //using (receiveCompleteEvent = new System.Threading.AutoResetEvent(false)) // not using ASYNC way
                            //{
                            //    MessageListener msgLsnr = new MessageListener(handleMessage);
                            //    consumer.MessageListener = msgLsnr;
                            //    conn.Start();
                            //}
                                                        
                            conn.Start();
                            IMessage msg = null;
                            msg = consumer.ReceiveNoWait(); // non-blocking
                                //Console.WriteLine(msg) ;
                                if (msg != null)
                                {
                                    if (msg is ITextMessage)
                                    {
                                        msg.SetIntProperty(XMSC.JMS_IBM_CHARACTER_SET, XMSC.CCSID_UTF8);
                                        msg.SetIntProperty(XMSC.JMS_IBM_ENCODING, XMSC.WMQ_ENCODING_NATIVE);
                                        msgStr = ((ITextMessage)msg).Text.Replace("\0", " "); // replace null-terminated characters to empty; otherwise, cannot display
                                        // tbxOutput.AppendText(((ITextMessage)msg).Text);
                                    }
                                    else if (msg is IBytesMessage)
                                    {
                                        byte[] bytes = null;
                                        int num = ((IBytesMessage)msg).ReadBytes(bytes);
                                        msgStr = Encoding.UTF8.GetString(bytes);
                                        // tbxOutput.AppendText(Encoding.UTF8.GetString(bytes));
                                    }

                                    msg.ClearBody();
                                }
                                else
                                {
                                    msgStr = "nothing received.\n";
                                }
                                consumer.Close();
                                consumer.Dispose();
                        }
                        dest.Dispose();
                    }
                    sess.Close();
                    sess.Dispose();
                }
                conn.Stop();
                conn.Close();
                conn.Dispose();
            } // close connection
            return msgStr;
        }

        /// <summary>
        /// Receive message only and non-blocking queue
        /// </summary>
        /// <param name="consumer"></param>
        /// <returns></returns>
        public String ConvertMessageObjectToString(IMessage msg)
        {
            String msgStr = null;
            if (msg != null)
            {
                if (msg is ITextMessage)
                {
                    msgStr = ((ITextMessage)msg).Text;
                    /// NOTE: there are some null-terminated characters from ES and CCS, so need to convert to whitespaces; otherwise, the message is truncated
                    //msgStr = ((ITextMessage)msg).Text.Replace("\0", " ");
                    //byte[] bytes = Encoding.UTF8.GetBytes(((ITextMessage)msg).Text);
                    //msgStr = "text <" + Encoding.UTF8.GetString(bytes) + ">";
                    //msgStr = msg.GetIntProperty(XMSC.JMS_IBM_CHARACTER_SET) + " " + msg.GetIntProperty(XMSC.JMS_IBM_ENCODING) + " " + ((ITextMessage)msg).Text;
                }
                else if (msg is IBytesMessage)
                {
                    int num = (int) ((IBytesMessage)msg).BodyLength;
                    byte[] bytes = new byte[num];
                    ((IBytesMessage)msg).ReadBytes(bytes);
                    msgStr = Encoding.UTF8.GetString(bytes);
                    
                }
                msg.Acknowledge();
                msg.ClearBody();
            }
            return msgStr;
        }
    }
}
