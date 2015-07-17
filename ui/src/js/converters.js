function employeeToObject(employee) {
    return {
        id: employee.getId(),
        username: employee.getUsername(),
        name: employee.getFullName(),
        firstName: employee.getFirstName(),
        lastName: employee.getLastName(),
        nationalId: employee.getNationalId(),
        password: employee.getPassword(),
        email: employee.getEmail(),
        roleName: employee.getRole().getName(),
        roleId: employee.getRole().getId(),
        permissionName: employee.getPermissionLevel().getName(),
        permissionId: employee.getPermissionLevel().getId()
    };
}

function roleToObject(role) {
    return {
        id: role.getId(),
        name: role.getName()
    };
}

function permissionToObject(permission) {
    return {
        id: permission.getId(),
        name: permission.getName()
    };
}