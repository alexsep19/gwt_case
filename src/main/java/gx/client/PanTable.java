package gx.client;

import java.util.List;

import gx.client.domain.CategoryPrx;
import gx.client.domain.FactSimple.rcSimple;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import gx.client.domain.FactSimple;

import com.google.gwt.cell.client.Cell.Context;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.RequestFactoryProxy;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

public class PanTable extends ContentPanel{
    private static final int PAN_TAB_WIDTH = 1000;
    private static final int PAN_TAB_HEIGHT = 800;
//=================== Category ======================    
    private static final int PAN_LIST_WIDTH = 420;
    private static final int PAN_LIST_HEIGHT = 410;
    interface CategoryProperties extends PropertyAccess<CategoryPrx> {
	    ModelKeyProvider<CategoryPrx> id();
	    @Path("id")
	    ValueProvider<CategoryPrx, Integer> idVal();
	    ValueProvider<CategoryPrx, String> name();
	  }
    private final CategoryProperties propCategory = GWT.create(CategoryProperties.class);
//===================================================      
    
    public PanTable(final FactSimple Fct, String Role) {
    	setCollapsible(false);
    	getHeader().addStyleName("txt_center");
    	setHeadingText("Таблицы");
    	addStyleName("margin-10");
    	setPixelSize(PAN_TAB_WIDTH, PAN_TAB_HEIGHT);
    	HtmlLayoutContainer contTools = new HtmlLayoutContainer(getImportMarkup());
    	
    	PanList<CategoryPrx> panList =  new PanList<CategoryPrx>(PAN_LIST_WIDTH, PAN_LIST_HEIGHT, "Список"){
    	    ColumnConfig<CategoryPrx, Integer> ccIdVal;
    	    ColumnConfig<CategoryPrx, String> ccName;
    	    TextField txName = new TextField();
    	    rcSimple reqIns;
    	    @Override
            public void fill(){
    	       ccIdVal = new ColumnConfig<CategoryPrx, Integer>( propCategory.idVal(), 20, "id");
    	       ccIdVal.setCell(new AbstractCell<Integer>() {
    			      @Override
    			      public void render(Context context, Integer value, SafeHtmlBuilder sb) {
    				      sb.appendHtmlConstant(value.toString());
    			      } });
    	       ccName = new ColumnConfig<CategoryPrx, String>(propCategory.name(), 40, "Name");
    		   getCcL().add(ccIdVal);
    	       getCcL().add(ccName);
    	       setStT(new ListStore<CategoryPrx>(propCategory.id()));
    	       setRfpT(new RequestFactoryProxy<ListLoadConfig, ListLoadResult<CategoryPrx>>() {
				@Override
				public void load(ListLoadConfig loadConfig,	Receiver<? super ListLoadResult<CategoryPrx>> receiver) {
    	 		  rcSimple req = Fct.creRcSimple();
	   	 		  List<SortInfo> sortInfo = createRequestSortInfo(req, loadConfig.getSortInfo());
	     		  req.getListCategory(sortInfo).to(receiver).fire();
					
				}});
    	       
    	       initValues(true, true, true);
    	       getEditing().addEditor(ccName, txName);
    	    }
		    @Override
		    public void mergItem(CategoryPrx item){
		       rcSimple req = null;
		       if (isIns) {req = reqIns; isIns = false;}
		       else req = Fct.creRcSimple();
		       CategoryPrx editItem = req.edit(item);
		       editItem.setName(txName.getText());
		       req.merg(editItem).fire(mergReceiver);
		    }
		    @Override
		    public void insItem(){
		     	reqIns = Fct.creRcSimple();
		     	CategoryPrx o = reqIns.create(CategoryPrx.class);
		     	o.setName("");
		        stT.add(0, o);
		    }
		    @Override
		    public String getItemName(CategoryPrx item){
			return String.valueOf(item.getId());
		    }
		    @Override
		    public void delItem(CategoryPrx item, Receiver<Void> R){
		    	Fct.creRcSimple().remo(item).fire(R);
		    }
		    @Override
		    protected void beforEdit(){
		    	txName.getCell().getInputElement(txName.getElement()).setMaxLength(CategoryPrx.LEN_name);
		    }
    	};
    	panList.fill();
    	//=======================================================================	
    	contTools.add( panList, new HtmlData(".list"));
   	
    	setWidget(contTools);
    }
    
    private native String getImportMarkup() /*-{
    return [ '<table cellpadding=0 cellspacing=4 cols="2">',
        '<tr><td class=list valign="top"></td><td class=page valign="top"></td></tr>',
        '</table>'
    ].join("");
  }-*/;
}
