package br.com.ursos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ursos.config.ExportConfig;
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
	public void persistFields(FieldList fields) {
		List<ExportConfig> exportConfigs = configService.getExportConfigs();

		long rowId = fieldDao.createRow(exportConfigs);

		exportConfigs.forEach(exportConfig -> {
			fields.forEach(field -> {
				if (field.name.equals(exportConfig.parserName)) {
					fieldDao.updateRow(rowId, field, exportConfig);
				}
			});
		});
	}

}
