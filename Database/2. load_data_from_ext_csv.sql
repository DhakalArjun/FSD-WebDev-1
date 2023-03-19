/***** Table: reservation_list  *****/
USE abc_restaurant_db;
SET GLOBAL local_infile =1;
-- ------------------------------------------------------------------------------------------------------------------------
-- populating reservation table
LOAD DATA LOCAL INFILE 'C:/Users/15144/Dropbox/FSD-07 WebService/Project InProgress/Database/data csv/employees.csv'
INTO TABLE employee
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
-- ------------------------------------------------------------------------------------------------------------------------
-- populating reservation table
LOAD DATA LOCAL INFILE 'C:/Users/15144/Dropbox/FSD-07 WebService/Project InProgress/Database/data csv/customer_list.csv'
INTO TABLE customer
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
-- ------------------------------------------------------------------------------------------------------------------------

-- populating reservation table
LOAD DATA LOCAL INFILE 'C:/Users/15144/Dropbox/FSD-07 WebService/Project InProgress/Database/data csv/reservation_list.csv'
INTO TABLE reservation
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
-- ------------------------------------------------------------------------------------------------------------------------
SET GLOBAL local_infile =0;


