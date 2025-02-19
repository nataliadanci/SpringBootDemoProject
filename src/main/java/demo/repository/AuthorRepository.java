package demo.repository;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import demo.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
