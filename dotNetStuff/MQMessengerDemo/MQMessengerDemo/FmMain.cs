using IBM.XMS;
using IBM.WMQ;
using System;
using System.Collections;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using MQMessengerDemo.util;

namespace MQMessengerDemo
{
    public partial class FmMain : Form
    {
        private PropertyFileHelper _props;
        private MQXMSHelper _mqHelper;
        MQQueueManager queueManager = null;
        MQTopic subscriber = null;

        // global varibles for XMS objects
        IConnectionFactory _mqConnectionFactory;
        IConnection _mqConnection;
        ISession _mqSession;
        IDestination _mqDestination;
        IMessageConsumer _mqConsumer;

        public FmMain()
        {
            InitializeComponent();
            _props = new PropertyFileHelper("MQMessengerDemo.properties");
            _mqHelper = new MQXMSHelper();
            if (rbSender.Checked)
            {
                RefreshSenderProperties();
            }
            else
            {
                RefreshReceiverProperties();
            }
        }

        private void RefreshSenderProperties()
        {
            txHost.Text = _props.GetProperty("mq.producer.host");
            txPort.Text = Int32.Parse(_props.GetProperty("mq.producer.listenerPort")) + "";
            txQMgr.Text = _props.GetProperty("mq.producer.queueManager");
            txQChannel.Text = _props.GetProperty("mq.producer.channel");
            txQName.Text = _props.GetProperty("mq.producer.queueOrTopicName");

            btnMessage.Text = "Send";
        }

        private void RefreshReceiverProperties()
        {
            txHost.Text = _props.GetProperty("mq.consumer.host");
            txPort.Text = Int32.Parse(_props.GetProperty("mq.consumer.listenerPort")) + "";
            txQMgr.Text = _props.GetProperty("mq.consumer.queueManager");
            txQChannel.Text = _props.GetProperty("mq.consumer.channel");
            txQName.Text = _props.GetProperty("mq.consumer.queueOrTopicName");

            btnMessage.Text = "Receive";
        }

        private void RefreshFormForTopic()
        {
            rbSender.Text = "Publisher";
            rbReceiver.Text = "Subscriber";
            lblTopicOrQueue.Text = "Topic String";

            if (rbTopic.Checked && rbReceiver.Checked)
            {
                gbxSubscriptionDurablity.Visible = true;
                btnPublishXMS.Visible = false;
                btnSubscribeXMS.Visible = true;
                btnUnsubscribeXMS.Visible = false;
            }
            else
            {
                gbxSubscriptionDurablity.Visible = false;
                btnPublishXMS.Visible = true;
                btnSubscribeXMS.Visible = false;
                btnUnsubscribeXMS.Visible = false;
            }
        }

        private void RefreshFormForQueue()
        {
            rbSender.Text = "Sender";
            rbReceiver.Text = "Receiver";
            lblTopicOrQueue.Text = "Queue Name";
            gbxSubscriptionDurablity.Visible = false;

            btnPublishXMS.Visible = false;
            btnSubscribeXMS.Visible = false;
            btnUnsubscribeXMS.Visible = false;
        }

        private void rbTopic_CheckedChanged(object sender, EventArgs e)
        {
            if (((RadioButton)sender).Checked)
            {
                RefreshFormForTopic();
            }
        }

        private void rbQueue_CheckedChanged(object sender, EventArgs e)
        {
            if (((RadioButton)sender).Checked)
            {
                RefreshFormForQueue();
            }
        }

        private void rbSender_CheckedChanged(object sender, EventArgs e)
        {
            if (((RadioButton)sender).Checked)
            {
                RefreshSenderProperties();
            }

            if (rbTopic.Checked)
            {
                gbxSubscriptionDurablity.Visible = false;
                btnPublishXMS.Visible = true;
                btnSubscribeXMS.Visible = false;
                btnUnsubscribeXMS.Visible = false;
            }
        }

        private void rbReceiver_CheckedChanged(object sender, EventArgs e)
        {
            if (((RadioButton)sender).Checked)
            {
                RefreshReceiverProperties();
            }

            if (rbTopic.Checked)
            {
                gbxSubscriptionDurablity.Visible = true;
                btnPublishXMS.Visible = false;
                btnSubscribeXMS.Visible = true;
                btnUnsubscribeXMS.Visible = false;
            }
        }

        private void btnMessage_Click(object sender, EventArgs e)
        {
            btnUnsubscribe.Visible = false;
            if (rbTopic.Checked)
            {
                if (rbSender.Checked)
                {
                    PublishMessage();
                }
                else
                {
                    SubscribeMessage();
                    btnMessage.Visible = false;
                    btnUnsubscribe.Visible = true;
                }
            }
            else
            {
                if (rbSender.Checked)
                {
                    ProduceMessageXMS();
                }
                else
                {
                    ConsumeMessageXMS();
                }
            }
        }

        private void PublishMessageXMS()
        {
            // option 2: XMS
            // NOTE: in MQ, Topic --> Object Authorities --> Manage Authority Records --> Specific Profiles, make sure the users/groups (using the XMS API calls) have the proper permissions, eg. Publish, Subscribe and etc.
            XMSFactoryFactory ff = null;
            IConnectionFactory cf = null;
            IConnection conn = null;
            ISession sess = null;
            IDestination dest = null;
            IMessageProducer prod = null;
            ITextMessage tmsg = null;
            string topicName = txQName.Text.ToString();

            try
            {
                ff = XMSFactoryFactory.GetInstance(XMSC.CT_WMQ);
                cf = ff.CreateConnectionFactory();
                cf.SetStringProperty(XMSC.WMQ_HOST_NAME, txHost.Text.ToString());
                int portNo = 0;
                Int32.TryParse(txPort.Text.ToString(), out portNo);
                cf.SetIntProperty(XMSC.WMQ_PORT, portNo);
                cf.SetStringProperty(XMSC.WMQ_CHANNEL, txQChannel.Text.ToString());
                cf.SetIntProperty(XMSC.WMQ_CONNECTION_MODE, XMSC.WMQ_CM_CLIENT_UNMANAGED); // default is XMSC.WMQ_CM_CLIENT, it is managed - using the same language; if need to access different platform, use unmanaged
                cf.SetStringProperty(XMSC.WMQ_QUEUE_MANAGER, txQMgr.Text.ToString());

                /*
                MessageBox.Show("Host:" + cf.GetStringProperty(XMSC.WMQ_HOST_NAME) + Environment.NewLine +
                    "Port:" + cf.GetIntProperty(XMSC.WMQ_PORT) + Environment.NewLine +
                    "Queue Manager:" + cf.GetStringProperty(XMSC.WMQ_QUEUE_MANAGER) + Environment.NewLine +
                    "Channel:" + cf.GetStringProperty(XMSC.WMQ_CHANNEL) + Environment.NewLine +
                    "Topic:" + topicName + Environment.NewLine);
                 */

                conn = cf.CreateConnection();
                sess = conn.CreateSession(false, AcknowledgeMode.AutoAcknowledge);
                // MessageBox.Show("Session created.");
                dest = sess.CreateTopic(topicName);
                // dest.SetIntProperty(XMSC.WMQ_TARGET_CLIENT, XMSC.WMQ_TARGET_DEST_MQ); // exclude RFH2 header since amqchange.exe cannot handle
                // MessageBox.Show("Destination created as Topic.");
                prod = sess.CreateProducer(dest);
                // MessageBox.Show("Message Producer created.");

                conn.Start();
                // MessageBox.Show("Connection started.");
                tmsg = sess.CreateTextMessage(txMessage.Text.ToString());

                prod.Send(tmsg);
                MessageBox.Show("Message " + tmsg.Text.Length + " sent to " + topicName);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message + Environment.NewLine + ex.StackTrace + Environment.NewLine + ex.Source + Environment.NewLine + ex.GetBaseException());
            }
            finally
            {
                if (prod != null)
                {
                    prod.Close();
                }
                if (dest != null)
                {
                    dest.Dispose();
                }
                if (sess != null)
                {
                    sess.Close();
                    sess.Dispose();
                }
                if (conn != null)
                {
                    conn.Close();
                    conn.Dispose();
                }
            }
        }

        private void PublishMessage()
        {
            // txMessage.Text = "Publishing...";
            try
            {
                //_mqHelper.Host = txHost.Text;
                //_mqHelper.Port = Int32.Parse(txPort.Text);
                _mqHelper.QueueManager = txQMgr.Text;
                //_mqHelper.Channel = txQChannel.Text;
                _mqHelper.ProducerQueue = txQName.Text;
                //_mqHelper.MQType = MQXMSHelper.MQTypes.TOPIC;

                // option 1: WMQ
                // MQQueueManager queueManager = null;
                MQTopic topic = null;
                MQMessage message = null;
                string msg = txMessage.Text.ToString();

                Hashtable properties = new Hashtable();
                properties = new Hashtable();
                properties.Add(IBM.WMQ.MQC.HOST_NAME_PROPERTY, _mqHelper.Host);
                properties.Add(IBM.WMQ.MQC.PORT_PROPERTY, _mqHelper.Port);
                properties.Add(IBM.WMQ.MQC.CHANNEL_PROPERTY, _mqHelper.Channel);
                properties.Add(IBM.WMQ.MQC.TRANSPORT_PROPERTY, IBM.WMQ.MQC.TRANSPORT_MQSERIES_CLIENT); //unmanaged, or use TRANSPORT_MQSERIES_MANAGED

                // queueManager = new MQQueueManager(_mqHelper.QueueManager, properties);
                queueManager = new MQQueueManager(_mqHelper.QueueManager);
                // MessageBox.Show("Queue Manager created as:" + Environment.NewLine + "Host:" + _mqHelper.Host + Environment.NewLine + "Port:" + _mqHelper.Port + Environment.NewLine + "Channel:" + _mqHelper.Channel + Environment.NewLine + "Queue Manager:" + _mqHelper.QueueManager);
                // MessageBox.Show("Queue Manager created!");
                // Note: API signiture is confusing, the 1st parameter of AccessTopic is actually Topic String, NOT Topic Name
                topic = queueManager.AccessTopic(_mqHelper.ProducerQueue, null, IBM.WMQ.MQC.MQTOPIC_OPEN_AS_PUBLICATION, IBM.WMQ.MQC.MQOO_OUTPUT + IBM.WMQ.MQC.MQOO_FAIL_IF_QUIESCING);
                // topic = queueManager.AccessTopic(_mqHelper.ConsumerQueue, null, IBM.WMQ.MQC.MQTOPIC_OPEN_AS_SUBSCRIPTION, IBM.WMQ.MQC.MQSO_CREATE | IBM.WMQ.MQC.MQSO_FAIL_IF_QUIESCING | IBM.WMQ.MQC.MQSO_MANAGED | IBM.WMQ.MQC.MQSO_DURABLE);
                // topic = new MQTopic(queueManager, "TEST1", null, IBM.WMQ.MQC.MQTOPIC_OPEN_AS_PUBLICATION, IBM.WMQ.MQC.MQSO_CREATE + IBM.WMQ.MQC.MQSO_FAIL_IF_QUIESCING);

                message = new MQMessage();
                message.WriteString(msg);
                // MQPutMessageOptions mqpo = new MQPutMessageOptions();
                topic.Put(message);
                // queueManager.Put(IBM.WMQ.MQC.MQOT_TOPIC, null, _mqHelper.QueueManager, _mqHelper.ProducerQueue, message);
                MessageBox.Show("Message " + message.MessageLength + " published to " + _mqHelper.ProducerQueue);

                topic.Close();
                //MessageBox.Show(MQEnvironment.properties.Count + "");
                queueManager.Disconnect();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message + Environment.NewLine + ex.StackTrace + Environment.NewLine + ex.Source + Environment.NewLine + ex.GetBaseException());
            }
        }

        /// <summary>
        /// paired with PublishMessage()
        /// </summary>
        private void SubscribeMessage()
        {
            tmrSubscribe.Enabled = true;

            //_mqHelper.Host = txHost.Text;
            //_mqHelper.Port = Int32.Parse(txPort.Text);
            _mqHelper.QueueManager = txQMgr.Text;
            //_mqHelper.Channel = txQChannel.Text;
            _mqHelper.ConsumerQueue = txQName.Text;
            //_mqHelper.MQType = MQXMSHelper.MQTypes.TOPIC;

            // option 1 WMQ APIs
            try
            {
                // mq properties
                Hashtable properties = new Hashtable();
                properties.Add(IBM.WMQ.MQC.HOST_NAME_PROPERTY, _mqHelper.Host);
                properties.Add(IBM.WMQ.MQC.PORT_PROPERTY, _mqHelper.Port);
                properties.Add(IBM.WMQ.MQC.CHANNEL_PROPERTY, _mqHelper.Channel);
                properties.Add(IBM.WMQ.MQC.TRANSPORT_PROPERTY, IBM.WMQ.MQC.TRANSPORT_MQSERIES_CLIENT); // or TRANSPORT_MQSERIES_MANAGED
                // properties.Add(IBM.WMQ.MQC.USER_ID_PROPERTY, "omsuat3");
                // properties.Add(IBM.WMQ.MQC.PASSWORD_PROPERTY, "Bch20195");

                // queueManager = new MQQueueManager(_mqHelper.QueueManager, properties); // why got NOT_AUTHORIZED error
                queueManager = new MQQueueManager(_mqHelper.QueueManager);
                // MessageBox.Show("Queue Manager created as:" + Environment.NewLine + "Host:" + _mqHelper.Host + Environment.NewLine + "Port:" + _mqHelper.Port + Environment.NewLine + "Channel:" + _mqHelper.Channel + Environment.NewLine + "Queue Manager:" + _mqHelper.QueueManager);
                // topic = queueManager.AccessTopic(_mqHelper.ConsumerQueue, null, IBM.WMQ.MQC.MQTOPIC_OPEN_AS_SUBSCRIPTION, IBM.WMQ.MQC.MQSO_CREATE | IBM.WMQ.MQC.MQSO_FAIL_IF_QUIESCING);
                if (rbDurable.Checked)
                {
                    string subName = txSubscriberName.Text;
                    subscriber = queueManager.AccessTopic(_mqHelper.ConsumerQueue, null, IBM.WMQ.MQC.MQSO_DURABLE | IBM.WMQ.MQC.MQSO_CREATE | IBM.WMQ.MQC.MQSO_RESUME | IBM.WMQ.MQC.MQSO_FAIL_IF_QUIESCING, null, subName);  // IBM.WMQ.MQC.MQSO_CREATE create a new subscription, while IBM.WMQ.MQC.MQSO_RESUME when subscription was created under queue manager already
                }
                else
                {
                    subscriber = queueManager.AccessTopic(_mqHelper.ConsumerQueue, null, IBM.WMQ.MQC.MQTOPIC_OPEN_AS_SUBSCRIPTION, IBM.WMQ.MQC.MQSO_CREATE); // undurable
                }
                
                // topic = new MQTopic(queueManager, _mqHelper.ConsumerQueue, null, IBM.WMQ.MQC.MQSO_CREATE | IBM.WMQ.MQC.MQSO_FAIL_IF_QUIESCING | IBM.WMQ.MQC.MQSO_DURABLE | IBM.WMQ.MQC.MQSO_RESUME, null, "SUB3"); // durable
                // subscriber = queueManager.AccessTopic(_mqHelper.ConsumerQueue, null, IBM.WMQ.MQC.MQTOPIC_OPEN_AS_SUBSCRIPTION, IBM.WMQ.MQC.MQOO_INPUT_SHARED); // undurable
                // IBM.WMQ.MQC.MQTOPIC_OPEN_AS_SUBSCRIPTION caused 2510 TOPIC_ALTER
                // IBM.WMQ.MQC.MQSO_CREATE or MQSO_RESUME caused 2035 - NOT_AUTH
                // MessageBox.Show("Subscriber created" + subscriber.GetType().ToString());
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message + Environment.NewLine + ex.StackTrace + Environment.NewLine + ex.Source + Environment.NewLine + ex.GetBaseException());
            }
        }

        private void UnsubscribeMessages()
        {
            if (subscriber != null)
            {
                subscriber.Close();
            }

            if (queueManager != null)
            {
                queueManager.Disconnect();
            }

            tmrSubscribe.Enabled =false;
        }

        private bool IsSubscriptionDurable()
        {
            return rbDurable.Visible && rbDurable.Checked;
        }

        /// <summary>
        /// paired with PublishMessageXMS(); otherwise, only receive the 1st characters put by using PublishMessage()
        /// </summary>
        private void SubscribeMessageXMS()
        {
            _mqHelper.Host = txHost.Text.ToString();
            int portNo = 0;
            Int32.TryParse(txPort.Text.ToString(), out portNo);
            _mqHelper.Port = portNo;
            _mqHelper.Channel = txQChannel.Text.ToString();
            _mqHelper.QueueManager = txQMgr.Text.ToString();
            if (rbTopic.Checked)
            {
                _mqHelper.MQType = MQXMSHelper.MQTypes.TOPIC;
            }
            else
            {
                _mqHelper.MQType = MQXMSHelper.MQTypes.QUEUE;
            }
            _mqHelper.ConsumerQueue = txQName.Text.ToString();
            string subName = null;
            if (IsSubscriptionDurable())
            {
                subName = txSubscriberName.Text.ToString();
            }

            // option 2: XMS - JMS Style - APIs. Supporting Async message receiving
            // NOTE: in MQ, Topic --> Object Authorities --> Manage Authority Records --> Specific Profiles, make sure the users/groups (using the XMS API calls) have the proper permissions, eg. Publish, Subscribe and etc.
            try
            {
                _mqConnectionFactory = _mqHelper.CreateMQConnectionFactoryWMQ();
                _mqConnection = _mqHelper.CreateMQConnection(_mqConnectionFactory);
                // NOTE: for durable subscribers created using XMS API or JMS, each subscriber needs to have a unique client if for the connection
                if (IsSubscriptionDurable())
                {
                    _mqConnection.ClientID = subName;
                }
                _mqSession = _mqHelper.CreateMQSession(_mqConnection);
                _mqDestination = _mqHelper.CreateMQDestination(_mqSession, _mqHelper.ConsumerQueue);
                // _mqDestination.SetBooleanProperty(XMSC.WMQ_MQMD_READ_ENABLED, true); // when XMSC.WMQ_MQMD_READ_ENABLED = true in Destination of receiver, XMSC.WMQ_MQMD_WRITE_ENABLED = true for Destination of sender
                
                _mqConsumer = null;
                if (IsSubscriptionDurable())
                {
                    _mqConsumer = _mqHelper.CreateMQDurableSubscriber(_mqSession, _mqDestination, subName);
                }
                else
                {
                    _mqConsumer = _mqHelper.CreateMQConsumer(_mqSession, _mqDestination);
                }

                MessageListener messageListener = new MessageListener(HandleMessages); // async listener for messages
                _mqConsumer.MessageListener = messageListener;
                
                if (_mqConnection != null)
                {
                    _mqConnection.ExceptionListener = new ExceptionListener(HandleMQConnectionExceptions);
                    _mqConnection.Start();
                    // System.Threading.Thread.Sleep(5000); // Wait for messages asynchronously.
                }
                txMessage.Text += Environment.NewLine + "Subscribing " + _mqHelper.MQType + "...";
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message + Environment.NewLine + ex.StackTrace + Environment.NewLine + ex.Source + Environment.NewLine + ex.GetBaseException());
            }
        }

        private void HandleMessages(IMessage msg)
        {
            if (msg != null)
            {
                txMessage.Text += _mqHelper.ConvertMessageObjectToString(msg) + Environment.NewLine;
            }
        }

        private void HandleMQConnectionExceptions(Exception ex)
        {
            if (ex != null)
            {
                txMessage.AppendText(Environment.NewLine + ex.Message + Environment.NewLine + ex.StackTrace + Environment.NewLine + ex.Source + Environment.NewLine + ex.GetBaseException() + Environment.NewLine);
            }
        }

        private void ProduceMessageXMS()
        {
            // txMessage.Text = "Producing...";
            _mqHelper.Host = txHost.Text;
            _mqHelper.Port = Int32.Parse(txPort.Text);
            _mqHelper.QueueManager = txQMgr.Text;
            _mqHelper.Channel = txQChannel.Text;
            _mqHelper.ProducerQueue = txQName.Text;
            _mqHelper.MQType = MQXMSHelper.MQTypes.QUEUE;

            string msg = txMessage.Text.ToString();

            try
            {
                IConnectionFactory _mqConnectionFactory = _mqHelper.CreateMQConnectionFactoryWMQ();
                IConnection _mqConnection = _mqHelper.CreateMQConnection(_mqConnectionFactory);
                ISession _mqSession = _mqHelper.CreateMQSession(_mqConnection);
                IDestination _mqDestination = _mqHelper.CreateMQDestination(_mqSession, _mqHelper.ProducerQueue);

                if (_mqSession != null && String.IsNullOrEmpty(msg) == false)
                {
                    IDestination mqDestination = _mqHelper.CreateMQDestination(_mqSession, _mqHelper.ProducerQueue);
                    // mqDestination.SetBooleanProperty(XMSC.WMQ_MQMD_WRITE_ENABLED, true); // when set XMSC.WMQ_MQMD_WRITE_ENABLED = true for the Destination of producer/sender, XMSC.WMQ_MQMD_READ_ENABLED = true must be set for the Destination of consumer/receiver
                    IMessageProducer mqProducer = _mqHelper.CreateMQProducer(_mqSession, mqDestination);
                    // mqProducer.DeliveryMode = DeliveryMode.NonPersistent;
                    // mqProducer.Priority = 0;
                    IMessage retMsgObj = _mqHelper.CreateMQMessage(_mqSession, "text", msg);
                    //retMsgObj.SetIntProperty(XMSC.JMS_IBM_MSGTYPE, MQC.MQMT_REPLY);
                    //retMsgObj.SetIntProperty(XMSC.JMS_IBM_ENCODING, XMSC.WMQ_ENCODING_NATIVE);
                    //retMsgObj.JMSCorrelationID = originalMsgObj.JMSMessageID;
                    //retMsgObj.SetStringProperty(XMSC.JMS_IBM_MQMD_REPLYTOQMGR, originalMsgObj.GetStringProperty(XMSC.JMS_IBM_MQMD_REPLYTOQMGR));

                    _mqHelper.SendMessage(_mqSession, mqProducer, retMsgObj);

                    _mqHelper.DestroyMQProducer(mqProducer);
                    mqDestination.Dispose();
                    MessageBox.Show("Message sent to " + _mqHelper.ProducerQueue);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message + Environment.NewLine + ex.StackTrace + Environment.NewLine + ex.Source + Environment.NewLine + ex.GetBaseException());
            }
        }

        private void ConsumeMessageXMS()
        {
            _mqHelper.Host = txHost.Text;
            _mqHelper.Port = Int32.Parse(txPort.Text);
            _mqHelper.QueueManager = txQMgr.Text;
            _mqHelper.Channel = txQChannel.Text;
            _mqHelper.ConsumerQueue = txQName.Text;
            _mqHelper.MQType = MQXMSHelper.MQTypes.QUEUE;

            IConnectionFactory _mqConnectionFactory = _mqHelper.CreateMQConnectionFactoryWMQ();
            IConnection _mqConnection = _mqHelper.CreateMQConnection(_mqConnectionFactory);
            if (_mqConnection != null)
            {
                _mqConnection.Start();
            }
            ISession _mqSession = _mqHelper.CreateMQSession(_mqConnection);
            IDestination _mqDestination = _mqHelper.CreateMQDestination(_mqSession, _mqHelper.ConsumerQueue);
            _mqDestination.SetBooleanProperty(XMSC.WMQ_MQMD_READ_ENABLED, true);
            IMessageConsumer _mqConsumer = _mqHelper.CreateMQConsumer(_mqSession, _mqDestination);

            IMessage recvMsgObj = _mqConsumer.ReceiveNoWait();
            if (recvMsgObj != null)
            {
                txMessage.Text += _mqHelper.ConvertMessageObjectToString(recvMsgObj) + Environment.NewLine;
            }
            else
            {
                txMessage.Text += "No message consumed" + Environment.NewLine;
            }

            if (_mqHelper != null)
            {
                if (_mqConnection != null)
                {
                    _mqConnection.Stop();
                }
                _mqHelper.DestroyMQConsumer(_mqConsumer);
                _mqHelper.DestroyMQDestination(_mqDestination);
                _mqHelper.DestroyMQSession(_mqSession);
                _mqHelper.DestroyMQConnection(_mqConnection);

                _mqConnectionFactory = null;
            }
        }

        private void rbNonDurable_CheckedChanged(object sender, EventArgs e)
        {
            lblSubscriberName.Visible = false;
            txSubscriberName.Visible = false;
        }

        private void rbDurable_CheckedChanged(object sender, EventArgs e)
        {
            lblSubscriberName.Visible = true;
            txSubscriberName.Visible = true;
        }

        private void tmrSubscribe_Tick(object sender, EventArgs e)
        {
            MQMessage message = new MQMessage();
            try
            {
                if (subscriber != null)
                {
                    subscriber.Get(message);
                }
                if (message != null)
                {
                    txMessage.Text += message.ReadString(message.MessageLength) + Environment.NewLine;
                }
            }
            catch (MQException mqex)
            {
                if (mqex.ReasonCode == IBM.WMQ.MQC.MQRC_NO_MSG_AVAILABLE)
                {
                    txMessage.Text += Environment.NewLine + "No new messages.";
                }
                else
                {
                    MessageBox.Show(mqex.Message + Environment.NewLine + mqex.StackTrace + Environment.NewLine + mqex.Source + Environment.NewLine + mqex.GetBaseException());
                }
            }
        }

        private void btnUnsubscribe_Click(object sender, EventArgs e)
        {
            UnsubscribeMessages();
            btnMessage.Visible = true;
            btnUnsubscribe.Visible = false; 
        }

        private void button1_Click(object sender, EventArgs e)
        {
            txMessage.Clear();
        }

        private void btnPublishXMS_Click(object sender, EventArgs e)
        {
            PublishMessageXMS();
        }

        private void btnSubscribeXMS_Click(object sender, EventArgs e)
        {
            SubscribeMessageXMS();
            btnSubscribeXMS.Visible = false;
            btnUnsubscribeXMS.Visible = true;
        }

        private void btnUnsubscribeXMS_Click(object sender, EventArgs e)
        {
            if (_mqHelper != null)
            {
                if (_mqConnection != null)
                {
                    _mqConnection.Stop();
                }
                _mqHelper.DestroyMQConsumer(_mqConsumer);
                _mqHelper.DestroyMQDestination(_mqDestination);
                _mqHelper.DestroyMQSession(_mqSession);
                _mqHelper.DestroyMQConnection(_mqConnection);

                _mqConnectionFactory = null;
            }

            btnUnsubscribeXMS.Visible = false;
            btnSubscribeXMS.Visible = true;
        }
    }
}
