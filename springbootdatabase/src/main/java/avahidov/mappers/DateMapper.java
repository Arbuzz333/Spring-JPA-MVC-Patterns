package avahidov.mappers;

import org.mapstruct.Mapper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;



@Mapper(componentModel = "spring")
public class DateMapper {

    public String asString(Date date) {
        return date != null ? new SimpleDateFormat( "dd-MM-yyyy" ).format( date ) : null;
    }

    public Date asDate(String date) {
        Date sqlDate = null;
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date parsed;
            try {
                parsed = format.parse(date);
                sqlDate = new Date(parsed.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return sqlDate;
    }
}