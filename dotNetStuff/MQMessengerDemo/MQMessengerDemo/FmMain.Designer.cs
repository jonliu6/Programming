namespace MQMessengerDemo
{
    partial class FmMain
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.rbQueue = new System.Windows.Forms.RadioButton();
            this.rbTopic = new System.Windows.Forms.RadioButton();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.btnUnsubscribeXMS = new System.Windows.Forms.Button();
            this.btnPublishXMS = new System.Windows.Forms.Button();
            this.btnSubscribeXMS = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.btnUnsubscribe = new System.Windows.Forms.Button();
            this.btnMessage = new System.Windows.Forms.Button();
            this.label6 = new System.Windows.Forms.Label();
            this.txMessage = new System.Windows.Forms.TextBox();
            this.txQName = new System.Windows.Forms.TextBox();
            this.txQChannel = new System.Windows.Forms.TextBox();
            this.txQMgr = new System.Windows.Forms.TextBox();
            this.txPort = new System.Windows.Forms.TextBox();
            this.txHost = new System.Windows.Forms.TextBox();
            this.lblTopicOrQueue = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.rbReceiver = new System.Windows.Forms.RadioButton();
            this.rbSender = new System.Windows.Forms.RadioButton();
            this.gbxSubscriptionDurablity = new System.Windows.Forms.GroupBox();
            this.txSubscriberName = new System.Windows.Forms.TextBox();
            this.lblSubscriberName = new System.Windows.Forms.Label();
            this.rbNonDurable = new System.Windows.Forms.RadioButton();
            this.rbDurable = new System.Windows.Forms.RadioButton();
            this.label5 = new System.Windows.Forms.Label();
            this.tmrSubscribe = new System.Windows.Forms.Timer(this.components);
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.gbxSubscriptionDurablity.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.rbQueue);
            this.groupBox1.Controls.Add(this.rbTopic);
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(184, 49);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Message Service Type";
            // 
            // rbQueue
            // 
            this.rbQueue.AutoSize = true;
            this.rbQueue.Checked = true;
            this.rbQueue.Location = new System.Drawing.Point(105, 19);
            this.rbQueue.Name = "rbQueue";
            this.rbQueue.Size = new System.Drawing.Size(57, 17);
            this.rbQueue.TabIndex = 1;
            this.rbQueue.TabStop = true;
            this.rbQueue.Text = "Queue";
            this.rbQueue.UseVisualStyleBackColor = true;
            this.rbQueue.CheckedChanged += new System.EventHandler(this.rbQueue_CheckedChanged);
            // 
            // rbTopic
            // 
            this.rbTopic.AutoSize = true;
            this.rbTopic.Location = new System.Drawing.Point(15, 19);
            this.rbTopic.Name = "rbTopic";
            this.rbTopic.Size = new System.Drawing.Size(52, 17);
            this.rbTopic.TabIndex = 0;
            this.rbTopic.Text = "Topic";
            this.rbTopic.UseVisualStyleBackColor = true;
            this.rbTopic.CheckedChanged += new System.EventHandler(this.rbTopic_CheckedChanged);
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.btnUnsubscribeXMS);
            this.groupBox2.Controls.Add(this.btnPublishXMS);
            this.groupBox2.Controls.Add(this.btnSubscribeXMS);
            this.groupBox2.Controls.Add(this.button1);
            this.groupBox2.Controls.Add(this.btnUnsubscribe);
            this.groupBox2.Controls.Add(this.btnMessage);
            this.groupBox2.Controls.Add(this.label6);
            this.groupBox2.Controls.Add(this.txMessage);
            this.groupBox2.Controls.Add(this.txQName);
            this.groupBox2.Controls.Add(this.txQChannel);
            this.groupBox2.Controls.Add(this.txQMgr);
            this.groupBox2.Controls.Add(this.txPort);
            this.groupBox2.Controls.Add(this.txHost);
            this.groupBox2.Controls.Add(this.lblTopicOrQueue);
            this.groupBox2.Controls.Add(this.label4);
            this.groupBox2.Controls.Add(this.label3);
            this.groupBox2.Controls.Add(this.label2);
            this.groupBox2.Controls.Add(this.label1);
            this.groupBox2.Controls.Add(this.rbReceiver);
            this.groupBox2.Controls.Add(this.rbSender);
            this.groupBox2.Location = new System.Drawing.Point(12, 77);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(608, 364);
            this.groupBox2.TabIndex = 2;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Messenger Type";
            // 
            // btnUnsubscribeXMS
            // 
            this.btnUnsubscribeXMS.Location = new System.Drawing.Point(485, 147);
            this.btnUnsubscribeXMS.Name = "btnUnsubscribeXMS";
            this.btnUnsubscribeXMS.Size = new System.Drawing.Size(105, 23);
            this.btnUnsubscribeXMS.TabIndex = 19;
            this.btnUnsubscribeXMS.Text = "Unsubscribe(XMS)";
            this.btnUnsubscribeXMS.UseVisualStyleBackColor = true;
            this.btnUnsubscribeXMS.Visible = false;
            this.btnUnsubscribeXMS.Click += new System.EventHandler(this.btnUnsubscribeXMS_Click);
            // 
            // btnPublishXMS
            // 
            this.btnPublishXMS.Location = new System.Drawing.Point(374, 120);
            this.btnPublishXMS.Name = "btnPublishXMS";
            this.btnPublishXMS.Size = new System.Drawing.Size(105, 23);
            this.btnPublishXMS.TabIndex = 18;
            this.btnPublishXMS.Text = "Publish(XMS)";
            this.btnPublishXMS.UseVisualStyleBackColor = true;
            this.btnPublishXMS.Visible = false;
            this.btnPublishXMS.Click += new System.EventHandler(this.btnPublishXMS_Click);
            // 
            // btnSubscribeXMS
            // 
            this.btnSubscribeXMS.Location = new System.Drawing.Point(374, 147);
            this.btnSubscribeXMS.Name = "btnSubscribeXMS";
            this.btnSubscribeXMS.Size = new System.Drawing.Size(105, 23);
            this.btnSubscribeXMS.TabIndex = 17;
            this.btnSubscribeXMS.Text = "Subscribe(XMS)";
            this.btnSubscribeXMS.UseVisualStyleBackColor = true;
            this.btnSubscribeXMS.Visible = false;
            this.btnSubscribeXMS.Click += new System.EventHandler(this.btnSubscribeXMS_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(24, 202);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 16;
            this.button1.Text = "Clear";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // btnUnsubscribe
            // 
            this.btnUnsubscribe.Location = new System.Drawing.Point(434, 19);
            this.btnUnsubscribe.Name = "btnUnsubscribe";
            this.btnUnsubscribe.Size = new System.Drawing.Size(75, 23);
            this.btnUnsubscribe.TabIndex = 15;
            this.btnUnsubscribe.Text = "Unsubscribe";
            this.btnUnsubscribe.UseVisualStyleBackColor = true;
            this.btnUnsubscribe.Visible = false;
            this.btnUnsubscribe.Click += new System.EventHandler(this.btnUnsubscribe_Click);
            // 
            // btnMessage
            // 
            this.btnMessage.Location = new System.Drawing.Point(515, 19);
            this.btnMessage.Name = "btnMessage";
            this.btnMessage.Size = new System.Drawing.Size(75, 23);
            this.btnMessage.TabIndex = 14;
            this.btnMessage.Text = "Receive";
            this.btnMessage.UseVisualStyleBackColor = true;
            this.btnMessage.Click += new System.EventHandler(this.btnMessage_Click);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(26, 186);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(53, 13);
            this.label6.TabIndex = 13;
            this.label6.Text = "Message:";
            // 
            // txMessage
            // 
            this.txMessage.Location = new System.Drawing.Point(105, 183);
            this.txMessage.Multiline = true;
            this.txMessage.Name = "txMessage";
            this.txMessage.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.txMessage.Size = new System.Drawing.Size(485, 156);
            this.txMessage.TabIndex = 12;
            // 
            // txQName
            // 
            this.txQName.Location = new System.Drawing.Point(105, 149);
            this.txQName.Name = "txQName";
            this.txQName.Size = new System.Drawing.Size(198, 20);
            this.txQName.TabIndex = 11;
            // 
            // txQChannel
            // 
            this.txQChannel.Location = new System.Drawing.Point(105, 123);
            this.txQChannel.Name = "txQChannel";
            this.txQChannel.Size = new System.Drawing.Size(198, 20);
            this.txQChannel.TabIndex = 10;
            // 
            // txQMgr
            // 
            this.txQMgr.Location = new System.Drawing.Point(105, 97);
            this.txQMgr.Name = "txQMgr";
            this.txQMgr.Size = new System.Drawing.Size(198, 20);
            this.txQMgr.TabIndex = 9;
            // 
            // txPort
            // 
            this.txPort.Location = new System.Drawing.Point(105, 71);
            this.txPort.Name = "txPort";
            this.txPort.Size = new System.Drawing.Size(198, 20);
            this.txPort.TabIndex = 8;
            // 
            // txHost
            // 
            this.txHost.Location = new System.Drawing.Point(105, 45);
            this.txHost.Name = "txHost";
            this.txHost.Size = new System.Drawing.Size(198, 20);
            this.txHost.TabIndex = 7;
            // 
            // lblTopicOrQueue
            // 
            this.lblTopicOrQueue.AutoSize = true;
            this.lblTopicOrQueue.Location = new System.Drawing.Point(26, 152);
            this.lblTopicOrQueue.Name = "lblTopicOrQueue";
            this.lblTopicOrQueue.Size = new System.Drawing.Size(73, 13);
            this.lblTopicOrQueue.TabIndex = 6;
            this.lblTopicOrQueue.Text = "Queue Name:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(50, 126);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(49, 13);
            this.label4.TabIndex = 5;
            this.label4.Text = "Channel:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 100);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(87, 13);
            this.label3.TabIndex = 4;
            this.label3.Text = "Queue Manager:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(70, 74);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(29, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Port:";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(67, 48);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(32, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Host:";
            // 
            // rbReceiver
            // 
            this.rbReceiver.AutoSize = true;
            this.rbReceiver.Checked = true;
            this.rbReceiver.Location = new System.Drawing.Point(105, 19);
            this.rbReceiver.Name = "rbReceiver";
            this.rbReceiver.Size = new System.Drawing.Size(68, 17);
            this.rbReceiver.TabIndex = 1;
            this.rbReceiver.TabStop = true;
            this.rbReceiver.Text = "Receiver";
            this.rbReceiver.UseVisualStyleBackColor = true;
            this.rbReceiver.CheckedChanged += new System.EventHandler(this.rbReceiver_CheckedChanged);
            // 
            // rbSender
            // 
            this.rbSender.AutoSize = true;
            this.rbSender.Location = new System.Drawing.Point(15, 19);
            this.rbSender.Name = "rbSender";
            this.rbSender.Size = new System.Drawing.Size(59, 17);
            this.rbSender.TabIndex = 0;
            this.rbSender.Text = "Sender";
            this.rbSender.UseVisualStyleBackColor = true;
            this.rbSender.CheckedChanged += new System.EventHandler(this.rbSender_CheckedChanged);
            // 
            // gbxSubscriptionDurablity
            // 
            this.gbxSubscriptionDurablity.Controls.Add(this.txSubscriberName);
            this.gbxSubscriptionDurablity.Controls.Add(this.lblSubscriberName);
            this.gbxSubscriptionDurablity.Controls.Add(this.rbNonDurable);
            this.gbxSubscriptionDurablity.Controls.Add(this.rbDurable);
            this.gbxSubscriptionDurablity.Controls.Add(this.label5);
            this.gbxSubscriptionDurablity.Location = new System.Drawing.Point(221, 12);
            this.gbxSubscriptionDurablity.Name = "gbxSubscriptionDurablity";
            this.gbxSubscriptionDurablity.Size = new System.Drawing.Size(399, 49);
            this.gbxSubscriptionDurablity.TabIndex = 3;
            this.gbxSubscriptionDurablity.TabStop = false;
            this.gbxSubscriptionDurablity.Text = "Subscription Durablity";
            this.gbxSubscriptionDurablity.Visible = false;
            // 
            // txSubscriberName
            // 
            this.txSubscriberName.Location = new System.Drawing.Point(270, 18);
            this.txSubscriberName.Name = "txSubscriberName";
            this.txSubscriberName.Size = new System.Drawing.Size(111, 20);
            this.txSubscriberName.TabIndex = 5;
            this.txSubscriberName.Visible = false;
            // 
            // lblSubscriberName
            // 
            this.lblSubscriberName.AutoSize = true;
            this.lblSubscriberName.Location = new System.Drawing.Point(176, 21);
            this.lblSubscriberName.Name = "lblSubscriberName";
            this.lblSubscriberName.Size = new System.Drawing.Size(88, 13);
            this.lblSubscriberName.TabIndex = 4;
            this.lblSubscriberName.Text = "Subscriber Name";
            this.lblSubscriberName.Visible = false;
            // 
            // rbNonDurable
            // 
            this.rbNonDurable.AutoSize = true;
            this.rbNonDurable.Checked = true;
            this.rbNonDurable.Location = new System.Drawing.Point(122, 19);
            this.rbNonDurable.Name = "rbNonDurable";
            this.rbNonDurable.Size = new System.Drawing.Size(39, 17);
            this.rbNonDurable.TabIndex = 3;
            this.rbNonDurable.TabStop = true;
            this.rbNonDurable.Text = "No";
            this.rbNonDurable.UseVisualStyleBackColor = true;
            this.rbNonDurable.CheckedChanged += new System.EventHandler(this.rbNonDurable_CheckedChanged);
            // 
            // rbDurable
            // 
            this.rbDurable.AutoSize = true;
            this.rbDurable.Location = new System.Drawing.Point(73, 19);
            this.rbDurable.Name = "rbDurable";
            this.rbDurable.Size = new System.Drawing.Size(43, 17);
            this.rbDurable.TabIndex = 2;
            this.rbDurable.Text = "Yes";
            this.rbDurable.UseVisualStyleBackColor = true;
            this.rbDurable.CheckedChanged += new System.EventHandler(this.rbDurable_CheckedChanged);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(17, 21);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(50, 13);
            this.label5.TabIndex = 1;
            this.label5.Text = "Durable?";
            // 
            // tmrSubscribe
            // 
            this.tmrSubscribe.Interval = 5000;
            this.tmrSubscribe.Tick += new System.EventHandler(this.tmrSubscribe_Tick);
            // 
            // FmMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(632, 453);
            this.Controls.Add(this.gbxSubscriptionDurablity);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Name = "FmMain";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "MQ Messenger Demo";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.gbxSubscriptionDurablity.ResumeLayout(false);
            this.gbxSubscriptionDurablity.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.RadioButton rbQueue;
        private System.Windows.Forms.RadioButton rbTopic;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.RadioButton rbReceiver;
        private System.Windows.Forms.RadioButton rbSender;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label lblTopicOrQueue;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button btnMessage;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox txMessage;
        private System.Windows.Forms.TextBox txQName;
        private System.Windows.Forms.TextBox txQChannel;
        private System.Windows.Forms.TextBox txQMgr;
        private System.Windows.Forms.TextBox txPort;
        private System.Windows.Forms.TextBox txHost;
        private System.Windows.Forms.GroupBox gbxSubscriptionDurablity;
        private System.Windows.Forms.RadioButton rbNonDurable;
        private System.Windows.Forms.RadioButton rbDurable;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label lblSubscriberName;
        private System.Windows.Forms.TextBox txSubscriberName;
        private System.Windows.Forms.Timer tmrSubscribe;
        private System.Windows.Forms.Button btnUnsubscribe;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button btnSubscribeXMS;
        private System.Windows.Forms.Button btnPublishXMS;
        private System.Windows.Forms.Button btnUnsubscribeXMS;
    }
}

