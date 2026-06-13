package pl.sklep.kataloggier.controller;

import pl.sklep.kataloggier.model.Favorite;
import pl.sklep.kataloggier.model.Game;
import pl.sklep.kataloggier.repository.FavoriteRepository;
import pl.sklep.kataloggier.repository.GameRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;
    private final GameRepository gameRepository;

    public FavoriteController(FavoriteRepository favoriteRepository,
                              GameRepository gameRepository) {
        this.favoriteRepository = favoriteRepository;
        this.gameRepository = gameRepository;
    }

    @GetMapping("/favorite/add/{id}")
    public String addFavorite(@PathVariable Long id, Authentication auth) {

        if (auth == null) {
            return "redirect:/login";
        }

        Favorite favorite = new Favorite();
        favorite.setUsername(auth.getName());
        favorite.setGameId(id);

        favoriteRepository.save(favorite);

        return "redirect:/favorites";
    }

    @GetMapping("/favorites")
    public String favorites(Authentication auth, Model model) {

        if (auth == null) {
            return "redirect:/login";
        }

        List<Favorite> favorites = favoriteRepository.findByUsername(auth.getName());

        List<Game> games = new ArrayList<>();

        for (Favorite f : favorites) {
            gameRepository.findById(f.getGameId()).ifPresent(games::add);
        }

        model.addAttribute("games", games);

        return "favorites";
    }

    @GetMapping("/favorite/delete/{id}")
    public String deleteFavorite(@PathVariable Long id, Authentication auth) {

        if (auth == null) {
            return "redirect:/login";
        }

        List<Favorite> favorites = favoriteRepository.findByUsername(auth.getName());

        for (Favorite f : favorites) {
            if (f.getGameId().equals(id)) {
                favoriteRepository.delete(f);
                break;
            }
        }

        return "redirect:/favorites";
    }
}