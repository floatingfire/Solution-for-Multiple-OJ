import java.util.*;

public class p4016 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		TreeSet<Student> stuts = new TreeSet<Student>();
		for (int i = 0; i < n; i++) {
			stuts.add(new Student(scan.next(), scan.nextInt(), i));
		}
		Iterator<Student> iter = stuts.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}

class Student implements Comparable<Student> {
	String id;
	int gra;
	int num;

	Student(String i, int grade, int number) {
		id = i;
		gra = grade;
		num = number;
	}

	public String toString() {
		return id;
	}

	public int compareTo(Student stu) {
		int comp = stu.gra - gra;
		return comp != 0 ? comp : num - stu.num;
	}
}