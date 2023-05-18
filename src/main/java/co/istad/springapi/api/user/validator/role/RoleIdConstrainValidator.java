package co.istad.springapi.api.user.validator.role;

import co.istad.springapi.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleIdConstrainValidator implements ConstraintValidator<RoleIdConstrain, List<Integer>> {
    private final UserMapper userMapper;
    @Override
    public boolean isValid(List<Integer> roleIds, ConstraintValidatorContext context) {
        for (Integer roleId : roleIds){
            if (!userMapper.checkRoleId(roleId)){
                return false;
            }
        }
        return true;

    }
}
