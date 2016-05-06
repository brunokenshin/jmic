package br.com.ursos.mail;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.DOTALL;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.ursos.config.ParserFieldConfig;
import br.com.ursos.export.Field;
import br.com.ursos.export.FieldList;

@Component
public class MailParser {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String parseMailToString(Message msg) throws IOException, MessagingException {
		String result = "";
		if (msg instanceof MimeMessage) {
			MimeMessage mimeMessage = (MimeMessage) msg;
			Object contentObject = mimeMessage.getContent();

			if (contentObject instanceof Multipart) {
				BodyPart clearTextPart = null;
				BodyPart htmlTextPart = null;
				Multipart content = (Multipart) contentObject;
				int count = content.getCount();

				for (int i = 0; i < count; i++) {
					BodyPart bodyPart = content.getBodyPart(i);

					if (bodyPart.isMimeType("text/plain")) {
						clearTextPart = bodyPart;
						break;
					}

					else if (bodyPart.isMimeType("text/html")) {
						htmlTextPart = bodyPart;
					}
				}

				if (clearTextPart != null) {
					result = (String) clearTextPart.getContent();
				}

				else if (htmlTextPart != null) {
					String html = (String) htmlTextPart.getContent();
					result = Jsoup.parse(html).text();
				}

			} else if (contentObject instanceof String) { // a simple text message
				result = (String) contentObject;

			} else { // not a mime message
				logger.warn("Not part or multipart {0}", msg.toString());
			}
		}

		return result;
	}

	public FieldList getMessageFields(Message msg, List<ParserFieldConfig> fieldsConfigs)
			throws IOException, MessagingException {
		FieldList fields = new FieldList();
		for (ParserFieldConfig config : fieldsConfigs) {
			fields.add(getMessageField(msg, config));
		}

		return fields;
	}

	private Field getMessageField(Message msg, ParserFieldConfig fieldConfig) throws IOException, MessagingException {
		String msgText = parseMailToString(msg);

		String startPattern = Pattern.quote(fieldConfig.fieldStartPattern);
		String endPattern = Pattern.quote(fieldConfig.fieldEndPattern);
		Pattern pattern = Pattern.compile(startPattern + "(.*?)" + endPattern, CASE_INSENSITIVE | DOTALL);

		Matcher matcher = pattern.matcher(msgText);

		if (matcher.find()) {
			return new Field(fieldConfig.fieldName, matcher.group(1).trim());
		}

		logger.error("Field " + fieldConfig + " not found in message: " + msg);
		throw new RuntimeException("Field not found: " + fieldConfig);
	}
	
}
