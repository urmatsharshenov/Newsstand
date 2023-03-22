package kg.sanack.newsstand.service.impl;

import kg.sanack.newsstand.entity.Newsstand;
import kg.sanack.newsstand.repository.NewsstandRepository;
import kg.sanack.newsstand.service.NewsstandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsstandServiceImpl implements NewsstandService {
    private final NewsstandRepository repository;

    public NewsstandServiceImpl(NewsstandRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Newsstand> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);

        return repository.findAll(pageable);
    }

    @Override
    public List<Newsstand> listAll() {
        return repository.findAll();
    }
}
