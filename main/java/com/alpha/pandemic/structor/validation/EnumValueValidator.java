package com.alpha.pandemic.structor.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValue, Object>
{
    private String[] stringValues;
    private int[] intvalues;

    @Override
    public void initialize(EnumValue constraintAnnotation)
    {
        intvalues=constraintAnnotation.intValues();
        stringValues=constraintAnnotation.stringValues();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext)
    {
        if(o instanceof String)
        {
            for(String s: stringValues)
            {
                if(s.equals(o)){return true;}
            }
        }
        else if (o instanceof Integer)
        {

            for(Integer s: intvalues)
            {
                if(s==o){return true;}
            }
        }
        return false;
    }
}
