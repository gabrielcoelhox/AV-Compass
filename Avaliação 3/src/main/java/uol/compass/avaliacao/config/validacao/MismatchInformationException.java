package uol.compass.avaliacao.config.validacao;

public class MismatchInformationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MismatchInformationException() {
		super("Data de fundação e tempo desde a fundação não correspondem.");
	}
}
