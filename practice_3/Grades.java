package git_collab.practice_3;

public class Grades {
	
	Student student;
	double totalMarks;
	
	public Grades() {}
	
	public Grades(Student student, double totalMarks) {
		super();
		this.student = student;
		this.totalMarks = totalMarks;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public double getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(double totalMarks) {
		this.totalMarks = totalMarks;
	}

	@Override
	public String toString() {
		return "Grades [student=" + student + ", totalMarks=" + totalMarks + "]";
	}
	
	public String overallGrade(Student s, double totalMarks) {
		if(totalMarks > 90) {
			return "A";
		}
		else if(totalMarks > 80) {
			return "B";
		}
		else if(totalMarks > 70) {
			return "C";
		}
		else if(totalMarks > 60) {
			return "D";
		}
		else {
			return "FAIL";
		}
	}
	

}
