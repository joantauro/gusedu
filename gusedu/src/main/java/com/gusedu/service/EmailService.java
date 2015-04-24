package com.gusedu.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
//import javax.persistence.StoredProcedureQuery;
//import javax.persistence.ParameterMode;

public class EmailService {
	/*
	@PersistenceContext
	EntityManager em;*/
	
	EntityManagerFactory factory;
	
	Session mailSession;
	private String fromUser;
	private String fromUserEmailPassword;
	private String emailHost;
	private String cabecera;
	private String cuerpo;
	Properties emailProperties;
	
	
	public void ejecutarStoredProcedure()
	{
		
		try
		{
			
			/*Query query = em.createNamedQuery("call SP_dia();");
			query.executeUpdate();*/
			
			  factory = Persistence.createEntityManagerFactory("dataSource");
			  EntityManager em = factory.createEntityManager();


		        // Create call stored procedure
		        em.getTransaction().begin();
		        StoredProcedureQuery storedProcedure = (StoredProcedureQuery) em.createQuery("SP_dia()");
		        
		        storedProcedure.execute();
		        System.out.println("Funco!!!");
		}
		catch(Exception e){
			System.out.println("Mensaje : "+e.getMessage()+"\nLocalizacion : "+e.getLocalizedMessage()+"\nCausa : "+e.getCause());
		}
	        //StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("sales_tax");
	}
	
	public void enviarEmail(String email) {
		String asunto = "Notificación de vencimiento de membresia GUSEDU";
		String body = "Se le notifica que su membresia <br/>"+
					  " esta a punto de caducar";
		propiedades();
		preparaEnviar(email, asunto, body);
	}

	
public String getFromUser() {
	return fromUser;
}

public void setFromUser(String fromUser) {
	this.fromUser = fromUser;
}

public String getFromUserEmailPassword() {
	return fromUserEmailPassword;
}

public void setFromUserEmailPassword(String fromUserEmailPassword) {
	this.fromUserEmailPassword = fromUserEmailPassword;
}

public String getEmailHost() {
	return emailHost;
}

public void setEmailHost(String emailHost) {
	this.emailHost = emailHost;
}

public String getCabecera() {
	return cabecera;
}

public void setCabecera(String cabecera) {
	this.cabecera = cabecera;
}

public String getCuerpo() {
	return cuerpo;
}

public void setCuerpo(String cuerpo) {
	this.cuerpo = cuerpo;
}


	public void propiedades() {
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", "587");
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		mailSession = Session.getDefaultInstance(emailProperties, null);
		setFromUser("odontologospadent@gmail.com");
		setFromUserEmailPassword("tp2014jp");
		setEmailHost("smtp.gmail.com");
	}

	public void preparaEnviar(String para, String cabecera, String cuerpo) {
		try {
			MimeMessage emailMessage = new MimeMessage(mailSession);
			emailMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(para));
			emailMessage.setSubject(cabecera);
			emailMessage.setContent(cuerpo, "text/html");
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(getEmailHost(), getFromUser(),
					getFromUserEmailPassword());
			transport
					.sendMessage(emailMessage, emailMessage.getAllRecipients());
			transport.close();
			System.out.println("Email sent successfully.");
		} catch (Exception e) {
			System.out.println(" Error : " +e.getMessage()+"\n Localizacion : "+ e.getLocalizedMessage()+
					"\n Causa : "+e.getCause());
		}

}

}
