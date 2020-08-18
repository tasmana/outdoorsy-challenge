package com.outdoorsy.challenge.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.outdoorsy.challenge.domain.Rental;

@Repository
public class NativeSqlRentalRepository {

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private QueryFactory queryFactory;

  public Page<Rental> findByFilters(RentalFilters rentalFilters, Pageable pageable) {
    Query query = em.createNativeQuery(queryFactory.createRentalFiltersQuery(rentalFilters, pageable));
    Query countQuery = em.createNativeQuery(queryFactory.createRentalFiltersQueryCountQuery(rentalFilters, pageable));

     // TODO Use the parameters from the QueryBuilder to set them for both queries

    return new PageImpl<>(query.getResultList(), pageable, countQuery.getFirstResult());
  }

}
