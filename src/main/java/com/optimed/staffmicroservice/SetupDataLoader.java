package com.optimed.staffmicroservice;

import com.optimed.staffmicroservice.model.Privilege;
import com.optimed.staffmicroservice.model.Role;
import com.optimed.staffmicroservice.repository.PrivilegeRepository;
import com.optimed.staffmicroservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        Privilege patientRead
                = createPrivilegeIfNotFound("PATIENT_READ");
        Privilege patientWrite
                = createPrivilegeIfNotFound("PATIENT_WRITE");

        Privilege staffRead
                = createPrivilegeIfNotFound("STAFF_READ");
        Privilege staffWrite
                = createPrivilegeIfNotFound("STAFF_WRITE");

        Privilege appointmentRead
                = createPrivilegeIfNotFound("APPOINTMENT_READ");
        Privilege appointmentWrite
                = createPrivilegeIfNotFound("APPOINTMENT_WRITE");

        List<Privilege> doctorPrivileges = Arrays.asList(
                patientRead, patientWrite, appointmentRead, appointmentWrite, staffRead);
        List<Privilege> adminPrivileges = Arrays.asList(
                patientRead, patientWrite, appointmentRead, appointmentWrite, staffRead, staffWrite);
        Role doctorRole = createRoleIfNotFound("ROLE_DOCTOR", doctorPrivileges);
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);

        alreadySetup = true;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
