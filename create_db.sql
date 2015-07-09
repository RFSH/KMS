DROP TABLE users;

CREATE TABLE users (
  id varchar(5) PRIMARY KEY,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  username varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  national_id varchar(100),
  password varchar(100)
);