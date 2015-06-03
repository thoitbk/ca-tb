package com.catb.bo;

import java.util.List;

import com.catb.model.AdministrativeProcedure;
import com.catb.model.AdministrativeProcedureFile;

public interface AdministrativeProcedureBO {

	public List<AdministrativeProcedure> getAdministrativeProcedures();
	public void addAdministrativeProcedure(
			AdministrativeProcedure administrativeProcedure,
			Integer departmentId, Integer fieldId,
			List<AdministrativeProcedureFile> files);
	public AdministrativeProcedure fetchAdministrativeProcedureById(Integer id);
	public void updateAdministrativeProcedure(
			AdministrativeProcedure administrativeProcedure,
			Integer departmentId, Integer fieldId,
			List<AdministrativeProcedureFile> files);
	public void deleteAdministrativeProcedures(Integer[] ids);
}
