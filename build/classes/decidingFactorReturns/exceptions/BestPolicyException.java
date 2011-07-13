package decidingFactorReturns.exceptions;

import decidingFactorReturns.utils.I18n;

public class BestPolicyException extends PolicyException {

    @Override
    public String getMessage() {
        return I18n.t("best_policy_exception");
    }
}
