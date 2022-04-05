package uz.pdp.cinema_room_individual_project.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uz.pdp.cinema_room_individual_project.dto.MailDto;
import uz.pdp.cinema_room_individual_project.interfaces.SendEmailService;
import uz.pdp.cinema_room_individual_project.model.User;
import uz.pdp.cinema_room_individual_project.payload.ApiResponse;
import uz.pdp.cinema_room_individual_project.projection.TicketProjection;
import uz.pdp.cinema_room_individual_project.repository.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Component
public class SendEmailServiceImpl implements SendEmailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PdfServiceImpl pdfService;
    String sendFrom = "zakhiriddinkhasanov013@gmail.com";


    @Override
    public HttpEntity<?> sendMessage(List<TicketProjection> ticketProjections) {
        User user = userRepository.findByUsername("zahriddin007");
        System.out.println(user.getEmail());
        try {
            for (TicketProjection ticket : ticketProjections) {
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(sendFrom);
                helper.setTo(user.getEmail());
                helper.setSubject("Your ticket has been successfully processed");
                helper.setText("Here you a your ticket");

                pdfService.pdfGenerate(ticket);

                URL resource = getClass().getClassLoader().getResource("ticket.pdf");
                if (resource == null) {
                    throw new IllegalArgumentException("file not found!");
                } else {
                    File file = new File(resource.toURI());
                    File file1 = new ClassPathResource("ticket.pdf").getFile();
                    helper.addAttachment(file1.getName(), file1);
                }
                javaMailSender.send(message);
            }
        } catch (MessagingException | URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Ticket(s) succesfully sent!!");
    }

    @Override
    public HttpEntity<?> sendTemplate() throws MessagingException {
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome Akbarali!!!");
        String html = "<!doctype html>\n" +
                "    <html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "      <head>\n" +
                "        <title>\n" +
                "          \n" +
                "        </title>\n" +
                "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "        <style type=\"text/css\">\n" +
                "          #outlook a { padding:0; }\n" +
                "          body { margin:0;padding:0;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%; }\n" +
                "          table, td { border-collapse:collapse;mso-table-lspace:0pt;mso-table-rspace:0pt; }\n" +
                "          img { border:0;height:auto;line-height:100%; outline:none;text-decoration:none;-ms-interpolation-mode:bicubic; }\n" +
                "          p { display:block;margin:13px 0; }\n" +
                "        </style>\n" +
                "        <!--[if mso]>\n" +
                "        <xml>\n" +
                "        <o:OfficeDocumentSettings>\n" +
                "          <o:AllowPNG/>\n" +
                "          <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "        </o:OfficeDocumentSettings>\n" +
                "        </xml>\n" +
                "        <![endif]-->\n" +
                "        <!--[if lte mso 11]>\n" +
                "        <style type=\"text/css\">\n" +
                "          .outlook-group-fix { width:100% !important; }\n" +
                "        </style>\n" +
                "        <![endif]-->\n" +
                "        \n" +
                "      <!--[if !mso]><!-->\n" +
                "        <link href=\"https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "        <style type=\"text/css\">\n" +
                "          @import url(https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700);\n" +
                "        </style>\n" +
                "      <!--<![endif]-->\n" +
                "\n" +
                "    \n" +
                "        \n" +
                "    <style type=\"text/css\">\n" +
                "      @media only screen and (max-width:480px) {\n" +
                "        .mj-column-per-50 { width:50% !important; max-width: 50%; }\n" +
                ".mj-column-per-100 { width:100% !important; max-width: 100%; }\n" +
                ".mj-column-per-25 { width:25% !important; max-width: 25%; }\n" +
                "      }\n" +
                "    </style>\n" +
                "    \n" +
                "  \n" +
                "        <style type=\"text/css\">\n" +
                "        \n" +
                "        \n" +
                "\n" +
                "    @media only screen and (max-width:480px) {\n" +
                "      table.full-width-mobile { width: 100% !important; }\n" +
                "      td.full-width-mobile { width: auto !important; }\n" +
                "    }\n" +
                "  \n" +
                "        </style>\n" +
                "        <style type=\"text/css\">.hide_on_mobile { display: none !important;} \n" +
                "        @media only screen and (min-width: 480px) { .hide_on_mobile { display: block !important;} }\n" +
                "        .hide_section_on_mobile { display: none !important;} \n" +
                "        @media only screen and (min-width: 480px) { \n" +
                "            .hide_section_on_mobile { \n" +
                "                display: table !important;\n" +
                "            } \n" +
                "\n" +
                "            div.hide_section_on_mobile { \n" +
                "                display: block !important;\n" +
                "            }\n" +
                "        }\n" +
                "        .hide_on_desktop { display: block !important;} \n" +
                "        @media only screen and (min-width: 480px) { .hide_on_desktop { display: none !important;} }\n" +
                "        .hide_section_on_desktop { \n" +
                "            display: table !important;\n" +
                "            width: 100%;\n" +
                "        } \n" +
                "        @media only screen and (min-width: 480px) { .hide_section_on_desktop { display: none !important;} }\n" +
                "        \n" +
                "          p, h1, h2, h3 {\n" +
                "              margin: 0px;\n" +
                "          }\n" +
                "\n" +
                "          ul, li, ol {\n" +
                "            font-size: 11px;\n" +
                "            font-family: Ubuntu, Helvetica, Arial;\n" +
                "          }\n" +
                "\n" +
                "          a {\n" +
                "              text-decoration: none;\n" +
                "              color: inherit;\n" +
                "          }\n" +
                "\n" +
                "          @media only screen and (max-width:480px) {\n" +
                "\n" +
                "            .mj-column-per-100 { width:100%!important; max-width:100%!important; }\n" +
                "            .mj-column-per-100 > .mj-column-per-75 { width:75%!important; max-width:75%!important; }\n" +
                "            .mj-column-per-100 > .mj-column-per-60 { width:60%!important; max-width:60%!important; }\n" +
                "            .mj-column-per-100 > .mj-column-per-50 { width:50%!important; max-width:50%!important; }\n" +
                "            .mj-column-per-100 > .mj-column-per-40 { width:40%!important; max-width:40%!important; }\n" +
                "            .mj-column-per-100 > .mj-column-per-33 { width:33.333333%!important; max-width:33.333333%!important; }\n" +
                "            .mj-column-per-100 > .mj-column-per-25 { width:25%!important; max-width:25%!important; }\n" +
                "\n" +
                "            .mj-column-per-100 { width:100%!important; max-width:100%!important; }\n" +
                "            .mj-column-per-75 { width:100%!important; max-width:100%!important; }\n" +
                "            .mj-column-per-60 { width:100%!important; max-width:100%!important; }\n" +
                "            .mj-column-per-50 { width:100%!important; max-width:100%!important; }\n" +
                "            .mj-column-per-40 { width:100%!important; max-width:100%!important; }\n" +
                "            .mj-column-per-33 { width:100%!important; max-width:100%!important; }\n" +
                "            .mj-column-per-25 { width:100%!important; max-width:100%!important; }\n" +
                "        }</style>\n" +
                "        \n" +
                "      </head>\n" +
                "      <body style=\"background-color:#F3EEB3;\">\n" +
                "        \n" +
                "        \n" +
                "      <div style=\"background-color:#F3EEB3;\">\n" +
                "        \n" +
                "      \n" +
                "      <!--[if mso | IE]>\n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "    \n" +
                "      \n" +
                "      <div style=\"background:#F3EEB3;background-color:#F3EEB3;margin:0px auto;max-width:600px;\">\n" +
                "        \n" +
                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#F3EEB3;background-color:#F3EEB3;width:100%;\">\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td style=\"direction:ltr;font-size:0px;padding:9px 0px 9px 0px;text-align:center;\">\n" +
                "                <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:300px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-50 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:50%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"left\" style=\"font-size:0px;padding:0px 0px 0px 10px;word-break:break-word;\">\n" +
                "                \n" +
                "      <div style=\"font-family:Ubuntu, Helvetica, Arial;font-size:11px;line-height:1.5;text-align:left;color:#131212;\"><p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\">Write short email preheader</p></div>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:300px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-50 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:50%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"right\" style=\"font-size:0px;padding:0px 10px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      <div style=\"font-family:Ubuntu, Helvetica, Arial;font-size:11px;line-height:1.5;text-align:right;color:#000000;\"><p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\"><a style=\"color: #808080;\" href=\"*|WEBVERSION|*\"><span style=\"color: #000000;\">Webversion</span></a></p></div>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "        \n" +
                "      </div>\n" +
                "    \n" +
                "      \n" +
                "      <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "    \n" +
                "      \n" +
                "      <div style=\"background:#FFFFFF;background-color:#FFFFFF;margin:0px auto;max-width:600px;\">\n" +
                "        \n" +
                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#FFFFFF;background-color:#FFFFFF;width:100%;\">\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td style=\"direction:ltr;font-size:0px;padding:0px 0px 0px 0px;text-align:center;\">\n" +
                "                <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:600px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td style=\"font-size:0px;word-break:break-word;\">\n" +
                "                \n" +
                "      \n" +
                "    <!--[if mso | IE]>\n" +
                "    \n" +
                "        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"18\" style=\"vertical-align:top;height:18px;\">\n" +
                "      \n" +
                "    <![endif]-->\n" +
                "  \n" +
                "      <div style=\"height:18px;\">\n" +
                "        &nbsp;\n" +
                "      </div>\n" +
                "      \n" +
                "    <!--[if mso | IE]>\n" +
                "    \n" +
                "        </td></tr></table>\n" +
                "      \n" +
                "    <![endif]-->\n" +
                "  \n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:0px 0px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      <div style=\"font-family:Ubuntu, Helvetica, Arial;font-size:11px;line-height:1.5;text-align:center;color:#000000;\"><p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\"><span style=\"font-size:28px;\"><strong><em>Company</em></strong></span></p></div>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "        \n" +
                "      </div>\n" +
                "    \n" +
                "      \n" +
                "      <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "    \n" +
                "      \n" +
                "      <div style=\"background:#FFFFFF;background-color:#FFFFFF;margin:0px auto;max-width:600px;\">\n" +
                "        \n" +
                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#FFFFFF;background-color:#FFFFFF;width:100%;\">\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td style=\"direction:ltr;font-size:0px;padding:0px 0px 0px 0px;text-align:center;\">\n" +
                "                <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:600px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:0px 0px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td style=\"width:600px;\">\n" +
                "              \n" +
                "      <img height=\"auto\" src=\"https://storage.googleapis.com/afuxova10642/2019/Feb/Thu/1550140665.png\" style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;\" width=\"600\">\n" +
                "    \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "        \n" +
                "      </div>\n" +
                "    \n" +
                "      \n" +
                "      <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "    \n" +
                "      \n" +
                "      <div style=\"background:#FFFFFF;background-color:#FFFFFF;margin:0px auto;max-width:600px;\">\n" +
                "        \n" +
                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#FFFFFF;background-color:#FFFFFF;width:100%;\">\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td style=\"direction:ltr;font-size:0px;padding:4px 0px 4px 0px;text-align:center;\">\n" +
                "                <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:600px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:5px 15px 4px 15px;word-break:break-word;\">\n" +
                "                \n" +
                "      <div style=\"font-family:Ubuntu, Helvetica, Arial;font-size:11px;line-height:1.5;text-align:center;color:#000000;\"><p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\"><span style=\"font-size:14px;\">Say &quot;Thank you&quot;&nbsp;for signing up to your new or potential customers.&nbsp;<br>\n" +
                "This is a great opportunity to catch them with your personality,<br>\n" +
                "so they look forward to your next newsletter.</span></p>\n" +
                "\n" +
                "<p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\"><span style=\"font-size:14px;\">It can be as simple as a line of text, or you can use images or GIFs.</span></p></div>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:0px 0px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td style=\"width:546px;\">\n" +
                "              \n" +
                "      <img height=\"auto\" src=\"https://storage.googleapis.com/afuxova10642/kisspng-youtube-greeting-note-cards-wedding-invitation-l-thank-you-icon-5b3768bc34c204.2302207915303579482161.png\" style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;\" width=\"546\">\n" +
                "    \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "        \n" +
                "      </div>\n" +
                "    \n" +
                "      \n" +
                "      <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "    \n" +
                "      \n" +
                "      <div style=\"background:#FFFFFF;background-color:#FFFFFF;margin:0px auto;max-width:600px;\">\n" +
                "        \n" +
                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#FFFFFF;background-color:#FFFFFF;width:100%;\">\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td style=\"direction:ltr;font-size:0px;padding:4px 0px 4px 0px;text-align:center;\">\n" +
                "                <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:600px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:9px 15px 8px 15px;word-break:break-word;\">\n" +
                "                \n" +
                "      <div style=\"font-family:Ubuntu, Helvetica, Arial;font-size:11px;line-height:1.5;text-align:center;color:#000000;\"><p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\"><span style=\"font-size:14px;\">Another great way to encourage engagement is by linking out to your social channels<br>\n" +
                "and having them connect with you on other platforms.</span></p>\n" +
                "\n" +
                "<p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\"><span style=\"font-size:14px;\">Or you can use a special sale code to motivate them to shop.</span></p>\n" +
                "\n" +
                "<p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\"><em><span style=\"font-size:20px;\"><strong>YOUR CODE: SPECIALCODE2019</strong></span></em></p></div>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "            <tr>\n" +
                "              <td align=\"center\" vertical-align=\"middle\" style=\"font-size:0px;padding:11px 11px 11px 11px;word-break:break-word;\">\n" +
                "                \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:separate;line-height:100%;\">\n" +
                "        <tr>\n" +
                "          <td align=\"center\" bgcolor=\"#0A0200\" role=\"presentation\" style=\"border:0px solid #000;border-radius:none;cursor:auto;mso-padding-alt:9px 26px;background:#0A0200;\" valign=\"middle\">\n" +
                "            <a href=\"https://google.com\" style=\"display: inline-block; background: #0A0200; color: #ffffff; font-family: Ubuntu, Helvetica, Arial, sans-serif, Helvetica, Arial, sans-serif; font-size: 13px; font-weight: normal; line-height: 100%; margin: 0; text-decoration: none; text-transform: none; padding: 9px 26px; mso-padding-alt: 0px; border-radius: none;\" target=\"_blank\">\n" +
                "              REDEEM A CODE\n" +
                "            </a>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "        \n" +
                "      </div>\n" +
                "    \n" +
                "      \n" +
                "      <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "    \n" +
                "      \n" +
                "      <div style=\"background:#FFFFFF;background-color:#FFFFFF;margin:0px auto;max-width:600px;\">\n" +
                "        \n" +
                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#FFFFFF;background-color:#FFFFFF;width:100%;\">\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td style=\"direction:ltr;font-size:0px;padding:0px 0px 0px 0px;text-align:center;\">\n" +
                "                <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:600px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:0px 0px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td style=\"width:600px;\">\n" +
                "              \n" +
                "      <img height=\"auto\" src=\"https://storage.googleapis.com/afuxova10642/2019/Feb/Thu/1550140523.png\" style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;\" width=\"600\">\n" +
                "    \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "        \n" +
                "      </div>\n" +
                "    \n" +
                "      \n" +
                "      <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "    \n" +
                "      \n" +
                "      <div style=\"background:#FFFFFF;background-color:#FFFFFF;margin:0px auto;max-width:600px;\">\n" +
                "        \n" +
                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#FFFFFF;background-color:#FFFFFF;width:100%;\">\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td style=\"direction:ltr;font-size:0px;padding:0px 0px 0px 0px;text-align:center;\">\n" +
                "                <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:150px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-25 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:25%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:0px 0px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      <div style=\"font-family:Ubuntu, Helvetica, Arial;font-size:11px;line-height:1.5;text-align:center;color:#000000;\"><p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\"><strong>ABOUT US</strong><br>\n" +
                "Company name</p></div>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:150px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-25 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:25%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:0px 0px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      <div style=\"font-family:Ubuntu, Helvetica, Arial;font-size:11px;line-height:1.5;text-align:center;color:#000000;\"><p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\"><strong>CUSTOMER CARE</strong><br>\n" +
                "Tel 111 222 111</p></div>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:150px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-25 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:25%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:0px 0px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      <div style=\"font-family:Ubuntu, Helvetica, Arial;font-size:11px;line-height:1.5;text-align:center;color:#000000;\"><p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\"><strong>FIND A SHOP</strong><br>\n" +
                "Choose a city</p></div>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:150px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-25 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:25%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:0px 0px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      <div style=\"font-family:Ubuntu, Helvetica, Arial;font-size:11px;line-height:1.5;text-align:center;color:#000000;\"><p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\"><strong>VISIT OUR WEB PAGE</strong><br>\n" +
                "www.companyname.com</p></div>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "        \n" +
                "      </div>\n" +
                "    \n" +
                "      \n" +
                "      <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "    \n" +
                "      \n" +
                "      <div style=\"background:#FFFFFF;background-color:#FFFFFF;margin:0px auto;max-width:600px;\">\n" +
                "        \n" +
                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#FFFFFF;background-color:#FFFFFF;width:100%;\">\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td style=\"direction:ltr;font-size:0px;padding:0px 0px 0px 0px;text-align:center;\">\n" +
                "                <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:600px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:0px 0px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td style=\"width:600px;\">\n" +
                "              \n" +
                "      <img height=\"auto\" src=\"https://storage.googleapis.com/afuxova10642/pruhbez.png\" style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;\" width=\"600\">\n" +
                "    \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "        \n" +
                "      </div>\n" +
                "    \n" +
                "      \n" +
                "      <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "    \n" +
                "      \n" +
                "      <div style=\"background:#FFFFFF;background-color:#FFFFFF;margin:0px auto;max-width:600px;\">\n" +
                "        \n" +
                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#FFFFFF;background-color:#FFFFFF;width:100%;\">\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td style=\"direction:ltr;font-size:0px;padding:0px 0px 0px 0px;text-align:center;\">\n" +
                "                <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:600px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:0px 0px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      <div style=\"font-family:Ubuntu, Helvetica, Arial;font-size:11px;line-height:1.5;text-align:center;color:#000000;\"><p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\"><strong>FOLLOW US</strong></p></div>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:0px 0px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      \n" +
                "     <!--[if mso | IE]>\n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "      \n" +
                "              <td>\n" +
                "            <![endif]-->\n" +
                "              <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"float:none;display:inline-table;\">\n" +
                "                \n" +
                "      <tr>\n" +
                "        <td style=\"padding:4px;\">\n" +
                "          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:transparent;border-radius:3px;width:35px;\">\n" +
                "            <tr>\n" +
                "              <td style=\"font-size:0;height:35px;vertical-align:middle;width:35px;\">\n" +
                "                <a href=\"https://www.facebook.com/sharer/sharer.php?u=https://www.facebook.com/PROFILE\" target=\"_blank\" style=\"color: #0000EE;\">\n" +
                "                    <img height=\"35\" src=\"https://s3-eu-west-1.amazonaws.com/ecomail-assets/editor/social-icos/rounded/facebook.png\" style=\"border-radius:3px;display:block;\" width=\"35\">\n" +
                "                  </a>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "        \n" +
                "      </tr>\n" +
                "    \n" +
                "              </table>\n" +
                "            <!--[if mso | IE]>\n" +
                "              </td>\n" +
                "            \n" +
                "              <td>\n" +
                "            <![endif]-->\n" +
                "              <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"float:none;display:inline-table;\">\n" +
                "                \n" +
                "      <tr>\n" +
                "        <td style=\"padding:4px;\">\n" +
                "          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:transparent;border-radius:3px;width:35px;\">\n" +
                "            <tr>\n" +
                "              <td style=\"font-size:0;height:35px;vertical-align:middle;width:35px;\">\n" +
                "                <a href=\"https://www.linkedin.com/shareArticle?mini=true&url=[[SHORT_PERMALINK]]&title=&summary=&source=\" target=\"_blank\" style=\"color: #0000EE;\">\n" +
                "                    <img height=\"35\" src=\"https://s3-eu-west-1.amazonaws.com/ecomail-assets/editor/social-icos/rounded/linkedin.png\" style=\"border-radius:3px;display:block;\" width=\"35\">\n" +
                "                  </a>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "        \n" +
                "      </tr>\n" +
                "    \n" +
                "              </table>\n" +
                "            <!--[if mso | IE]>\n" +
                "              </td>\n" +
                "            \n" +
                "              <td>\n" +
                "            <![endif]-->\n" +
                "              <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"float:none;display:inline-table;\">\n" +
                "                \n" +
                "      <tr>\n" +
                "        <td style=\"padding:4px;\">\n" +
                "          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:transparent;border-radius:3px;width:35px;\">\n" +
                "            <tr>\n" +
                "              <td style=\"font-size:0;height:35px;vertical-align:middle;width:35px;\">\n" +
                "                <a href=\"https://www.youtube.com\" target=\"_blank\" style=\"color: #0000EE;\">\n" +
                "                    <img height=\"35\" src=\"https://s3-eu-west-1.amazonaws.com/ecomail-assets/editor/social-icos/rounded/youtube.png\" style=\"border-radius:3px;display:block;\" width=\"35\">\n" +
                "                  </a>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "        \n" +
                "      </tr>\n" +
                "    \n" +
                "              </table>\n" +
                "            <!--[if mso | IE]>\n" +
                "              </td>\n" +
                "            \n" +
                "              <td>\n" +
                "            <![endif]-->\n" +
                "              <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"float:none;display:inline-table;\">\n" +
                "                \n" +
                "      <tr>\n" +
                "        <td style=\"padding:4px;\">\n" +
                "          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:transparent;border-radius:3px;width:35px;\">\n" +
                "            <tr>\n" +
                "              <td style=\"font-size:0;height:35px;vertical-align:middle;width:35px;\">\n" +
                "                <a href=\"[[SHORT_PERMALINK]]\" target=\"_blank\" style=\"color: #0000EE;\">\n" +
                "                    <img height=\"35\" src=\"https://s3-eu-west-1.amazonaws.com/ecomail-assets/editor/social-icos/rounded/instagram.png\" style=\"border-radius:3px;display:block;\" width=\"35\">\n" +
                "                  </a>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "        \n" +
                "      </tr>\n" +
                "    \n" +
                "              </table>\n" +
                "            <!--[if mso | IE]>\n" +
                "              </td>\n" +
                "            \n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      <![endif]-->\n" +
                "    \n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "            <tr>\n" +
                "              <td style=\"font-size:0px;word-break:break-word;\">\n" +
                "                \n" +
                "      \n" +
                "    <!--[if mso | IE]>\n" +
                "    \n" +
                "        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"10\" style=\"vertical-align:top;height:10px;\">\n" +
                "      \n" +
                "    <![endif]-->\n" +
                "  \n" +
                "      <div style=\"height:10px;\">\n" +
                "        &nbsp;\n" +
                "      </div>\n" +
                "      \n" +
                "    <!--[if mso | IE]>\n" +
                "    \n" +
                "        </td></tr></table>\n" +
                "      \n" +
                "    <![endif]-->\n" +
                "  \n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "        \n" +
                "      </div>\n" +
                "    \n" +
                "      \n" +
                "      <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      \n" +
                "      <table\n" +
                "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                "      >\n" +
                "        <tr>\n" +
                "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "      <![endif]-->\n" +
                "    \n" +
                "      \n" +
                "      <div style=\"background:#F3EEB3;background-color:#F3EEB3;margin:0px auto;max-width:600px;\">\n" +
                "        \n" +
                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#F3EEB3;background-color:#F3EEB3;width:100%;\">\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td style=\"direction:ltr;font-size:0px;padding:9px 0px 9px 0px;text-align:center;\">\n" +
                "                <!--[if mso | IE]>\n" +
                "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                \n" +
                "        <tr>\n" +
                "      \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:300px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-50 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:50%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:0px 0px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      <div style=\"font-family:Ubuntu, Helvetica, Arial;font-size:11px;line-height:1.5;text-align:center;color:#000000;\"><p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\">Please enter your address and your contact here.</p>\n" +
                "\n" +
                "<p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\">Explain why your subscribers are receiving this email.</p></div>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "            <td\n" +
                "               class=\"\" style=\"vertical-align:top;width:300px;\"\n" +
                "            >\n" +
                "          <![endif]-->\n" +
                "            \n" +
                "      <div class=\"mj-column-per-50 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:50%;\">\n" +
                "        \n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "        \n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"font-size:0px;padding:0px 0px 0px 0px;word-break:break-word;\">\n" +
                "                \n" +
                "      <div style=\"font-family:Ubuntu, Helvetica, Arial;font-size:11px;line-height:1.5;text-align:center;color:#000000;\"><p style=\"font-family: Ubuntu, Helvetica, Arial; font-size: 11px;\">If you don&acute;t&nbsp;want to receive any more information<br>\n" +
                "from us, please<span style=\"color:#000000;\"> </span><strong><span style=\"color: rgb(0, 0, 0);\"><a href=\"*|UNSUB|*\" style=\"color: #000000;\">click this line</a>.</span></strong></p></div>\n" +
                "    \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "      </table>\n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "          <!--[if mso | IE]>\n" +
                "            </td>\n" +
                "          \n" +
                "        </tr>\n" +
                "      \n" +
                "                  </table>\n" +
                "                <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "        \n" +
                "      </div>\n" +
                "    \n" +
                "      \n" +
                "      <!--[if mso | IE]>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      <![endif]-->\n" +
                "    \n" +
                "    \n" +
                "      </div>\n" +
                "    \n" +
                "      </body>\n" +
                "    </html>";
        helper.setText(html, true);
        helper.setTo("akbaraliasqaraliyev0610@gmail.com");
        javaMailSender.send(mimeMessage);
        return ResponseEntity.ok("Email template succesfully sent!!!");
    }

    @Override
    public HttpEntity<?> sendGif(Map<String, Object> templateModel) {
//        try {
//            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("");
//            FreeMarkerTemplateUtils.processTemplateIntoString(template, templateModel);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.ok("Gif succesfully sent!!");
        return null;
    }
}
