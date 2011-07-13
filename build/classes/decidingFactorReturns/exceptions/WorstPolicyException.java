package decidingFactorReturns.exceptions;

import decidingFactorReturns.utils.I18n;

public class WorstPolicyException extends PolicyException {

    @Override
    public String getMessage() {
        return I18n.t("worst_policy_exception");
    }
}
