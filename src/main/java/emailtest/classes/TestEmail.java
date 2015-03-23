package emailtest.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class TestEmail {


	public TestEmail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void sendEmail(String address) {
		
		 
		Properties props = new Properties();
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
		try {
			String path = this.getClass().getClassLoader().getResource("trainingcenter-email.properties").getPath();
			props.load(new FileInputStream(path));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		final String username = props.getProperty("username");
		final String password = props.getProperty("password");
		
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected  PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			};
		});
		
		
		
		MimeMessage mimeMessage = new MimeMessage(session);
		
		try {
			mimeMessage.setFrom(new InternetAddress(username));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
			mimeMessage.setSubject("Felicitaciones, bienvenido a nuestro curso");
			
			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = this.getHtmlMailText();
			messageBodyPart.setContent(htmlText, "text/html");
			multipart.addBodyPart(messageBodyPart);
			
			messageBodyPart = new MimeBodyPart();
			String headerImgPath = this.getClass().getClassLoader().getResource("email-header.png").getPath();
			String footerImgPath = this.getClass().getClassLoader().getResource("email-footer.png").getPath();
			DataSource fds = new FileDataSource(headerImgPath);
			messageBodyPart.setDataHandler(new DataHandler(fds));
	        messageBodyPart.setHeader("Content-ID", "<roca-email-header>");
	        multipart.addBodyPart(messageBodyPart);
	        
	        fds = new FileDataSource(footerImgPath);
			messageBodyPart.setDataHandler(new DataHandler(fds));
	        messageBodyPart.setHeader("Content-ID", "<roca-email-footer>");
	        
	        mimeMessage.setContent(multipart);
	        
			
//			mimeMessage.setContent("Usted se ha suscrito al mejor curso de todos los tiempos", "text/html");
			Transport.send(mimeMessage);
			System.out.println("message has been sent");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String getHtmlMailText() {

		String result = "";
		result = "<html>"
				+ "<head>"
				+ "<style type='text/css'>"
				+ "body {"
				+ "color: #555555;"
				+ "font-family: swiss_721roman;"
				+ "font-size: 14px;"
				+ "}"
				+ ".ser_box_main {"
				+ "background-color: #eeeeee;"
				+ "border-top: 4px solid #dddddd;"
				+ "float: left;"
				+ "margin-bottom: 30px;"
				+ "padding: 8px 10px 0;"
				+ "width: 709px;"
				+ "}"
				+ ".content_box {"
				+ "float: left;"
				+ "width: 100%;"
				+ "}"
				+ ".content_box .content_dis_box {"
				+ "float: left;"
				+ "padding-top: 10px;"
				+ "width: 100%;"
				+ "}"
				+ ".content_box .alcala_box_main {"
				+ "float: left;"
				+ "width: 100%;"
				+ "}"
				+ ".content_box .alcala_box_main tr td a {"
				+ "background-color: #dddddd;"
				+ "border-radius: 3px;"
				+ "float: left;"
				+ "font-size: 15px;"
				+ "width: auto;"
				+ "color: #0a81dd;"
				+ "padding: 8px 18px;"
				+ "}"
				+ "a {"
				+ "text-decoration: none;"
				+ "}"
				+ "td {"
				+ "padding-bottom: 10px;"
				+ "padding-top: 10px;"
				+ "}"
				+ "</style>"
				+ "</head>"
				
				
				
				+ "<div style='margin-left: 262px'>"
				+ "<img src=<img src=\"cid:roca-email-header\" style='width: 720px;' />"
				+ "<br><br>"
				+ "<div style='width: 709px'>Apreciado/a amigo/a, <br><br> Hemos recibido tu solicitud de"
				+ "inscripci&oacute;n a fecha 15/03/2013 para asistir al curso que a"
				+ "continuaci&oacute;n detallamos</div>"
				+ "<br><br>"
				+ "<div class='ser_box_main'>"
				+ "<div class='content_box'>"
				+ "<div class='content_dis_box'>"
				+ "<div class='alcala_box_main'>"
				+ "<div class='todos_box'>"
				+ "<table style='width: 100%'>"
				+ "<tr>"
				+ "<td style='width: 5%; border-bottom: 2px solid white !important;'><a href='#' idcity='-1' class='select'"
				+ "style='color: #0a81dd; padding: 8px 15px;'>Centro</a></td>"
				+ "<td style='border-bottom: 2px solid white !important;'><span style='padding-left: 20px; margin-top: 5px;'>Alcal&aacute;"
				+ "de Henares</span></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td style='width: 5%; border-bottom: 2px solid white !important;'><a href='#' idcity='-1' class='select'"
				+ "style='color: #0a81dd; padding: 8px 18px;'>Curso</a></td>"
				+ "<td style='border-bottom: 2px solid white !important;'><span style='padding-left: 20px; margin-top: 5px;'>Hidromasaje</span></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td style='width: 5%;'><a href='#' idcity='-1' class='select'"
				+ "style='color: #0a81dd; padding: 8px 18px;'>Fecha</a></td>"
				+ "<td><span style='padding-left: 20px; margin-top: 5px;'>24/10/2013</span></td>"
				+ "</tr>"
				+ "</table>"
				+ "</div>"
				+ "</div>"
				+ "</div>"
				+ "</div>"
				+ "</div>"
				+ "<div style='width: 709px'>"
				+ "En principio hemos formalizado tu inscripci&oacute;n, reserv&aacute;ndote una plaza para el curso"
				+ "y fechas se&ntilde;aladas. Unos d&iacute;as antes de la celebraci&oacute;n del curso recibir&aacute;s una carta"
				+ "para que nos confirmes tu asistencia al mismo y poder efectuar la reserva definitiva de plaza."
				+ "</div>"
				+ "<br><br>"
				+ "<div style='width: 709px'>"
				+ "Agradecemos tu atenci&oacute;n y aprovechamos la ocasi&oacute; para saludarte atentamente.<br><br>"
				+ "Tlf. de contacto: 902 102 147"
				+ "</div>"
				+ "<br><br><br><br><br><br><br><br>"
				+ "<div style='width: 709px; font-weight: bold'>"
				+ "I&Ntilde;AKI GARCIA"
				+ "</div>"
				+ "<div style='width: 709px'>"
				+ "Jefe Formaci&oacute;n T&eacute;cnica Clientes"
				+ "</div>"
				+ "<div style='width: 709px'>"
				+ "<img src=\"cid:roca-email-footer\" style='width: 720px;' />"
				+ "<br><br>"
				+ "</div>"
				+ "</div>"
				+ "</html>";
		
		return result;
	}
	
}
