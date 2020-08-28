package swsketch.web.payload;

import java.util.List;

import javax.validation.constraints.NotNull;

public class CreateTagPayload {
	@NotNull
	private List<String> names;

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}
}
