using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace MQWMQSenderApp.util
{
    abstract class Logger
    {
        public abstract void Log(String message);
    }

    class ConsoleLogger : Logger
    {
        public override void Log(String message)
        {
            Console.WriteLine(message);
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
                    sw.WriteLine(message);
                    sw.Close();
                }
            }
        }
    }
}
