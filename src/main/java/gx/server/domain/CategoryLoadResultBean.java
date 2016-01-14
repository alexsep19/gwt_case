package gx.server.domain;

import java.util.List;

import jpaSimple.Category;

import com.sencha.gxt.data.shared.loader.ListLoadResultBean;

public class CategoryLoadResultBean extends ListLoadResultBean<Category>{
	private static final long serialVersionUID = -3244604033151786200L;
	public CategoryLoadResultBean() {
		// TODO Auto-generated constructor stub
	}
	public CategoryLoadResultBean(List<Category> list) {
	      super(list);
	    }
    public Integer getVersion() { return 1;}
}
