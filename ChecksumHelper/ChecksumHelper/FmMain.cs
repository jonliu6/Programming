using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Security.Cryptography;


namespace ChecksumHelper
{
    public partial class FmMain : Form
    {
        private ChecksumHelper checksumHelper = null;

        public FmMain()
        {
            InitializeComponent();
        }

        private ChecksumHelper getChecksumHelper()
        {
            if (checksumHelper == null)
            {
                checksumHelper = new ChecksumHelper();
            }
            return checksumHelper;
        }

        private FileStream GetFileStream(string pathName)
        {
            return (new FileStream(pathName, FileMode.Open, FileAccess.Read, FileShare.ReadWrite));
        }

        //public string GetSHA1Hash(string pathName)
        //{
        //    string strResult = "";
        //    string strHashData = "";

        //    byte[] arrbytHashValue;
        //    System.IO.FileStream oFileStream = null;

        //    System.Security.Cryptography.SHA1CryptoServiceProvider oSHA1Hasher =
        //               new System.Security.Cryptography.SHA1CryptoServiceProvider();

        //    try
        //    {
        //        oFileStream = GetFileStream(pathName);
        //        arrbytHashValue = oSHA1Hasher.ComputeHash(oFileStream);
        //        oFileStream.Close();

        //        strHashData = System.BitConverter.ToString(arrbytHashValue);
        //        strHashData = strHashData.Replace("-", "");
        //        strResult = strHashData;
        //    }
        //    catch (System.Exception ex)
        //    {
        //        System.Windows.Forms.MessageBox.Show(ex.Message, "Error!",
        //                 System.Windows.Forms.MessageBoxButtons.OK,
        //                 System.Windows.Forms.MessageBoxIcon.Error,
        //                 System.Windows.Forms.MessageBoxDefaultButton.Button1);
        //    }

        //    return (strResult.ToLower());
        //}

        

        //public string GetFileHash(string pathName, string hashTypeText)
        //{
        //    string strResult = "";
        //    string strHashData = "";
        //    HashAlgorithm algorithm = getHashAlgorithm(hashTypeText);

        //    byte[] arrbytHashValue;
        //    System.IO.FileStream oFileStream = null;

        //    //System.Security.Cryptography.MD5CryptoServiceProvider oMD5Hasher =
        //    //           new System.Security.Cryptography.MD5CryptoServiceProvider();

        //    try
        //    {
        //        if (algorithm != null)
        //        {
        //            oFileStream = GetFileStream(pathName);
        //            arrbytHashValue = algorithm.ComputeHash(oFileStream);
        //            oFileStream.Close();

        //            strHashData = System.BitConverter.ToString(arrbytHashValue);
        //            strHashData = strHashData.Replace("-", "");
        //            strResult = strHashData;
        //        }
        //    }
        //    catch (System.Exception ex)
        //    {
        //        System.Windows.Forms.MessageBox.Show(ex.Message, "Error!",
        //                 System.Windows.Forms.MessageBoxButtons.OK,
        //                 System.Windows.Forms.MessageBoxIcon.Error,
        //                 System.Windows.Forms.MessageBoxDefaultButton.Button1);
        //    }

        //    return (strResult.ToLower());
        //}

        private string getInput()
        {
            string strValue = "";
            if (radioButton1.Checked)
            {
                strValue = textBox1.Text;
            }
            else if (radioButton2.Checked)
            {
                strValue = textBox3.Text;
            }
            return strValue;
        }

        private string getHashResult( string strValue )
        {
            string hashResult = "";
            if (radioButton1.Checked)
            {
                hashResult = getChecksumHelper().getHash(strValue, comboBox1.Text);
            }
            else if (radioButton2.Checked)
            {
                hashResult = getChecksumHelper().getFileHash(GetFileStream(strValue), comboBox1.Text);
            }
            return hashResult;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string strInput = getInput();
            string strResult = null;
            if (strInput == null || strInput.Equals(""))
            {
                MessageBox.Show("Please either enter a string in Text or browse a file to generate the checksum.");
                return;
            }

            strResult = getHashResult( strInput );
            
            textBox2.Text = strResult;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            string hashResult = getHashResult( getInput() );
            if (textBox2.Text == null || textBox2.Text.Equals(""))
            {
                MessageBox.Show("Please enter a " + comboBox1.Text + " string as a \"Result\" to compare.", "Checksum Information", MessageBoxButtons.OK, MessageBoxIcon.Information );
                return;
            }

            if (textBox2.Text.Equals(hashResult))
            {
                MessageBox.Show("Identical.", "Checksum Information", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else
            {
                MessageBox.Show("NOT match!", "Checksum Warning", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            radioButton2.Checked = true;
            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                textBox3.Text = openFileDialog1.FileName;
            }
                
        }

        private void button3_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            radioButton1.Checked = true;
            textBox1.Clear();
            textBox2.Clear();
            textBox3.Clear();
            comboBox1.Text = "SHA1";
        }
    }
}
