package decidingFactorReturns.exceptions;

import decidingFactorReturns.utils.I18n;

public class AllPolicyException extends PolicyException {

    @Override
    public String getMessage() {
        return I18n.t("all_policy_exception");
    }

}
