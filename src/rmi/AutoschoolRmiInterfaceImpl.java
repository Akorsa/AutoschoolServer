package rmi;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import beans.Answers;
import beans.Avto;
import beans.Categories;
import beans.Drivingschedule;
import beans.Educationtypes;
import beans.Groups;
import beans.LinkTeacherSubject;
import beans.Questions;
import beans.Schedules;
import beans.Students;
import beans.Subjects;
import beans.Teachers;
import beans.Tests;
import beans.Themes;
import beans.TypeEducation;
import beans.TypeUser;
import beans.Users;
import beans.YearOfEducation;
import dao.Impl.AnswersDAOImpl;
import dao.Impl.AvtoDAOImpl;
import dao.Impl.CategoriesDAOImpl;
import dao.Impl.DrivingscheduleDAOImpl;
import dao.Impl.EducationtypesDAOImpl;
import dao.Impl.GroupDAOImpl;
import dao.Impl.LinkTeacherSubjectDAOImpl;
import dao.Impl.QuestionDAOImpl;
import dao.Impl.ScheduleDAOImpl;
import dao.Impl.StudentDAOImpl;
import dao.Impl.SubjectDAOImpl;
import dao.Impl.TeacherDAOImpl;
import dao.Impl.TestDAOImpl;
import dao.Impl.ThemeDAOImpl;
import dao.Impl.TypeEducationDAOImpl;
import dao.Impl.TypeUserDAOImpl;
import dao.Impl.UserDAOImpl;
import dao.Impl.YearOfEducationDAOImpl;

public class AutoschoolRmiInterfaceImpl extends UnicastRemoteObject implements
	AutoschoolRmiInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3486203846367615861L;
	/**
	 * 
	 */

	public AutoschoolRmiInterfaceImpl() throws RemoteException {
		super();
	}
	
	public Collection<Categories> getCategoryList() throws RemoteException {
		
		CategoriesDAOImpl categories = new CategoriesDAOImpl();
		Collection<Categories> o;
		o = categories.listCategories();
		return o;
	}
	
	public Collection<YearOfEducation> getYearOfEducation() throws RemoteException {
		
		YearOfEducationDAOImpl year = new YearOfEducationDAOImpl();
		Collection<YearOfEducation> o = null ;
		o = year.listYearOfEducations();
		return o;
	}

	@Override
	public List<Groups> getGroupYearEdu(String year)
			throws RemoteException {
		GroupDAOImpl group = new GroupDAOImpl();
		List<Groups> o = null;
		o = group.getGroupOnYear(year);
		return o;
	}

	@Override
	public List<Students> getStudentsFromGroup(int groupid)
			throws RemoteException {
		StudentDAOImpl sdi = new StudentDAOImpl();
		List<Students>  students = null;
		students = sdi.getStudentsFromGroup(groupid);
		return students;
		
	}

	@Override
	public Collection<Groups> getGroupList() throws RemoteException {
		GroupDAOImpl group = new GroupDAOImpl();
		List<Groups> o = null;
		o = (List<Groups>) group.listGroup();
		return o;
	}

	@Override
	public List<TypeEducation> getTypeEducation() throws RemoteException {
		TypeEducationDAOImpl typeEd = new TypeEducationDAOImpl();
		List<TypeEducation> o = null;
		o = (List<TypeEducation>) typeEd.listTypeEducations();
		return o;
	}

	@Override
	public void insertStudent(Students s) throws RemoteException {
		StudentDAOImpl sdi = new StudentDAOImpl();
		sdi.addStudent(s);
		
	}

	@Override
	public void updateStudent(Students s) throws RemoteException {
		StudentDAOImpl sdi = new StudentDAOImpl();
		sdi.updateStudent(s);
		
	}

	@Override
	public void deleteBet(Students s) throws RemoteException {
		StudentDAOImpl sdi = new StudentDAOImpl();
		sdi.deleteStudent(s);
		
	}

	@Override
	public void updateStGr(Groups gr, int id) throws RemoteException {
		StudentDAOImpl sdi = new StudentDAOImpl();
		sdi.updateStGr(gr,id);
		
	}

	@Override
	public List<Educationtypes> getEducationtypes() throws RemoteException {
		EducationtypesDAOImpl typeEd = new EducationtypesDAOImpl();
		List<Educationtypes> o = null;
		o = (List<Educationtypes>) typeEd.listEducationtype();
		return o;
	}

	@Override
	public void insertGrup(Groups g) throws RemoteException {
		GroupDAOImpl sdi = new GroupDAOImpl();
		sdi.addGroup(g);
		
	}

	@Override
	public void updateGrup(Groups s1) throws RemoteException {
		GroupDAOImpl sdi = new GroupDAOImpl();
		sdi.updateGrup(s1);
		
	}

	@Override
	public void deleteBet(Groups s) throws RemoteException {
		GroupDAOImpl sdi = new GroupDAOImpl();
		sdi.removeGroup(s.getGroupid());
		
	}

	@Override
	public List<Students> searchStudByFIO(String[] s) throws RemoteException {
		StudentDAOImpl sdi = new StudentDAOImpl();
		List<Students>  students = null;
		students = sdi.getStudentsByFIO(s);
		return students;
	}

	@Override
	public List<Students> searchStudByNomSvid(String s) throws RemoteException {
		StudentDAOImpl sdi = new StudentDAOImpl();
		List<Students>  students = null;
		students = sdi.getStudentsByNomSvid(s);
		return students;
	}

	@Override
	public List<Students> searchStudByDate(Date d) throws RemoteException {
		StudentDAOImpl sdi = new StudentDAOImpl();
		List<Students>  students = null;
		students = sdi.getStudentsByDate(d);
		return students;
	}

	@Override
	public List<Teachers> getTeacherList() throws RemoteException {
		TeacherDAOImpl teach = new TeacherDAOImpl();
		List<Teachers> o = null;
		o = (List<Teachers>) teach.listTeachers();
		return o;
	}
	
	@Override
	public List<Teachers> getMastersList() throws RemoteException {
		TeacherDAOImpl teach = new TeacherDAOImpl();
		List<Teachers> o = null;
		o = (List<Teachers>) teach.listMasters();
		return o;
	}

	@Override
	public void insertPrepod(Teachers g) throws RemoteException {
		TeacherDAOImpl sdi = new TeacherDAOImpl();
		sdi.addTeacher(g);
		
		
	}

	@Override
	public void updateTeachers(Teachers s1) throws RemoteException {
		TeacherDAOImpl sdi = new TeacherDAOImpl();
		sdi.updateTeacher(s1);
		
	}

	@Override
	public void deleteTeacher(Teachers s) throws RemoteException {
		TeacherDAOImpl sdi = new TeacherDAOImpl();
		sdi.removeTeacher(s.getPrepodid());
		
	}

	@Override
	public List<Avto> getAvtoList() throws RemoteException {
		AvtoDAOImpl teach = new AvtoDAOImpl();
		List<Avto> o = null;
		o = (List<Avto>) teach.listAvto();
		return o;
	}

	@Override
	public void insertAvto(Avto g) throws RemoteException {
		AvtoDAOImpl sdi = new AvtoDAOImpl();
		sdi.addAvto(g);
		
	}

	@Override
	public void updateAvto(Avto s1) throws RemoteException {
		AvtoDAOImpl sdi = new AvtoDAOImpl();
		sdi.updateAvto(s1);
		
	}

	@Override
	public void deleteAvto(Avto s) throws RemoteException {
		AvtoDAOImpl sdi = new AvtoDAOImpl();
		sdi.removeAvto(s.getAvtoid());
		
	}

	@Override
	public void insertCateg(Categories g) throws RemoteException {
		CategoriesDAOImpl sdi = new CategoriesDAOImpl();
		sdi.addCategories(g);
		
	}

	@Override
	public void updateCateg(Categories s1) throws RemoteException {
		CategoriesDAOImpl sdi = new CategoriesDAOImpl();
		sdi.updateCategories(s1);
		
	}

	@Override
	public void deleteCateg(Categories s) throws RemoteException {
		CategoriesDAOImpl sdi = new CategoriesDAOImpl();
		sdi.removeCategories(s.getKategoriesid());
		
	}

	@Override
	public List<Subjects> getSubjectList() throws RemoteException {
		SubjectDAOImpl teach = new SubjectDAOImpl();
		List<Subjects> o = null;
		o = (List<Subjects>) teach.listSubjects();
		return o;
	}

	@Override
	public void insertSubject(Subjects g) throws RemoteException {
		SubjectDAOImpl sdi = new SubjectDAOImpl();
		sdi.addSubject(g);
		
	}

	@Override
	public void deleteSubject(Subjects s) throws RemoteException {
		SubjectDAOImpl sdi = new SubjectDAOImpl();
		sdi.removeSubject(s.getSubjectId());
		
	}

	@Override
	public void updateSubject(Subjects s1) throws RemoteException {
		SubjectDAOImpl sdi = new SubjectDAOImpl();
		sdi.updateSubj(s1);
		
	}

	@Override
	public List<Themes> getThemeList() throws RemoteException {
		ThemeDAOImpl teach = new ThemeDAOImpl();
		List<Themes> o = null;
		o = (List<Themes>) teach.listThemes();
		return o;
	}

	@Override
	public void deleteTheme(Themes s) throws RemoteException {
		ThemeDAOImpl sdi = new ThemeDAOImpl();
		sdi.removeTheme(s.getThemeId());
		
	}

	@Override
	public void updateTheme(Themes s1) throws RemoteException {
		ThemeDAOImpl sdi = new ThemeDAOImpl();
		sdi.updateTheme(s1);
		
		
	}

	@Override
	public void insertTheme(Themes g) throws RemoteException {
		ThemeDAOImpl sdi = new ThemeDAOImpl();
		sdi.addTheme(g);
		
	}

	@Override
	public List<Users> getUsersList() throws RemoteException {
		UserDAOImpl teach = new UserDAOImpl();
		List<Users> o = null;
		o = (List<Users>) teach.listUsers();
		return o;
	}

	@Override
	public List<TypeUser> getRolesList() throws RemoteException {
		TypeUserDAOImpl tud = new TypeUserDAOImpl();
		List<TypeUser> o = null;
		o = (List<TypeUser>) tud.listTypeUsers();
		return o;
	}

	@Override
	public void insertUser(Users g) throws RemoteException {
		UserDAOImpl sdi = new UserDAOImpl();
		sdi.addUser(g);
		
	}

	@Override
	public void updateUser(Users s1) throws RemoteException {
		UserDAOImpl sdi = new UserDAOImpl();
		sdi.updateUser(s1);
	}

	@Override
	public void deleteUser(Users s) throws RemoteException {
		UserDAOImpl sdi = new UserDAOImpl();
		sdi.removeUser(s.getUserid());
	}

	@Override
	public void insertRole(TypeUser g) throws RemoteException {
		TypeUserDAOImpl sdi = new TypeUserDAOImpl();
		sdi.addTypeUser(g);
		
	}

	@Override
	public void updateRole(TypeUser s1) throws RemoteException {
		TypeUserDAOImpl sdi = new TypeUserDAOImpl();
		sdi.updateUser(s1);
		
	}

	@Override
	public void deleteRole(TypeUser s) throws RemoteException {
		TypeUserDAOImpl sdi = new TypeUserDAOImpl();
		sdi.removeTypeUser(s.getUsertypeId());
		
	}

	@Override
	public List<Answers> getAnswersByQID(int id) throws RemoteException {
		AnswersDAOImpl adi = new AnswersDAOImpl();
		List<Answers> o = null;
		o = (List<Answers>) adi.getAnswersByQId(id);
		return o;
	}

	@Override
	public List<Questions> getQuestList() throws RemoteException {
		QuestionDAOImpl qdi = new QuestionDAOImpl();
		List<Questions> o = null;
		o = (List<Questions>) qdi.listQuestions();
		return o;
	}

	@Override
	public void deleteQuestion(Questions s) throws RemoteException {
		QuestionDAOImpl adi = new QuestionDAOImpl();
		adi.removeQuestion(s.getQuestionsId());
		
	}

	@Override
	public void updateQuestion(Questions g) throws RemoteException {
		QuestionDAOImpl sdi = new QuestionDAOImpl();
		sdi.updateQuestion(g);
		
	}

	@Override
	public void insertQuestion(Questions g) throws RemoteException {
		QuestionDAOImpl sdi = new QuestionDAOImpl();
		sdi.addQuestion(g);
		
	}

	@Override
	public void insertAnswers(List<Answers> a) throws RemoteException {
		AnswersDAOImpl adi = new AnswersDAOImpl(); 
		for (Answers answers : a) {
	            adi.addAnswers(answers);
	        }
	}

	@Override
	public void updateAnswers(List<Answers> a) throws RemoteException {
		AnswersDAOImpl adi = new AnswersDAOImpl(); 
		for (Answers answers : a) {
	            adi.updateAnswers(answers);
	        }
		
	}

	@Override
	public Questions getQuestById(int id) throws RemoteException {
		QuestionDAOImpl sdi = new QuestionDAOImpl();
		Questions q = sdi.getQuestById(id);
		return q;
	}
	
	@Override
	public List<Students> getStudentsFromGroup2(int groupid)
			throws RemoteException {
		StudentDAOImpl sdi = new StudentDAOImpl();
		List<Students>  students = null;
		students = sdi.getStudentsFromGroup2(groupid);
		return students;
		
	}

	@Override
	public List<Schedules> getRaspList(int i,Date dt1,Date dt2) throws RemoteException {
		ScheduleDAOImpl sdi = new ScheduleDAOImpl();
		List<Schedules>  students = null;
		students = sdi.getScheduleFromGroup(i,dt1,dt2);
		return students;
	}

	@Override
	public List<Themes> getThemeListBySubj(int id) throws RemoteException {
		ThemeDAOImpl teach = new ThemeDAOImpl();
		List<Themes> o = null;
		o = (List<Themes>) teach.listThemesBySubj(id);
		return o;
	}

	@Override
	public void insertRasp(Schedules g) throws RemoteException {
		ScheduleDAOImpl sdi = new ScheduleDAOImpl();
		sdi.addSchedule(g);
	}

	@Override
	public void updateRasp(Schedules sh) throws RemoteException {
		ScheduleDAOImpl sdi = new ScheduleDAOImpl();
		sdi.updateSchedule(sh);
		
	}

	@Override
	public void deleteRasp(Schedules s) throws RemoteException {
		ScheduleDAOImpl sdi = new ScheduleDAOImpl();
		sdi.removeSchedule(s.getScheduleId());
		
	}

	@Override
	public List<Users> getUsersByLogin(String text) throws RemoteException {
		UserDAOImpl teach = new UserDAOImpl();
		List<Users> o = null;
		o = (List<Users>) teach.getUserByLogin(text);
		return o;
	}

	@Override
	public TypeUser getTypeUserById(int i) throws RemoteException {
		TypeUserDAOImpl tud = new TypeUserDAOImpl();
		TypeUser o = null;
		o =  tud.getRolesById(i);
		return o;
	}

	@Override
	public List<Questions> getQuestionsByTheme(int id) throws RemoteException {
		QuestionDAOImpl sdi = new QuestionDAOImpl();
		List<Questions> o = null;
		o = (List<Questions>)sdi.getQuestByTheme(id);
		return o;
	}

	@Override
	public Students getStudentByUser(int id) throws RemoteException {
		StudentDAOImpl sdi = new StudentDAOImpl();
		Students o = null;
		o = sdi.getStudentByUser(id);
		return o;
	}

	@Override
	public List<Subjects> getSubjectByName(String string)
			throws RemoteException {
		SubjectDAOImpl teach = new SubjectDAOImpl();
		List<Subjects> o = null;
		o = (List<Subjects>) teach.getSubjectByName(string);
		return o;
	}

	@Override
	public List<LinkTeacherSubject> getZakrList() throws RemoteException {
		LinkTeacherSubjectDAOImpl ltd = new LinkTeacherSubjectDAOImpl();
		List<LinkTeacherSubject> o = null;
		o = (List<LinkTeacherSubject>) ltd.listLinkTeacherSubjects();
		return o;
	}

	@Override
	public void insertZakr(LinkTeacherSubject g) throws RemoteException {
		LinkTeacherSubjectDAOImpl ltd = new LinkTeacherSubjectDAOImpl();
		ltd.addLinkTeacherSubject(g);	
	}

	@Override
	public void deleteZakr(LinkTeacherSubject s) throws RemoteException {
		LinkTeacherSubjectDAOImpl ltd = new LinkTeacherSubjectDAOImpl();
		ltd.addLinkTeacherSubject(s);
		
	}

	@Override
	public void updateZakr(LinkTeacherSubject s1) throws RemoteException {
		LinkTeacherSubjectDAOImpl ltd = new LinkTeacherSubjectDAOImpl();
		ltd.removeLinkTeacherSubject(s1.getId());
		
	}

	@Override
	public List<LinkTeacherSubject> getZakrByTeacher(Teachers teacher)
			throws RemoteException {
		LinkTeacherSubjectDAOImpl ltd = new LinkTeacherSubjectDAOImpl();
		List<LinkTeacherSubject> o = null;
		o = (List<LinkTeacherSubject>) ltd.getZakrByTeacher(teacher);
		return o;
	}

	@Override
	public Themes getThemeById(int themeId) throws RemoteException {
		ThemeDAOImpl sdi = new ThemeDAOImpl();
		Themes o = null;
		o = sdi.getThemeById(themeId);
		return o;
	}

	@Override
	public YearOfEducation getYearById(int yearOfEducationId)
			throws RemoteException {
		YearOfEducationDAOImpl sdi = new YearOfEducationDAOImpl();
		YearOfEducation o = null;
		o = sdi.getYearById(yearOfEducationId);
		return o;
	}

	@Override
	public List<Groups> getCurrentGroupList() throws RemoteException {
		GroupDAOImpl group = new GroupDAOImpl();
		List<Groups> o = null;
		o = (List<Groups>) group.getCurrentGroupList();
		return o;
	}

	@Override
	public List<Questions> getQuestByLvl(double level) throws RemoteException {
		QuestionDAOImpl qdi = new QuestionDAOImpl();
		List<Questions> o = null;
		o = (List<Questions>) qdi.getQuestByLvl(level);
		return o;
	}

	@Override
	public void addTest(Tests test) throws RemoteException {
		TestDAOImpl ltd = new TestDAOImpl();
		ltd.addTest(test);
		
	}

	@Override
	public List<Drivingschedule> getDriveList(int groupid,Date dt1,Date dt2)
			throws RemoteException {
		DrivingscheduleDAOImpl qdi = new DrivingscheduleDAOImpl();
		List<Drivingschedule> o = null;
		o = (List<Drivingschedule>) qdi.getDriveByGroup(groupid,dt1,dt2);
		return o;
	}

	@Override
	public void insertDrive(Drivingschedule sh) throws RemoteException {
		DrivingscheduleDAOImpl ltd = new DrivingscheduleDAOImpl();
		ltd.addDrivingschedule(sh);
		
		
	}

	@Override
	public void updateDrive(Drivingschedule sh) throws RemoteException {
		DrivingscheduleDAOImpl ltd = new DrivingscheduleDAOImpl();
		ltd.updateDrivingschedule(sh);
		
	}

	@Override
	public void deleteDrive(Drivingschedule s) throws RemoteException {
		DrivingscheduleDAOImpl ltd = new DrivingscheduleDAOImpl();
		ltd.removeDrivingschedule(s.getIddrivingshedule());
		
		
	}

	@Override
	public List<Drivingschedule> getDriveByTeacherAndTime(int teacherid,
			Date t1, Date t2) throws RemoteException {
		DrivingscheduleDAOImpl qdi = new DrivingscheduleDAOImpl();
		List<Drivingschedule> o = null;
		o = (List<Drivingschedule>) qdi.getDriveByTeacherAndTime(teacherid, t1, t2);
		return o;
	}

	@Override
	public List<Avto> getAvtoByTime(Date dt,Date dt2) throws RemoteException {
		AvtoDAOImpl qdi = new AvtoDAOImpl();
		List<Avto> o = null;
		o = (List<Avto>) qdi.getAvtoByTime(dt,dt2);
		return o;
	}

	@Override
	public List getDriveStat(int id,Date t1,Date t2) throws RemoteException {
		DrivingscheduleDAOImpl qdi = new DrivingscheduleDAOImpl();
		List o = null;
		o = qdi.getDriveStat(id,t1,t2);
		return o;
		
	}

	@Override
	public List<Tests> getStudStat(int idstudent) throws RemoteException {
		TestDAOImpl qdi = new TestDAOImpl();
		List<Tests> o = null;
		o = qdi.getStat(idstudent);
		return o;
	}

	@Override
	public List<Avto> getAvtoByTime2(Date dt, Date dt2) throws RemoteException {
		AvtoDAOImpl qdi = new AvtoDAOImpl();
		List<Avto> o = null;
		o = (List<Avto>) qdi.getAvtoByTime2(dt,dt2);
		return o;
	}

	@Override
	public List<Students> getSvid() throws RemoteException {
		StudentDAOImpl qdi = new StudentDAOImpl();
		List<Students> o = null;
		o = (List<Students>) qdi.getSvid();
		return o;
	}

	@Override
	public List<Integer> getHours(int id) throws RemoteException {
		DrivingscheduleDAOImpl qdi = new DrivingscheduleDAOImpl();
		List<Integer> o = null;
		o = qdi.getHours(id);
		return o;
	}

		

}
