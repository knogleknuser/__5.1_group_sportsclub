/*
    This sql script should only be executed once to create table structures for the test database.
    It copies the table structures from the 5 tables in the original database (sportsclub)
    1) First create a new database called "sportsclub_test" on MySql
    2) Run this script
*/

CREATE TABLE sportsclub_test.member LIKE sportsclub.member;
CREATE TABLE sportsclub_test.zip LIKE sportsclub.zip;
CREATE TABLE sportsclub_test.registration LIKE sportsclub.registration;
CREATE TABLE sportsclub_test.sport LIKE sportsclub.sport;
CREATE TABLE sportsclub_test.team LIKE sportsclub.team;