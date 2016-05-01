package br.com.ursos.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.ursos.config.ExportConfig;
import br.com.ursos.export.Field;
import br.com.ursos.export.FieldList;
import br.com.ursos.persistance.FieldDao;

public class PersistServiceTest {

	private PersistService service;
	private ConfigurationService configService;
	private FieldDao fieldDao;

	@Before
	public void setup() {
		configService = mock(ConfigurationService.class);
		fieldDao = mock(FieldDao.class);
		service = new PersistService(configService, fieldDao);
	}

	@Test
	public void testPersistFields() throws SQLException {
		FieldList fields = new FieldList();
		fields.add(new Field("NAME", "Bruce Wayne"));
		fields.add(new Field("LOCATION", "Wayne's Mansion, Gothan City"));

		List<ExportConfig> exportConfigs = new ArrayList<ExportConfig>();
		exportConfigs.add(new ExportConfig("NAME", "CLIENT", "CLIENT_NAME"));
		exportConfigs.add(new ExportConfig("LOCATION", "CLIENT", "CLIENT_LOCATION"));

		long rowId = 1;

		when(configService.getExportConfigs()).thenReturn(exportConfigs);
		when(fieldDao.createRow(exportConfigs.get(0).tableName)).thenReturn(rowId);

		service.persistFields(fields);

		verify(fieldDao).createRow(exportConfigs.get(0).tableName);
		verify(fieldDao).updateRow(rowId, fields.get(0), exportConfigs.get(0));
		verify(fieldDao).updateRow(rowId, fields.get(1), exportConfigs.get(1));
	}

}
