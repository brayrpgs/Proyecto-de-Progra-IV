package cr.ac.una.booleanKitchen.service;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import cr.ac.una.booleanKitchen.domain.Notice;

public interface NoticeRepository extends PagingAndSortingRepository<Notice, Integer> {
    Notice save(Notice notice);
    Optional<Notice> findByIDENTIFICADOR(String IDENTIFICADOR);
    Optional<Notice> findByURL(String URL);
    void deleteByID(Integer ID);
}
