package gallantmedia.services.contact;

import gallantmedia.models.Contact;

import java.util.List;

public interface ContactService
{
    public List<Contact> findAll();

    public String findName();

    public void save(Contact contact);

}
