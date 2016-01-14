package gx.client.domain;

import jpaSimple.Category;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Category.class, locator = gx.server.domain.CategoryLoc.class)
public interface CategoryPrx extends EntityProxy{
	public Integer getId();
	public String getName();
	public void setName(String name);
	
	public static final int LEN_name = 30;
	public Integer getVersion();
}
