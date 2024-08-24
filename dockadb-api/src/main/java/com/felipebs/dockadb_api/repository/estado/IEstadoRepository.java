package com.felipebs.dockadb_api.repository.estado;

import com.felipebs.dockadb_api.entity.Estado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IEstadoRepository extends MongoRepository<Estado, String> {

}
