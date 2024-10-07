<%-- 
    Document   : solohtml
    Created on : 7/09/2024, 9:27:31 a. m.
    Author     : Kenneth
--%>

<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import= "modelo.Puesto" %>
<%@page import= "modelo.Empleado" %>
<%@page import = "java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-DsPTg1lUwd6DpD1m2+f2MAWZf1Rr4p6vTIl2YJxnjbeGc9wxZ7ErfFEl8f4HpFOE" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        
        
    </head>
    <body>
        
        
  <!-- Content here -->

        <h1>Formulario Empleados</h1>
        <a href="index.jsp">Menu</a>
        <div class="container">
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal_empleado" onclick="limpiar()">Nuevo</button>
        
        <!-- The Modal -->
<div class="modal fade" id="modal_empleado">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Formulario</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
          <form  action="sr_empleado"  method="post" class="form-group needs-validation" novalidate> <!-- get = envio de parametrosurl, post = oculto -->
        
            <label  for = "lbl_id" class="form-label"><b>ID</b></label>
            <input type="text" name="txt_id" id="txt_id" class="form-control" value = "0" readonly>
            
            <br>
            
            <label for = "lbl_codigo" class="form-label"><b>Codigo</b></label>
            <input type="text" name="txt_codigo" id="txt_codigo" class="form-control" placeholder="Ejemplo: E001" pattern="[E]{1}[0-9]{2}[1-9]{1}" required>
            <br>

            <label for = "lbl_nombres"class="form-label"><b>Nombre</b></label>
            <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Ejemplo: Nombre1 Nombre2"required>
            <br>

            <label for = "lbl_apellidos"class="form-label"><b>Apellidos</b></label>
            <input type="text" name="txt_apellidos" id="txt_apellidos"class="form-control"  placeholder="Ejemplo: Apellido1 Apellido 2"required>
            <br>
            
            <label for = "lbl_direccion"class="form-label"><b>Direccion</b></label>
            <input type="text" name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Ejemplo: Guatemala No.casa"required>
            <br>

            <label for = "lbl_telefono"class="form-label"><b>Telefono</b></label>
            <input type="text" name="txt_telefono" id="txt_telefono" class="form-control"  placeholder="Ejemplo:1234-1232"required>
            <br>
            
            <label for = "lbl_fn"class="form-label"><b>F.Nacimiento</b></label>
            <input type="date" name="txt_fn" id="txt_fn" class="form-control"  placeholder="Ejemplo:2003-09-09"required>
            <br>
            
            <label for = "lbl_fn" class="form-label"><b>Puestos</b></label>
            <select  name="drop_puesto" id="drop_puesto" class="form-select" required>
                
                <option selected disabled value="">Elija un puesto</option>
               
                <%
                Puesto puesto = new Puesto();
                HashMap<String,String> drop = puesto.drop_puesto();
                for(String i:drop.keySet()){
                
                out.println("<option value = '"+i+"'>"+drop.get(i)+"</option>");
                    }
                
                
                
                %>
                
            </select>
            
            <br>
            <br>
            <button name="btn_crear" id="btn_crear" value="crear" class="btn btn-primary" type="submit"><i class="bi bi-floppy"></i></button>
            <button name="btn_actualizar" id="btn_actualizar" value="actualizar" class="btn btn-warning" type="submit"><i class="bi bi-arrow-down-up"></i></button>
            <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" type="submit" onclick="javascript:if(!confirm('¿Desea Eliminarl?'))return false"><i class="bi bi-trash"></i></button>
  </form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
        
        
        
    
                
<table class="table table-striped">
    <thead>
      <tr>
        <th>Codigo</th>
        <th>Nombres</th>
        <th>Apellidos</th>
        <th>Direccion</th>
        <th>Telefono</th>
        <th>Nacimiento</th>
        <th>Puesto</th>
        
      </tr>
    </thead>
    <tbody id="tbl_empleados">
        <%
        Empleado empleado = new Empleado();
        DefaultTableModel tabla = new DefaultTableModel();
        tabla  = empleado.leer();
        for(int t=0;t<tabla.getRowCount(); t++){
               out.println("<tr data-id=" + tabla.getValueAt(t, 0) + " data-id_p=" + tabla.getValueAt(t, 8) + ">");
               out.println("<td>"  + tabla.getValueAt(t, 1) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 2) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 3) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 4) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 5) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 6) + "</td>");
               out.println("<td> " + tabla.getValueAt(t, 7) + "</td>");

               out.println("</tr>");
               
            }
        %>
      
    </tbody>
</table>
        </div>
        
        
        
        
        
        
        
        
        
        
   
      
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha384-LZTz/rX2m2H/aAe4NYq40YxjL89DICM2Fev4M/tvSRe9TbFg8beP2B3Rar9A3zM0" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-e8dtFdxD4qRvTndk65X8zRLUblX2n8jNiMmjGu/AeGKeRi9c18OqdfdNwsHbcDBn" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-rxPHAN2n4IfwiOHgjHqLUQ4k7n+/qDmlM1fgR0zCslujHQMKp4RkBrIsz8KcTAlJ" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>       
<script type="text/javascript">
    
    
    function limpiar(){
        $("#txt_id").val(0);
        $("#txt_codigo").val('');
        $("#txt_nombres").val('');
        $("#txt_apellidos").val('');
        $("#txt_direccion").val('');
        $("#txt_telefono").val('');
        $("#txt_fn").val('');
        $("#drop_puesto").val(1);
    }
    
    $('#tbl_empleados').on('click','tr td', function(evt){
        var target, id, id_p, codigo, nombres, apellidos, direccion, telefono, nacimiento;
        target = $(event.target);
        id = target.parent().data('id');
        id_p = target.parent().data('id_p');
        codigo = target.parent("tr").find("td").eq(0).html();
        nombres = target.parent("tr").find("td").eq(1).html();
        apellidos = target.parent("tr").find("td").eq(2).html();
        direccion = target.parent("tr").find("td").eq(3).html();
        telefono = target.parent("tr").find("td").eq(4).html();
        nacimiento = target.parent("tr").find("td").eq(5).html().trim();
        
                console.log("Fecha de nacimiento: ", nacimiento); // Verificar la fecha aquí


        $("#txt_id").val(id);
        $("#txt_codigo").val(codigo);
        $("#txt_nombres").val(nombres);
        $("#txt_apellidos").val(apellidos);
        $("#txt_direccion").val(direccion);
        $("#txt_telefono").val(telefono);
        $("#txt_fn").val(nacimiento);
        $("#drop_puesto").val(id_p);
        $("#modal_empleado").modal('show');

    });


</script>     






        
            <script>// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict';

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation');

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault();
          event.stopPropagation();
        }

        form.classList.add('was-validated');
      }, false);
    });
})();</script>



    </body>
    
</html>
