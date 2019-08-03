using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Windows.Forms;

namespace WSDemo
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (textBox1.Text == null || textBox1.Text.Trim() == "")
            {
                MessageBox.Show("Please enter a title first.");
                return;
            }

            try
            {
                ServiceReference1.getNoteByTitleRequest req = new ServiceReference1.getNoteByTitleRequest();
                req.title = textBox1.Text;

                ServiceReference1.getNoteByTitleResponse resp = null;
                ServiceReference1.NotePortClient wsCli = new WSDemo.ServiceReference1.NotePortClient();
                resp = wsCli.getNoteByTitle(req);

                if (resp != null)
                {
                    textBox2.Text = "";
                    ServiceReference1.note note = resp.note;
                    if (note == null)
                    {
                        MessageBox.Show("Not found!");
                        return;
                    }
                    textBox2.AppendText("Title: " + note.title + Environment.NewLine);
                    textBox2.AppendText("Cateogry: " + note.category + Environment.NewLine);
                    textBox2.AppendText("Sub-category: " + note.subCategory + Environment.NewLine);
                    textBox2.AppendText("Description: " + note.description + Environment.NewLine);
                }
            }
            catch (EndpointNotFoundException enfe)
            {
                MessageBox.Show("Service unavailable.");
            }
        }
    }
}
