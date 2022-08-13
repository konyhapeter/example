package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	@Transactional
	void deleteProductByName(String name);

	@Transactional
	ProductEntity getProductByName(String name);

}
