package providers;

import Enums.SchoolStudyType;
import characters.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StudentsProvider {
    final static String [] zanstySubjects = {"Math", "Science", "Chemistry", "Physic", "Arabic", "English","Kurdish"};
    final static String []  WezhaiySubjects = {"Math", "Mezhu", "Abury", "Jugraphia", "Arabic", "English","Kurdish"};
    final static String [] aynySubjects = {"Math", "FiqeIslami", "FaraizQuraan", "UsulFiqe", "Arabic", "English","Kurdish"};
        public static List<Student> zanstyStudents = Arrays.asList(
                new Student(
                        "S00001",
                        "Twana Abubakr Abdulla",
                        "12345678",
                        "Male",
                        "20-4-2003",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(96.00, 100.00, 88.00, 100.00, 98.00, 98.00, 100.00)
                        ),
                new Student(
                        "S00002",
                        "Emad Adil Qadr",
                        "12345678",
                        "Male",
                        "10-5-2002",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(98.00, 90.00, 94.00, 96.00, 92.00, 98.00, 100.00)
                ),
                new Student(
                        "S00003",
                        "Shallaw Fars Ali",
                        "12345678",
                        "Male",
                        "13-10-2001",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(100.00, 100.00, 92.00, 96.00, 96.00, 94.00, 76.00)
                ),
                new Student(
                        "S00004",
                        "Chea Jasim Antar",
                        "12345678",
                        "Male",
                        "1-4-2001",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(92.00, 100.00, 90.00, 80.00, 78.00, 98.00, 100.00)
                ),
                new Student(
                        "S00005",
                        "Dlnya Dlzar Botan",
                        "12345678",
                        "FeMale",
                        "20-5-2003",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(80.00, 64.00, 58.00, 50.00, 70.00, 68.00, 52.00)
                ),
                new Student(
                        "S00006",
                        "Tara Hama Abdulla",
                        "12345678",
                        "FeMale",
                        "25-4-2002",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(58.00, 70.00, 88.00, 60.00, 78.00, 58.00, 80.00)
                ),
                new Student(
                        "S00007",
                        "Zana Abubakr Ali",
                        "12345678",
                        "Male",
                        "20-6-2003",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(96.00, 90.00, 88.00, 80.00, 98.00, 88.00, 100.00)
                ),
                new Student(
                        "S00008",
                        "Shnyar Awni Shema",
                        "12345678",
                        "FeMale",
                        "1-4-2001",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(96.00, 100.00, 98.00, 100.00, 98.00, 98.00, 100.00)
                ),
                new Student(
                        "S00009",
                        "Zryan Dno Abdulla",
                        "12345678",
                        "Male",
                        "1-6-2003",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(56.00, 70.00, 68.00, 60.00, 58.00, 68.00, 70.00)
                ),
                new Student(
                        "S00010",
                        "Alan Hawez Hama",
                        "12345678",
                        "Male",
                        "10-10-2001",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(76.00, 70.00, 88.00, 90.00, 88.00, 68.00, 70.00)
                ),
                new Student(
                        "S00011",
                        "Shara Shero Las",
                        "12345678",
                        "FeMale",
                        "20-4-2003",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(96.00, 100.00, 88.00, 90.00, 98.00, 98.00, 100.00)
                ),
                new Student(
                        "S00012",
                        "Twana Jalal Kawa",
                        "12345678",
                        "Male",
                        "20-7-2002",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(96.00, 100.00, 82.00, 100.00, 90.00, 98.00, 100.00)
                ),
                new Student(
                        "S00013",
                        "Sara Aziz Khidir",
                        "12345678",
                        "FeMale",
                        "2-4-2001",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(96.00, 100.00, 88.00, 100.00, 98.00, 98.00, 100.00)
                ),
                new Student(
                        "S00014",
                        "Las Said Zana",
                        "12345678",
                        "Male",
                        "20-12-2003",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(96.00, 100.00, 88.00, 86.00, 94.00, 88.00,90.00)
                ),
                new Student(
                        "S00015",
                        "Zina Abdull Karim",
                        "12345678",
                        "FeMale",
                        "20-1-2000",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(96.00, 100.00, 98.00, 100.00, 98.00, 98.00, 100.00)
                ),
                new Student(
                        "S00016",
                        "Karzan Saadi Hama",
                        "12345678",
                        "Male",
                        "20-3-2003",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(66.00, 54.00, 70.00, 50.00, 68.00, 78.00, 70.00)
                ),
                new Student(
                        "S00017",
                        "Klara Abubakr Andam",
                        "12345678",
                        "FeMale",
                        "2-9-2003",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(96.00, 90.00, 88.00, 100.00, 98.00, 98.00, 90.00)
                ),
                new Student(
                        "S00018",
                        "Asma Ari Ali",
                        "12345678",
                        "FeMale",
                        "20-10-2000",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(66.00, 60.00, 58.00, 50.00, 78.00, 68.00, 90.00)
                ),
                new Student(
                        "S00019",
                        "Ala Sami Abdulla",
                        "12345678",
                        "FeMale",
                        "25-9-2003",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(96.00, 100.00, 88.00, 90.00, 98.00, 98.00, 92.00)
                ),
                new Student(
                        "S00020",
                        "Dalya Abubakr Zrebar",
                        "12345678",
                        "FeMale",
                        "26-3-2001",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(56.00, 70.00, 68.00, 80.00, 58.00, 68.00, 80.00)
                ),
                new Student(
                        "S00021",
                        "Namiq Sdiq Abdulla",
                        "12345678",
                        "Male",
                        "20-1-2000",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(96.00, 100.00, 98.00, 100.00, 98.00, 98.00, 100.00)
                ),
                new Student(
                        "S00022",
                        "Dana Abuaala Abdulla",
                        "12345678",
                        "Male",
                        "20-12-2003",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(76.00, 50.00, 58.00, 60.00, 58.00, 68.00, 50.00)
                ),
                new Student(
                        "S00023",
                        "Yallda Edo Kibar",
                        "12345678",
                        "FeMale",
                        "20-2-2002",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(98.00, 100.00, 98.00, 100.00, 98.00, 98.00, 100.00)
                ),
                new Student(
                        "S00024",
                        "Matin Haji Nihhmala",
                        "12345678",
                        "Male",
                        "21-3-2003",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(66.00, 70.00, 88.00, 90.00, 68.00, 78.00, 80.00)
                ),
                new Student(
                        "S00025",
                        "Bilia Muhammad Ali",
                        "12345678",
                        "FeMale",
                        "20-11-2003",
                        SchoolStudyType.Zansty,
                        Arrays.asList(zanstySubjects),
                        Arrays.asList(80.00, 70.00, 88.00, 90.00, 88.00, 78.00, 90.00)
                )
        );

    public static List<Student> wezhaiyStudents = Arrays.asList(
            new Student(
                    "S00026",
                    "Lana Amir Hassan",
                    "12345678",
                    "FeMale",
                    "2-10-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(96.00, 90.00, 88.00, 90.00, 98.00, 98.00, 100.00)
            ),

            new Student(
                    "S00027",
                    "Amir Shukr Ahmed",
                    "12345678",
                    "Male",
                    "2-1-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(56.00, 60.00, 58.00, 70.00, 68.00, 58.00, 70.00)
            ),
            new Student(
                    "S00028",
                    "Chawan Aziz Kawa",
                    "12345678",
                    "FeMale",
                    "2-8-2002",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(96.00, 90.00, 88.00, 90.00, 92.00, 98.00, 90.00)
            ),
            new Student(
                    "S00029",
                    "Maria Fars Ali",
                    "12345678",
                    "FeMale",
                    "18-10-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(90.00, 86.00, 88.00, 80.00, 88.00, 88.00, 80.00)
            ),
            new Student(
                    "S00030",
                    "Shnrwe Shayda Karwan",
                    "12345678",
                    "FeMale",
                    "2-10-2001",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(66.00, 60.00, 58.00, 60.00, 58.00, 68.00, 70.00)
            ),
            new Student(
                    "S00031",
                    "Karmand Dara Botan",
                    "12345678",
                    "Male",
                    "10-10-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(66.00, 60.00, 68.00, 70.00, 58.00, 68.00, 60.00)
            ),
            new Student(
                    "S00032",
                    "Zhewar Hawez Hamad",
                    "12345678",
                    "Male",
                    "10-9-2000",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(76.00, 70.00, 78.00, 70.00, 78.00, 68.00, 60.00)
            ),
            new Student(
                    "S00033",
                    "Shazad Fars Ali",
                    "12345678",
                    "Male",
                    "9-5-2001",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(96.00, 90.00, 88.00, 90.00, 98.00, 98.00, 100.00)
            ),
            new Student(
                    "S00034",
                    "Shanya Haji Hamad",
                    "12345678",
                    "FeMale",
                    "10-1-2002",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(76.00, 60.00, 88.00, 70.00, 78.00, 78.00, 70.00)
            ),
            new Student(
                    "S00035",
                    "Dara Kawa Botan",
                    "12345678",
                    "Male",
                    "1-10-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(56.00, 50.00, 58.00, 50.00, 58.00, 58.00, 50.00)
            ),
            new Student(
                    "S00036",
                    "Huday Salm Husen",
                    "12345678",
                    "Male",
                    "10-6-2000",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(86.00, 90.00, 88.00, 70.00, 78.00, 88.00, 90.00)
            ),
            new Student(
                    "S00037",
                    "Bayan Ebrahim Khidir",
                    "12345678",
                    "FeMale",
                    "2-1-2001",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(56.00, 60.00, 58.00, 50.00, 58.00, 58.00, 60.00)
            ),
            new Student(
                    "S00038",
                    "Znar Zayto Zana",
                    "12345678",
                    "FeMale",
                    "9-10-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(66.00, 60.00, 68.00, 60.00, 58.00, 68.00, 60.00)
            ),
            new Student(
                    "S00039",
                    "Kamal Kawa Shamal",
                    "12345678",
                    "Male",
                    "11-1-2001",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(66.00, 50.00, 68.00, 70.00, 58.00, 68.00, 60.00)
            ),
            new Student(
                    "S00040",
                    "Ahmed Botan Syawash",
                    "12345678",
                    "Male",
                    "30-10-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(66.00, 60.00, 68.00, 80.00, 58.00, 88.00, 60.00)
            ),
            new Student(
                    "S00041",
                    "Sana Jamal Khidir",
                    "12345678",
                    "FeMale",
                    "30-1-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(96.00, 90.00, 88.00, 80.00, 88.00, 88.00, 90.00)
            ),
            new Student(
                    "S00042",
                    "Jalal Soran Kawa",
                    "12345678",
                    "Male",
                    "30-3-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(56.00, 50.00, 58.00, 50.00, 58.00, 58.00, 60.00)
            ),
            new Student(
                    "S00043",
                    "Sima Kaiwan Haji",
                    "12345678",
                    "FeMale",
                    "30-5-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(66.00, 60.00, 68.00, 70.00, 58.00, 68.00, 60.00)
            ),
            new Student(
                    "S00044",
                    "Khidir Awlla Adam",
                    "12345678",
                    "Male",
                    "8-12-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(76.00, 70.00, 68.00, 80.00, 78.00, 68.00, 80.00)
            ),
            new Student(
                    "S00045",
                    "Vana Shaho Ali",
                    "12345678",
                    "FeMale",
                    "30-2-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(86.00, 80.00, 78.00, 80.00, 88.00, 88.00, 80.00)
            ),
            new Student(
                    "S00046",
                    "Khairwlla Shema Shamheddin",
                    "12345678",
                    "Male",
                    "30-12-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(96.00, 90.00, 88.00, 90.00, 88.00, 88.00, 100.00)
            ),
            new Student(
                    "S00047",
                    "Qadir Agha Hussen",
                    "12345678",
                    "Male",
                    "3-11-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(56.00, 60.00, 68.00, 80.00, 58.00, 68.00, 60.00)
            ),
            new Student(
                    "S00048",
                    "Lya Redar Jalal",
                    "12345678",
                    "FeMale",
                    "5-12-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(66.00, 60.00, 68.00, 70.00, 58.00, 88.00, 60.00)
            ),
            new Student(
                    "S00049",
                    "Abdullrahman Abdulla Ali",
                    "12345678",
                    "Male",
                    "20-1-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(76.00, 70.00, 68.00, 80.00, 78.00, 88.00, 70.00)
            ),
            new Student(
                    "S00050",
                    "Natalia Ali Hamad",
                    "12345678",
                    "FeMale",
                    "1-10-2003",
                    SchoolStudyType.Wezhaiy,
                    Arrays.asList( WezhaiySubjects),
                    Arrays.asList(90.00, 90.00, 88.00, 90.00, 94.00, 98.00, 90.00)
            )
    );

    public static List<Student> AynyStudents = Arrays.asList(
            new Student(
                    "S00051",
                    "Rahman Raza Awlla",
                    "12345678",
                    "Male",
                    "2-4-2003",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(96.00, 90.00, 88.00, 80.00, 88.00, 88.00, 80.00)
            ),
            new Student(
                    "S00052",
                    "Ruqia Raza Ali",
                    "12345678",
                    "FeMale",
                    "10-1-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 80.00, 84.00, 86.00, 82.00, 88.00, 80.00)
            ),
            new Student(
                    "S00053",
                    "Ali Hamad Ali",
                    "12345678",
                    "Male",
                    "11-1-2001",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(78.00, 70.00, 74.00, 76.00, 82.00, 78.00, 70.00)
            ),
            new Student(
                    "S00054",
                    "Amir Hassan Andam",
                    "12345678",
                    "Male",
                    "10-5-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(68.00, 60.00, 64.00, 56.00, 62.00, 68.00, 60.00)
            ),
            new Student(
                    "S00055",
                    "Kalthum Ahmed Kawa",
                    "12345678",
                    "FeMale",
                    "10-7-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 80.00, 88.00, 86.00, 72.00, 88.00, 80.00)
            ),
            new Student(
                    "S00056",
                    "Mustafa Mahmud Dana",
                    "12345678",
                    "Male",
                    "10-4-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 80.00, 84.00, 86.00, 72.00, 78.00, 80.00)
            ),
            new Student(
                    "S00057",
                    "Ebrahim Brwa Ali",
                    "12345678",
                    "Male",
                    "1-9-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(58.00, 60.00, 64.00, 66.00, 62.00, 78.00, 60.00)
            ),
            new Student(
                    "S00058",
                    "Saya Shema Karim",
                    "12345678",
                    "FeMale",
                    "19-9-2001",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 80.00, 84.00, 96.00, 92.00, 88.00, 80.00)
            ),
            new Student(
                    "S00060",
                    "Asaad Saadi Faruq",
                    "12345678",
                    "Male",
                    "10-5-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 80.00, 84.00, 86.00, 82.00,98.00, 80.00)
            ),
            new Student(
                    "S00061",
                    "Jawahir Agha Hikmat",
                    "12345678",
                    "FeMale",
                    "20-7-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(78.00, 80.00, 84.00, 96.00, 82.00,98.00, 80.00)
            ),
            new Student(
                    "S00062",
                    "Hikmat Awlaa Mala",
                    "12345678",
                    "Male",
                    "1-5-2000",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 80.00, 84.00, 66.00, 82.00,70.00, 80.00)
            ),
            new Student(
                    "S00063",
                    "Saadi Amir Faruq",
                    "12345678",
                    "Male",
                    "17-9-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 80.00, 84.00, 86.00, 82.00,98.00, 90.00)
            ),
            new Student(
                    "S00064",
                    "Fakhri Shwre Shwan",
                    "12345678",
                    "FeMale",
                    "10-2-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(68.00, 70.00, 64.00, 66.00, 82.00,68.00, 70.00)
            ),
            new Student(
                    "S00065",
                    "Hamza Hassan Usu",
                    "12345678",
                    "Male",
                    "10-4-2000",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 80.00, 84.00, 86.00, 72.00,78.00, 80.00)
            ),
            new Student(
                    "S00066",
                    "Osman Salah Faruq",
                    "12345678",
                    "Male",
                    "13-5-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(58.00, 80.00, 84.00, 56.00, 82.00,58.00, 80.00)
            ),
            new Student(
                    "S00067",
                    "Kmaran Faruq Karzan",
                    "12345678",
                    "Male",
                    "12-5-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 80.00, 84.00, 86.00, 82.00,78.00, 80.00)
            ),
            new Student(
                    "S00068",
                    "Zainab Zana Kawa",
                    "12345678",
                    "FeMale",
                    "14-5-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(68.00, 80.00, 64.00, 66.00, 82.00,58.00, 70.00)
            ),
            new Student(
                    "S00069",
                    "Bashar Aziz Azad ",
                    "12345678",
                    "Male",
                    "3-5-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 80.00, 84.00, 86.00, 82.00,68.00, 50.00)
            ),
            new Student(
                    "S00070",
                    "Layla Lawa Barzan",
                    "12345678",
                    "FeMale",
                    "2-8-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 70.00, 84.00, 86.00, 82.00,58.00, 80.00)
            ),
            new Student(
                    "S00071",
                    "Luqman Jamih Jamal",
                    "12345678",
                    "Male",
                    "2-11-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 70.00, 84.00, 86.00, 92.00,58.00, 80.00)
            ),
            new Student(
                    "S00072",
                    "Salim Samir Kamal",
                    "12345678",
                    "Male",
                    "21-9-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 80.00, 84.00, 86.00, 82.00,88.00, 80.00)
            ),
            new Student(
                    "S00073",
                    "Khalid Fakhraddin Barzan",
                    "12345678",
                    "Male",
                    "22-8-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 70.00, 84.00, 76.00, 82.00,88.00, 80.00)
            ),
            new Student(
                    "S00074",
                    "Fatima Mustafa Nuraddin",
                    "12345678",
                    "FeMale",
                    "2-2-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 80.00, 84.00, 86.00, 82.00,58.00, 80.00)
            ),
            new Student(
                    "S00075",
                    "Sinam Aziz Muhammad",
                    "12345678",
                    "FeMale",
                    "2-9-2002",
                    SchoolStudyType.Ayny,
                    Arrays.asList(aynySubjects),
                    Arrays.asList(88.00, 90.00, 94.00, 96.00, 92.00,98.00, 80.00)
            )
    );
}

