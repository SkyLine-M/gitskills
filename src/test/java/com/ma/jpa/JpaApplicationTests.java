package com.ma.jpa;

import com.ma.jpa.entity.*;
import com.ma.jpa.repository.JpaTestRepository;
import com.ma.jpa.repository.RoleRepository;
import com.ma.jpa.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaApplicationTests {

    @Autowired
    JpaTestRepository jpaTestRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    RoleRepository roleRepository;

    @Test
    void t0() {
        TUser user = new TUser();
        user.setName("fuck");
        jpaTestRepository.save(user);
    }

    @Test
    void t1() {
        //System.out.println( jpaTestRepository.findAll());

        //创建用户，目的就是为了传递模糊查询和筛选的参数
        TUser tUser = new TUser();
        tUser.setName("a");
        tUser.setAge(23);
        //当前页码
        int pageNow = 1;
        //每页显示数量
        int pageSize = 2;

        //1：定义分页对象
        //Pageable pageable = PageRequest.of(pageNow-1, pageSize);  //没有排序
        //@1：当前页码  @2：每页显示数量  @3：排序方向  @4：排序属性名称
        Pageable pageable = PageRequest.of(pageNow - 1, pageSize, Sort.Direction.ASC, "id");  //用PageRequest接收也可以
        //2：调用查询方法
        Page<TUser> page = jpaTestRepository.findAll(new Specification<TUser>() {
            @Override
            public Predicate toPredicate(Root<TUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取条件对象
                Predicate predicate = criteriaBuilder.conjunction();
                //判断用户对象是否为空
                if (tUser != null) {
                    //用户名
                    if (null != tUser.getName() && !tUser.getName().equals("")){
                        predicate.getExpressions().add(criteriaBuilder.like(root.get("name"),"%"+ tUser.getName()+"%"));
                    }
                    //年龄
                    if (null != tUser.getAge()){
                        predicate.getExpressions().add(criteriaBuilder.ge(root.get("age"),tUser.getAge()));
                    }
                }
                return predicate;
            }
        },pageable);

        List<TUser> tUsers = page.getContent();
        for (TUser user : tUsers) {
            System.out.println(user);
            
        }

    }
    @Test
    void t2() {
        System.out.println(jpaTestRepository.findByNameLike("x"));
    }

    @Test
    @Transactional
    public void t3() {
        System.out.println(jpaTestRepository.updateUserAge(23,"xiaoma"));
    }

    @Test
    @Transactional
    public void t4() {
        System.out.println(jpaTestRepository.deleteUser("aj"));
    }

    @Test
    public void testOneToOne() {
        Grade grade = new Grade();
        grade.setGradeName("1");

        Student student = new Student();
        student.setStudentName("xiaoma");
        student.setGrade(grade);

        studentRepository.save(student);
    }

    @Test
    public void testOneToOneFind() {
        System.out.println(studentRepository.findAll());
    }

    @Test
    public void testOneToMany() {
        Role role = new Role();
        role.setRoleName("管理员");

        User user = new User();
        user.setUserName("小马");
        user.setRole(role);

        User user1 = new User();
        user1.setUserName("小罗");
        user1.setRole(role);

        User user2 = new User();
        user2.setUserName("人");
        user2.setRole(role);

        //设置关系
        role.getUsers().add(user);
        role.getUsers().add(user1);
        role.getUsers().add(user2);
        roleRepository.save(role);

    }

    //查询角色id为1的用户列表
    @Test
    public void testOneToManyFind() {
        Optional<Role> users = roleRepository.findById("1e5fecf5-a48a-4645-85ba-38eb18ef2426");
        if (!Optional.empty().equals(users)) {
            System.out.println(users.get().getUsers());
        }
    }
}
