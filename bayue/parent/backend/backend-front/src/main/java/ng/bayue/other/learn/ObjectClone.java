package ng.bayue.other.learn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectClone {

	public static void main(String[] args) {
		// shallowCopy();
		deepCopy();
		deepCopy1();
	}

	public static void shallowCopy() {
		Student stu = new Student();
		stu.setId(1);
		stu.setName("zhangsan");

		Teacher t = new Teacher();
		t.setId(1);
		t.setAge(29);
		stu.setTeacher(t);

		Student stu1 = (Student) stu.clone();

		System.out.println(stu.toString());
		System.out.println(stu1.toString());
		t.setId(2);
		t.setAge(20);
		System.out.println(stu.toString());
		System.out.println(stu1.toString());
	}

	public static void deepCopy() {
		StudentChild sc = new StudentChild();
		sc.setId(1);
		sc.setName("zhangsan");
		TeacherChild tc = new TeacherChild();
		tc.setAge(20);
		tc.setId(1);
		sc.setTc(tc);
		StudentChild sc1 = (StudentChild) sc.clone();
		System.out.println(sc.toString());
		System.out.println(sc1.toString());
		tc.setAge(33);
		tc.setId(2);
		System.out.println(sc.toString());
		System.out.println(sc1.toString());
	}

	public static void deepCopy1() {
		StudentDeepCopy sdc = new StudentDeepCopy();
		sdc.setId(1);
		sdc.setName("zhangsa");
		Teacher t = new Teacher();
		t.setId(1);
		t.setAge(20);
		sdc.setTeacher(t);
		StudentDeepCopy sdc1 = (StudentDeepCopy) sdc.deepCopy();
		System.out.println(sdc.toString());
		System.out.println(sdc1.toString());
		t.setId(2);
		t.setAge(33);
		System.out.println(sdc.toString());
		System.out.println(sdc1.toString());
	}

}

class Student implements Cloneable, Serializable {
	/**  */
	private static final long serialVersionUID = 1024231760017269640L;
	private Integer id;
	private String name;
	private Teacher teacher;

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", teacher=" + (null == teacher ? null : teacher.toString())
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}

class Teacher implements Serializable {
	/**  */
	private static final long serialVersionUID = -477853530034302392L;
	private Integer id;
	private Integer age;

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", age=" + age + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}

class StudentChild extends Student implements Cloneable {
	private TeacherChild tc;

	@Override
	public Object clone() {
		StudentChild sc = (StudentChild) super.clone();
		sc.setTc((TeacherChild) sc.tc.clone());
		return sc;
	}

	@Override
	public String toString() {
		return "Student [id=" + super.getId() + ", name=" + super.getName() + ", teacher="
				+ (null == super.getTeacher() ? null : super.getTeacher().toString()) + ", tc=" + tc.toString() + "]";
	}

	public TeacherChild getTc() {
		return tc;
	}

	public void setTc(TeacherChild tc) {
		this.tc = tc;
	}

}

class TeacherChild extends Teacher implements Cloneable {

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}


}

/**
 * <pre>
 * </pre>
 *
 * @author lenovopc
 * @version $Id: ObjectClone.java, v 0.1 2016年11月23日 下午5:02:27 lenovopc Exp $
 */
class StudentDeepCopy extends Student {
	/**  */
	// implements Serializable
	// 如果父类实现了Serializable接口，则子类可以不用实现该接口，会自动序列化
	// private static final long serialVersionUID = 8618002500412631204L;

	/**
	 * <pre>
	 * 使用此方法深克隆时,所有相关类（父类：Student,Student中的引用类Teacher都必须实现Serializable接口，否则不能克隆）
	 * </pre>
	 *
	 * @return
	 */
	public Object deepCopy() {
		try {
			// 将对象写到流里
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(this);
			// 从流里读出来
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			ObjectInputStream oi = new ObjectInputStream(bi);
			return (oi.readObject());

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
