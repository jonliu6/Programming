// var oracleHelper = require('./oracleHelper.js');
var dbConfig = require('./db_config/dbconfig.js');
var oracledb = require('oracledb');
oracledb.outFormat = oracledb.OBJECT; // with JSON key/value pair format
var express = require('express');
var app = express();
var serverPort = 8888;
// var bodyParser = require('body-parser');

// app.use(bodyParser());

app.get('/activeOrders', function(req,res){
    var sqlStr = 'SELECT o.Reference_Label, o.Type, o.Status, o.Priority, o.Assigned_Time, o.Dispatched_Time, o.Enroute_Time, o.Arrival_Time, o.System_Completion_Time, c.NAME AS Cause, ' +
                 's2a.AREA_DESC, s2a.DISTRICT_DESC ' +
                 'FROM po_order o, PO_BCH_SIMPLE_TO_AREA s2a, PO_UI_PROJECT_CAUSE c ' +
                 'WHERE s2a.SIMPLE_AREA_ID = o.SIMPLE_AREA_ID AND o.cause = c.name(+) AND o.ID > 0 ' + 
                 'AND o.Status NOT IN (\'cancelled\',\'closed\',\'archived\') ORDER BY Creation_Time';
    findActiveOrders(req, res, sqlStr);
});

// app.use(express.static(__dirname + '/public'));

app.get('/testJSON', function (req, res) {
    res.writeHead(200, {'Content-Type': 'application/json'});
    res.write('{"username":"testUser", "password":"pass"}');
    res.jsonp();
});

app.get('/', function (req, res) {
    res.writeHead(200, {'Content-Type': 'text/html'});
    res.write("No Data Requested, so none is returned");
    res.end();
});

function findActiveOrders(req, res, sqlStr) {
    oracledb.createPool({
			user          : dbConfig.user,
			password      : dbConfig.password,
			connectString : dbConfig.connectionString
		},
		function (err, pool) {
			if (err) {
				console.error('createPool() error: ' + err.message); 
				return;
			}
			
			console.log('createPool() completed.');
			pool.getConnection(function(err1, connection){
				if (err1) {
					console.error('getConnection() error: ' + err1.message);
					return;
				}
				
				console.log('getConnection() completed.');
				connection.execute(
                    sqlStr,
                    {},
                    {
                        maxRows: 10000 // large enough number to allow retrieve all
                        // resultSet: true
                    },
                    function(err2, result) {
                        if (err2) {
                            console.error('' + err2.message);
                            return;
                    }
                    
                    // console.log('db response is ready '+result.rows);
                    res.writeHead(200, {'Content-Type': 'application/json'});
                    res.end(JSON.stringify(result.rows));    
                    // res.end(JSON.stringify(result.resultSet.getRows()));
                        
                    connection.close(function(err){
                        if (err) {console.error(err.message);}
                        console.log('oracle connection closed now.');
                    });   
                });
			});
		});
}

var server = app.listen(serverPort, function () {
   console.log("Example app listening at http://%s:%s", 'localhost', serverPort)
});

