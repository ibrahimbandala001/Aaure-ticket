package com.org.ticketgenerate.repository;

import com.org.ticketgenerate.model.TokenModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends MongoRepository<TokenModel,Integer> {
}
