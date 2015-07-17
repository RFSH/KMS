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

function knowledgeToObject(knowledge) {
    var tags = [];

    for (var i = 0; i < knowledge.getTags().size(); i++) {
        tags.push(knowledge.getTags().get(i).getName());
    }

    return {
        id: knowledge.getId(),
        employee: knowledge.getOwner(),
        employeeId: knowledge.getOwner().getId(),
        employeeName: knowledge.getOwner().getFullName(),
        tags: tags,
        voteSum: knowledge.getVoteSum(),
    };
}

function wikiKnowledgeToObject(knowledge) {
    var ret = knowledgeToObject(knowledge);
    ret.usecases = [];

    for (var i = 0; i < knowledge.getUseCaseList().size(); i++) {
        ret.usecases.push(knowledge.getUseCaseList().get(i));
    }

    ret.title = knowledge.getTitle();
    ret.content = knowledge.getContent();
    ret.attachment = knowledge.getAttachment();
    ret.isApproved = knowledge.isApproved();
    ret.isDeprecated = knowledge.isDeprecated();

    return ret;
}

