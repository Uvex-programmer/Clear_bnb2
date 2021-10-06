@FilterDefs({
        @FilterDef(name = "bedroomFilter", parameters = @ParamDef(name = "minBeds", type = "int")),
        @FilterDef(name = "bathroomFilter", parameters = @ParamDef(name = "minBath", type = "int")),
        @FilterDef(name = "dateFilter", parameters = {
                @ParamDef(name = "startDate", type = "java.sql.Timestamp"),
                @ParamDef(name = "endDate", type = "java.sql.Timestamp")
        })
        ,
        @FilterDef(name = "guestFilter", parameters = @ParamDef(name = "minGuests", type = "int")),
        @FilterDef(name = "priceFilter", parameters = @ParamDef(name = "maxPrice", type = "int")),
        @FilterDef(name = "cityFilter", parameters = {
                @ParamDef(name = "city", type = "string"),
        })
})


package models;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;