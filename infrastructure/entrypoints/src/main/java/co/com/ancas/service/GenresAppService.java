package co.com.ancas.service;

import co.com.ancas.uses_cases.genres.FindAllGenresAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenresAppService {
    private final FindAllGenresAdapter findAllGenresAdapter;

    @Transactional(value = "libraryTransactionManager",readOnly = true,rollbackFor = Exception.class)
    public List<String> findAll() {
        return findAllGenresAdapter.execute();
    }
}
