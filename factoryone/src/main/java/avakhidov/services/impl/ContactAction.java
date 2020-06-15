package avakhidov.services.impl;

import avakhidov.anotations.TimesOfDayModification;
import avakhidov.data.ContactEvent;
import avakhidov.enumes.TimesOfDay;
import avakhidov.services.Action;
import org.springframework.stereotype.Service;

import static avakhidov.utils.UtilsForString.concatWithSpaceOrEmpty;

@Service
@TimesOfDayModification(mod = TimesOfDay.DAY)
public class ContactAction  implements Action<ContactEvent> {

    @Override
    public String getAction(ContactEvent type) {
        ContactEvent typeContact = type;
        String name = typeContact.getName();
        String phoneNumber = typeContact.getPhoneNumber();
        String concat = concatWithSpaceOrEmpty(name, phoneNumber);

        return concat;
    }

    @Override
    public Class getEventType() {
        return ContactEvent.class;
    }
}
