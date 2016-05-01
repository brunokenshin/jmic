package br.com.ursos.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ursos.config.ExportConfig;
import br.com.ursos.export.Field;
import br.com.ursos.export.FieldList;
import br.com.ursos.persistance.FieldDao;

@Component
public class PersistService {

	private final ConfigurationService configService;
	private final FieldDao fieldDao;

	@Autowired
	public PersistService(ConfigurationService configService, FieldDao fieldDao) {
		this.configService = configService;
		this.fieldDao = fieldDao;
	}

	@Transactional
	public void persistFields(FieldList fields) throws SQLException {
		List<ExportConfig> exportConfigs = configService.getExportConfigs();
		long rowId = fieldDao.createRow(exportConfigs.get(0).tableName);
		
		for (ExportConfig config : exportConfigs) {
			for (Field field : fields) {
				if (field.name.equals(config.parserName)) {
					fieldDao.updateRow(rowId, field, config);
				}

			}
		}
	}

}
