package com.restaurant.abc.api;

import com.restaurant.abc.exception.DatabaseException;
import com.restaurant.abc.exception.ReservationNotFoundException;
import com.restaurant.abc.model.FindTable;
import com.restaurant.abc.model.Reservation;
import com.restaurant.abc.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/admin/reservation/all")
    public ResponseEntity<List<Reservation>> getAllReservation(){
        return new ResponseEntity<>(reservationService.getAllReservation(), HttpStatus.OK);
    } // end of getALLReservation method

    @GetMapping("/admin/reservation/date")
    public ResponseEntity<List<Reservation>> getReservationByDate(@RequestParam LocalDate date){
        return new ResponseEntity<>(reservationService.getReservationByDate(date), HttpStatus.OK);
    }


    @GetMapping("/admin/reservation/id")
    public ResponseEntity<Reservation> getReservationById(@RequestParam long reservationId, Model model){
        try {
            model.addAttribute("getByID", reservationService.getReservationById(reservationId));
            return new ResponseEntity<>(reservationService.getReservationById(reservationId), HttpStatus.OK);
        }
        catch (ReservationNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }//end of getReservationById method

    @GetMapping("/admin/reservation/email")
    public ResponseEntity<List<Reservation>> getReservationByEmail(@RequestParam String email){
        try {
            return new ResponseEntity<>(reservationService.getReservationByEmail(email), HttpStatus.OK);
        }
        catch (ReservationNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }//end of getReservationBySkuId method

    @GetMapping("/admin/reservation/name")
    public ResponseEntity<List<Reservation>> getReservationByName(@RequestParam String name){
        try {
            return new ResponseEntity<>(reservationService.getReservationByName(name), HttpStatus.OK);
        }
        catch (ReservationNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }//end of getReservationBySkuId method


    @PostMapping("/reservation-form")
    public ResponseEntity<Reservation> createNewReservation(@RequestBody Reservation newReservation){
        try {
            return new ResponseEntity<>(reservationService.createNewReservation(newReservation), HttpStatus.CREATED);
        }
        catch (DatabaseException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } //end of createNewReservation method

    @PutMapping("/admin/reservation/update")
    public ResponseEntity<Reservation> updateReservation(@RequestParam long reservationId, @RequestBody Reservation updatedReservation){
        try {
            return new ResponseEntity<>(reservationService.updateReservation(reservationId, updatedReservation), HttpStatus.OK);
        }
        catch(ReservationNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (DatabaseException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } //end of updateReservation method

    @PutMapping("/admin/reservation/cancel")
    public ResponseEntity<Reservation> cancelReservation(@RequestParam long reservationId, @RequestParam int updatedBy){
        try {
            return new ResponseEntity<>(reservationService.cancelReservation(reservationId, updatedBy), HttpStatus.OK);
        }
        catch(ReservationNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (DatabaseException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } //end of updateReservation method

    @GetMapping("reservation/tables")
    public ResponseEntity<List<FindTable>> getAvailableTables(@RequestParam String inputDateStr, @RequestParam String inputTimeStr, ModelMap model){
        LocalDate date = LocalDate.parse(inputDateStr);
        List<FindTable> findTableResult = reservationService.getAvailableTables(date, inputTimeStr);
        model.addAttribute("tableList",findTableResult);
        return new ResponseEntity<>(findTableResult, HttpStatus.OK);
    }
}// end of ReservationController
