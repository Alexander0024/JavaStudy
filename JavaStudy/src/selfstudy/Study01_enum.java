package selfstudy;

public class Study01_enum {
	public static void main(String[] args) {
		PERSON alex = new PERSON("Alex", PERSON.SEX.MAN);
		PERSON sophia = new PERSON("Wang YiFei", PERSON.SEX.WOMAN);
		PERSON huyifei = new PERSON("Hu YiFei", PERSON.SEX.WOMAN_DOCTOR);
	}
}

class PERSON {
	public PERSON(String name, SEX sex) {
		this.name = name;
		this.sex = sex;
		System.out.println(name + " is a " + sex);
	}
	enum SEX {
		MAN, WOMAN, WOMAN_DOCTOR
	}
	String name;
	SEX sex;
}