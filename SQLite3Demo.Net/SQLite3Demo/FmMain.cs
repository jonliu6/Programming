using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Common;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data.SQLite;

namespace SQLite3Demo
{
    public partial class FmMain : Form
    {
        private SQLiteHelper sqlite;
        public FmMain()
        {
            InitializeComponent();
        }

        private void clearForm()
        {
            listBox1.Items.Clear();
            textBox1.Text = "";
            listView1.Columns.Clear();
            listView1.Items.Clear();
            txSQL.Text = "";
        }

        private void button1_Click(object sender, EventArgs e)
        {
            clearForm();
            //connect SQLite 3 DB
            DialogResult dr = openFileDialog1.ShowDialog();
            if (DialogResult.OK.Equals(dr))
            {
                sqlite = new SQLiteHelper(openFileDialog1.FileName);
                textBox1.Clear();
                // string sql = "SELECT * FROM knowledge_base";
                String sql = "SELECT * FROM sqlite_master WHERE type='table'";
                sqlite.Connection.Open();
                DbDataReader reader = sqlite.executeQuery(sql);
                // textBox1.Text += "Table: \r\n";
                while (reader.Read())
                {
                    // textBox1.Text += "Item: " + reader["title"];
                    String tblName = reader["name"].ToString();
                    listBox1.Items.Add(tblName);
                    //textBox1.Text += tblName + "\r\n";
                    //tblReader = sqlite.executeQuery("SELECT * FROM " + tblName);
                    //// textBox1.Text += "Fields: " + "\r\n";
                    //for (int i = 0; i < tblReader.FieldCount; ++i)
                    //{
                    //    textBox1.Text += "\t" + tblReader.GetName(i) + "\r\n";
                    //}
                    //// Console.WriteLine("Item: " + reader["title"]);
                    //textBox1.Text += "\r\n";
                }
                // tblReader.Close();
                reader.Close();
                // sqlite.Connection.Close();
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            clearForm();
            if (sqlite != null && sqlite.Connection != null)
            {
                sqlite.Connection.Close();
            }
        }

        private void button5_Click(object sender, EventArgs e)
        {
            button4_Click(sender, e);
            Application.Exit();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (listBox1.SelectedIndex >= 0)
            {
                String tblName = listBox1.SelectedItem.ToString();
                DbDataReader tblReader = null;
                if (sqlite != null && sqlite.Connection != null)
                {
                    textBox1.Text = "";
                    listView1.Columns.Clear();
                    listView1.Items.Clear();
                    tblReader = sqlite.executeQuery("SELECT * FROM " + tblName);
                    for (int i = 0; i < tblReader.FieldCount; ++i)
                    {
                        String colName = tblReader.GetName(i);
                        textBox1.Text += colName + "\r\n";
                        listView1.Columns.Add(colName);
                    }
                    tblReader.Close();
                }
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            if (listBox1.SelectedIndex >= 0)
            {
                String tblName = listBox1.SelectedItem.ToString();
                DbDataReader tblReader = null;
                if (sqlite != null && sqlite.Connection != null)
                {
                    textBox1.Text = "";
                    listView1.Columns.Clear();
                    listView1.Items.Clear();
                    tblReader = sqlite.executeQuery("SELECT * FROM " + tblName);
                    // display field headers
                    for (int i = 0; i < tblReader.FieldCount; ++i)
                    {
                        // textBox1.Text += tblReader.GetName(i) + "\r\n";
                        listView1.Columns.Add(tblReader.GetName(i));
                    }

                    while (tblReader.Read())
                    {
                        int fieldCount = tblReader.FieldCount;
                        String[] rec = new String[fieldCount];
                        for (int i = 0; i < fieldCount; ++i)
                        {
                            // textBox1.Text += tblReader[i] + "\t";
                            rec[i] = tblReader[i].ToString();
                        }
                        ListViewItem lvi = new ListViewItem(rec);
                        listView1.Items.Add(lvi);
                    }
                    tblReader.Close();
                }
            }
        }

        private bool isValidSQL(String sqlStr)
        {
            if (isDMLQuery(sqlStr) ||
                isDMLStatement(sqlStr) ||
                isDDLSQL(sqlStr))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        private bool isDMLQuery(String sqlStr)
        {
            return sqlStr != null && sqlStr.StartsWith("select ");
        }

        private bool isDMLStatement(String sqlStr)
        {
            return sqlStr != null && (sqlStr.StartsWith("insert ") || sqlStr.StartsWith("update ") || sqlStr.StartsWith("delete "));
        }

        private bool isDDLSQL(String sqlStr)
        {
            return sqlStr != null && (sqlStr.StartsWith("create ") || sqlStr.StartsWith("alter ") || sqlStr.StartsWith("drop "));
        }

        private void btnSQL_Click(object sender, EventArgs e)
        {
            if (txSQL != null && txSQL.Text != null && txSQL.Text.Trim().Length > 0)
            {
                String sql = txSQL.Text.ToLower();
                bool isDBConnected = (sqlite != null && sqlite.Connection != null);

                if (isValidSQL(sql))
                {
                    if (isDBConnected)
                    {
                        try
                        {

                            if (isDMLQuery(sql))
                            {
                                // MessageBox.Show("SELECT QUERY");
                                DbDataReader tblReader = null;
                                listView1.Columns.Clear();
                                listView1.Items.Clear();
                                tblReader = sqlite.executeQuery(sql);
                                // display field headers
                                for (int i = 0; i < tblReader.FieldCount; ++i)
                                {
                                    listView1.Columns.Add(tblReader.GetName(i));
                                }

                                while (tblReader.Read())
                                {
                                    int fieldCount = tblReader.FieldCount;
                                    String[] rec = new String[fieldCount];
                                    for (int i = 0; i < fieldCount; ++i)
                                    {
                                        rec[i] = tblReader[i].ToString();
                                    }
                                    ListViewItem lvi = new ListViewItem(rec);
                                    listView1.Items.Add(lvi);
                                }
                                tblReader.Close();
                            }
                            else if (isDMLStatement(sql))
                            {
                                int count = sqlite.executeNonQuery(sql);
                                MessageBox.Show("Data Manipulation Language Statement was executed on " + count + " record(s).");
                            }
                            else if (isDDLSQL(sql))
                            {
                                int count = sqlite.executeNonQuery(sql);
                                MessageBox.Show("Data Definition Language Statement was executed.");
                            }

                        }
                        catch(SQLiteException se)
                        {
                            MessageBox.Show("SQLiteException Caught: " + se.Message, "SQLiteException", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        }
                    }
                    else
                    {
                        MessageBox.Show("Please open a SQLite 3 database first.");
                    }                     
                }   
                else
                {
                    MessageBox.Show("Un-recognized SQL Statement?!");
                }
            }
            else
            {
                MessageBox.Show("Please enter a SQL statement.");
            }
        }
    }
}
