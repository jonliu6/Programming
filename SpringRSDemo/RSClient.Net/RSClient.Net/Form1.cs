using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Windows.Forms;
using System.Net;
using System.Web;
using System.Web.Script.Serialization;

namespace RSClient.Net
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (textBox1.Text == null)
            {
                MessageBox.Show("Please enter a RESTful web service URL.");
                return;
            }
            String url = textBox1.Text;
            HttpWebRequest req = (HttpWebRequest)WebRequest.Create(url);
            req.Method = "GET";
            req.ContentType = "application/json";

            try
            {
                HttpWebResponse resp = (HttpWebResponse)req.GetResponse();
                using (Stream respStrm = resp.GetResponseStream())
                {
                    StreamReader sr = new StreamReader(respStrm, Encoding.UTF8);
                    String respStr = sr.ReadToEnd();
                    if (respStr != null)
                    {
                        JavaScriptSerializer jss = new JavaScriptSerializer();
                        List<Note> notes = jss.Deserialize<List<Note>>(respStr);
                        foreach (Note note in notes)
                        {
                            textBox2.AppendText("Title: " + note.title + Environment.NewLine);
                            textBox2.AppendText("Category: " + note.category + Environment.NewLine);
                            textBox2.AppendText("Sub-category: " + note.subCategory + Environment.NewLine);
                            textBox2.AppendText("Description: " + note.description + Environment.NewLine);
                            textBox2.AppendText(Environment.NewLine);
                        }
                        
                    }
                }
            }
            catch (WebException we)
            {
                MessageBox.Show(we.Message);

            }
        }
    }
}
