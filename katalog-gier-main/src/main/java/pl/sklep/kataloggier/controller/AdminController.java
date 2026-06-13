package pl.sklep.kataloggier.controller;

import pl.sklep.kataloggier.model.Game;
import pl.sklep.kataloggier.repository.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {


        private final GameRepository gameRepository;

        public AdminController(GameRepository gameRepository) {
            this.gameRepository = gameRepository;
        }

        @GetMapping("/games")
        public String adminGames(Model model) {
            model.addAttribute("games", gameRepository.findAll());
            return "admin-games";
        }

        @GetMapping("/add-game")
        public String addGamePage(Model model) {
            model.addAttribute("game", new Game());
            return "add-game";
        }

        @PostMapping("/add-game")
        public String addGame(@ModelAttribute Game game) {
            gameRepository.save(game);
            return "redirect:/admin/games";
        }

        @GetMapping("/delete/{id}")
        public String deleteGame(@PathVariable Long id) {
            gameRepository.deleteById(id);
            return "redirect:/admin/games";
        }
}
