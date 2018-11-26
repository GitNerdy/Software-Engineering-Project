package ebooking.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {

    @GetMapping("/search")
    public ModelAndView getSearch(@RequestParam(name = "search") String title) {

        SearchResult temp = new SearchResult();
        List<SearchResult> results = temp.searchMovies(title);

        ModelAndView mav = new ModelAndView("search");
        mav.addObject("searchQuery", results);

        return mav;
    }
}
