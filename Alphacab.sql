--DROP Table User;
CREATE TABLE Users (
  Username varchar(20),
  Password varchar(60),
  UserType varchar(10),
  id int not null,
  PRIMARY KEY (id)
);

INSERT INTO Users (Username, Password, UserType, id) VALUES
('Eva Smith', 'eSmith', 'driver', 1),
('Rob Manton', 'rManton', 'driver', 2),
('Bob Currie', 'bCurrie', 'driver', 3),
('Jim Hunter', 'jHunter', 'driver', 4),
('Phil Johnson', 'pJohnson', 'admin', 5),
('Saim Soyler', 'sSoyler', 'admin', 6),
('Gul Hikmet', 'gHikmet', 'customer', 7),
('John Smith', 'jSmith', 'customer', 8),
('Mehmet Aydin', 'mAydin', 'customer', 9),
('Mark Johnson', 'mJohnson', 'customer', 10);

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
('Phil Johnson', '75 Squires Lane, London, N3', 5),
('Saim Soyler', '2 Rosemary Ave, London, N3', 6),
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
  PRIMARY KEY (id)
);


INSERT INTO Demands (id, Name, Address, Destination, Date, Time, Status) VALUES
(1, 'M. E. Aydin', 'Finchley, London', 'King''s Cross, London', '2015-11-02', '09:22:18', 'Outstanding');

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

INSERT INTO Journey (jid, Destination, Distance, id, Registration, Date, Time) VALUES
(1, 'King''s Cross Station, London', 5, 1, 'BN60WKA', '2015-10-14', '09:30:00'),
(2, 'Heathrow Terminal 3, London', 20, 7, 'BN60WKA', '2015-10-14', '12:00:00'),
(3, '120 Green Lanes, London, N13', 7, 7, 'AK52VZV', '2015-10-15', '06:00:00'),
(4, '131 Stoke Newington High Road, London, N12', 8, 7, 'AK52VZV', '2015-10-15', '12:00:00'),
(5, 'Luton Airport, Luton', 30, 1, 'R34AKP', '2015-10-22', '10:00:00');