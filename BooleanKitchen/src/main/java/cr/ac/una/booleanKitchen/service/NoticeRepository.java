package cr.ac.una.booleanKitchen.service;

import java.util.LinkedList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import cr.ac.una.booleanKitchen.domain.Notice;

public interface NoticeRepository extends PagingAndSortingRepository<Notice, Integer> {

    @Query("SELECT n FROM Notice n")
    LinkedList<Notice> findAllNotices();

    Notice save(Notice notice);
    Optional<Notice> findByIDENTIFICADOR(String IDENTIFICADOR);
    Optional<Notice> findByURL(String URL);
    void deleteByID(Integer ID);
}
