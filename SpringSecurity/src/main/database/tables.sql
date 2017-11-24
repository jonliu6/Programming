CREATE TABLE IF NOT EXISTS xlogin
(
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  enabled BIT(1) NOT NULL,
  PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS xauthority
(
    username VARCHAR(50) NOT NULL,
	authority VARCHAR(50) NOT NULL
);