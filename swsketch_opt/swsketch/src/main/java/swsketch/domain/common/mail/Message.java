package swsketch.domain.common.mail;

public interface Message {
	  String getTo();

	  /**
	   * Get the subject of the message
	   *
	   * @return message's subject
	   */
	  String getSubject();

	  /**
	   * Get the body of the message
	   *
	   * @return the body of the message
	   */
	  String getBody();

	  /**
	   * Get the from of this message
	   *
	   * @return where this message is from
	   */
	  String getFrom();
}
