package AddressBookPackage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "BuddyInfo", path = "buddyinfo")
public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Integer> {
    void deleteById(int id);

    BuddyInfo findById(int id);
}
