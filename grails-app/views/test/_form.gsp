<%@ page import="comedor.Test" %>



<div class="fieldcontain ${hasErrors(bean: testInstance, field: 'pruebaEscritura', 'error')} ">
	<label for="pruebaEscritura">
		<g:message code="test.pruebaEscritura.label" default="Prueba Escritura" />
		
	</label>
	<g:textField name="pruebaEscritura" value="${testInstance?.pruebaEscritura}"/>
</div>

