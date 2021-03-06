--DROP Table User;
CREATE TABLE Users (
  Username varchar(20),
  Password varchar(60),
  UserType varchar(10),
  id int not null,
  PRIMARY KEY (id)
);

INSERT INTO Users (Username, Password, UserType, id) VALUES
('Eva Smith', 'eSmith', 'customer', 1),
('Rob Manton', 'rManton', 'customer', 2),
('Bob Currie', 'bCurrie', 'customer', 3),
('Jim Hunter', 'jHunter', 'customer', 4),
('Phil Johnson', 'pJohnson', 'admin', 5),
('Saim Soyler', 'sSoyler', 'admin', 6),
('Gul Hikmet', 'gHikmet', 'customer', 7),
('John Smith', 'jSmith', 'driver', 8),
('Mehmet Aydin', 'mAydin', 'customer', 9),
('Mark Johnson', 'mJohnson', 'driver', 10),
('Callum Sellick', 'cSellick', 'admin', 11);

--DROP Table Customer;
CREATE TABLE Customer (
  Name varchar(20),
  Address varchar(60),
  id int not null,
  PRIMARY KEY (id)
);


INSERT INTO Customer (Name, Address, id) VALUES
('Eva Smith', '129 Station Rd, London, N3 2AS', 1),
('Rob Manton', '23 Bow Lane, London, N3', 2),
('Bob Currie', '54 Teignmouth Rd, London, NW2', 3),
('Jim Hunter', '765 High Road, London, N12', 4),
('Gul Hikmet', '31 Clifton Rd, London, N3 2SG', 7);

-- --------------------------------------------------------
--DROP Table Demands;
CREATE TABLE Demands (
  id int NOT NULL,
  Name varchar(20),
  Address varchar(60),
  Destination varchar(60),
  Date date DEFAULT NULL,
  Time time DEFAULT NULL,
  Status varchar(15) NOT NULL,
  Booked_By varchar(20),
  PRIMARY KEY (id)
);


-- --------------------------------------------------------
--DROP Table Drivers;
CREATE TABLE Drivers (
  Registration varchar(10) NOT NULL,
  Name varchar(20),
  PRIMARY KEY (Registration)
);


INSERT INTO Drivers (Registration, Name) VALUES
('AK52VZV', 'John Smith'),
('BN60WKA', 'Mehmet Aydin'),
('R34AKP', 'Mark Johnson');

-- --------------------------------------------------------

--DROP Table Journey;
CREATE TABLE Journey (
  jid int NOT NULL,
  id int NOT NULL,
  Destination varchar(60),
  Distance integer NOT NULL DEFAULT 1,
  Registration varchar(10) NOT NULL,
  Date date NOT NULL,
  Time time DEFAULT NULL,
  PRIMARY KEY (jid)
);

Alter table Journey add foreign key (id) references Customer;
Alter table Journey add foreign key (Registration) references Drivers;

CREATE TABLE Pricing (
  Mileage decimal,
  Price decimal,
  Vat decimal
);

INSERT INTO Pricing (Mileage, Price, Vat) VALUES
(1, 6, 20);

CREATE TABLE Invoices (
  ID integer,
  JID integer,
  CustomerName varchar(30),
  DriverReg varchar(8),
  Mileage decimal,
  Date varchar(20),
  Time varchar(20),
  Price decimal
);