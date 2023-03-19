package com.restaurant.abc.service;

import com.restaurant.abc.exception.ReservationNotFoundException;
import com.restaurant.abc.model.FindTable;
import com.restaurant.abc.model.Reservation;
import com.restaurant.abc.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.restaurant.abc.constants.ErrorMessage.*;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private JavaMailSender mailSender;

    public List<Reservation> getAllReservation() {
        return reservationRepository.getAllReservation();
    }

    public Reservation getReservationById(long reservationID) {
        Reservation reservationMatched = reservationRepository.getReservationById(reservationID);
        if(reservationMatched == null){
            throw new ReservationNotFoundException(String.format(RESERVATION_NOT_FOUND_WITH_THIS_ID, reservationID));
        }
        return reservationMatched;
    }

    public List<Reservation> getReservationByDate(LocalDate date){
        return reservationRepository.getReservationByDate(date);
    }


    public List<Reservation> getReservationByEmail(String email) {
        List<Reservation> reservationMatchedList = reservationRepository.getReservationByEmail(email);
        if(reservationMatchedList == null){
            throw new ReservationNotFoundException(String.format(RESERVATION_NOT_FOUND_WITH_THIS_EMAIL, email));
        }
        return reservationMatchedList;
    }

    public List<Reservation> getReservationByName(String name) {
        List<Reservation> reservationMatchedList = reservationRepository.getReservationByName(name);
        if(reservationMatchedList == null){
            throw new ReservationNotFoundException(String.format(RESERVATION_NOT_FOUND_WITH_THIS_NAME, name));
        }
        return reservationMatchedList;
    }

    public void createNewCustomer(Reservation newReservation){
        reservationRepository.createNewCustomer(newReservation);
    }




    public Reservation createNewReservation(Reservation newReservation) {
        Reservation reservationNew = reservationRepository.createNewReservation(newReservation);
        SimpleMailMessage notification = new SimpleMailMessage();
        notification.setFrom("fsd07team@gmail.com");
        notification.setTo(newReservation.getEmail());
        notification.setText("Thank you for your reservation at ABC Restaurant, your reservation details are as follows \n" +

                " Reservation ID: " + reservationNew.getReservationId() + "\n" +
                " Date: " + reservationNew.getReservationDate() +"\n" +
                " Time: " + reservationNew.getReservationStart() +"\n" +
                " No of guest: " + reservationNew.getGuestNumber() +"\n \n" +
                "Note: Please call us any of above detail is not correct. \n " +
                "      If you need to cancel this reservation please call at least 6 hours prior to your reservation time."
        );

        notification.setSubject("Notification: Reservation confirmation");
        mailSender.send(notification);
        return reservationNew;
    }


    public Reservation updateReservation(long reservationId, Reservation updatedReservation) {
        Reservation reservationPresent = reservationRepository.getReservationById(reservationId);
        if (reservationPresent == null) {
            throw new ReservationNotFoundException(String.format(RESERVATION_NOT_FOUND_WITH_THIS_ID, reservationId));
        }
        reservationRepository.updateReservation(reservationId, updatedReservation);
        Reservation updatedNewRes = reservationRepository.getReservationById(reservationId);
        SimpleMailMessage notification = new SimpleMailMessage();
        notification.setFrom("fsd07team@gmail.com");
        notification.setTo(updatedNewRes.getEmail());

        notification.setText("As per your request your reservation at ABC Restaurant has been updated, your reservation details are as follows \n" +
                " Reservation ID: " + updatedNewRes.getReservationId() + "\n" +
                " Date: " + updatedNewRes.getReservationDate() +"\n" +
                " Time: " + updatedNewRes.getReservationStart() +"\n" +
                " No of guest: " + updatedNewRes.getGuestNumber() +"\n" +
                " Reservation Updated by (Employee ID): " + updatedNewRes.getUpdatedBy() +"\n \n" +

                "Note: Please call us any if above detail is still incorrect. \n " +
                "      If you need to cancel this reservation please call at least 6 hours prior to your reservation time."
        );
        notification.setSubject("Notification: Reservation Update Confirmation");
        mailSender.send(notification);
        return updatedNewRes;
    }

    public Reservation cancelReservation(long reservationId, int updatedBy) {
        Reservation reservationPresent = reservationRepository.getReservationById(reservationId);
        if (reservationPresent == null) {
            throw new ReservationNotFoundException(String.format(RESERVATION_NOT_FOUND_WITH_THIS_ID, reservationId));
        }
        reservationRepository.cancelReservation(reservationId, updatedBy);
        Reservation canceledRes = reservationRepository.getReservationById(reservationId);
        SimpleMailMessage notification = new SimpleMailMessage();
        notification.setFrom("fsd07team@gmail.com");
        notification.setTo(canceledRes.getEmail());

        notification.setText("As per your request your reservation at ABC Restaurant has been canceled, your reservation details was as follows \n" +
                " Reservation ID: " + canceledRes.getReservationId() + "\n" +
                " Date: " + canceledRes.getReservationDate() +"\n" +
                " Time: " + canceledRes.getReservationStart() +"\n" +
                " No of guest: " + canceledRes.getGuestNumber() +"\n" +
                " Reservation Updated by (Employee ID): " + canceledRes.getUpdatedBy() +"\n \n" +

                "Thank you for informing us about this cancellation."
        );
        notification.setSubject("Notification: Reservation Cancellation");
        mailSender.send(notification);
        return canceledRes;
    }

    public List<FindTable> getAvailableTables (LocalDate date, String inputTimeStr){
        return reservationRepository.getAvailableTables(date, inputTimeStr);
    }
}// end of ReservationService class
