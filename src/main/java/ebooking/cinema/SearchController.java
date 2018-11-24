package ebooking.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {

    @GetMapping("/search")
    public ModelAndView getSearch(Model model) {

        SearchResult temp = new SearchResult();
        List<SearchResult> results = temp.searchMovies();

        model.addAttribute("searchQuery", results);
        //System.out.println("Result 0 Movie title is: " + results[0].movieTitle);

        ModelAndView mav = new ModelAndView("search");
        mav.addObject("searchQuery", results);

        return mav;
    }


    @PostMapping("/search")
    public ModelAndView search(@ModelAttribute("search") SearchQuery searchquery) {

        ModelAndView mav = new ModelAndView("search");

        return mav;
    }
}
