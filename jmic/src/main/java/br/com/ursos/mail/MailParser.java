package br.com.ursos.mail;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.util.MimeMessageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.ursos.config.ParserFieldConfig;
import br.com.ursos.export.Field;
import br.com.ursos.export.FieldList;

@Component
public class MailParser {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String parseMailToString(Message msg) throws Exception {
		String result = "";
		if (msg instanceof MimeMessage) {
			MimeMessageParser parser = new MimeMessageParser((MimeMessage) msg);
			parser.parse();
			return parser.getPlainContent();
		}
		logger.warn("Not a MimeMessage{0}", msg.toString());
		return result;
	}

	public FieldList getMessageFields(Message msg, List<ParserFieldConfig> fieldsConfigs) throws Exception {
		FieldList fields = new FieldList();
		for (ParserFieldConfig config : fieldsConfigs) {
			Field field = getMessageField(msg, config);
			if (field.isValid()) {
				fields.add(field);
			}
		}
		return fields;
	}

	private Field getMessageField(Message msg, ParserFieldConfig fieldConfig) throws Exception {
		String msgText = linebreakTransform(parseMailToString(msg));
		Pattern pattern = Pattern.compile(fieldConfig.startPattern + "(.*?)" + fieldConfig.endPattern);
		Matcher matcher = pattern.matcher(msgText);

		if (matcher.find()) {
			return new Field(fieldConfig.name, matcher.group(1).trim());
		}

		logger.warn("Field " + fieldConfig + " not found in message: " + msg);
		return new Field(null, null);
	}

	private String linebreakTransform(String text) {
		text = text.replace("\r\n", "\n");
		text = text.replace("\r", "\n");
		return text;
	}
	
}
