-- login by using: c:\temp> psql -h localhost -d xproject_db -U xuser -W
-- xproject_db> \list
SELECT * FROM information_schema.tables WHERE table_type = 'BASE TABLE' AND table_schema NOT IN ('pg_catalog','information_schema');

CREATE TABLE t_knowledge_category (id INTEGER, name VARCHAR(30), parent_id INTEGER, CONSTRAINT pk_knowledge_category PRIMARY KEY (id));

INSERT INTO t_knowledge_category (id, name, parent_id) VALUES (1, 'Software Development', 0);
INSERT INTO t_knowledge_category (id, name, parent_id) VALUES (2, 'Home Family Stuff', 0);
INSERT INTO t_knowledge_category (id, name, parent_id) VALUES (3, 'Hobby', 0);
INSERT INTO t_knowledge_category (id, name, parent_id) VALUES (4, 'Entertainment', 0);
INSERT INTO t_knowledge_category (id, name, parent_id) VALUES (5, 'Learning', 0);
INSERT INTO t_knowledge_category (id, name, parent_id) VALUES (101, 'Programm Language', 1);
INSERT INTO t_knowledge_category (id, name, parent_id) VALUES (102, 'Database', 1);
INSERT INTO t_knowledge_category (id, name, parent_id) VALUES (103, 'System and Environment', 1);
INSERT INTO t_knowledge_category (id, name, parent_id) VALUES (104, 'Configuration', 1);
INSERT INTO t_knowledge_category (id, name, parent_id) VALUES (201, 'Home Improvement', 2);
INSERT INTO t_knowledge_category (id, name, parent_id) VALUES (202, 'Maintenance Contact', 2);

SELECT * FROM t_knowledge_category ORDER BY parent_id, id;

CREATE TABLE t_knowledge_item (id BIGINT, title VARCHAR(100), description VARCHAR, CONSTRAINT pk_knowledge_item PRIMARY KEY (id));
