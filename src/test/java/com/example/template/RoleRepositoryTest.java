package com.example.template;

import com.example.template.enums.ERoleType;
import com.example.template.models.Role;
import com.example.template.repository.RoleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

   @Test
    public void testCreateRoles(){
       Role admin = new Role(ERoleType.ADMIN);
       Role guest = new Role(ERoleType.GUEST);
       Role user = new Role(ERoleType.USER);

       roleRepository.saveAll(List.of(admin,guest,user));
       List<Role> listRoles = roleRepository.findAll();

      Assertions.assertThat(listRoles.size()).isEqualTo(3);
   }

}
