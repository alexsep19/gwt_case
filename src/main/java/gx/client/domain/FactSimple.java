package gx.client.domain;

import gx.server.domain.CategoryLoadResultBean;
import gx.server.domain.Dao;
import gx.server.domain.DaoServ;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

public interface FactSimple extends RequestFactory{
	rcSimple creRcSimple();
	
	@Service(value = Dao.class, locator = DaoServ.class)
    public interface rcSimple extends RequestContext{
		Request<List<String>> getUserInfo();

		Request<Void> merg(CategoryPrx rec);
		Request<Void> remo(CategoryPrx rec);
		
		@ProxyFor(CategoryLoadResultBean.class)
		public interface DdLoadResultProxy extends ValueProxy, ListLoadResult<CategoryPrx> {
		    @Override
		    public List<CategoryPrx> getData();
		  }
		Request<DdLoadResultProxy> getListCategory(List<? extends SortInfo> sortInfo);

	}
}
