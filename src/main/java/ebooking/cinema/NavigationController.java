package ebooking.cinema;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("user")
public class NavigationController {

    User user = new User();

    String showingID;
    String movieName;
    int promoAmount = 0;
    boolean promoUsed = false;
    float totalCost = 0;
    int totalTickets = 0;

    List<Ticket> ticketList = new ArrayList<Ticket>();
    List<SeatAtShowing> seatShowingList = new ArrayList<SeatAtShowing>();
    SeatList list;
    Booking userBooking = new Booking();


    @GetMapping("/index")
    public ModelAndView goHome(Model model) {


        /*if (user.loggedIn != true) {
            model.addAttribute("user", new User());
        }*/

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", user);


        return mav;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", user);
        model.addAttribute("register", new User());
        model.addAttribute("address", new Address());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(Model model, @ModelAttribute User register) {
        model.addAttribute("user", user);
        register.register();
        user = register;
        System.out.println(user.email);
        Email.sendMail(user.email);
        user.loggedIn = true;
        return "index";
    }

    @GetMapping("/login")
    public String userForm(Model model) {
        model.addAttribute("login", new User());
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String userSubmit(@ModelAttribute("login") User login, Model model) {

        if (login.login()) {
            System.out.println("User status: " + user.loggedIn);
            user = login;
            model.addAttribute("user", user);
            return "redirect:/index";
        }

        else {
            model.addAttribute("user", user);
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String userLogout(Model model) {
        user = new User();
        model.addAttribute("user", user);
        System.out.println("User status: " + user.loggedIn);
        return "index";
    }

    @GetMapping("/admin")
    public ModelAndView adminPage(Model model) {

        ModelAndView mav = new ModelAndView("adminMain");
        mav.addObject("user", user);

        return mav;
    }

    @GetMapping("/editMovieList")
    public ModelAndView movieList(Model model) {

        SearchResult temp = new SearchResult();
        List<SearchResult> results = temp.getMovieList();

        ModelAndView mav = new ModelAndView("editMovieList");
        mav.addObject("movieList", results);
        mav.addObject("user", user);

        return mav;
    }

    @GetMapping("/editShowTimes")
    public ModelAndView showTimeList(Model model) {

        model.addAttribute("newTime", new ShowTime());

        ShowTime temp = new ShowTime();
        List<ShowTime> results = temp.getShowTimes();

        ModelAndView mav = new ModelAndView("editShowTimes");
        mav.addObject("showTimeList", results);
        mav.addObject("user", user);


        return mav;
    }

    @GetMapping("/editPromotions")
    public ModelAndView showPromoList(Model model) {

        model.addAttribute("newPromo", new Promotion());

        Promotion temp = new Promotion();
        List<Promotion> results = temp.getPromos();

        ModelAndView mav = new ModelAndView("editPromotions");
        mav.addObject("promoList", results);
        mav.addObject("user", user);


        return mav;
    }

    @GetMapping("/editUsers")
    public ModelAndView showUserList() {

        User temp = new User();
        List<User> results = temp.getUsers();

        ModelAndView mav = new ModelAndView("editUsers");
        mav.addObject("userList", results);
        mav.addObject("user", user);


        return mav;
    }

    @GetMapping("/editHalls")
    public ModelAndView showHallList(Model model) {

        model.addAttribute("newShowing", new MovieShowing());

        Hall tempHall = new Hall();
        MovieShowing tempShowing = new MovieShowing();
        SearchResult tempMovie = new SearchResult();
        ShowTime tempShowTime = new ShowTime();

        List<Hall> hallResults = tempHall.getHalls();
        List<MovieShowing> showingResults = tempShowing.getShowings();
        List<SearchResult> movieResults = tempMovie.getMovieList();
        List<ShowTime>  showTimeResults = tempShowTime.getShowTimes();

        ModelAndView mav = new ModelAndView("editHallList");
        mav.addObject("hallList", hallResults);
        mav.addObject("showingList", showingResults);
        mav.addObject("movieList", movieResults);
        mav.addObject("showTimeList", showTimeResults);
        mav.addObject("user", user);


        return mav;
    }

    @GetMapping("/getMovie/{id}")
    public ModelAndView movieDetail(@PathVariable String id) {

        Movie result = new Movie();
        MovieShowing tempShowing = new MovieShowing();

        List<MovieShowing> showings = tempShowing.getShowingsMovie(id);

        result = result.getMovie(id);
        movieName = result.title;

        ModelAndView mav = new ModelAndView("movieDetail");
        mav.addObject("movie", result);
        mav.addObject("showingList", showings);
        mav.addObject("user", user);



        return mav;
    }

    @GetMapping("/getEditMovie/{id}")
    public ModelAndView editMovie(@PathVariable String id, Model model) {

        Movie newMovie = new Movie();

        Movie result = new Movie();
        result = result.getMovie(id);

        newMovie.title = result.title;
        newMovie.synopsis = result.synopsis;
        newMovie.director = result.director;
        newMovie.producer = result.producer;
        newMovie.cast = result.cast;
        newMovie.image = result.image;
        newMovie.genre = result.genre;

        model.addAttribute("newMovie", newMovie);


        ModelAndView mav = new ModelAndView("editMovie");
        mav.addObject("movie", result);
        mav.addObject("user", user);


        return mav;
    }

    @GetMapping("/getAddMovie")
    public String addMovie(Model model) {
        model.addAttribute("newMovie", new Movie());
        model.addAttribute("user", user);

        return "addMovie";
    }

    @GetMapping("/getTicketsPage/{id}")
    public ModelAndView getTicketsPage(@PathVariable String id, Model model){
        showingID = id;
        totalTickets = 0;
        model.addAttribute("ticket", new Ticket());

        List<AgeGroup> restults = AgeGroup.getAgeGroups();

        ModelAndView mav = new ModelAndView("booking");
        mav.addObject("showingID", showingID);
        mav.addObject("ageGroupList", restults);
        mav.addObject("user", user);

        promoAmount = 0;
        promoUsed = false;

        return mav;
    }

    @GetMapping("/search")
    public ModelAndView getSearch(@RequestParam(name = "search") String title) {

        SearchResult temp = new SearchResult();
        List<SearchResult> results = temp.searchMovies(title);

        ModelAndView mav = new ModelAndView("search");
        mav.addObject("searchQuery", results);
        mav.addObject("user", user);

        return mav;
    }

    @PostMapping("/getSeatsPage/{id}")
    public ModelAndView getSeatsPage(@PathVariable String id, @ModelAttribute Ticket ticket, Model model){

        Ticket temp = new Ticket();
        showingID = id;
        ticketList = new ArrayList<Ticket>();
        int j = 1;

        list = new SeatList();

        Seat tempSeat = new Seat();
        List<Seat> seats = new ArrayList<Seat>();

        for(int i = 0; i < ticket.numSeniorTicket; i++) {
            temp = new Ticket();
            temp.setAgeID(3);
            temp.setTicketID(j);
            temp.setCost(AgeGroup.getCost("3"));
            j++;
            temp.setAgeGroup("Senior");
            temp.setShowingID(showingID);
            temp.setSeatID(1);
            temp.setBookingID(3);
            ticketList.add(temp);
        }

        for(int i = 0; i < (ticket.numAdultTicket); i++) {
            temp = new Ticket();
            temp.setAgeID(2);
            temp.setTicketID(j);
            temp.setCost(AgeGroup.getCost("2"));
            j++;
            temp.setAgeGroup("Adult");
            temp.setShowingID(showingID);
            temp.setSeatID(1);
            temp.setBookingID(3);
            ticketList.add(temp);
        }

        for(int  i =0; i < ticket.numChildTicket; i++) {
            temp = new Ticket();
            temp.setAgeID(1);
            temp.setAgeGroup("Child");
            temp.setCost(AgeGroup.getCost("1"));
            temp.setShowingID(showingID);
            temp.setSeatID(1);
            temp.setTicketID(j);
            j++;
            temp.setBookingID(3);
            ticketList.add(temp);
        }

        MovieShowing mov = new MovieShowing();
        List<SeatAtShowing> seatAtShowingsList = mov.getSeatsAtShowing(showingID);

        for (SeatAtShowing seat: seatAtShowingsList) {
            tempSeat = new Seat();
            tempSeat.seatID = seat.seatID;
            tempSeat.name = seat.seatName;
            tempSeat.status = seat.status;
            list.addSeat(tempSeat);
        }

        ModelAndView mav = new ModelAndView("seating");
        mav.addObject("showingID", showingID);
        mav.addObject("ticketList", ticketList);
        mav.addObject("seatList", seatAtShowingsList);
        mav.addObject("seatSpots", list);
        mav.addObject("user", user);


        return mav;
    }

    @RequestMapping(value = "/getBookingConfirmation/{id}", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView getBookingConfirmation(@PathVariable String id, Model model, @ModelAttribute SeatList seatList) {

        totalCost = 0;
        String costString = "";
        Booking bookingCheckout = new Booking();
        List<Seat> seats = new ArrayList<Seat>();

        for (Seat seat: seatList.seatList) {
            seats.add(seat);
        }

        model.addAttribute("id", showingID);

        totalCost = Booking.getTotalCost(ticketList, promoAmount);
        costString = String.format("%.2f", totalCost);



        ModelAndView mav = new ModelAndView("checkout");
        mav.addObject("ticketList", ticketList);
        mav.addObject("booking", bookingCheckout);
        mav.addObject("movieTitle", movieName);
        mav.addObject("totalCost", costString);
        mav.addObject("promoUsed", promoUsed);
        mav.addObject("promo", new Promotion());
        mav.addObject("showingID", showingID);
        mav.addObject("user", user);


        return mav;
    }

    @PostMapping("/applyPromo/{id}")
    public String applyPromo(@ModelAttribute Promotion promo, @PathVariable String id, Model model){

        promoAmount = Promotion.checkPromo(promo.code);
        model.addAttribute("user", user);

        if (promoAmount > 0) {
            promoUsed = true;
        }

        return "redirect:/getBookingConfirmation/{id}";
    }

    @GetMapping("/deleteTicket/{id}")
    public String deleteTicket(@PathVariable int id, Model model) {

        model.addAttribute("user", user);

        for (Ticket ticket : ticketList) {
            System.out.println("Ticket id: " + ticket.ticketID);
            if (ticket.ticketID == id) {
                System.out.println("Here!");
                ticketList.remove(ticket);
                System.out.println("Removed ticket +" + ticket.ticketID);
                break;
            }
        }



        return "redirect:/getBookingConfirmation/{id}";
    }

    @GetMapping("/submitBooking/{id}")
    public String submitBooking(@PathVariable String id, Model model) {

        model.addAttribute("user", user);

        userBooking.setTotalCost(totalCost);
        userBooking.setNumTickets(totalTickets);
        userBooking.setPromoID(0);
        userBooking.setPaymentID(1);
        userBooking.setUserID(1);

        SeatAtShowing.updateSeat(seatShowingList);
        Ticket.makeTicket(ticketList);

        userBooking.makeBooking();

        System.out.println("Successful!");

        return "coConf";
    }

}
