package com.felipebs.dockadb_api.repository.estado;

import com.felipebs.dockadb_api.entity.Estado;
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
public class EstadoCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${MONGO_DB_DATABASE}")
    private String mongoDb;

    @Autowired
    private IEstadoRepository estadoRepository;
    public IEstadoRepository estadoRepository() {
        return estadoRepository;
    }

    public void salvaSeNaoExiste(Estado entity) {
        Query query = new Query();

        query.addCriteria(Criteria.where("nome").is(entity.getNome())
                .and("siglaEstado").is(entity.getSiglaEstado()));

        if (mongoTemplate.count(query, Estado.class) == 0) {
            log.debug("Salvar estado: ".concat(entity.getNome()));
            mongoTemplate.insert(entity);
        }
    }

    public Page<Estado> buscarEstadoPaginado(String nome, String siglaEstado, String siglaRegiao, Pageable pageable) {
        Query query = new Query();

        if (nome != null && !nome.isEmpty()) {
            query.addCriteria(Criteria.where("nome").regex(nome, "i"));
        }

        if (siglaEstado != null && !siglaEstado.isEmpty()) {
            query.addCriteria(Criteria.where("siglaEstado").regex(siglaEstado, "i"));
        }

        if (siglaRegiao != null && !siglaRegiao.isEmpty()) {
            query.addCriteria(Criteria.where("siglaRegiao").regex(siglaRegiao, "i"));
        }

        long total = mongoTemplate.count(query, Estado.class);
        query.with(pageable);

        return PageableExecutionUtils.getPage(mongoTemplate.find(query, Estado.class), pageable, () -> total);

    }
}
