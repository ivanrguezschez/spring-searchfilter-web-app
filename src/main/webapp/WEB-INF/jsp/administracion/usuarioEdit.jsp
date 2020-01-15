<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<script type="text/javascript">
    $(document).ready(function () {
        $('#nif').focus();
        $("#buttonBack").click(function () {
            document.location.href = '${ctxAdministracion}/usuarioSearchReload.htm';
        });
        $('.date').datepicker({
            autoclose: true,
            todayHighlight: true,
            language: "es"
        });
    });
</script>
<div class="row row-heading-app">
    <div class="col-md-9">
        <h4 class="text-primary text-uppercase"><fmt:message key="header.administracion"/> <small><fmt:message key="header.usuarios"/></small></h4>
    </div>
    <div class="col-md-3 text-right">
        <button id="buttonBack" type="button" class="btn btn-sm btn-default">
            <span class="glyphicon glyphicon-arrow-left"></span> <fmt:message key="button.volver"/>
        </button>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/common/messages-error.jsp" flush="true">
    <jsp:param name="beanName" value="usuarioEditCommand"/>
</jsp:include>
<jsp:include page="/WEB-INF/jsp/common/messages-check.jsp"/>
<div id="panelEdit" class="panel panel-info panel-table">
    <div class="panel-heading">
        <c:if test="${empty usuarioEditCommand.idUsuario}"><fmt:message key="panel.title.usuario.add"/></c:if>
        <c:if test="${not empty usuarioEditCommand.idUsuario}"><fmt:message key="panel.title.usuario.update"/></c:if>
        </div>		
        <div class="panel-body">
        <form:form role="form" action="${ctxAdministracion}/usuarioEditSubmit.htm" commandName="usuarioEditCommand">
            <form:hidden path="idUsuario"/>
            <%@include file="/WEB-INF/jsp/common/required.jsp" %>	
            <div class="row">
                <div class="col-md-6">
                    <spring:bind path="nif">
                        <div class="form-group form-group-sm ${status.error ? 'has-error' : ''}">
                            <label for="nif" class="control-label"><fmt:message key="label.nif"/><fmt:message key="label.required"/></label>
                            <form:input path="nif" maxlength="9" size="8" class="form-control input-sm" />
                            <form:errors path="nif" cssClass="text-danger"/>
                        </div>
                    </spring:bind>
                    <%--
                        Otra forma de mostrar el error
                        <c:set var="nifHasBindError"><form:errors path="nif"/></c:set>
                        <div class="form-group form-group-sm ${not empty nifHasBindError?"has-error":""}">
                            <label for="nif" class="control-label"><fmt:message key="label.nif"/><fmt:message key="label.required"/></label>
                            <form:input path="nif" maxlength="9" size="8" class="form-control input-sm" />
                            ${nifHasBindError}
                        </div>
                    --%>
                </div>
                <div class="col-md-6">	
                    <spring:bind path="nombre">
                        <div class="form-group form-group-sm ${status.error ? 'has-error' : ''}">
                            <label for="nombre" class="control-label"><fmt:message key="label.nombre"/><fmt:message key="label.required"/></label>
                            <form:input path="nombre" maxlength="50" size="50" class="form-control input-sm" />
                            <form:errors path="nombre" cssClass="text-danger"/>
                        </div>	
                    </spring:bind>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <spring:bind path="apellido1">
                        <div class="form-group form-group-sm ${status.error ? 'has-error' : ''}">
                            <label for="apellido1" class="control-label"><fmt:message key="label.apellido1"/><fmt:message key="label.required"/></label>
                            <form:input path="apellido1" maxlength="50" size="50" class="form-control input-sm" />
                            <form:errors path="apellido1" cssClass="text-danger"/>
                        </div>
                    </spring:bind>
                </div>
                <div class="col-md-6">
                    <spring:bind path="apellido2">
                        <div class="form-group form-group-sm ${status.error ? 'has-error' : ''}">
                            <label for="apellido2" class="control-label"><fmt:message key="label.apellido2"/><fmt:message key="label.required"/></label>
                            <form:input path="apellido2" maxlength="50" size="50" class="form-control input-sm" />
                            <form:errors path="apellido2" cssClass="text-danger"/>
                        </div>
                    </spring:bind>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <spring:bind path="email">
                        <div class="form-group form-group-sm ${status.error ? 'has-error' : ''}">
                            <label for="email" class="control-label"><fmt:message key="label.email"/></label>
                            <form:input path="email" maxlength="100" size="100" class="form-control input-sm" />
                            <form:errors path="email" cssClass="text-danger"/>
                        </div>
                    </spring:bind>
                </div>
                <div class="col-md-3">
                    <spring:bind path="rol">
                        <div class="form-group form-group-sm ${status.error ? 'has-error' : ''}">
                            <label for="rol" class="control-label"><fmt:message key="label.rol"/><fmt:message key="label.required"/></label>
                            <form:select path="rol" class="form-control input-sm">
                                <form:option value="" label="${maskSelect}"/>
                                <form:options items="${roles}" itemValue="idRol" itemLabel="rol"/>
                            </form:select>
                            <form:errors path="rol" cssClass="text-danger"/>
                        </div>	
                    </spring:bind>
                </div>
                <div class="col-md-3">
                    <spring:bind path="fechaAlta">
                        <div class="form-group form-group-sm ${status.error ? 'has-error' : ''}">
                            <label for="fechaAlta" class="control-label"><fmt:message key="label.fechaAlta"/><fmt:message key="label.required"/></label>
                            <div class='input-group date'>
                                <form:input path="fechaAlta" class="form-control" />
	                        <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
	                        </span>
	                    </div>
                            <form:errors path="fechaAlta" cssClass="text-danger"/>
	                </div>
                    </spring:bind>
                </div>
            </div>
            <div class="text-right">
                <button type="submit" name="cancelar" value="<fmt:message key='button.cancelar'/>" class="btn btn-sm btn-default "><span class="glyphicon glyphicon-remove"></span> <fmt:message key="button.cancelar"/></button>
                <button type="submit" name="aceptar" value="<fmt:message key='button.aceptar'/>" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-ok"></span> <fmt:message key="button.aceptar"/></button>
            </div>
        </form:form>
    </div>
</div>
