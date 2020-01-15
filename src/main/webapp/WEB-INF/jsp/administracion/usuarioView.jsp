<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<script type="text/javascript">
    $(document).ready(function () {
        $("#buttonBack").click(function () {
            document.location.href = '${ctxAdministracion}/usuarioSearchReload.htm';
        });
    });
</script>
<div class="row row-heading-app">
    <div class="col-md-10">
        <h4 class="text-primary text-uppercase"><fmt:message key="header.administracion"/> <small><fmt:message key="header.usuarios"/></small></h4>
    </div>
    <div class="col-md-2 text-right">
        <button id="buttonBack" type="button" class="btn btn-sm btn-default">
            <span class="glyphicon glyphicon-arrow-left"></span> <fmt:message key="button.volver"/>
        </button>
    </div>
</div>
<div class="panel panel-info panel-table">
    <div class="panel-heading"><fmt:message key="panel.title.usuario.view"/></div>		
    <div class="panel-body">
        <form role="form" action="#">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group form-group-sm">
                        <label for="nif" class="control-label"><fmt:message key="label.nif"/></label>
                        <%--<p class="form-control-static form-control-static-app"><sgtic:value value="${usuario.nif}"/></p>--%>
                        <input id="nif" type="text" class="form-control input-sm form-control-app" disabled="disabled" value="${usuario.nif}"/>
                    </div>
                </div>
                <div class="col-md-6">					  			
                    <div class="form-group form-group-sm">
                        <label for="nombre" class="control-label"><fmt:message key="label.nombre"/></label>
                        <%--<p class="form-control-static form-control-static-app"><sgtic:value value="${usuario.nombre}"/></p>--%>
                        <input id="nombre" type="text" class="form-control input-sm form-control-app" disabled="disabled" value="${usuario.nombre}"/>
                    </div>	
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group form-group-sm">
                        <label for="apellido1" class="control-label"><fmt:message key="label.apellido1"/></label>
                        <%--<p class="form-control-static form-control-static-app"><sgtic:value value="${usuario.apellido1}"/></p>--%>
                        <input id="apellido1" type="text" class="form-control input-sm form-control-app" disabled="disabled" value="${usuario.apellido1}"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group form-group-sm">
                        <label for="apellido2" class="control-label"><fmt:message key="label.apellido2"/></label>
                        <%--<p class="form-control-static form-control-static-app"><sgtic:value value="${usuario.apellido2}"/></p>--%>
                        <input id="apellido2" type="text" class="form-control input-sm form-control-app" disabled="disabled" value="${usuario.apellido2}"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group form-group-sm">
                        <label for="email" class="control-label"><fmt:message key="label.email"/></label>
                        <%--<p class="form-control-static form-control-static-app"><sgtic:value value="${usuario.email}"/></p>--%>
                        <input id="email" type="text" class="form-control input-sm form-control-app" disabled="disabled" value="${usuario.email}"/>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group form-group-sm">
                        <label for="rol" class="control-label"><fmt:message key="label.rol"/></label>
                        <%--<p class="form-control-static form-control-static-app"><sgtic:value value="${usuario.rol.rol}"/></p>--%>
                        <input id="rol" type="text" class="form-control input-sm form-control-app" disabled="disabled" value="${usuario.rol.rol}"/>
                    </div>	
                </div>
                <div class="col-md-3">
                    <div class="form-group form-group-sm">
                        <label for="fechaAlta" class="control-label"><fmt:message key="label.fechaAlta"/></label>
                        <%--<p class="form-control-static form-control-static-app"><sgtic:valueDate value="${usuario.fechaAlta}"/></p>--%>
                        <input id="fechaAlta" type="text" class="form-control input-sm form-control-app" disabled="disabled" value="<fmt:formatDate value='${usuario.fechaAlta}' pattern='${patternDate}'/>"/>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>