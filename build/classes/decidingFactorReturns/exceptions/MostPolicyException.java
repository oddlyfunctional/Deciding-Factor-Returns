/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package decidingFactorReturns.exceptions;

import decidingFactorReturns.utils.I18n;

/**
 *
 * @author marcos
 */
public class MostPolicyException extends PolicyException {

    @Override
    public String getMessage() {
        return I18n.t("most_policy_exception");
    }
}
