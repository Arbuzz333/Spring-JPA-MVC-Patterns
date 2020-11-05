package avahidov.mappers;

import org.mapstruct.Mapper;

import java.sql.Date;
import java.text.SimpleDateFormat;



@Mapper(componentModel = "spring")
public class DateMapper {

    public String asString(Date date) {
        return date != null ? new SimpleDateFormat( "yyyy-MM-dd" ).format( date ) : null;
    }

    public Date asDate(String date) {
        return date != null ? Date.valueOf(date) : null;
    }
}