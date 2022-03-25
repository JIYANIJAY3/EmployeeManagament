package util;

public class AddProjectDetailsValidation implements AddProjectDetailsValidationInterface {

	@Override
	public String checkProjectIdValidation(String ProjectId) {

		if (ProjectId.isEmpty()) {
			return "Project Can't Empty";
		} else {
			return " ";
		}
	}

	@Override
	public String checkProjectProjectNameValidation(String ProjectName) {
		if (ProjectName.isEmpty()) {
			return "ProjectName Can't Empty";
		} else {
			return " ";
		}
	}

	@Override
	public String checkProjectProjectDescValidation(String ProjectDesc) {
		if (ProjectDesc.isEmpty()) {
			return "ProjectDesc Can't Empty";
		} else {
			return " ";
		}
	}

}
