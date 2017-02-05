var oracledb = require('oracledb');
oracledb.outFormat = oracledb.OBJECT; // with JSON key/value pair format
var dbConfig = require('./db_config/dbconfig.js');

oracledb.getConnection(
  {
    user          : dbConfig.user,
    password      : dbConfig.password,
    connectString : dbConfig.connectionString
  },
  function(err, connection)
  {
    if (err) { console.error(err.message); return; }

    connection.execute(
      // "SELECT Name, External_Name FROM PO_UI_PROJECT_CAUSE WHERE System = :id",
	  "SELECT Id, Reference_Label, Type, Status, Priority, Assigned_Time, System_Completion_Time FROM po_order WHERE Status NOT IN ('cancelled','closed','archived') ORDER BY Creation_Time",
      //[1],  // bind value for :id
	  {},
	  // { outFormat: oracledb.OBJECT },
      function(err, result)
      {
        if (err) { console.error(err.message); return; }
        console.log(result.rows);
		// console.log(result.metaData);
        connection.close(function(err){
            if (err) {console.error(err.message);}
        });
      });
  });