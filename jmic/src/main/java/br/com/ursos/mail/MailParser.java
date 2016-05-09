package br.com.ursos.mail;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.DOTALL;

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
			fields.add(getMessageField(msg, config));
		}

		return fields;
	}

	private Field getMessageField(Message msg, ParserFieldConfig fieldConfig) throws Exception {
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
