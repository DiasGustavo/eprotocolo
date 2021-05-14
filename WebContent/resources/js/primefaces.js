/**
 * 
 */
function showStatus() {
PF('statusDialog').show();
}
function hideStatus() {
PF('statusDialog').hide();
}

function exibir_ocultar(){
    var valor = $("#listaTiposDocumentos").val();

    if(valor == 'requerimentoCD'){
         $("#certidaoCD").show();         
     } 
}

