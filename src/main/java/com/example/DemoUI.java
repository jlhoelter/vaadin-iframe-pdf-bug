package com.example;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@SpringUI
@Theme("valo")
public class DemoUI extends UI {
	
	@Value("classpath:book-of-vaadin.pdf")
    private Resource pdf;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2402504965371697045L;

	private BrowserFrame pdfFrame;

	@Override
	protected void init(VaadinRequest request) {
		HorizontalLayout content = new HorizontalLayout();
		content.setSizeFull();
		content.setMargin(true);
		content.setSpacing(true);
		
		VerticalLayout labelAndButton = labelAndButton();
		content.addComponent(labelAndButton);
		
		createPdfFrame();
		content.addComponent(pdfFrame);
		
		content.setExpandRatio(labelAndButton, 0.3f);
		content.setExpandRatio(pdfFrame, 0.7f);
		
		this.setContent(content);
	}

	private VerticalLayout labelAndButton() {
		Label someLabelWithTooltip = new Label("Hover me to see a tooltip");
		someLabelWithTooltip.setDescription("Some tooltip");
		CheckBox checkBox = new CheckBox("Hide PDF Viewer", false);
		checkBox.addValueChangeListener((event) ->{
			pdfFrame.setVisible(!(Boolean)event.getProperty().getValue());
		});
		VerticalLayout labelAndButton = new VerticalLayout(someLabelWithTooltip, checkBox);
		labelAndButton.setMargin(true);
		labelAndButton.setSpacing(true);
		return labelAndButton;
	}

	private void createPdfFrame() {
		pdfFrame = new BrowserFrame();
		pdfFrame.setSizeFull();
		try {
			pdfFrame.setSource(new FileResource(pdf.getFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	

}
