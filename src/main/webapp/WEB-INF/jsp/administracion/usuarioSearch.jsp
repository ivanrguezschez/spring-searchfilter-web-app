<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<script type="text/javascript">
    $(document).ready(function () {
        $('#nif').focus();
        $('#buttonAdd').click(function () {
            document.location.href = '${ctxAdministracion}/usuarioEdit.htm';
        });
        $('#buttonSearch').click(function () {
            $('#idFiltro').val('');
            $('form').submit();
        });
        $('#confirmDelete').on('show.bs.modal', function (event) {
            $(this).find('.btn-ok').attr('href', $(event.relatedTarget).data('href'));
        });
        $('.date').datepicker({
            autoclose: true,
            todayHighlight: true,
            language: "es"
        });
        $('#dialogFiltroSave').on('shown.bs.modal', function (event) {
            $('#nombreFiltro').focus();
        });
        $('#dialogFiltroSave').on('show.bs.modal', function (event) {
            messageErrorRemove($("#dialogFiltroSave div.modal-body"));
            messageCheckRemove($("#dialogFiltroSave div.modal-body"));
            dialogFiltroSaveClean();
	});
        $('#dialogFiltroRemove').on('show.bs.modal', function (event) {
            $('p.form-control-static').html($('#buttonFiltros > button').text()); 
	});
        $('#dialogFiltroSave').on('click', '#buttonFiltroSaveAceptar', function(e) {
            var dataFiltro = { 
		nombre: $('#nombreFiltro').val() 
            };
            $.post('${ctx}/ajax/filtroSaveValidateAjax.htm', dataFiltro, function(data) {
                messageErrorRemove($("#dialogFiltroSave div.modal-body"));
	        if (data != '') {
                    messageErrorCreate($("#dialogFiltroSave div.modal-body"), data);
	        } else {
                    dataFiltro = { 
                        idTipoEntidad: '1',
                        nombre: $('#nombreFiltro').val(),
                        valor: JSON.stringify($('form').serializeArray())
                    };
                    $.post('${ctx}/ajax/filtroSaveAjax.htm', dataFiltro, function(data) {
                        // Obtendria todos los filtros, limpiaria la lista de filtros y dejaria seleccionado el filtro insertado    
                        if ($('#filtros > li').length === 2) {
                            $('#filtros').append('<li role="separator" class="divider"></li>');
                        }
                        $('#filtros').append('<li><a id="filtro_' + data.idFiltro + '" href="#">' + data.nombre + '</a></li>');
                        $('#dialogFiltroSave').modal('hide');
                        
                        // Guardamos el filtro que acabamos de insertar y ejecutamos la busqueda
                        $('#idFiltro').val(data.idFiltro);
                        $('form').submit();
                    }).fail(function(e) {
                        //location.href = '${ctx}/error.htm?error=19&status=' + e.status;
                        alert(e.status);
                    });
                }
            }).fail(function(e) {
                //location.href = '${ctx}/error.htm?error=17&status=' + e.status;
                alert(e.status);
            });
        });
        $('#dialogFiltroRemove').on('click', '#buttonFiltroRemoveAceptar', function(e) {
            $.post('${ctx}/ajax/filtroDeleteAjax.htm', { idFiltro: $('#idFiltro').val() }, function(data) {
                $('#dialogFiltroRemove').modal('hide');
                //$("#buttonSearch").trigger('click');
                $('#filtros > li').each(function() {
                    var a = $(this).find('a');
                    if (a.attr('id') == 'filtro_' + $('#idFiltro').val()) {
                        $(this).remove();
                        $('#idFiltro').val('');
                    }
                });
                if ($('#filtros > li').length === 3) {
                    $('#filtros > li').remove('.divider');
                    disabledButtonRemoveFilter();
                }
                $('#buttonFiltros > button').html('<span class="glyphicon glyphicon-filter"></span> <span class="caret"></span>');
            }).fail(function(e) {
                //location.href = '${ctx}/error.htm?error=20&status=' + e.status;
                alert(e.status);
            });
        });
        $('#filtros').on('click', 'a[id^="filtro"]', function(event) {
            // Capturar el click en el filtro_N para cargar el filtro en los campos y ejecutarlo
            event.preventDefault();
            var id = extractId($(this).attr('id'));
            $.getJSON('${ctx}/ajax/filtroSearchByIdAjax.htm', {idFiltro: id} ,function(data) {
                //data.valor: [{"name": "idFiltro", "value": "1"},{"name": "nif", "value": "R"},{"name": "nombre", "value": ""},{"name": "apellido1", "value": ""},{"name": "apellido2", "value": ""},{"name": "rol", "value": "1"}]
                //var f = jQuery.parseJSON(data.valor);
                var filterUsuario = buildFilterUsuario(data.valor);
                $('#nif').val(filterUsuario.nif);
                $('#nombre').val(filterUsuario.nombre);
                $('#apellido1').val(filterUsuario.apellido1);
                $('#apellido2').val(filterUsuario.apellido2);
                $('#rol').val(filterUsuario.rol);
                $('#idFiltro').val(id);
                
                $('form').submit();
            });
	});
        $('#buttonRemoveFilter').on('click', function(e) {
            if ($(this).hasClass('disabled')) {
                e.stopPropagation();
                e.preventDefault();
            }
        });
        loadFiltros();
    });
   
    function loadFiltros() {
        $.getJSON('${ctx}/ajax/filtroSearchAjax.htm', function(data) {
            if (data.length > 0) {
                if ($('#filtros > li').length === 2) {
                    $('#filtros').append('<li role="separator" class="divider"></li>');
                }
                jQuery.each(data, function(index, item) {
                    $('#filtros').append('<li><a id="filtro_' + item.idFiltro + '" href="#">' + item.nombre + '</a></li>');
                });
                // Selecciono el filtro que tenia seleccionado el usuario
                if ($('#idFiltro').val() !== '') {
                    $('#filtros > li > a[id^="filtro"]').each(function() {
                        var id = extractId($(this).attr('id'));
                        if (id === $('#idFiltro').val()) {
                            $('#buttonFiltros > button').html('<span class="glyphicon glyphicon-filter"></span> ' + $(this).text() + ' <span class="caret"></span>');
                            enabledButtonRemoveFilter();
                            return false; // break del bucle each
                        }
                    });
                }
            }
        });
        if ($('#idFiltro').val() === '') {
            disabledButtonRemoveFilter();
        }
    }
    function enabledButtonRemoveFilter() {
        $('#liRemoveFilter, #buttonRemoveFilter').removeClass("disabled");
    }
    function disabledButtonRemoveFilter() {
        $('#liRemoveFilter, #buttonRemoveFilter').addClass("disabled");
    }
    function messageErrorRemove(element) {
        element.find('div.alert-danger').remove();
    }
    function messageCheckRemove(element) {
        element.find('div.alert-success').remove();
    }
    function messageErrorCreate(element, messages) {
	element.prepend('<div class="alert alert-danger"></div>');
   	$.each(messages, function(index, item) {								           					
   		element.find("div.alert-danger").append('<div>' + item + '</div>');
   	});
    }
    function dialogFiltroSaveClean() {
        $('#nombreFiltro').val('');
    }
    
    function FilterUsuario() { 
        this.nif = null;
        this.nombre = null;
        this.apellido1 = null;
        this.apellido2 = null;
        this.rol = null;
    }
    function buildFilterUsuario(valueFilter) {
        // valueFilter: [{"name": "idFiltro", "value": "1"},{"name": "nif", "value": "R"},{"name": "nombre", "value": ""},{"name": "apellido1", "value": ""},{"name": "apellido2", "value": ""},{"name": "rol", "value": "1"}]
        var f = jQuery.parseJSON(valueFilter);
        var filterUsuario = new FilterUsuario();

        filterUsuario.nif = findFieldValue(f, 'nif');
        filterUsuario.nombre = findFieldValue(f, 'nombre');
        filterUsuario.apellido1 = findFieldValue(f, 'apellido1');
        filterUsuario.apellido2 = findFieldValue(f, 'apellido2');
        filterUsuario.rol = findFieldValue(f, 'rol');

        return filterUsuario;            
    };
    function findFieldValue(fields, fieldName) {
        var fieldValue = $.grep(fields, function(f, i) { return f.name === fieldName; });
        // fieldValue: [{"name": "nif", "value": "R"}]
        return $.isArray(fieldValue) && !$.isEmptyObject(fieldValue) ? fieldValue[0].value : '';
    }
</script>
<div class="row row-heading-app">
    <div class="col-md-9">
        <h4 class="text-primary text-uppercase"><fmt:message key="header.administracion"/> <small><fmt:message key="header.usuarios"/></small></h4>
    </div>
    <div class="col-md-3 text-right">
        <button id="buttonAdd" type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-plus"></span> <fmt:message key="button.nuevo.usuario"/>
        </button>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/common/messages-error.jsp" flush="true">
    <jsp:param name="beanName" value="usuarioSearchCommand"/>
</jsp:include>
<jsp:include page="/WEB-INF/jsp/common/messages-check.jsp"/>

<%--
<div id="buttonFiltros" class="btn-group">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <span class="glyphicon glyphicon-filter"></span> <span class="caret"></span>
    </button>
    <ul id="filtros" class="dropdown-menu">
        <li id="liSaveFilter">
            <a id="buttonSaveFilter" data-toggle="modal" data-backdrop="static" data-target="#dialogFiltroSave" href="#">
                <span class="glyphicon glyphicon-save"></span> <fmt:message key="link.save.filtro"/>
            </a>
        </li>
        <li id="liRemoveFilter">
            <a id="buttonRemoveFilter" data-toggle="modal" data-backdrop="static" data-target="#dialogFiltroRemove" href="#">
                <span class="glyphicon glyphicon-trash"></span> <fmt:message key="link.remove.filtro"/>
            </a>
        </li>
    </ul>
</div>
--%>
            
<div class="panel panel-info panel-table">
    <div class="panel-heading">
        <a class="state" data-toggle="collapse" href="#collapseSearchCriteria"></a>
        <fmt:message key="panel.header.search"/>
        
        <div class="pull-right">
            <button id="buttonSearch" class="btn btn-xs btn-default"><span class="glyphicon glyphicon-search"></span> <fmt:message key="button.buscar"/></button>
            <div id="buttonFiltros" class="btn-group btn-group-xs">
                <button type="button" class="btn btn-xs btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="glyphicon glyphicon-filter"></span> <span class="caret"></span>
                </button>
                <ul id="filtros" class="dropdown-menu">
                    <li id="liSaveFilter">
                        <a id="buttonSaveFilter" data-toggle="modal" data-backdrop="static" data-target="#dialogFiltroSave" href="#">
                            <span class="glyphicon glyphicon-save"></span> <fmt:message key="link.save.filtro"/>
                        </a>
                    </li>
                    <li id="liRemoveFilter">
                        <a id="buttonRemoveFilter" data-toggle="modal" data-backdrop="static" data-target="#dialogFiltroRemove" href="#">
                            <span class="glyphicon glyphicon-trash"></span> <fmt:message key="link.remove.filtro"/>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>	
    <div id="collapseSearchCriteria" class="panel-collapse collapse in">
        <div class="panel-body">
            <form:form role="form" action="${ctxAdministracion}/usuarioSearch.htm" commandName="usuarioSearchCommand">	
                <form:hidden path="idFiltro"/>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group form-group-sm">
                            <label for="nif" class="control-label"><fmt:message key="label.nif"/></label>
                            <form:input path="nif" maxlength="9" size="8" class="form-control input-sm" />
                        </div>
                    </div>
                    <div class="col-md-6">					  			
                        <div class="form-group form-group-sm">
                            <label for="nombre" class="control-label"><fmt:message key="label.nombre"/></label>
                            <form:input path="nombre" maxlength="50" size="50" class="form-control input-sm"/>
                        </div>	
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group form-group-sm">
                            <label for="apellido1" class="control-label"><fmt:message key="label.apellido1"/></label>
                            <form:input path="apellido1" maxlength="50" size="50" class="form-control input-sm"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group form-group-sm">
                            <label for="apellido2" class="control-label"><fmt:message key="label.apellido2"/></label>
                            <form:input path="apellido2" maxlength="50" size="50" class="form-control input-sm"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group form-group-sm">
                            <label for="rol" class="control-label"><fmt:message key="label.rol"/></label>
                            <form:select path="rol" class="form-control input-sm">
                                <form:option value="" label="${maskSelect}"/>
                                <form:options items="${roles}" itemValue="idRol" itemLabel="rol"/>
                            </form:select>
                        </div>	
                    </div>
                </div>      
            </form:form>
        </div>
    </div>
</div>
<c:if test="${usuarioList != null}">
    <div class="panel panel-info">
        <div class="panel-heading">
            <a class="state" data-toggle="collapse" href="#collapseSearchResult"></a>
            <fmt:message key="panel.header.result"/>
        </div>
        <div id="collapseSearchResult" class="panel-collapse collapse in">
            <div class="panel-body">
                <c:if test="${not empty usuarioList}">
                    <table class="table table-bordered table-striped table-hover table-condensed table-app">
                        <thead class="bg-primary">
                            <tr>
                                <th width="60px"><fmt:message key="table.head.label.nif"/></th>
                                <th><fmt:message key="table.head.label.nombre"/></th>
                                <th><fmt:message key="table.head.label.apellido1"/></th>
                                <th><fmt:message key="table.head.label.apellido2"/></th>
                                <th width="100px"><fmt:message key="table.head.label.rol"/></th>
                                <th width="90px"><fmt:message key="table.head.label.fechaAlta"/></th>
                                <th width="90px">&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${usuarioList}" var="usuario" varStatus="status">
                                <tr>
                                    <td><c:out value="${usuario.nif}"/></td>   	
                                    <td><c:out value="${usuario.nombre}"/></td>
                                    <td><c:out value="${usuario.apellido1}"/></td>
                                    <td><c:out value="${usuario.apellido2}"/></td>
                                    <td><c:out value="${usuario.rol.rol}"/></td>
                                    <td><fmt:formatDate value="${usuario.fechaAlta}" pattern="${patternDate}"/></td>
                                    <td class="text-center">
                                        <div class="btn-group" role="group">
                                            <a class="btn btn-xs btn-default" href="${ctxAdministracion}/usuarioEdit.htm?idUsuario=${usuario.idUsuario}" title="<fmt:message key='icon.usuario.edit.title'/>"><span class="glyphicon glyphicon-edit"/></a>
                                            <a class="btn btn-xs btn-default" data-toggle="modal" data-backdrop="static" href="#confirmDelete" data-href="${ctxAdministracion}/usuarioDelete.htm?idUsuario=${usuario.idUsuario}" title="<fmt:message key='icon.usuario.delete.title'/>"><span class="glyphicon glyphicon-trash"/></a>
                                            <a class="btn btn-xs btn-default" href="${ctxAdministracion}/usuarioView.htm?idUsuario=${usuario.idUsuario}" title="<fmt:message key='icon.usuario.view.title'/>"><span class="glyphicon glyphicon-eye-open"/></a>
                                        </div>	
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${empty usuarioList}">
                    <div class="alert alert-warning"><fmt:message key="info.message.notfound.usuario"/></div>	   
                </c:if>
            </div>
        </div>		
    </div>
</c:if>
<!-- Modal Confirm Delete -->
<div class="modal fade" id="confirmDelete" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="modalLabel"><fmt:message key="info.message.remove.usuario.dialog.title"/></h5>
            </div>
            <div class="modal-body">
                <p><fmt:message key="info.message.remove.usuario.dialog.message"/></p>
            </div>
            <div class="modal-footer">
                <a class="btn btn-primary btn-ok"><fmt:message key="button.si"/></a>
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.no"/></button>
            </div>
        </div>
    </div>
</div>
            
            
<!-- Modal Save Filtro -->
<div class="modal fade" id="dialogFiltroSave" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button type="button" class="close close-app" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h5 class="modal-title" id="modalLabel"><fmt:message key="dialog.title.filtro.save"/></h5>
            </div>
            <div class="modal-body">
                <%@include file="/WEB-INF/jsp/common/required.jsp" %>
		<div class="row">
                    <div class="col-md-12">
                        <div class="form-group form-group-sm">
                            <label for="nombreFiltro" class="control-label"><fmt:message key="label.nombreFiltro"/><fmt:message key="label.required"/></label>
                            <input type="text" id="nombreFiltro" name="nombreFiltro" maxlength="50" size="50" class="form-control input-sm" />
			</div>
                    </div>
                </div>
	    </div>
	    <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> <fmt:message key="button.cancelar"/></button>
		<button id="buttonFiltroSaveAceptar" type="button" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-ok"></span> <fmt:message key="button.aceptar"/></button>
	    </div>
	</div>
    </div>
</div>
<!-- Modal Remove Filtro -->
<div class="modal fade" id="dialogFiltroRemove" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button type="button" class="close close-app" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h5 class="modal-title" id="modalLabel"><fmt:message key="dialog.title.filtro.remove"/></h5>
            </div>
            <div class="modal-body">
		<div class="row">
                    <div class="col-md-12">
                        <div class="form-group form-group-sm">
                            <label class="control-label"><fmt:message key="label.nombreFiltro"/></label>
                            <p class="form-control-static"></p>
			</div>
                    </div>
                </div>
	    </div>
	    <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> <fmt:message key="button.cancelar"/></button>
		<button id="buttonFiltroRemoveAceptar" type="button" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-ok"></span> <fmt:message key="button.aceptar"/></button>
	    </div>
	</div>
    </div>
</div>