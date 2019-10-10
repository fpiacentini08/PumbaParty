CREATE database pumba_party;
USE pumba_party
CREATE TABLE users (username VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, room_id BIGINT NOT NULL,  PRIMARY KEY (username)) COLLATE='latin1_swedish_ci' ENGINE=InnoDB;
