package com.ybl.pdfgeneratordemo;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import lombok.SneakyThrows;


public class Header implements IEventHandler {
    private String header;

    public Header(String header) {
        this.header = header;
    }

    @SneakyThrows
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDocument = documentEvent.getDocument();

        PdfPage page = documentEvent.getPage();
        Rectangle pageSize = page.getPageSize();

        Canvas canvas = new Canvas(new PdfCanvas(page), pageSize);
        ImageData imageData = ImageDataFactory.create("src/main/resources/static/header.png");
        Image image = new Image(imageData);
        float width = image.getImageWidth();
        float height = image.getImageHeight();
        canvas.add(image);

        /*ImageData imageData = ImageDataFactory.create("src/main/resources/static/header.png");
        Image image = new Image(imageData);*/

        /*Canvas canvas = new Canvas(new PdfCanvas(page), pageSize);
        canvas.setFontSize(18);

        // Write text at position
        canvas.showTextAligned(header,
                pageSize.getWidth() / 2,
                pageSize.getTop() - 30, TextAlignment.CENTER);
        canvas.close();*/


    }
}
