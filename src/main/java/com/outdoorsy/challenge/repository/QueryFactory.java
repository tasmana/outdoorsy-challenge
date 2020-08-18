package com.outdoorsy.challenge.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class QueryFactory {

  private static final String DISTANCE_ALIAS = "distance";
  //TODO Create constants for all query parameters

  public String createRentalFiltersQuery(RentalFilters rentalFilters, Pageable pageable) {
    return buildRentalFiltersQuery(rentalFilters, pageable, false);
  }

  public String createRentalFiltersQueryCountQuery(RentalFilters rentalFilters, Pageable pageable) {
    return buildRentalFiltersQuery(rentalFilters, pageable, true);
  }

  private String buildRentalFiltersQuery(RentalFilters rentalFilters, Pageable pageable, boolean countQuery) {
    StringBuilder query = new StringBuilder();
    query.append("SELECT ");
    if (countQuery) {
      query.append("count(*) ");
    } else {
      query.append("* ");
    }
    query.append(createFromClause(rentalFilters))
        .append(createWhereClause(rentalFilters));
    if (!countQuery) {
      query.append(" LIMIT ")
          .append(pageable.getPageSize())
          .append(" OFFSET ")
          .append(pageable.getOffset());
    }
    return query.toString();
  }

  private String createFromClause(RentalFilters rentalFilters) {
    StringBuilder fromBuilder = new StringBuilder();
    fromBuilder.append(" FROM ");
    if (rentalFilters.getNear() != null) {
      fromBuilder.append("*");
    } else {
      fromBuilder
          .append("(")
          .append(" SELECT *, (3959 * acos(cos(radians(:lat)) * cos(radians(lat)) * cos(radians(lng) - radians(:lng)) + sin(radians(:lat)) * sin( radians(lat)))) AS ")
          .append(DISTANCE_ALIAS)
          .append(" FROM rentals")
          .append(")");
    }
    return fromBuilder.toString();
  }

  private String createWhereClause(RentalFilters rentalFilters) {
    StringBuilder whereBuilder = new StringBuilder();
    whereBuilder
        .append(" WHERE ");
    if (rentalFilters.getNear() != null) {
      whereBuilder.append(DISTANCE_ALIAS)
          .append(" < :distance");
    }
    //TODO add all filters
    return whereBuilder.toString();
  }
}
