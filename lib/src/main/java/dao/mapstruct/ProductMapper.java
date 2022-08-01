package dao.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import dao.ProductEntity;
import productservice.model.Product;

@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	@Mapping(target = "name", source = "name")
	Product entityToModel(ProductEntity entity);

	@Mapping(target = "id", ignore = true)
	ProductEntity modelToEntity(Product entity);

}
