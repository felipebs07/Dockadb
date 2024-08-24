package com.felipebs.dockadb_api.repository.continenteRy;

import com.felipebs.dockadb_api.entity.Continente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IContinenteRepository extends MongoRepository<Continente, Long> {

}
