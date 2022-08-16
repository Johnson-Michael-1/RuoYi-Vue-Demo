package test.java.org.javaboy.springbootvue.
import com.ruoyi.common.core.domain.entity.SysStudent;
import com.ruoyi.system.service.ISysStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class MyTest {
    @Autowired
    private ISysStudentService studentService;

    @Test
    public void test1(){
        List<SysStudent> sysStudents = studentService.selectAllStudents();
        for (SysStudent sysStudent : sysStudents) {
            System.out.println("sysStudent = " + sysStudent);
        }
        System.out.println("this is test1");
    }
}
