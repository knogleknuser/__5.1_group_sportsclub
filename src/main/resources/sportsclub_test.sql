/*
    This sql script should only be executed once to create table structures for the test database.
    It replicates the table structures from the 5 tables in the original database (sportsclub)
    1) First create a new database called "sportsclub_test" 
    2) Run this script
*/
-- Table structure for table zip
CREATE TABLE zip (
  zip int PRIMARY KEY,
  city varchar(45) NOT NULL
);


-- Table structure for table sport
CREATE TABLE sport (
  sport_id serial PRIMARY KEY,
  sport varchar(45) NOT NULL
);


-- Table structure for table team
CREATE TABLE team (
  team_id varchar(10) NOT NULL,
  sport_id int NOT NULL,
  PRIMARY KEY (team_id),
  CONSTRAINT fk_team_sport1 FOREIGN KEY (sport_id) REFERENCES sport (sport_id)
);


-- Table structure for table member
CREATE TABLE member (
  member_id serial PRIMARY KEY,
  name varchar(45),
  address varchar(45),
  zip int,
  gender varchar(1),
  year varchar(45),
  CONSTRAINT fk_member_zip FOREIGN KEY (zip) REFERENCES zip (zip)
);

-- Table structure for table registration
CREATE TABLE registration (
  member_id int NOT NULL,
  team_id varchar(10) NOT NULL,
  price int,
  PRIMARY KEY (member_id, team_id),
  CONSTRAINT fk_registration_member1 FOREIGN KEY (member_id) REFERENCES member (member_id),
  CONSTRAINT fk_registration_team1 FOREIGN KEY (team_id) REFERENCES team (team_id)
);


