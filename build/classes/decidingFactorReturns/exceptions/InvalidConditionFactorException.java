package decidingFactorReturns.exceptions;

import decidingFactorReturns.utils.I18n;

public class InvalidConditionFactorException extends PolicyException {

    @Override
    public String getMessage() {
        return I18n.t("invalid_condition_factor_exception");
    }
}
