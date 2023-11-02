package com.github.whatasame.springdatajpa.instagram;

import jakarta.persistence.ParameterMode;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.procedure.ProcedureCall;

public class PostIdGenerator implements IdentifierGenerator {

    private static final String PROCEDURE_NAME = "generate_post_id";
    private static final String PROCEDURE_PARAM_NAME = "user_id";
    private static final String PROCEDURE_RETURN_NAME = "post_id";

    @Override
    public Object generate(
        final SharedSessionContractImplementor session,
        final Object object
    ) {
        try (
            final ProcedureCall procedureCall = session.createStoredProcedureCall(PROCEDURE_NAME)
        ) {
            procedureCall.registerParameter(PROCEDURE_PARAM_NAME, Long.class, ParameterMode.IN);
            procedureCall.registerParameter(PROCEDURE_RETURN_NAME, Long.class, ParameterMode.OUT);

            final Post post = (Post) object;
            procedureCall.setParameter(PROCEDURE_PARAM_NAME, post.getUserId());

            return procedureCall.getOutputs().getOutputParameterValue(PROCEDURE_RETURN_NAME);
        }
    }
}
