package gx.server.domain;

import com.google.web.bindery.requestfactory.shared.Locator;

import jpaSimple.Category;

public class CategoryLoc extends Locator<Category, Integer> {

	@Override
	public Category create(Class<? extends Category> clazz) {
		// TODO Auto-generated method stub
		return new Category();
	}

	@Override
	public Category find(Class<? extends Category> clazz, Integer id) {
		// TODO Auto-generated method stub
		return (Category) Dao.findObject(clazz, id);
//		return Dao.findCategory(id);
	}

	@Override
	public Class<Category> getDomainType() {
		// TODO Auto-generated method stub
		return Category.class;
	}

	@Override
	public Integer getId(Category domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getId();
	}

	@Override
	public Class<Integer> getIdType() {
		// TODO Auto-generated method stub
		return Integer.class;
	}

	@Override
	public Object getVersion(Category domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion();
	}

}
