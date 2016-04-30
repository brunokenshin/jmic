----------------------------------------------------------------------------------------
-- PARSER_CONFIG INSERTS

INSERT INTO PARSER_CONFIG (FIELD_NAME, FIELD_START_PATTERN, FIELD_END_PATTERN)
	VALUES ('NAME','Nome: ','\r');

INSERT INTO PARSER_CONFIG (FIELD_NAME, FIELD_START_PATTERN, FIELD_END_PATTERN)
	VALUES ('PHONE','Telefone: ','\r');

INSERT INTO PARSER_CONFIG (FIELD_NAME, FIELD_START_PATTERN, FIELD_END_PATTERN)
	VALUES ('EMAIL','Email: ','\r');
    
INSERT INTO PARSER_CONFIG (FIELD_NAME, FIELD_START_PATTERN, FIELD_END_PATTERN)
	VALUES ('LOCATION','Endereço: ','\r');
	
----------------------------------------------------------------------------------------
-- EXPORT_CONFIG INSERTS
	
INSERT INTO EXPORT_CONFIG(PARSER_NAME, TABLE_NAME, COLUMN_NAME)
	VALUES ('NAME', 'CLIENTE', 'NOME');

INSERT INTO EXPORT_CONFIG(PARSER_NAME, TABLE_NAME, COLUMN_NAME)
	VALUES ('PHONE', 'CLIENTE', 'TELEFONE');

INSERT INTO EXPORT_CONFIG(PARSER_NAME, TABLE_NAME, COLUMN_NAME)
	VALUES ('EMAIL', 'CLIENTE', 'EMAIL');

INSERT INTO EXPORT_CONFIG(PARSER_NAME, TABLE_NAME, COLUMN_NAME)
	VALUES ('LOCATION', 'CLIENTE', 'ENDERECO');
	
----------------------------------------------------------------------------------------
-- MAIL_CONNECTION_CONFIG INSERTS
	
INSERT INTO MAIL_CONNECTION_CONFIG(CONFIG_NAME, CONFIG_VALUE)
	VALUES ('host', 'imap.gmail.com');

INSERT INTO MAIL_CONNECTION_CONFIG(CONFIG_NAME, CONFIG_VALUE)
	VALUES ('inboxfolder', 'INBOX');

INSERT INTO MAIL_CONNECTION_CONFIG(CONFIG_NAME, CONFIG_VALUE)
	VALUES ('username', 'bruce.wayne@waynecorp.com');
    
INSERT INTO MAIL_CONNECTION_CONFIG(CONFIG_NAME, CONFIG_VALUE)
	VALUES ('password', 'alfred');

INSERT INTO MAIL_CONNECTION_CONFIG(CONFIG_NAME, CONFIG_VALUE)
	VALUES ('mail.store.protocol', 'imaps');
    
INSERT INTO MAIL_CONNECTION_CONFIG(CONFIG_NAME, CONFIG_VALUE)
	VALUES ('mail.imap.ssl.enable', 'true');
    
INSERT INTO MAIL_CONNECTION_CONFIG(CONFIG_NAME, CONFIG_VALUE)
	VALUES ('mail.imap.socketFactory.class', 'javax.net.ssl.SSLSocketFactory');    

INSERT INTO MAIL_CONNECTION_CONFIG(CONFIG_NAME, CONFIG_VALUE)
	VALUES ('mail.imap.socketFactory.fallback', 'false');

INSERT INTO MAIL_CONNECTION_CONFIG(CONFIG_NAME, CONFIG_VALUE)
	VALUES ('mail.imap.socketFactory.port', '993');

INSERT INTO MAIL_CONNECTION_CONFIG(CONFIG_NAME, CONFIG_VALUE)
	VALUES ('filter.daysago', '20');

INSERT INTO MAIL_CONNECTION_CONFIG(CONFIG_NAME, CONFIG_VALUE)
	VALUES ('filter.sender', 'alfred@waynecorp.com');

INSERT INTO MAIL_CONNECTION_CONFIG(CONFIG_NAME, CONFIG_VALUE)
	VALUES ('filter.subject', 'Joker');

INSERT INTO MAIL_CONNECTION_CONFIG(CONFIG_NAME, CONFIG_VALUE)
	VALUES ('filter.unread', 'false');