/**
 * 
 */
package com.idega.block.basket.presentation;

import java.text.MessageFormat;

import com.idega.block.basket.business.BasketBusiness;
import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.business.IBORuntimeException;
import com.idega.core.builder.data.ICPage;
import com.idega.idegaweb.IWUserContext;
import com.idega.presentation.Block;
import com.idega.presentation.IWContext;
import com.idega.presentation.Layer;
import com.idega.presentation.Span;
import com.idega.presentation.text.LinkContainer;
import com.idega.presentation.text.Text;

/**
 * Last modified: $Date: 2008/05/11 14:13:53 $ by $Author: laddi $
 * 
 * @author <a href="mailto:laddi@idega.com">laddi</a>
 * @version $Revision: 1.1 $
 */
public class BasketLink extends Block {

	private ICPage page;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.presentation.PresentationObject#main(com.idega.presentation.IWContext)
	 */
	@Override
	public void main(IWContext iwc) throws Exception {
		if (!getBasketBusiness(iwc).isBasketEmpty() && getPage() != null) {
			Layer layer = new Layer();
			layer.add("basketLink");
			add(layer);
			
			LinkContainer link = new LinkContainer();
			layer.add(link);
			
			Span span = new Span();
			span.setStyleClass("basketLinkTitle");
			span.add(new Text(getResourceBundle(iwc).getLocalizedString("basket.view_basket", "View basket")));
			link.add(span);
			
			Object[] arguments = { String.valueOf(getBasketBusiness(iwc).getQuantity()) };
			
			span = new Span();
			span.setStyleClass("basketLinkItems");
			span.add(new Text(MessageFormat.format(getResourceBundle(iwc).getLocalizedString("basket.items_in_basket", "{0} items in basket"), arguments)));
			link.add(span);
		}
	}

	protected BasketBusiness getBasketBusiness(IWUserContext iwuc) {
		try {
			return (BasketBusiness) IBOLookup.getSessionInstance(iwuc, BasketBusiness.class);
		}
		catch (IBOLookupException e) {
			throw new IBORuntimeException(e);
		}
	}

	/**
	 * @return the page
	 */
	private ICPage getPage() {
		return this.page;
	}
	
	/**
	 * @param page the page to set
	 */
	public void setPage(ICPage page) {
		this.page = page;
	}
}