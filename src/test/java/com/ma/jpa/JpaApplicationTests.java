package com.ma.jpa;

import com.ma.jpa.bean.JpaPageTemplateBean;
import com.ma.jpa.bean.ResultTemplateBean;
import com.ma.jpa.common.query.JpaSpec;
import com.ma.jpa.entity.*;
import com.ma.jpa.repository.HolidayRepository;
import com.ma.jpa.repository.JpaTestRepository;
import com.ma.jpa.repository.RoleRepository;
import com.ma.jpa.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    @Autowired
    HolidayRepository holidayRepository;

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

        //1：定义分页对象 s
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

    @Test
    public void testHoliday(){
        Holiday holiday = holidayRepository.findFirstByEndTimeGreaterThanEqualAndStartTimeLessThanEqualAndDeleteFlagFalse(111L, 111L);
        System.out.println(111);
    }

    //测试分页查询
    @Test
    public void testPage(){
        String name = "xi";
        int age = 0;

        List<TUser> users = jpaTestRepository.findAll(new Specification<TUser>() {
                /**
                 * @description toPredicate
                 * @param root 1  代表实体对象，我们可以通过它获取属性值
                 * @param cq 2    用于生成SQL语句
                 * @param cb 3    用于拼接查询条件
                 * @return javax.persistence.criteria.Predicate
                 *
                 * @date 2020/4/6 22:24
                 * @author xiaoma
                 */
                @Override
                public Predicate toPredicate(Root<TUser> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                    List<Predicate> list = new ArrayList<>();
                    if (!StringUtils.isBlank(name)) {
                        Predicate predicate = cb.like(root.get("name").as(String.class), "%x%");
                        list.add(predicate);
                    }
                    if (0!=age) {
                        Predicate predicate = cb.equal(root.get("age"), age);
                        list.add(predicate);
                    }
                    return cb.and(list.toArray(new Predicate[]{}));
                }
        });

        for (TUser tUser:users){
            System.out.println(tUser);
        }
    }

    @Test
    public void testJpaCommon(){
        JpaSpec<TUser> userSpec = (predicates,root,criteriaBuilder)->{
            // age>70 或者 age<20
            Predicate p1 = criteriaBuilder.gt(root.get("age"),70);
            Predicate p2 = criteriaBuilder.lt(root.get("age"),20);
            predicates.add(criteriaBuilder.or(p1,p2));

            Predicate p3 = criteriaBuilder.or(criteriaBuilder.gt(root.get("age"),100),criteriaBuilder.lt(root.get("age"),0));
            Predicate p4 = criteriaBuilder.or(criteriaBuilder.gt(root.get("age"),20),criteriaBuilder.lt(root.get("age"),24));
            predicates.add(criteriaBuilder.or(p3,p4));

            //.....
        };

        //查询
        List<TUser> tUsers = jpaTestRepository.findAll(userSpec);
        //封装结果
        ResultTemplateBean.success("成功了",tUsers);
        System.out.println(tUsers);

//        //单条件排序,多个条件只能同时升序或降序（按id和name降序）:注意坑：下面的page是从0开始算的，所以-1才是我们人类逻辑
//        //分页条件
//        PageRequest pageRequest = PageRequest.of(1,2,Sort.Direction.ASC,"id");
//        //查询
//        Page singleSort = jpaTestRepository.findAll(userSpec, pageRequest);
//        //封装结果
//        JpaPageTemplateBean convert = JpaPageTemplateBean.convert(singleSort);
//        ResultTemplateBean.success("成功了",convert);
//
//        //多条件多排序（按id升序，然后按name降序）:注意坑：下面的page是从0开始算的
//        //排序条件
//        List<Sort.Order> orders=new ArrayList< Sort.Order>();
//        orders.add( new Sort.Order(Sort.Direction. ASC, "id"));
//        orders.add( new Sort.Order(Sort.Direction. DESC, "name"));
//        //分页条件
//        PageRequest pageRequest1 = PageRequest.of(1,4, Sort.by(orders));
//        //查询
//        Page multiSort = jpaTestRepository.findAll(userSpec, pageRequest1);
//        //封装结果
//        JpaPageTemplateBean convert1 = JpaPageTemplateBean.convert(multiSort);
//        ResultTemplateBean.success("成功了",convert1);
//        System.out.println(ResultTemplateBean.success("成功了",convert1));
    }

    @Test
    public void tsest11(){
        for (int i = 0; i < 5; i++) {
            System.out.println("i:"+i);
            for (int j = 0; j < 5; j++) {
                System.out.println("j:"+j);
                break;
            }
        }
    }
}
