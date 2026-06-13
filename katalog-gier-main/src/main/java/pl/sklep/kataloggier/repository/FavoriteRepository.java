package pl.sklep.kataloggier.repository;

import pl.sklep.kataloggier.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUsername(String username);
}
