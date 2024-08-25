package com.felipebs.dockadb_api.repository.pais;

import com.felipebs.dockadb_api.entity.Pais;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPaisRepository extends MongoRepository<Pais, String> {

}
