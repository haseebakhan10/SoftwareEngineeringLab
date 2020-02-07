package AddressBookPackage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "AddressBook", path = "addressbook")
public interface AddressBookRepository extends CrudRepository<AddressBook, Integer> {
    AddressBook findById(int id);
}
