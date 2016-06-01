/**
 * Created by Tomas on 31/05/2016.
 */
$(document).ready(function()
{

    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15, // Creates a dropdown of 15 years to control year
        format: 'dd-mm-yyyy'
    });

    $('#description').trigger('autoresize').val("");
});