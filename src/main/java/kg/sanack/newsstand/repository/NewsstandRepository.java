package kg.sanack.newsstand.repository;

import kg.sanack.newsstand.entity.Newsstand;
import kg.sanack.newsstand.entity.Product;
import kg.sanack.newsstand.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsstandRepository extends JpaRepository<Newsstand, Long> {
}
