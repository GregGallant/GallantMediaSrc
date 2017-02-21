package gallantmedia;

import java.util.List;

public interface ContactService
{
    public List<Contact> findAll();

    public String findName();

    public void saveContact(Contact contact);
}
