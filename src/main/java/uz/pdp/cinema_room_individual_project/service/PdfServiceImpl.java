package uz.pdp.cinema_room_individual_project.service;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.interfaces.PdfService;
import uz.pdp.cinema_room_individual_project.projection.TicketProjection;
import java.io.FileOutputStream;
@Component
public class PdfServiceImpl implements PdfService {
    @SneakyThrows
    @Override
    public void pdfGenerate(TicketProjection ticket) {
        FileOutputStream fos = new FileOutputStream("D:\\Java Back End\\Cinema_Room_Individual_Project\\src\\main\\resources\\ticket.pdf");
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
        document.open();
        document.add(new Paragraph(ticket.getMovieTitle()));
        document.add(new Paragraph("======================================"));
        document.add(new Paragraph(String.valueOf(ticket.getPrice())));
        document.close();
        pdfWriter.close();

    }
}
