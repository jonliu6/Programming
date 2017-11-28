CREATE TABLE IF NOT EXISTS xlogin
(
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  enabled BIT(1) NOT NULL,
  PRIMARY KEY (username)
);

INSERT INTO xlogin (username, password, enabled) VALUES ('jon','jon','1');
INSERT INTO xlogin (username, password, enabled) VALUES ('po','po','1');
INSERT INTO xlogin (username, password, enabled) VALUES ('user','user','1');

-- If you want to encrypt, use BCrypt, SHA256+, or MD5 (weak)
UPDATE xlogin SET password = '006cb570acdab0e0bfc8e3dcb7bb4edf' WHERE username = 'jon';
UPDATE xlogin SET password = 'f6122c971aeb03476bf01623b09ddfd4' WHERE username = 'po';
UPDATE xlogin SET password = 'ee11cbb19052e40b07aac0ca060c23ee' WHERE username = 'user';

CREATE TABLE IF NOT EXISTS xauthority
(
    username VARCHAR(50) NOT NULL,
	authority VARCHAR(50) NOT NULL
);

INSERT INTO xauthority (username, authority) VALUES ('jon', 'ROLE_USER');
INSERT INTO xauthority (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO xauthority (username, authority) VALUES ('po', 'ROLE_USER');
INSERT INTO xauthority (username, authority) VALUES ('po', 'ROLE_ADMIN');
