CREATE database pumba_party;
USE pumba_party
CREATE TABLE users (password VARCHAR(255) NOT NULL, username VARCHAR(255) NOT NULL, PRIMARY KEY (username)) COLLATE='latin1_swedish_ci' ENGINE=InnoDB;
