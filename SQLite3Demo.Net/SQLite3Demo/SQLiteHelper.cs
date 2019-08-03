using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Data.SQLite;
using System.IO;
using System.Linq;
using System.Text;

namespace SQLite3Demo
{
    class SQLiteHelper
    {
        private String _connectionString = null;
        private SQLiteConnection _connection = null;

        /// <summary>
        /// useful queries
        /// select sqlite_version(); // for version
        /// </summary>
        /// <param name="fileName"></param>

        public SQLiteHelper(String fileName)
        {
            if (fileName != null && File.Exists(fileName))
            {
                _connectionString = "Data Source=" + fileName + ";Version=3";
                _connection = new SQLiteConnection(_connectionString);
            }
        }

        public SQLiteConnection Connection
        {
            get { return _connection; }
            set { _connection = value; }
        }

        public void createConnection()
        {
            if (_connection == null)
            {
                _connection = new SQLiteConnection(_connectionString);
            }
        }

        public DbDataReader executeQuery(String sql) {
            SQLiteDataReader dataReader = null;
            try
            {
                SQLiteCommand cmd = new SQLiteCommand(sql, _connection);
                dataReader = cmd.ExecuteReader();
            }
            catch (Exception ex)
            {
                throw new SQLiteException(ex.Message);
            }
            
            return dataReader;
        }

        public int executeNonQuery(String sql)
        {
            SQLiteCommand cmd = new SQLiteCommand(sql, _connection);
            return cmd.ExecuteNonQuery();
        }
    }
}
