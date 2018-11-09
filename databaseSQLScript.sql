CREATE TABLE Customer (
  username varchar(20) NOT NULL,
  password varchar(20),
  name varchar(20),
  home_address varchar(150),
  start_address varchar(150),
  end_address varchar(150),
  contact_number varchar(20),
  PRIMARY KEY (username) 
) ;
INSERT INTO Customer (username, password, name, home_address, start_address, end_address, contact_number) VALUES(
'c2-sellick', 
'0000', 
'Callum', 
'1 Make belive Rd, Coldhabour Ln, Bristol, BS32 123', 
'2 Make belive Rd, Coldhabour Ln, Bristol, BS32 123', 
'3 Make belive Rd, Coldhabour Ln, Bristol, BS32 123', 
'07854321556');

CREATE TABLE Admin (
  username varchar(20) NOT NULL,
  password varchar(20),
  name varchar(20),
  contact_number varchar(20),
  PRIMARY KEY (username) 
) ;
INSERT INTO Admin (username, password, name, contact_number) VALUES(
'k2-Bourne', 
'007', 
'Jason', 
'00700700700');

CREATE TABLE Driver (
  username varchar(20) NOT NULL,
  password varchar(20),
  name varchar(20),
  vehicle_reg varchar(20),
  contact_number varchar(20),
  PRIMARY KEY (username) 
) ;
INSERT INTO Driver (username, password, name, vehicle_reg, contact_number) VALUES(
'B2_Pop', 
'007', 
'Bogdan-Stefan',
'P0P 513FAN', 
'11234567891');