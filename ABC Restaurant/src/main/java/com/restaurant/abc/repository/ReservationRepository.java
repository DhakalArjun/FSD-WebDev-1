package com.restaurant.abc.repository;

import com.restaurant.abc.exception.DatabaseException;
import com.restaurant.abc.model.FindTable;
import com.restaurant.abc.model.Reservation;
import com.restaurant.abc.repository.entity.FindCustomerIdEntity;
import com.restaurant.abc.repository.entity.FindTableEntity;
import com.restaurant.abc.repository.entity.ReservationEntity;
import com.restaurant.abc.repository.mapper.FindCustomerIDRowMapper;
import com.restaurant.abc.repository.mapper.FindTableRowMapper;
import com.restaurant.abc.repository.mapper.ReservationRowMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.restaurant.abc.constants.ErrorMessage.DATABASE_EXCEPTION;

@Repository
public class ReservationRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ModelMapper modelMapper;

    public List<Reservation> getAllReservation(){
        try {
            String sqlStr = "SELECT * FROM reservation_view";
            List<ReservationEntity> reservationEntityList = jdbcTemplate.query(sqlStr, new ReservationRowMapper());
            List<Reservation> reservationResult = new ArrayList<>();
            for (ReservationEntity entity : reservationEntityList) {
                Reservation reservation = modelMapper.map(entity, Reservation.class);
                reservationResult.add(reservation);
            }
            return reservationResult;
        }
        catch (Exception ex){
            throw new DatabaseException(DATABASE_EXCEPTION);
        }
    } //end of Method

    public Reservation getReservationById(long reservationId){
        try {
            String sqlStr = "SELECT * FROM reservation_view WHERE reservation_id=?";
            ReservationEntity reservationEntity = (ReservationEntity) jdbcTemplate.queryForObject(sqlStr, new ReservationRowMapper(), reservationId);
            return modelMapper.map(reservationEntity, Reservation.class);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception ex){
            throw new DatabaseException(DATABASE_EXCEPTION);
        }
    }

    public List<Reservation> getReservationByDate(LocalDate date){
        try {
            String sqlStr = "SELECT * FROM reservation_view WHERE reservation_date>=?";
            List<ReservationEntity> reservationEntityList = jdbcTemplate.query(sqlStr, new ReservationRowMapper(), date);
            List<Reservation> reservationResult = new ArrayList<>();
            for (ReservationEntity entity : reservationEntityList) {
                Reservation reservation = modelMapper.map(entity, Reservation.class);
                reservationResult.add(reservation);
            }
            return reservationResult;
        }
        catch (Exception ex){
            throw new DatabaseException(DATABASE_EXCEPTION);
        }
    }

    public List<Reservation> getReservationByEmail(String email) {
        try {
            String sqlStr = "SELECT * FROM reservation_view WHERE email=?";;
            List<ReservationEntity> reservationEntityList = jdbcTemplate.query(sqlStr, new ReservationRowMapper(), email);
            List<Reservation> reservationResult = new ArrayList<>();
            for (ReservationEntity entity : reservationEntityList) {
                Reservation reservation = modelMapper.map(entity, Reservation.class);
                reservationResult.add(reservation);
            }
            return reservationResult;
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception exc){
            throw new DatabaseException(DATABASE_EXCEPTION);
        }
    }

    public List<Reservation> getReservationByName(String name) {
        try {
            String wildcardName = "%" + name + "%";
            String sqlStr = "SELECT * FROM reservation_view HAVING first_name LIKE ? OR last_name LIKE ?";
//            String sqlStr = "SELECT * FROM reservation_view WHERE first_name=?";
            List<ReservationEntity> reservationEntityList = jdbcTemplate.query(sqlStr, new ReservationRowMapper(), wildcardName, wildcardName);
//            List<ReservationEntity> reservationEntityList = jdbcTemplate.query(sqlStr, new ReservationRowMapper(), name);
            List<Reservation> reservationResult = new ArrayList<>();
            for (ReservationEntity entity : reservationEntityList) {
                Reservation reservation = modelMapper.map(entity, Reservation.class);
                reservationResult.add(reservation);
            }
            return reservationResult;
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception exc){
            throw new DatabaseException(DATABASE_EXCEPTION);
        }
    }




    public void createNewCustomer (Reservation newReservation){
        try {
            String sqlGetCustomerByEmail = "SELECT customer_id FROM customer WHERE email=?";
            FindCustomerIdEntity findCustomerIdEntity = jdbcTemplate.queryForObject(sqlGetCustomerByEmail, new FindCustomerIDRowMapper(), newReservation.getEmail());//
        }
        catch(EmptyResultDataAccessException e) {
            String sqlCustomerInsert = "INSERT INTO customer (first_name, last_name, phone, email) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sqlCustomerInsert, newReservation.getFirstName(), newReservation.getLastName(), newReservation.getPhone(), newReservation.getEmail());
            }
        }

    public Reservation createNewReservation (Reservation newReservation){
        try{
            createNewCustomer(newReservation);
            String sqlGetCustomerId = "SELECT customer_id FROM customer WHERE email=?";
            FindCustomerIdEntity findCustomerIdEntity = jdbcTemplate.queryForObject(sqlGetCustomerId, new FindCustomerIDRowMapper(), newReservation.getEmail());
            long customerIdGet = findCustomerIdEntity.getCustomerID();

            String sqlStr = "INSERT INTO reservation (customer_id, reservation_date, reservation_start, reservation_end, guest_number, no_of_table, communication_mode, special_request) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sqlStr, customerIdGet, newReservation.getReservationDate(), newReservation.getReservationStart(), newReservation.getReservationEnd(), newReservation.getGuestNumber(), newReservation.getNoOfTables(), newReservation.getCommunicationMode(), newReservation.getSpecialRequest());
            //return getReservationById(newReservation.getReservationId());
            long reservationId = jdbcTemplate.queryForObject("SELECT MAX(reservation_id) FROM reservation", Integer.class);
            return getReservationById(reservationId);
        }
        catch (Exception ex){
            throw new DatabaseException(DATABASE_EXCEPTION);
        }
    }

    public void updateReservation(long reservationId, Reservation updatedReservation) {
        try{
            String sqlCustomer  = "UPDATE customer SET first_name=?, last_name=?, phone=?, email=? WHERE customer_id=?";
            jdbcTemplate.update(sqlCustomer,updatedReservation.getFirstName(), updatedReservation.getLastName(), updatedReservation.getPhone(), updatedReservation.getEmail(), updatedReservation.getCustomerId());

            String sqlReservation = "UPDATE reservation SET reservation_date=?, reservation_start=?, reservation_end=?, guest_number=?, no_of_table=?," +
                    " communication_mode=?, special_request=?, update_status= 'updated', updated_by=? WHERE reservation_id=?";
            jdbcTemplate.update(sqlReservation, updatedReservation.getReservationDate(), updatedReservation.getReservationStart(), updatedReservation.getReservationEnd(), updatedReservation.getGuestNumber(),
                    updatedReservation.getNoOfTables(), updatedReservation.getCommunicationMode(), updatedReservation.getSpecialRequest(), updatedReservation.getUpdatedBy(), reservationId);
        }
        catch (Exception ex){
            throw new DatabaseException(DATABASE_EXCEPTION);
        }
    }

    public void cancelReservation(long reservationId, int updatedBy) {
        try{
            String sqlReservation = "UPDATE reservation SET no_of_table=0, update_status='canceled', updated_by=? WHERE reservation_id=?";
            jdbcTemplate.update(sqlReservation, updatedBy, reservationId);

        }
        catch (Exception ex){
            throw new DatabaseException(DATABASE_EXCEPTION);
        }
    }

    public List<FindTable> getAvailableTables(LocalDate date, String inputTimeStr){
        LocalTime breakFastStart = LocalTime.parse("07:29");
        LocalTime breakFastEnd = LocalTime.parse("10:00");
        LocalTime lunchStart = LocalTime.parse("11:29");
        LocalTime lunchEnd= LocalTime.parse("02:30");
        LocalTime dinnerStart = LocalTime.parse("16:59");
        LocalTime dinnerFirstSlot = LocalTime.parse("17:00");
        LocalTime dinnerSecondLastSlot = LocalTime.parse("20:59");
        LocalTime beforeDinnerEnd = LocalTime.parse("23:00");
        LocalTime inputTime = LocalTime.parse(inputTimeStr);

        List<LocalTime> timeSlotList = new ArrayList<>();
        long durationMinutes;

        if (inputTime.isAfter(breakFastStart) && inputTime.isBefore(breakFastEnd)){
            timeSlotList = Arrays.asList(LocalTime.parse("07:30"),LocalTime.parse("08:00"),LocalTime.parse("08:30"),LocalTime.parse("09:00"));
            durationMinutes = 89;
        }
        else if (inputTime.isAfter(lunchStart) && inputTime.isBefore(lunchEnd)){
            timeSlotList = Arrays.asList(LocalTime.parse("11:30"),LocalTime.parse("12:00"),LocalTime.parse("12:30"),LocalTime.parse("1:00"));
            durationMinutes = 89;
        }
        else if (inputTime.equals(dinnerFirstSlot)){
           timeSlotList = Arrays.asList(LocalTime.parse("17:00"),LocalTime.parse("17:30"),LocalTime.parse("18:00"),LocalTime.parse("18:30"));
            durationMinutes = 149;
        }else if (inputTime.isAfter(dinnerSecondLastSlot)) {
            timeSlotList = Arrays.asList(LocalTime.parse("19:30"), LocalTime.parse("20:00"), LocalTime.parse("20:30"), LocalTime.parse("21:00"));
            durationMinutes = 149;
        }else{
            timeSlotList.add(inputTime.minusMinutes(30));
            timeSlotList.add(inputTime);
            timeSlotList.add(inputTime.plusMinutes(30));
            timeSlotList.add(inputTime.plusMinutes(60));
            durationMinutes = 149;
        }

        List<FindTable>  findTableList = new ArrayList<>();
        String sqlStr = "SELECT ? as time_slot, (20 -sum(no_of_table)) AS available_tables FROM reservation WHERE reservation_date=? AND reservation_end >=? AND  reservation_start <=?";
        for (LocalTime timeSlot : timeSlotList){
            LocalTime reservationEndAt = timeSlot.plusMinutes(durationMinutes);

            FindTableEntity findTableEntity= (FindTableEntity) jdbcTemplate.queryForObject(sqlStr, new FindTableRowMapper(), timeSlot, date, timeSlot, reservationEndAt);
            FindTable findTableResult = modelMapper.map(findTableEntity, FindTable.class);
            findTableList.add(findTableResult);
        }
        return findTableList;
    }
}//end of RepositoryClass
