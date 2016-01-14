package gx.client;

import gx.client.domain.FactSimple;

import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class NavPan extends ContentPanel{
	FactSimple fct;
	PanTable panTable = null;
    TextButton bTable = null, bLog = null;
    FlowLayoutContainer contPan = null;
    private String role = "";
    
    public void setActive(String Role){
	role = Role;
//	bRss.setEnabled(true);
//	bLog.setEnabled(role.indexOf("A") >= 0);
//	if (panRss == null) panRss = new PanRss(fct, role);
//	ShowPan(panRss);
    }
    
    public NavPan(final FlowLayoutContainer blc, final FactSimple Fct){
	    fct = Fct;
	    contPan = blc;
	    setHeadingText("Навигация");
	    getHeader().addStyleName("txt_center");
        VBoxLayoutContainer bc = new VBoxLayoutContainer();
        bc.setPadding(new Padding(1));
        bc.setVBoxLayoutAlign(VBoxLayoutAlign.STRETCH);
        bTable = new TextButton("Table", new SelectHandler(){
	       @Override
	       public void onSelect(SelectEvent event) {
	    	bTable.setEnabled(false);
		    if (panTable == null) panTable = new PanTable(fct, role);
		    ShowPan(panTable);
	        bTable.setEnabled(true);
	        }});

        bc.add( bTable, new BoxLayoutData(new Margins(0, 0, 1, 0)));
        
//        bLog = new TextButton("Журнал", new SelectHandler(){
//	       @Override
//	       public void onSelect(SelectEvent event) {
//		   if (panLog == null) panLog = new PanLog(fct);
//		   ShowPan(panLog);
//	       }}); 
//        bLog.setEnabled(false);
//        bc.add( bLog, new BoxLayoutData(new Margins(0)));
        
        add(bc);
    }
    
    private void ShowPan(ContentPanel pan){
    	contPan.clear();
    	contPan.add(pan);
    }
}
