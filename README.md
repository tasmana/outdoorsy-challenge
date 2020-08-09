**Outdoorsy challenge**

Implemented endpoints:
- `/campervans`
- `/campervans?price.min=9000&price.max=75000`
- `/campervans?ids=2000,51155,54318`
- `/campervans?near=33.64,-117.93` // within 100 miles
- `/campervans/<CAMPER_VAN_ID>`

All endpoints are paginated(except GET by id) and the pageable parameters are as follows:
* page - specifies the wanted page(zero based)
* size - specified the wanted page size(default is 20)

All endpoints (except GET by id) can be sorted on using the sort parameter.

`example: ?sort=pricePerDay`
