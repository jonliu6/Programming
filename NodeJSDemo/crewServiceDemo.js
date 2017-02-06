// var oracleHelper = require('./oracleHelper.js');
var dbConfig = require('./db_config/dbconfig.js');
var oracledb = require('oracledb');
oracledb.outFormat = oracledb.OBJECT; // with JSON key/value pair format
var express = require('express');
var app = express();
var url = require('url');
var serverPort = 8888;
// var bodyParser = require('body-parser');

// app.use(bodyParser());

app.get('/recentCrewLocations', function(req,res){
	var aCrewNumber = '0080';
	var numberOfDaysBefore = 15;
	// parse query parameters. e.g. /recentCrewLocations?crewNumber=0080&daysBefore=15
	var queryObj = url.parse(req.url,true).query;
	if (queryObj != null) {
		// console.log('Query Parameters: ' + queryObj['crewNumber'] + ', ' + queryObj['daysBefore']);
		aCrewNumber = queryObj['crewNumber'];
		numberOfDaysBefore = queryObj['daysBefore'];
	}
	
    var sqlStr = 'SELECT SL_LATITUDE AS Latitude, SL_LONGITUDE AS Longitude FROM po_bch_crew_gps_log WHERE bch_mdt_no = \'' + aCrewNumber + '\' AND EXTRACT_TIME > (SYSDATE - ' + numberOfDaysBefore + ') ORDER BY EXTRACT_TIME';
    findRecentCrewLocations(req, res, sqlStr);
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

function findRecentCrewLocations(req, res, sqlStr) {
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
				
				console.log('getConnection() completed and executing SQL: ' + sqlStr);
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

