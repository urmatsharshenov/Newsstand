package kg.sanack.newsstand.service;

import kg.sanack.newsstand.entity.Newsstand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsstandService{
    Page<Newsstand> findAll(int page, int size);
    List<Newsstand> listAll();
}
