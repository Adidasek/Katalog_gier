package pl.sklep.kataloggier.controller;

import org.springframework.web.bind.annotation.PathVariable;
import pl.sklep.kataloggier.model.Game;
import pl.sklep.kataloggier.repository.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GameController {
    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/games";
    }

    @GetMapping("/games")
    public String games(Model model) {
        model.addAttribute("games", gameRepository.findAll());
        return "games";
    }

    @GetMapping("/game/{id}")
    public String gameDetails(@PathVariable Long id, Model model) {

        Game game = gameRepository.findById(id).orElse(null);

        if (game == null) {
            return "redirect:/games";
        }

        model.addAttribute("game", game);

        return "game-details";
    }
}
