CREATE TABLE t_knowledge_base (
    title VARCHAR(200),
    category VARCHAR(100),
    description TEXT
);

INSERT INTO t_knowledge_base (title, category, description)
  VALUES ('How to use SQLite3 JDBC','Java','1. add SQLite3-JDBC library (jar file) into the classpath. 2. implement connection, statement, query and etc.');
  
INSERT INTO t_knowledge_base (title, category, description)
  VALUES ('How to initialize SQLite3 database','Database','1. sqlite3 <db name>. ' || CHAR(10) || '2. CREATE TABLE <table name> (<field1> VARCHAR(10), <field2> INTEGER);' || CHAR(10) || '3. INSERT INTO <table name> (<field1>, <field2>) VALUES (<value1>, <value2>)');