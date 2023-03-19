package com.restaurant.abc.api;


import com.restaurant.abc.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private AuthenticationService authenticationService;
    //constructor injection
    public AdminController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";

    }

    @GetMapping("/admin/reservation")
    public String adminReservation(){
        return "adminReservation";

    }

    @GetMapping("/admin/manageProduct")
    public String manageProduct(){
        return "manageProduct";

    }
    @GetMapping("/admin/orders")
    public String adminOrders(){
        return "adminOrders";

    }
    @GetMapping("/admin/massages")
    public String adminMassages(){
        return "adminMassages";

    }

    //---------------------------------------------------
    @GetMapping("/index")
    public String indexPage(){
        return "index";
    }
    @GetMapping("/menu-breakfast")
    public String menuBreakfastPage(){
        return "menu-breakfast";
    }
    @GetMapping("/about")
    public String aboutPage(){
        return "about";
    }
    @GetMapping("/contact")
    public String contactPage(){
        return "contact";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/reservation")
    public String reservationPage(){
        return "reservation";
    }
    @GetMapping("/reservation-form")
    public String reservationFormPage(){
        return "reservation-form";
    }

    @PostMapping("/login")
    public String goToAdminHome(@RequestParam String user, @RequestParam String password, ModelMap modelMap){
        //Authentication: for admin, user: admin, password: admin
        if(authenticationService.authenticate(user, password)) {
            modelMap.put("user", user);
//            return "adminHome";
            return "adminReservation";
        }
        modelMap.put("user", user);
        modelMap.put("errorMessage", "Invalid Credentials, Please try again!!");
        return "login";
    }
}
