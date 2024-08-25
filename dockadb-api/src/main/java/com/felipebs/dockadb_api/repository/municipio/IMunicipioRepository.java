package com.felipebs.dockadb_api.repository.municipio;

import com.felipebs.dockadb_api.entity.Municipio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMunicipioRepository extends MongoRepository<Municipio, String> {

}
