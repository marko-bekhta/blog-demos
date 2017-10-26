/**
 * @author Marko Bekhta
 */
public class Student {

	private String firstName;

	private String lastName;

	private Long averageMark;

	public Student(String firstName, String lastName, Long averageMark) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.averageMark = averageMark;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Long getAverageMark() {
		return averageMark;
	}
}
