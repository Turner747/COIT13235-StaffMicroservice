package com.optimed.staffmicroservice.loader;

import com.optimed.staffmicroservice.model.*;
import com.optimed.staffmicroservice.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {
    private PrivilegeRepository privilegeRepo;
    private RoleRepository roleRepo;
    private StaffRepository staffRepo;
    private ShiftRepository shiftRepo;
    private PasswordRepository passwordRepo;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        Privilege patient_read = createPrivilegeIfNotFound("PATIENT_READ");
        Privilege patient_write = createPrivilegeIfNotFound("PATIENT_WRITE");
        Privilege appointment_read = createPrivilegeIfNotFound("APPOINTMENT_READ");
        Privilege appointment_write = createPrivilegeIfNotFound("APPOINTMENT_WRITE");
        Privilege staff_read = createPrivilegeIfNotFound("STAFF_READ");
        Privilege staff_write = createPrivilegeIfNotFound("STAFF_WRITE");

        List<Privilege> admin_privileges = Arrays.asList(
                patient_read, patient_write,
                appointment_read, appointment_write,
                staff_read, staff_write);
        List<Privilege> user_privileges = Arrays.asList(
                patient_read,
                appointment_read,
                staff_read);
        createRoleIfNotFound("ROLE_ADMIN", admin_privileges);
        createRoleIfNotFound("ROLE_USER", user_privileges);

        Staff admin = createStaffIfNotFound("Admin", "admin@hotmail.com",
                "admin", null, null, roleRepo.findByName("ROLE_ADMIN"));
        Staff user = createStaffIfNotFound("User", "user@hotmail.com",
                "user", null, null, roleRepo.findByName("ROLE_USER"));
        Staff doctor = createStaffIfNotFound("Dr. John", "john@hotmail.com",
                "pwdpwd", "123456789", "123456789", roleRepo.findByName("ROLE_USER"));
        Staff staff = createStaffIfNotFound("James", "james@hotmail.com",
                "pwdpwd", null, null, roleRepo.findByName("ROLE_USER"));


        createShift(doctor, "2023-10-09 08:30", "2023-10-09 17:00");
        createShift(doctor, "2023-10-10 08:30", "2023-10-10 17:00");
        createShift(doctor, "2023-10-11 08:30", "2023-10-11 17:00");

        createShift(staff, "2023-10-09 08:30", "2023-10-09 17:00");
        createShift(staff, "2023-10-10 08:30", "2023-10-10 17:00");
        createShift(staff, "2023-10-11 08:30", "2023-10-11 17:00");

    }

    private void createShift(Staff staff, String startTime, String finishTime) {
        String datePattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

        Shift shift = new Shift();
        shift.setStaff(staff);
        try {
            shift.setStartTime(dateFormat.parse(startTime));
            shift.setFinishTime(dateFormat.parse(finishTime));
        } catch (Exception e) {
            System.out.println("Date format: yyyy-MM-dd HH:mm");
        }
        try {
            shiftRepo.save(shift);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private Staff createStaffIfNotFound(String firstName, String email, String password,
                                        String providerNumber, String prescriberNumber, Role role) {
        Optional<Staff> optional = staffRepo.findByEmail(email);
        if (optional.isEmpty()) {
            Staff staff = new Staff();
            Password pwd = new Password();
            pwd.setPassword(new BCryptPasswordEncoder().encode(password));
            staff.setFirstName(firstName);
            staff.setLastName("Smith");
            staff.setEmail(email);
            staff.setPassword(pwd);
            staff.setProviderNumber(providerNumber);
            staff.setPrescriberNumber(prescriberNumber);
            staff.setRole(role);
            staffRepo.save(staff);
            return staff;
        }
        return optional.get();
    }

    private void createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleRepo.findByName(name);
        if (role == null) {
            Role new_role = new Role(name);
            new_role.setPrivileges(privileges);
            roleRepo.save(new_role);
        }
    }

    public Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepo.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepo.save(privilege);
        }
        return privilege;
    }
}
