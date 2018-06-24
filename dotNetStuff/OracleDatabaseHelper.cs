using Oracle.DataAccess.Client;
using Oracle.DataAccess.Types;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;

namespace RemoteDispatchTestClient.util
{
    class OracleDatabaseHelper
    {
        private String _host;  // database server name or IP address
        private int _port; // database server port #
        private String _sid; // database service ID or name
        private String _uid; // database user ID
        private String _passwd; // database user password
        private static String _connectionString; // database connection String
        private static OracleConnection _connection; // database connection
        private readonly static object lockObject = new object(); // lock object for synchronizaation
        
        /// <summary>
        /// constructor with initializing the database configuration
        /// </summary>
        /// <param name="hostName"></param>
        /// <param name="portNumber"></param>
        /// <param name="serviceId"></param>
        public OracleDatabaseHelper(String hostName, int portNumber, String serviceId)
        {
            _host = hostName;
            _port = portNumber;
            _sid = serviceId;

            _connectionString = "Data Source=(DESCRIPTION = (ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)(HOST = " + _host + ")(PORT = " + _port + ")))(CONNECT_DATA = (SID = " + _sid + ")));";
        }

        /// <summary>
        /// constructor with initializing the database configuration including the credential
        /// </summary>
        /// <param name="hostName"></param>
        /// <param name="portNumber"></param>
        /// <param name="serviceId"></param>
        /// <param name="userId"></param>
        /// <param name="password"></param>
        public OracleDatabaseHelper(String hostName, int portNumber, String serviceId, String userId, String password)
        {
            _host = hostName;
            _port = portNumber;
            _sid = serviceId;
            _uid = userId;
            _passwd = password;

            _connectionString = "Data Source=(DESCRIPTION = (ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)(HOST = " + _host + ")(PORT = " + _port + ")))(CONNECT_DATA = (SID = " + _sid + ")));" +
                                "User Id=" + _uid + ";Password=" + _passwd + ";";
        }

        /// <summary>
        /// used for executing a SQL statement - Insert, Update, Delete, and etc.
        /// </summary>
        /// <param name="sql"></param>
        /// <param name="parameters"></param>
        /// <returns></returns>
        public int ExecuteStatement(String sql, IList<IDataParameter> parameters)
        {
            int result = 0;
            
            IDbCommand cmd = _connection.CreateCommand();
            cmd.CommandText = sql;
            cmd.CommandType = CommandType.Text;
            if (parameters != null && parameters.Count > 0)
            {
                foreach (IDataParameter param in parameters)
                {
                    cmd.Parameters.Add(param);
                }
            }
            result = cmd.ExecuteNonQuery();
            
            return result;
        }

        /// <summary>
        /// used for run a SQL query - select - retrieving a single record
        /// </summary>
        /// <param name="sql"></param>
        /// <returns></returns>
        public object ExecuteQueryForSingle(String sql)
        {
            object result = null;
            IDbCommand cmd = Connection.CreateCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = sql;
            result = cmd.ExecuteScalar();

            return result;
        }

        /// <summary>
        /// used for run a SQL query - select - retrieving multiple records
        /// </summary>
        /// <param name="sql"></param>
        /// <returns></returns>
        public IDataReader ExecuteQuery(String sql)
        {
            IDataReader dtReader = null;
            
            IDbCommand cmd = Connection.CreateCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = sql;
            dtReader = cmd.ExecuteReader();
            
            return dtReader;
        }

        /// <summary>
        /// execute a stored procedure with return records
        /// </summary>
        /// <param name="procName"></param>
        /// <param name="parameters"></param>
        /// <returns></returns>
        public IDataReader ExecuteStoredProcedureWithReturn(String procName, IList<IDataParameter> parameters)
        {
            IDataReader dtReader = null;
            
            IDbCommand cmd = Connection.CreateCommand();
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.CommandText = procName;
            if (parameters != null && parameters.Count > 0)
            {
                cmd.Parameters.Clear();
                foreach (IDataParameter param in parameters)
                {
                    cmd.Parameters.Add(param);
                }
            }
            dtReader = cmd.ExecuteReader();
            

            return dtReader;
        }

        /// <summary>
        /// execute a stored procedure without a return
        /// </summary>
        /// <param name="procName"></param>
        /// <param name="parameters"></param>
        /// <returns></returns>
        public void ExecuteStoredProcedureWithoutReturn(String procName, IList<IDataParameter> parameters)
        {
            IDbCommand cmd = null;
            
            cmd = Connection.CreateCommand();
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.CommandText = procName;
            if (parameters != null && parameters.Count > 0)
            {
                cmd.Parameters.Clear();
                foreach (IDataParameter param in parameters)
                {
                    cmd.Parameters.Add(param);
                }
            }
            cmd.ExecuteNonQuery();
            
        }

        /// <summary>
        /// open the database connection, optional
        /// </summary>
        public void OpenConnection()
        {
            if (_connection == null)
            {
                lock (lockObject)
                {
                    _connection = new OracleConnection();
                    _connection.ConnectionString = _connectionString;
                }
            }
            _connection.Open();
        }

        /// <summary>
        /// close database connection
        /// </summary>
        public void CloseConnection()
        {
            if (_connection != null)
            {
                _connection.Close();
            }
        }

        public static String ConnectionString
        {
            get{ return _connectionString; }
        }

        static OracleConnection Connection
        {
            get
            {
                //if (_connection == null)
                //{
                //    lock (lockObject)
                //    {
                //        _connection = new OracleConnection();
                //        _connection.ConnectionString = _connectionString;
                //        _connection.Open();
                //    }
                //}
                return _connection;
            }
        }
    }
}
