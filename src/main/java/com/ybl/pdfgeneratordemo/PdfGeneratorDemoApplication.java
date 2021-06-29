package com.ybl.pdfgeneratordemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.util.CssDimensionParsingUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class PdfGeneratorDemoApplication {

    public static final String DEST = "results/result.pdf";

    static {
        new File(DEST).getParentFile().mkdirs();
    }

    public static void main(String[] args) throws TemplateException, IOException {
        SpringApplication.run(PdfGeneratorDemoApplication.class, args);
        ftlConverter();
        convertHtmlToPdf();
        System.out.println(new ObjectMapper().writeValueAsString(getAccountInfo()));
    }

    //sending + encryption (send a dynamic pdf name, parameters to be used)
    @RequestMapping(value = "/getpdf/{fileName}", method = RequestMethod.POST)
    public static ResponseEntity<byte[]> getPdf(@PathVariable String fileName) throws IOException {
        Path path = Paths.get("results/result.pdf");
        byte[] fileContents = Files.readAllBytes(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(fileName, fileName);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(fileContents, headers, HttpStatus.OK);
    }

    public static void ftlConverter() throws IOException, TemplateException {
        //Instantiate Configuration Class
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates/"));
        cfg.setDefaultEncoding("UTF-8");

        //create data model
        Map<String, Object> map = new HashMap<>();
        map.put("title", "YBL Savings Report");
        map.put("bodyText", "--------");
        map.put("message", "Account Number: ");

        //initiate template
        Template template = cfg.getTemplate("sample.ftl");
        //to console
        /* Writer console = new OutputStreamWriter(System.out);
        template.process(map, console);
        console.flush();*/

        try (Writer outputFile = new FileWriter(new File("src/main/resources/sample.html"))) {
            template.process(getAccountInfo(), outputFile);
            outputFile.flush();
        }

    }

    public static void convertHtmlToPdf() {
        try {
            Path path = Paths.get("src/main/resources/sample.html");
            PdfWriter writer = new PdfWriter(new FileOutputStream(DEST));

            PdfDocument pdfDocument = new PdfDocument(writer);
            // set the result to be tagged
            pdfDocument.setTagged();
            pdfDocument.setDefaultPageSize(PageSize.A4);

            /*String header = "pdfHtml Header using page-events";
            Header headerHandler = new Header(header);
            pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, headerHandler);*/

            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setBaseUri("src/main/resources/");

            //set media device description details:
            MediaDeviceDescription mediaDescription
                    = new MediaDeviceDescription(com.itextpdf.styledxmlparser.css.media.MediaType.SCREEN);
            float width = CssDimensionParsingUtils.parseAbsoluteLength(Float.toString(PageSize.A4.getWidth()));
            float height = CssDimensionParsingUtils.parseAbsoluteLength(Float.toString(PageSize.A4.getHeight()));
            mediaDescription.setWidth(width);
            mediaDescription.setHeight(height);

            converterProperties.setMediaDeviceDescription(mediaDescription);
            HtmlConverter.convertToPdf(Files.newInputStream(path), pdfDocument, converterProperties);

            //HtmlConverter.convertToPdf(Files.newInputStream(path), writer);
            System.out.println("Generating pdf..");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static AccountInfo getAccountInfo() {
        CustomerAddress customerAddress = CustomerAddress.builder()
                .addressLine1("201 GALI NO 7")
                .addressLine2("SAFDARJUNG ENCL")
                .area("KRISHNA NAGER")
                .district("")
                .state("DELHI")
                .pinCode("110029")
                .build();
        CustomerInfo customerInfo =
                CustomerInfo.builder()
                        .customerName("Sudheer Muvva")
                        .emailId("sudheer.muvva@gmail.com")
                        .mobileNumber("9808009090")
                        .customerAddress(customerAddress)
                        .build();
        NomineeInfo nomineeInfo = NomineeInfo.builder()
                .nomineeName("Radha")
                .nomineeAge(40)
                .relationShip("WIFE")
                .build();
        TransactionInfo transactionInfo1 = TransactionInfo.builder()
                .transactionDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")))
                .valueDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")))
                .description("FUNDS TRF FROM XX9839/REFERE NCENO2")
                .refNo("15131653069491")
                .depositedAmount(15408.00)
                .withdrawnAmount(0.00)
                .runningBalance(15408.00)
                .build();

        TransactionInfo transactionInfo2 = TransactionInfo.builder()
                .transactionDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")))
                .valueDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")))
                .description("FUNDS TRF FROM XX9839/REFERE NCENO2")
                .refNo("15131653069491")
                .depositedAmount(3285.00)
                .withdrawnAmount(0.00)
                .runningBalance(18693.00)
                .build();

        List<TransactionInfo> transactionInfoList
                = Arrays.asList(transactionInfo1, transactionInfo2, transactionInfo1, transactionInfo1, transactionInfo2, transactionInfo1, transactionInfo1, transactionInfo2, transactionInfo1, transactionInfo1, transactionInfo2, transactionInfo1, transactionInfo2, transactionInfo1, transactionInfo2, transactionInfo1, transactionInfo2, transactionInfo1, transactionInfo2, transactionInfo1, transactionInfo2, transactionInfo1, transactionInfo2);

        return AccountInfo.builder()
                .accountNumber("000481300001957")
                .accountType("CURRENT")
                .currency("INR")
                .balance(18030.00)
                .branchCode("0004")
                .branchAddress("YES BANK LTD - NARIMAN POINT BRANCH,MITTAL CHAMBERS,NARIMAN POINT, MUMBAI - 400 021,MAHARASHTRA")
                .productCode(813)
                .IFSCCode("YESB0000004")
                .MICRCode("400532003")
                .salutation("Mr.")
                .primaryHolder("Sudheer Muvva")
                .jointHolder1("NA")
                .jointHolder2("NA")
                .accountStatus("REGULAR")
                .overdraftLimit(0.00)
                .accountOpeningDate("25-Mar-2017")
                .customerInfo(customerInfo)
                .nomineeInfo(nomineeInfo)
                .transactionInfoList(transactionInfoList)
                .build();
    }

}
