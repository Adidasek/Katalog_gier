package pl.sklep.kataloggier.repository;

import pl.sklep.kataloggier.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameRepository extends JpaRepository<Game, Long> {
}
