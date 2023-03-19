select * from reservation where  reservation_date="2023-03-10" ;

-- following sql give occupancy for date: 2023-03-10 and selected timeslot 17:30 
SELECT sum(no_of_table) AS occupied_tables, reservation_start, reservation_end FROM reservation  -- , start_time, occupied_till
where  reservation_date="2023-03-10"  and reservation_end >="17:30:00" And  reservation_start <="20:00:00"
--                           date                  -- selection timeslot              -- end time for selected timeslot
group by reservation_start, reservation_end
;

-- sql for find_table method
-- to book a table for 17:30:00 we need to find the no of table available from 17:30:00 to 20:00:00 - assuming table will be occupied for 2.5 hour for eveing
-- in java we declare variabes : date, start_time, end_time (= if breakfast/lunch, start_time + 1.5 hour, while for dinner start_time + 2.5 hours)-- 
-- time_slot = start_time
-- Note: 20 is total number of table in the restaurant

SELECT "17:30:00" as time_slot, (20 -sum(no_of_table)) AS available_tables FROM reservation
where  reservation_date="2023-03-10"  and reservation_end >="17:30:00" And  reservation_start <="20:00:00";
--                          date                  -- selection timeslot      -- end time for selected timeslot
-- we can run above query to find availability for other close time slot inside a for loop

SELECT "17:30:00" as time_slot, (20 -sum(no_of_table)) AS available_tables FROM reservation
where  reservation_date="2023-03-10"  and reservation_end >="17:30:00" And  reservation_start <="20:00:00";