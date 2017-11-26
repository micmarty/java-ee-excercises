package pl.gda.pg.eti.kask.javaee.enterprise.forests.auth;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Forest;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.User;

import javax.annotation.Priority;
import javax.annotation.Resource;
import javax.ejb.AccessLocalException;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@OwnerOrAdminAllowed
@Priority(1000)
public class OwnerOrAdminAllowedInterceptor implements Serializable {

    @Resource
    SessionContext sessionCtx;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        Object parameter = context.getParameters()[0];

        if(parameter instanceof Forest){
            Forest forest = (Forest) parameter;

            boolean isAdmin = sessionCtx.isCallerInRole(User.Roles.ADMIN);
            String login = sessionCtx.getCallerPrincipal().getName();
            boolean isOwner = forest.getOwner().getLogin().equals(login);

            if (isAdmin || isOwner) {
                return context.proceed();
            }
        }

        throw new AccessLocalException("Client not authorized for this invocation");
    }
}
