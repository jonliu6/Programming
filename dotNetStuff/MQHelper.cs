using IBM.WMQ;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MQWMQSenderApp.util
{
    class MQHelper
    {
        /// <summary>
        /// create a queue managerbased on the given MQ configuration
        /// </summary>
        /// <param name="host"></param>
        /// <param name="port"></param>
        /// <param name="channelName"></param>
        /// <param name="queueManagerName"></param>
        /// <returns></returns>
        public static MQQueueManager CreateQueueManager(string host, int port, string channelName, string queueManagerName)
        {
            string connectionName = host + "(" + port + ")";
            return new MQQueueManager(queueManagerName, channelName, connectionName);
        }

        /// <summary>
        /// create a queue from the given queue manager and the queue name
        /// </summary>
        /// <param name="queueManager"></param>
        /// <param name="queueName"></param>
        /// <returns></returns>
        public static MQQueue CreateQueue(MQQueueManager queueManager, string queueName)
        {
            int queueOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;
            return queueManager.AccessQueue(queueName, queueOptions);
        }

        /// <summary>
        /// currently, only support text/string and bytes formats
        /// </summary>
        /// <param name="messageFormat">text, bytes</param>
        /// <param name="messageText"></param>
        /// <returns></returns>
        public static MQMessage CreateMessage(string messageFormat, string messageText)
        {
            MQMessage qMessage = new MQMessage();
            // default was MQC.CODESET_USC
            qMessage.CharacterSet = MQC.CODESET_UTF;
            if (messageFormat == "text")
            {
                qMessage.Format = MQC.MQFMT_STRING;
                qMessage.WriteString(messageText);
            }
            else if (messageFormat == "bytes")
            {
                qMessage.Format = MQC.MQFMT_NONE;
                qMessage.WriteBytes(messageText); // or WriteBytes()
            }

            return qMessage;
        }

        /// <summary>
        /// send a given queue message through the given queue
        /// </summary>
        /// <param name="queue"></param>
        /// <param name="qMessage"></param>
        /// <returns></returns>
        public bool SendMessage(MQQueue queue, MQMessage qMessage)
        {
            bool sentFlag = false;
            MQPutMessageOptions msgOptions = new MQPutMessageOptions();
            queue.Put(qMessage, msgOptions);
            sentFlag = true;

            return sentFlag;
        }

    }
}
