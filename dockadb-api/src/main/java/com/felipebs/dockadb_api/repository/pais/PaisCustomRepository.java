package com.felipebs.dockadb_api.repository.pais;

import com.felipebs.dockadb_api.entity.Pais;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PaisCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${MONGO_DB_DATABASE}")
    private String mongoDb;

    @Autowired
    private IPaisRepository countryRepository;

    public IPaisRepository countryRepository() {
        return countryRepository;
    }

    public void salvaSeNaoExiste(Pais entity) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nome").is(entity.getNome())
                .and("m49CodeLevel1").is(entity.getM49CodeLevel1())
                .and("m49CodeLevel2").is(entity.getM49CodeLevel2()));

        if (mongoTemplate.count(query, Pais.class) == 0) {
            log.debug("Salvar pais: ".concat(entity.getNome()));
            mongoTemplate.insert(entity);
        }
    }

    public Page<Pais> buscarPaisPaginado(String nome, Pageable pageable) {
        Query query = new Query();

        if (nome != null && !nome.isEmpty()) {
            query.addCriteria(Criteria.where("nome").regex(nome, "i"));
        }

        long total = mongoTemplate.count(query, Pais.class);
        query.with(pageable);

        return PageableExecutionUtils.getPage(mongoTemplate.find(query, Pais.class), pageable, () -> total);

    }
}
