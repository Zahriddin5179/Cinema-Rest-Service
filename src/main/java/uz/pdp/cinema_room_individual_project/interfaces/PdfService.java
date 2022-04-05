package uz.pdp.cinema_room_individual_project.interfaces;

import uz.pdp.cinema_room_individual_project.model.Ticket;
import uz.pdp.cinema_room_individual_project.projection.TicketProjection;

public interface PdfService {
    void pdfGenerate(TicketProjection ticket);
}
