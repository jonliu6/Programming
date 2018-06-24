using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Net.Mime;
using System.Text;

namespace RemoteDispatchTestClient.util
{
    class SMTPHelper
    {
        private const String SMTP_USER_TOKEN = "SMTPHelper";
        private char[] SMTP_RECIPIENT_DELIMITERS = {';', ',', ' '};
        private SmtpClient _msgSender;
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
        private bool _sentFlag = false;
        public bool SentFlag
        {
            get { return _sentFlag; }
        }

        public SMTPHelper(String serverHost, int serverPort)
        {
            _host = serverHost;
            _port = serverPort;
            _msgSender = new SmtpClient(_host, _port);
        }

        /// <summary>
        /// Synchronizely send a message
        /// </summary>
        /// <param name="senderAddr"></param>
        /// <param name="recipientAddr"></param>
        /// <param name="subject"></param>
        /// <param name="msg"></param>
        public bool sendMessage(String senderAddr, String recipientAddr, String subject, String msg)
        {
            MailMessage mail = null;
            _sentFlag = false;
            try
            {
                if (recipientAddr != null)
                {
                    String[] recipients = recipientAddr.Split(SMTP_RECIPIENT_DELIMITERS, StringSplitOptions.RemoveEmptyEntries);
                    if (recipients != null && recipients.Length > 0)
                    {
                        for (int i = 0, len = recipients.Length; i < len; ++i)
                        {
                            mail = new MailMessage(senderAddr, recipients[i], subject, msg);
                            mail.SubjectEncoding = System.Text.Encoding.UTF8;
                            mail.Body += Environment.NewLine;
                            mail.BodyEncoding = System.Text.Encoding.UTF8;
                            _msgSender.Send(mail);
                            _sentFlag = true;
                        }
                    }
                }
                
                mail.Dispose();
            }
            catch (Exception ex)
            {
                throw ex;
            }

            return _sentFlag;
        }

        /// <summary>
        /// Asynchronizely send a message
        /// </summary>
        /// <param name="senderAddr"></param>
        /// <param name="recipientAddr"></param>
        /// <param name="subject"></param>
        /// <param name="msg"></param>
        public void sendMessageAsync(String senderAddr, String recipientAddr, String subject, String msg)
        {
            MailMessage mail = null;
            _sentFlag = false;
            try
            {
                if (recipientAddr != null)
                {
                    String[] recipients = recipientAddr.Split(SMTP_RECIPIENT_DELIMITERS, StringSplitOptions.RemoveEmptyEntries);
                    if (recipients != null && recipients.Length > 0)
                    {
                        for (int i = 0, len = recipients.Length; i < len; ++i)
                        {
                            mail = new MailMessage(senderAddr, recipients[i], subject, msg);
                            mail.SubjectEncoding = System.Text.Encoding.UTF8;
                            mail.Body += Environment.NewLine;
                            mail.BodyEncoding = System.Text.Encoding.UTF8;
                            _msgSender.SendCompleted += new SendCompletedEventHandler(HandleSMTPCompletion);
                            _msgSender.SendAsync(mail, SMTP_USER_TOKEN);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally
            {
                if (mail != null)
                {
                    mail.Dispose();
                }
            }
        }

        /// <summary>
        /// Callback function of SendMessageAsync
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void HandleSMTPCompletion(object sender, AsyncCompletedEventArgs e)
        {
            String token = (String) e.UserState;
            if (e.Cancelled)
            {
                _sentFlag = false;
                Console.WriteLine("[" + token + "] message cancelled." );
            }
            else if (e.Error != null)
            {
                _sentFlag = false;
                Console.WriteLine("[" + token + "] message fail to send.");
            }
            else
            {
                _sentFlag = true;
                Console.WriteLine("[" + token + "] message sent.");
            }
        }
    }
}
