package com.felipebs.dockadb_api.repository.municipio;

import com.felipebs.dockadb_api.entity.Municipio;
import com.felipebs.dockadb_api.repository.estado.EstadoCustomRepository;
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
public class MunicipioCustomRepository {

    @Value("${MONGO_DB_DATABASE}")
    private String mongoDb;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IMunicipioRepository municipioRepository;

    @Autowired
    private EstadoCustomRepository estadoRepository;

    public IMunicipioRepository municipioRepository() {
        return municipioRepository;
    }

    public void salvaSeNaoExiste(Municipio entity) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nome").is(entity.getNome())
                .and("siglaEstado").is(entity.getSiglaEstado())
                .and("ibge").is(entity.getIbge()));

        if (mongoTemplate.count(query, Municipio.class) == 0) {
            mongoTemplate.insert(entity);
        }
    }

    public Page<Municipio> buscarMunicipioPaginado(String nome, String siglaEstado, String ibge, Pageable pageable) {
        Query query = new Query();

        if (nome != null && !nome.isEmpty()) {
            query.addCriteria(Criteria.where("nome").regex(nome, "i"));
        }

        if (siglaEstado != null && !siglaEstado.isEmpty()) {
            query.addCriteria(Criteria.where("siglaEstado").regex(siglaEstado, "i"));
        }

        if (ibge != null && !ibge.isEmpty()) {
            query.addCriteria(Criteria.where("ibge").regex(ibge, "i"));
        }

        long total = mongoTemplate.count(query, Municipio.class);
        query.with(pageable);

        return PageableExecutionUtils.getPage(mongoTemplate.find(query, Municipio.class), pageable, () -> total);

    }

    public Page<Municipio> buscarComEstadoCompleto(String nome, String siglaEstado, Pageable pageable) {
        Query query = new Query();

        if (nome != null && !nome.isEmpty()) {
            query.addCriteria(Criteria.where("nome").regex(nome, "i"));
        }

        if (siglaEstado != null && !siglaEstado.isEmpty()) {
            query.addCriteria(Criteria.where("siglaEstado").regex(siglaEstado, "i"));
        }


        long total = mongoTemplate.count(query, Municipio.class);
        query.with(pageable);

        var paginacao = PageableExecutionUtils.getPage(mongoTemplate.find(query, Municipio.class), pageable, () -> total);

        if (paginacao.isEmpty()) return paginacao;

        var estados = estadoRepository.estadoRepository().findAll();
        paginacao.forEach(municipio -> municipio.setEstado(estados.stream().filter(estado -> estado.getSiglaEstado().equals(municipio.getSiglaEstado())).findFirst().orElse(null)));

        return paginacao;
    }

    public void deletarTodos() {
        municipioRepository.deleteAll();
        ;
    }


}
