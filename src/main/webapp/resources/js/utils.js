function pad(str, max) {
    str = str.toString();
    return str.length < max ? pad("0" + str, max) : str;
}

function formatDate(date) {
    var day = date.getDate();
    var monthIndex = date.getMonth() + 1;
    var year = date.getFullYear();

    day = pad(day, 2);
    monthIndex = pad(monthIndex, 2);

    return day + '' + monthIndex + '' + year;
}

function hideDialogOnSuccess(args, dialogWidgetVar, tableWidgetVar) {
    if (args && !args.validationFailed) {
        PF(dialogWidgetVar).hide();
        PF(tableWidgetVar).filter();
    }
}

$(document).ready(function(){ 
    $(window).on('beforeunload', function(){
         //console.log("beforeUnload event!");
         clearAttributesRC();
     });
});