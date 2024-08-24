package com.felipebs.dockadb_api.repository.continenteRy;

import com.felipebs.dockadb_api.entity.Continente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
public class ContinenteCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${MONGO_DB_DATABASE}")
    private String mongoDb;

    @Autowired
    private IContinenteRepository continenteRepository;

    public IContinenteRepository continenteRepository() {
        return continenteRepository;
    }

    public Page<Continente> findContinente(String nome, Pageable pageable) {
        Query query = new Query();

        if (nome != null && !nome.isEmpty()) {
            query.addCriteria(Criteria.where("nome").regex(nome, "i"));
        }

        long total = mongoTemplate.count(query, Continente.class);
        query.with(pageable);

        return PageableExecutionUtils.getPage(mongoTemplate.find(query, Continente.class), pageable, () -> total);

    }
}
