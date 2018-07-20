using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace MQWMQSenderApp.util
{
    abstract class Logger
    {
        public abstract void Log(String message);
		public abstract void Err(String message);
        public const String LONG_TIMESTAMP_FORMAT = "yyyy/MM/dd HH:mm:ss tt";
    }

    class ConsoleLogger : Logger
    {
        public override void Log(String message)
        {
            Console.WriteLine(DateTime.Now.ToString(LONG_TIMESTAMP_FORMAT) + " " + message);
        }
        public override void Err(String message)
        {
            Console.WriteLine(DateTime.Now.ToString(LONG_TIMESTAMP_FORMAT) + " ERROR: " + message);
        }
    }

    class FileLogger : Logger
    {
        private String _logFileName;

        public FileLogger(String fileName)
        {
            _logFileName = fileName;
        }

        public override void Log(String message)
        {
            if (_logFileName != null)
            {
                using (StreamWriter sw = new StreamWriter(_logFileName, true))
                {
                    sw.WriteLine(DateTime.Now.ToString(LONG_TIMESTAMP_FORMAT) + " " + message);
                    sw.Close();
                }
            }
        }

        public override void Err(String message)
        {
            if (_logFileName != null)
            {
                using (StreamWriter sw = new StreamWriter(_logFileName, true))
                {
                    sw.WriteLine(DateTime.Now.ToString(LONG_TIMESTAMP_FORMAT) + " ERROR: " + message);
                    sw.Close();
                }
            }
        }
    }
}
