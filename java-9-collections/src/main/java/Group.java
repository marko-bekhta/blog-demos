import java.util.HashSet;
import java.util.Set;

/**
 * @author Marko Bekhta
 */
public class Group {

	private String name;

	private Set<Student> students = new HashSet<>();

	public Group(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public Group addStudent(Student student) {
		this.students.add( student );
		return this;
	}

	public String getName() {
		return name;
	}

}
