package rikkei_ptit.javawebservicesession14.hackathon.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rikkei_ptit.javawebservicesession14.hackathon.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query("UPDATE Book b SET b.deletedAt = CURRENT_TIMESTAMP WHERE b.id = :id")
    void softDelete(Long id);
}
